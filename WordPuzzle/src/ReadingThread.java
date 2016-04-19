import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.ClosedByInterruptException;
import java.nio.channels.ReadableByteChannel;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class ReadingThread implements Runnable{

	ArrayList<Word> words;
	
	ReadingThread(ArrayList<Word> words){
		this.words=words;
	}
	@Override
	public void run() {
		try{
			ReadableByteChannel localIn= Channels.newChannel(System.in);
			ByteBuffer buffer= ByteBuffer.allocate(128);
			while(!Thread.interrupted()){
				int read=localIn.read(buffer);
				if (read>0){
					buffer.flip();
					this.words.add(new Word(new String(buffer.array()).trim(), System.currentTimeMillis()));
					buffer.clear();
				}else if (read==-1){
					throw new IOException("System.in closed");
				} else{
					System.out.println("read nothing");
				}
			}
			throw new InterruptedException("Interrupted");
		} catch (InterruptedException e){
			return;
		} catch (ClosedByInterruptException e){
			return;
		}catch (IOException e) {
			e.printStackTrace();
		} 
		
	}
	
}
