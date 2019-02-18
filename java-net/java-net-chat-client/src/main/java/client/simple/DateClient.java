package client.simple;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

//resources:
//http://cs.lmu.edu/~ray/notes/javanetexamples/
public class DateClient {

	public static void main(String[] args) throws IOException {

		//createClient_Option1();
		createClient_Option2();		
	}
	
	public static void createClient_Option1() throws UnknownHostException, IOException {
		
		try(Socket socket = new Socket("0.0.0.0", 59092)){

			PrintWriter writer = new PrintWriter(socket.getOutputStream());
			Scanner in = new Scanner(socket.getInputStream());

			writer.println("message from client!");
			writer.flush();
			System.out.println("first request sent to server!");
			if(in.hasNextLine()) {
				System.out.println("Server response: " + in.nextLine());
			}

			// NOTE: when we use a Stream as a text stream (reading it with a scanner that searches for "newLine"),
			// we must use a PrintWriter (with .println() etc.) in order to create the expected new lines the scanner expect!
			// all other options (see Server implementation) are causing problems - not recognizing the end of line.

			writer.println("another message from client sent to server!");
			writer.flush();
			System.out.println("second request sent to server!");
			if(in.hasNextLine()) {
				System.out.println("Server response: " + in.nextLine());
			}
		}
	}
	
	public static void createClient_Option2() throws UnknownHostException, IOException {
		
		try(Socket socket = new Socket("0.0.0.0", 59092)){

			OutputStream outputStream = socket.getOutputStream();			
			InputStream inputStream = socket.getInputStream();
			
			try(OutputStreamWriter writer = new OutputStreamWriter(outputStream);
					BufferedWriter bfwr = new BufferedWriter(writer);
					BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))){
			
				bfwr.write("first message from client!" + "\n"); // NOTE: DON'T use System.lineSeparator(), since it adds \r\n which are considered as 2 line separators for .readLine() method!
				// bfwr.newLine();
				bfwr.flush();
				
				String line = bufferedReader.readLine();
				System.out.println("Received a response from server: " + line);	
				
				bfwr.write("second message from client!" + "\n");
				//bfwr.newLine();
				bfwr.flush();
				
				line = bufferedReader.readLine();
				System.out.println("Received a response from server: " + line);		
			}
		}
	}
}
