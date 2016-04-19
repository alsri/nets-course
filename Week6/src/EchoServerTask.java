import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.concurrent.Callable;

public class EchoServerTask implements Callable<Integer>{

	DatagramSocket socket;
	
	EchoServerTask(DatagramSocket ds){
		this.socket=ds;
	}
	public void print(String message){
		System.out.println(Thread.currentThread().getName()+message);
	}
	@Override
	public Integer call() {
		DatagramPacket request= new DatagramPacket(new byte[EchoServer.MAX_LENGTH],
				EchoServer.MAX_LENGTH);
		while(true){
			try {
				this.print(" Waiting for clients...");
				socket.receive(request);
				this.print(" Received: "+new String(request.getData(),0,request.getLength(),"UTF-8"));
				try {
					Thread.sleep(15000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				DatagramPacket response= new DatagramPacket(
						request.getData(), request.getLength(),request.getSocketAddress());
				socket.send(response);
				request.setLength(EchoServer.MAX_LENGTH);
			} catch (IOException e) {
				System.err.println("Error in communication: "+e.getMessage());
			}
			return 0;
		}
	
		
	}

}
