//Will Luttmann
public class Message {
	private int timeVector[];
	private int senderId;
	private String request;
	private String value1, value2;

	public Message(int t[], int id, String r, String v1, String v2) {
		timeVector = new int[Chat.MAX_NUM_PROCESSES.value];
		for (int i=0; i<Chat.MAX_NUM_PROCESSES.value; i++)
			timeVector[i] = t[i];
		senderId = id;
		request = r;
		value1 = v1;
		value2 = v2;
	}
	public Message(int t[], int id, String r, String v) {
		this(t, id, r, v, "");
	}
	public int[] getTimeVector() {
		return timeVector;
	}
	public int getSenderId() {
		return senderId;
	}
	public String getRequest() {
		return request;
	}
	public String getValue1() {
		return value1;
	}
	public String getValue2() {
		return value2;
	}
	public String getMessageAsString() {
		// String s = "";
		// for (int t : timeVector)	
			// s = s + " " + t;
		// s = s + " " + senderId;
		// s = s + " " + request + " " + value1 + " " + value2;
		// return s;
		return value1;
	}

}
