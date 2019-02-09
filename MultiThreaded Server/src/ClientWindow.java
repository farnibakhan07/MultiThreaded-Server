import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;

public class ClientWindow {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JTextArea textArea;
	private JScrollPane scrollPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientWindow window = new ClientWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(89, 11, 86, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblEnterUrl = new JLabel("Enter URL:");
		lblEnterUrl.setBounds(10, 14, 69, 14);
		frame.getContentPane().add(lblEnterUrl);
		
		textField_1 = new JTextField();
		textField_1.setBounds(89, 55, 314, 20);
		frame.getContentPane().add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblEnterCommand = new JLabel("Enter Command");
		lblEnterCommand.setBounds(10, 58, 90, 14);
		frame.getContentPane().add(lblEnterCommand);
		
		JButton btnExecuteCommand = new JButton("Execute Command");
		btnExecuteCommand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					
					String op = socketConn(textField.getText(), textField_1.getText());
					textArea.append(op);
					
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			}
			
		});
		btnExecuteCommand.setBounds(89, 90, 143, 23);
		frame.getContentPane().add(btnExecuteCommand);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 130, 391, 101);
		frame.getContentPane().add(scrollPane);
		
		textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
	}
	
	public String socketConn(String host, String command) throws IOException
	{

		Socket clientSocket = new Socket(host, 5555);
		if (clientSocket != null)
		{
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

			outToServer.writeBytes(command + "\n");						   
			String line = null;
			String reply = "";
			while ((line = inFromServer.readLine()) != null )
			{
				 if (line.isEmpty()) {
				        break;
				    }
				reply += line;
				reply +="\n";
			}
			//inFromServer.close();
			return reply;
		}

		else	
			return "Not Connected";
		
	}
	public JTextArea getTextArea() {
		return textArea;
	}
}
