import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class EchoClientHandler implements Runnable{
	static int MAX_MSG=10;
	Socket client;
	int messageCount;
	
	public EchoClientHandler(Socket client) {
		this.client=client;
		this.messageCount=0;
	}
	@Override
	public void run() {
		try (BufferedReader reader= new BufferedReader(
				new InputStreamReader(this.client.getInputStream()));
				BufferedWriter writer= new BufferedWriter(
						new OutputStreamWriter(this.client.getOutputStream()));){
			this.client.setSoTimeout(200000);
			String message="";
			while (this.messageCount<MAX_MSG && (message= reader.readLine())!=null){
					this.messageCount++;
					System.out.println("Client sent: "+message);
					String responseFooter="";
					if(this.messageCount==MAX_MSG)
						responseFooter=" This was your last message";
					writer.write(message+responseFooter+"\r\n");
					writer.flush();	
			}
		} catch (IOException e){
			System.out.println("Client closed connection or an error appeared.");
		} finally{
			try {this.client.close();
			} catch (IOException e) {}
		}
		
	}

}
