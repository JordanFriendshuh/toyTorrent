import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientTransmit extends Thread {
	private boolean finish;
	int port;
	private List<Integer> data;
	private List<Integer> requested;
	InetAddress serverAddr;
	ClientPeer mainPeer;
	public ClientTransmit(int port, ClientPeer mainPeer){
		finish = false;
		this.port = port;
		this.mainPeer = mainPeer;
		this.serverAddr = null;
	}
	public void reset(InetAddress IPin){
		finish = true;
		serverAddr = IPin;
	}
	public void run() {
		while(true) {
			// get server address and port
			if(serverAddr != null){
			Socket clientSocket;
			try{
			InetAddress serverIPAddress = serverAddr;
				int serverPort = port; 
				// create socket which connects to server
				clientSocket = new Socket(serverIPAddress, serverPort);
				// get input from keyboard
				// write to server
				DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				ArrayList<Integer> indexes = mainPeer.getFileIndicesAlreadyDLed();
				outToServer.writeByte(indexes.size());
				for(int i = 0; i<indexes.size();i++){
					outToServer.writeByte(indexes.get(i));
				}
				int count = in.read();
				for(int i = 0; i < count; i++){
					requested.add(in.read());
				}
				requested.retainAll(mainPeer.getFileIndicesAlreadyDLed());
				while(!finish){
					int index;
					if(requested.size() > 0){
						index = requested.get(0);
						requested.remove(0);
						outToServer.writeByte(index);
						outToServer.writeByte(data.get(index));
					}else{
						outToServer.writeByte(-1);
						outToServer.writeByte(-1);
					}
					index = in.read();
					int value = in.read();
					if(index != -1){
						data.add(index, value);
					}
						
				}
				clientSocket.close();
			}catch(Exception e){
			}
			// create read stream and receive from server
			//BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			//String countFromServer = inFromServer.readLine();
			//System.out.println("From Server: " + countFromServer);
			// close client socket
			
		}
		}
	}	
}
