import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ChatTextArea implements ActionListener, KeyListener {
	JTextArea chatText;
	JButton connectButton;
	JButton disconnectButton;
	protected ChatClientSvcsImpl client;

	public ChatTextArea(String name, ChatClientSvcsImpl c) {
		client = c;
		create(name);
	}

	private void create(String name) {
		JFrame frame = new JFrame(name);
		FrameCloser closer = new FrameCloser();
		frame.addWindowListener(closer);
		
		JPanel panel = new JPanel();

		connectButton = new JButton("Connect");
		panel.add(connectButton);
		connectButton.addActionListener(this);

		disconnectButton = new JButton("Disconnect");
		panel.add(disconnectButton);
		disconnectButton.addActionListener(this);

		chatText = new JTextArea(8, 60);
		chatText.setLineWrap(true);
		chatText.addKeyListener(this);

		JScrollPane scroller = new JScrollPane(chatText);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

		panel.add(scroller);

		frame.getContentPane().add(BorderLayout.CENTER, panel);

		//frame.setTitle("Chat Client: " + name);
		frame.setSize(700, 220);
		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {

		JButton b = (JButton) e.getSource();

		if (b == connectButton) {
			System.err.println("Connecting...");
			client.connect();
			chatText.requestFocus();
		}
		else if (b == disconnectButton) {
			System.err.println("Disconnecting...");
			client.disconnect();
		}
	}
	
    public void keyTyped(KeyEvent e) {
        int id = e.getID();
        char c = e.getKeyChar();
        client.sendMsg(String.valueOf(c));
	}
    
    public void keyPressed(KeyEvent e) {
    	// ignore this event
        }
    
    public void keyReleased(KeyEvent e) {
    	// ignore this event
        }

	public void displayMsg(String msg){
		chatText.append(msg);
	}

	class FrameCloser extends WindowAdapter {

		public void windowClosing(WindowEvent e) {
			client.disconnect();
			System.exit(0);
		}
	}
}
