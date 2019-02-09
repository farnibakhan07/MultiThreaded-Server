import java.io.BufferedReader;  
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class ServerMultithreaded implements Runnable {

	Socket csocket;
	private final static Logger LOGGER = Logger.getLogger(Logger.class.getName());
	static FileHandler fh; 

	ServerMultithreaded(Socket csocket) {
		this.csocket = csocket;
	}

	public void run() {
		try {
			PrintWriter outToClient = new PrintWriter(csocket.getOutputStream(), true);
			BufferedReader inFromClient = new BufferedReader(new InputStreamReader(csocket.getInputStream()));

			String commandFromClient = inFromClient.readLine();
			
			//To get input from socket
			PrintStream pstream = new PrintStream(csocket.getOutputStream());
			ExecuteCommand obj = new ExecuteCommand();
			String reply = obj.execute(commandFromClient);
			pstream.println(reply);
			outToClient.write(reply);
			pstream.close();
			csocket.close();
			LOGGER.setLevel(Level.INFO);
			LOGGER.info("TCP Connection Closed!");
			
		} catch (IOException e) {
			System.out.println(e);
			LOGGER.setLevel(Level.SEVERE);
			LOGGER.severe("IO Exception detected!");
			LOGGER.warning("Exception occured!");
		}
	}

	public static void main(String args[]) throws Exception { 
		//fh = new FileHandler("/home/ubuntu/MultiThreadLog.log",true); //for ubuntu
		fh = new FileHandler("C:\\Users\\Farniba\\Documents\\logfile\\MultiThreadLog.log",true); //for windows  
		LOGGER.addHandler(fh);
		SimpleFormatter formatter = new SimpleFormatter();  
		fh.setFormatter(formatter);
		
		ServerSocket newSocket = new ServerSocket(5555);		
		LOGGER.setLevel(Level.INFO);
		LOGGER.info("Server is Listening to port: 5555");

		while (true) {
			Socket sock = newSocket.accept();
			
			LOGGER.setLevel(Level.INFO);
			LOGGER.info("Connected");
			new Thread(new ServerMultithreaded(sock)).start();
		}
	}

}