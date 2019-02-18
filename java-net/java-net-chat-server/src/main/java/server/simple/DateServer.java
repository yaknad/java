package server.simple;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.Scanner;
import java.util.stream.Collectors;

//resources:
//http://cs.lmu.edu/~ray/notes/javanetexamples/
public class DateServer {

	public static void main(String[] args) throws IOException {

		//createServer_Option1();
		createServer_Option2();

	}

	public static void createServer_Option1() throws IOException {

		try(ServerSocket listener = new ServerSocket(59092)){	

			System.out.println("The date server is running...");
			boolean serverOn = true;
			while (serverOn) {            	

				try(Socket socket = listener.accept()){

					System.out.println("New client!");

					InputStream inputStream = socket.getInputStream();
					OutputStream outputStream = socket.getOutputStream();

					Scanner scanner = new Scanner(inputStream);
					PrintWriter out = new PrintWriter(outputStream, true);

					while(scanner.hasNextLine()) {

						System.out.println("Listening to client's messages:");	

						String clientMsg = scanner.nextLine();										
						System.out.println("Received a request from client: " + clientMsg);	

						// NOTE: when the client is using a Scanner to read the input stream, only the PrintWriter.println worked fine
						// so the scanner would recognize the new line. All the other options didn't work for me:
						
//						try(OutputStreamWriter writer = new OutputStreamWriter(outputStream)){
//							System.out.println("Sending response to client");	
//							writer.write("The current date time is: " + new Date().toString() + System.lineSeparator());
//						}

//						try(OutputStreamWriter writer = new OutputStreamWriter(outputStream);
//								BufferedWriter bfwr = new BufferedWriter(writer)){
//
//							System.out.println("Sending response to client");	
//							bfwr.write("The current date time is: " + new Date());
//							bfwr.newLine();
//						}

						System.out.println("Sending response to client");	
						out.println("The current date time is: " + new Date());
					}			

				}				
			}
		}
	}

	public static void createServer_Option2() throws IOException {

		try(ServerSocket listener = new ServerSocket(59092)){	

			System.out.println("The date server is running...");
			boolean serverOn = true;
			while (serverOn) {            	

				try(Socket socket = listener.accept()){

					System.out.println("New client!");

					InputStream inputStream = socket.getInputStream();
					OutputStream outputStream = socket.getOutputStream();

					try(OutputStreamWriter writer = new OutputStreamWriter(outputStream);
							BufferedWriter bfwr = new BufferedWriter(writer);
							BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))){

						String line;
						while ((line = bufferedReader.readLine()) != null) {

							System.out.println("Received a request from client: " + line);	

							System.out.println("Sending response to client");	
							bfwr.write("The current date time is: " + new Date() + "\n");
							//bfwr.newLine();  // I guess this writes "\r\n" and the client's BufferedReader.readLine() sees that as 2 lines. 
							bfwr.flush();
						}
					}
				}
			}
		}
	}
}



