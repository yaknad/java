package client.simple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class CapitalizeClient {

	public static void main(String[] args) throws IOException {

		Scanner scanner = new Scanner(System.in);
		try(Socket socket = new Socket("0.0.0.0", 59898)){
            System.out.println("Enter lines of text then Ctrl+D or Ctrl+C to quit");
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            while (scanner.hasNextLine()) {
                out.println(scanner.nextLine());
                System.out.println(in.readLine());
            }
        }
	}
}

// TODO:
// try multiple clients (to test the concurrent server) + use thread.sleep in server to check capacity!

// resources:
// http://cs.lmu.edu/~ray/notes/javanetexamples/
