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
			writer.write("message from client!\r\n");
			writer.flush();
		
			Scanner in = new Scanner(socket.getInputStream());
			System.out.println("Server response: " + in.nextLine());

			
			//TODO: why the second message doesn't arrive?
			
			writer.write("another message from client!\r\n");
			writer.flush();
			System.out.println("Server response: " + in.nextLine());

		}
	}
}

// resources:
// http://cs.lmu.edu/~ray/notes/javanetexamples/
