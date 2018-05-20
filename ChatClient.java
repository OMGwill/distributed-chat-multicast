// ChatClient.java
//Will Luttmann
import Chat.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import java.util.Properties;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

class RandomSleep {
	static public void SleepAWhile(int min) 	{
		try 		{
			Thread.sleep(min + (int)(Math.random() * 1000));
		}
		catch (InterruptedException e) 		{
			e.printStackTrace();
		}
	}
}

class ChatClientSvcsImpl extends ChatClientSvcsPOA implements ClockApp {
	private boolean _connected = false;
	private ChatClientSvcs myRef = null;
	private ChatServerSvcs serverRef = null;
	private ChatTextArea chatText = null;
	private String myName = null;
	private int myId = -1;
	private CausallyOrderingClock clock;

	public ChatClientSvcsImpl (String name) {
		myName = name;
	}
	
	public void setMyRef(ChatClientSvcs r) {
		myRef = r;
	}
	
	public void setServerRef(ChatServerSvcs r) {
		serverRef = r;
	}
	
	public void setTextArea(ChatTextArea t) {
		chatText = t;
	}

	public void setId(int id) {
		myId = id;
		// create the clock here
		clock = new CausallyOrderingClock(this, myId);
	}

	// implement interface ClockApp
	// The type of message must be "receiveMsg"
	// Only value1 is used for this type of message.
	public void processMessage(Message m){
		
		if (chatText == null)
		{
			System.err.println("Uninitialized chat text area.");
			return;
		}
		System.out.println("processing message...");
		chatText.displayMsg(m.getMessageAsString());
	}
	

	public synchronized void receiveMsg(int t[], int senderId, String msg) {
		Message m = new Message(t, senderId, "", msg, "");
		
		System.out.println("At time " + clock.getTimeAsString() + " " 
			+ myName + " received message " + msg);
	
		if(clock.deliverable(t,senderId)){
			clock.updateTime_Receive(t);
			processMessage(m);
			while (clock.tryToDeliverOldMessages());
			
		} else {
			clock.enqueueMessage(m);
		}		
	}
	
	public void connect() {

		if (myRef == null || serverRef == null) {
			System.err.println("Uninitialized client and/or server references.");
			return;
		}
		if (_connected) {
			return;
		}
		try {
			serverRef.register(myRef);
			_connected = true;
		}
		catch (Chat.CannotEstablishConnection ex) {
			ex.printStackTrace();
		}
		catch (Chat.DuplicateClient ex) {
			ex.printStackTrace();
		}
		catch (org.omg.CORBA.SystemException exb) {
			exb.printStackTrace();
		}
	}

	public void disconnect() {

		if (myRef == null || serverRef == null) {
			System.err.println("Uninitialized client and/or server references.");
			return;
		}
		if (!_connected) {
			return;
		}
		try {
			serverRef.unRegister(myRef);
			_connected = false;
		}
		catch (Chat.UnknownClient ex) {
			ex.printStackTrace();
		}
		catch (org.omg.CORBA.SystemException exb) {
			exb.printStackTrace();
		}
	}
	
	public void sendMsg(String msg) {

		if (myRef == null || serverRef == null) {
			System.err.println("Uninitialized client and/or server references.");
			return;
		}
		try {
			clock.updateTime_Send();
			serverRef.sendMsg(clock.getTime(), myRef, msg, myId);
		}
		catch (Chat.UnknownClient ex) {
			ex.printStackTrace();
		}
		catch (org.omg.CORBA.SystemException ex) {
			ex.printStackTrace();
		}
	}
}

public class ChatClient {

	public static void main(String args[]) {
		try {
        	// The name of this client is a command line arg.
        	String myName;
        	if (args.length == 0)
        		myName = "Greta";
        	else
        		myName = args[0];

			// activate the client
			ChatClientSvcsImpl compImpl = new ChatClientSvcsImpl(myName);

			//create the component reference for the client
			ORB orb = ORB.init(args, null);
			POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootPOA.activate_object(compImpl);
			ChatClientSvcs clientRef = ChatClientSvcsHelper.narrow(rootPOA.servant_to_reference(compImpl));
			compImpl.setMyRef(clientRef);

			// locate a component that implements ChatServerSvcs
			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);
			ChatServerSvcs serverRef = ChatServerSvcsHelper.narrow(ncRef.resolve_str("ChatServerSvcs"));
			System.out.println("Obtained a handle on server object: " + serverRef);
			compImpl.setServerRef(serverRef);

			rootPOA.the_POAManager().activate();

			ChatTextArea chatText = new ChatTextArea(myName, compImpl);
			compImpl.setTextArea(chatText);
			System.out.println("Created frame");

			int myId = serverRef.getNextId();
			compImpl.setId(myId);
			System.out.println("myId is " + myId);

			orb.run();
			while (true) {
				compImpl.sendMsg("I am " + myName + "\n");
				RandomSleep.SleepAWhile(1000);
			}
		}
		catch (Exception e) {
			System.out.println("ERROR : " + e);
			e.printStackTrace(System.out);
		}
	}
}
