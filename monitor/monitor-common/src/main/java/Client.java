import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Client extends Thread {

	Socket sk = null;
	BufferedReader reader = null;
	PrintWriter wtr = null;

	public Client() {
		try {
			sk = new Socket("localhost", 9999);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void run() {
		try {
			reader = new BufferedReader(new InputStreamReader(
					sk.getInputStream()));
			wtr = new PrintWriter(sk.getOutputStream());

			while (true) {
				wtr.println("I am here");
				wtr.flush();

				if (reader != null) {
					String line = reader.readLine();
					System.out.println("从服务器来的信息：" + line);

					Thread.sleep(1000);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		for (int i = 0; i < 5; i++) {
			new Client().start();
		}
	}
}
