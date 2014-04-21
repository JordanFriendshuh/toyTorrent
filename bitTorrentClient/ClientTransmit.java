package bitTorrentClient;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class ClientTransmit extends Thread {
	
	
	public ClientTransmit(int port, InetAddress serverAddr, ClientPeer mainPeer){
		finish = false;
		this.port = port;
		this.mainPeer = mainPeer;
		this.serverAddr = serverAddr;
	}
	public void reset(InetAddress IPin){
		finish = true;
	}
	public void run() {
		while(true) {
			// get server address and port
			InetAddress serverIPAddress = serverAddr;
			int serverPort = port; 
			// create socket which connects to server
			Socket clientSocket = new Socket(serverIPAddress, serverPort);
			// get input from keyboard
			// write to server
			DataOutputStream outToServer = new DataOutputStream(clientSocket.getOutputStream());
			for (int i = 0; i < 10000; i++) {
				outToServer.writeInt(i);
				outToServer.writeInt(mainPeer.get(i));
			}
			// create read stream and receive from server
			//BufferedReader inFromServer = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
			//String countFromServer = inFromServer.readLine();
			//System.out.println("From Server: " + countFromServer);
			// close client socket
			clientSocket.close();
		}
	}	
}
