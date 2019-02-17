package server.simple;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Capitalizer implements Runnable{

	private Socket socket;
	
	public Capitalizer(Socket socket) {
		this.socket = socket;
		System.out.println("Connected: " + socket);
	}
	
	@Override
	public void run() {

		 try {
			 Scanner in = new Scanner(socket.getInputStream());
			 PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             while (in.hasNextLine()) {
                 out.println(in.nextLine().toUpperCase());
             }
         } catch (Exception e) {
             System.out.println("Error:" + socket);
         } finally {
             try { socket.close(); } catch (IOException e) {}
             System.out.println("Closed: " + socket);
         }		
	}
}
