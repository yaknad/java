package client.simple;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class DateClient {

	public static void main(String[] args) throws IOException {

		try(Socket socket = new Socket("0.0.0.0", 59092)){
			
			OutputStream outputStream = socket.getOutputStream();
			PrintWriter writer = new PrintWriter(outputStream);
			Scanner in = new Scanner(socket.getInputStream());
			
			writer.println("message from client!");
			writer.flush();
			System.out.println("first request sent to server!");
			System.out.println("Server response: " + in.nextLine());

			
			// NOTE: when we use a Stream as a text stream (reading it with a scanner that searches for "newLine"),
			// we must use a PrintWriter (with .println() etc.) in order to create the expected new lines the scanner expect! 
			
			writer.println("another message from client!");
		    writer.flush();
			System.out.println("second request sent to server!");
			if(in.hasNextLine()) {
				System.out.println("Server response: " + in.nextLine());
			}

		}
	}
}

// resources:
// http://cs.lmu.edu/~ray/notes/javanetexamples/
