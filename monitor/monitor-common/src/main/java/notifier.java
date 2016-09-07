import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class notifier extends Thread {

	Socket sk = null;
	BufferedReader reader = null;
	PrintWriter wtr = null;

	public notifier() {
		try {
			sk = new Socket("localhost", 8099);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public void run() {
		try {
			reader = new BufferedReader(new InputStreamReader(
					sk.getInputStream()));
			wtr = new PrintWriter(sk.getOutputStream());

			wtr.println("1");
			wtr.flush();

			if (reader != null) {
				String line = reader.readLine();
				System.out.println("从服务器来的信息：" + line);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws IOException {

		new notifier().start();
		System.in.read();

	}
}
