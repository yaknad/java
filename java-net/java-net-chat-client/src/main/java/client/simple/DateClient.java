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

			
			//TODO: why the second message doesn't arrive?
			// NOTE that without the \r\n the server dosn't recognize a new line.
			// but CHECK that with the \r\n, it gets an empty line!!!
			
			writer.println("another message from client!\r\n");
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
