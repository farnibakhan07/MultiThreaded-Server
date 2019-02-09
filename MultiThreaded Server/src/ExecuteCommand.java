

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ExecuteCommand {
	String reply = "";
	public String execute(String command) {
		StringBuffer result = new StringBuffer();

		Process pro;
		try {
			
			pro = Runtime.getRuntime().exec(command);
			System.out.println(command);
			pro.waitFor(); //Causes the current thread to wait, if necessary, until the process represented by this Process object has terminated. This method returns immediately if the subprocess has already terminated. If the subprocess has not yet terminated, the calling thread will be blocked until the subprocess exits.
			BufferedReader reader = new BufferedReader(new InputStreamReader(pro.getInputStream()));

			String line = "";
			while ((line = reader.readLine())!= null) {
				result.append(line + "\n");
				System.out.println(line);
			}
			
			reply = result.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reply;
	}
}
