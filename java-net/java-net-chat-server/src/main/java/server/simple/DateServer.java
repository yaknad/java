package server.simple;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.Scanner;

public class DateServer {

	public static void main(String[] args) throws IOException {

		try(ServerSocket listener = new ServerSocket(59092)){	
			
			System.out.println("The date server is running...");
			boolean serverOn = true;
			while (serverOn) {            	
				
				try(Socket socket = listener.accept()){
					
					InputStream inputStream = socket.getInputStream();
					OutputStream outputStream = socket.getOutputStream();
					
					Scanner scanner = new Scanner(inputStream);
					
					while(scanner.hasNextLine()) {
						
						String clientMsg = scanner.nextLine();										
						System.out.println("Received a request from client:" + clientMsg);	
						
						try(OutputStreamWriter writer = new OutputStreamWriter(outputStream, Charset.defaultCharset())){
							writer.write("The current date time is: " + new Date());
						}
						
//						clientMsg = new BufferedReader(new InputStreamReader(inputStream))
//								.lines().collect(Collectors.joining("\n"));

						// stop server
						if(clientMsg.contains("end server")) {
							System.out.println("Received a stop server request from client. STOPPING!");
							serverOn = false;
							break;
						} 						
					}					          			
				}
			}
		}
	}
}


// resources:
// http://cs.lmu.edu/~ray/notes/javanetexamples/
