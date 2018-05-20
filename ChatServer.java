// ChatServer.java
//will Luttmann
import java.util.*;
import Chat.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import java.util.Properties;
import java.util.Vector;
import java.util.Iterator;

class ChatServerSvcsImpl extends ChatServerSvcsPOA implements ClockApp{
	ArrayList<ChatClientSvcs> clients;
	static private int nextId = 0;
	int myId = -1;
	CausallyOrderingClock clock;


	
	ChatServerSvcsImpl() {
		clients = new ArrayList<ChatClientSvcs>(10);
		myId = getNextId();
		System.out.println("server Id is " + myId);
		// now create the clock
		clock = new CausallyOrderingClock(this , myId);
	}

	public int getNextId() {
		int id = nextId;
		nextId++;
		return(id);
	}

	public void register (ChatClientSvcs clientRef) 
			throws Chat.CannotEstablishConnection, Chat.DuplicateClient {
		//clock.tick();
		System.out.println("Registering client at time " + clock.getTimeAsString() + "\n");
		
		for (ChatClientSvcs c : clients) {
			if (clientRef.equals(c))
				throw new Chat.DuplicateClient();
		}
		clients.add(clientRef);
	}

	public void unRegister (ChatClientSvcs clientRef) throws Chat.UnknownClient {
		//clock.tick();
		System.out.println("Unregistering client at time " + clock.getTimeAsString() + "\n");

		if (!clients.remove(clientRef))
			throw new Chat.UnknownClient();
	}

	public synchronized void sendMsg(int t[] ,ChatClientSvcs clientRef, String msg, int clientID) throws Chat.UnknownClient {
		
		int i = clients.indexOf(clientRef);
		if (i < 0) throw new Chat.UnknownClient();

			clock.updateTime(t);
				
			System.out.println("At time " + clock.getTimeAsString() 
				+ " server received message " + msg);

			for (ChatClientSvcs c : clients) {
				clock.updateTime_Send();
				//System.out.println("SENDINGGG");
				c.receiveMsg(t, clientID, msg);
			}
	}
	public void processMessage (Message m){
		
	}
}

public class ChatServer {

	public static void main(String args[]) {
		try {
			// activate
			ChatServerSvcsImpl compImpl = new ChatServerSvcsImpl();

			// register the component with the Naming Service
			ORB orb = ORB.init(args, null);
			POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootpoa.the_POAManager().activate();
			org.omg.CORBA.Object ref = rootpoa.servant_to_reference(compImpl);
			ChatServerSvcs compRef = ChatServerSvcsHelper.narrow(ref);
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			NameComponent path[] = ncRef.to_name("ChatServerSvcs");
			ncRef.rebind(path, compRef);

			System.out.println("ChatServer ready and waiting ...");

			// run the component
			orb.run();
		}
		catch (Exception e) {
			System.err.println("ERROR: " + e);
			e.printStackTrace(System.out);
		}
		System.out.println("ChatServer Exiting ...");
	}
}
