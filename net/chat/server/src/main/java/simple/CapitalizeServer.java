package simple;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CapitalizeServer {

	public static void main(String[] args) throws IOException {

		System.out.println("The capitalization server is running...");
		ExecutorService pool = Executors.newFixedThreadPool(20);
		try (ServerSocket listener = new ServerSocket(59898)) {
			while (true) {
				pool.execute(new Capitalizer(listener.accept()));
			}
		}
	}
}


// resources:
// http://cs.lmu.edu/~ray/notes/javanetexamples/
