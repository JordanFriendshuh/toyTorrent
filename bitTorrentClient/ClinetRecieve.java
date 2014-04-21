import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class ClinetRecieve extends Thread{

	private boolean finish;
	int port;
	private List<Integer> data;
	private List<Integer> requested;
	ServerSocket mainSocket;
	ClientPeer mainPeer;
	public ClinetRecieve(int port, ClientPeer mainPeer){
		finish = false;
		this.port = port;
		this.mainPeer = mainPeer;
	}
	public void reset(InetAddress IPin){
		finish = true;
	}
	public void run(){
		while(true){
			try {
				mainSocket = new ServerSocket(port);
				Socket connection;
				connection = mainSocket.accept();
				DataOutputStream out = new DataOutputStream(connection.getOutputStream());
				BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
				ArrayList<Integer> indexes = mainPeer.getFileIndicesAlreadyDLed();
				out.writeByte(indexes.size());
				for(int i = 0; i < indexes.size(); i++){
					out.writeByte(indexes.get(i));
				}
				while(!finish){
					
					
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}	
	}
}
