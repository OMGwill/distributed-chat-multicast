//will Luttmann
import java.util.ArrayList;

public class CausallyOrderingClock {
	//static final public MAX_NUM_PROCESSES = 10;
	private ClockApp clockApp;
	private int myId;
	private int timeVector[];
	private ArrayList<Message> messageQueue;

	public CausallyOrderingClock(ClockApp p, int id) {
		clockApp = p;
		myId = id;
		timeVector = new int[Chat.MAX_NUM_PROCESSES.value];
		messageQueue = new ArrayList<Message>(20);
	}
	public int[] getTime() {
		return (timeVector);
	}
	public String getTimeAsString() {
		String s = "";
		for (int t : timeVector)	
			s = s + " " + t;
		return s;
	}
	public void updateTime_Send() {
		tick();
	}
	public void updateTime_Receive(int[] t) {
		updateTime(t);	
	}
	public void tick() {
		timeVector[myId]++;	
	}
	public void updateTime(int[] t) {
		for (int i=0; i < Chat.MAX_NUM_PROCESSES.value; i++)
			if (t[i] > timeVector[i])
				timeVector[i] = t[i];
	}
	
	public boolean deliverable(int[] t, int senderId) {
		boolean delay = false;
		for (int i=0; i < Chat.MAX_NUM_PROCESSES.value; i++) {
			if (i == senderId) {
				if (t[i] != timeVector[i]+1)
					delay = true;
			} else {
				if (t[i] > timeVector[i])
					delay = true;
			}
		}
		return !delay;
	}
	public void enqueueMessage(Message m) {
		messageQueue.add(m);
		System.out.println("Adding message to queue");
	}
	public boolean tryToDeliverOldMessages() {
		if (messageQueue.isEmpty())
			return false;
		boolean delivered_one = false;
		ArrayList<Message> newQueue = new ArrayList<Message>(20);
		while (!messageQueue.isEmpty()) {
			Message m = messageQueue.remove(0);
			if (deliverable(m.getTimeVector(), m.getSenderId())) {
				delivered_one = true;
				updateTime(m.getTimeVector());
				clockApp.processMessage(m);
			} else
				newQueue.add(m);
		}
		messageQueue = newQueue;
		return (delivered_one);
	}
}
