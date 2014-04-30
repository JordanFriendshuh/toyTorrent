package bitTorrentClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.List;


public class serverTalk extends Thread{
	private Socket mainSocket;
	private List<Peer> peers;
	private ClientPeer myPeer;
	public serverTalk(String IP, int portIn, List<Peer> inPeers, ClientPeer inPeer){
		InetAddress ipAddress = null;
		try{
			ipAddress = InetAddress.getByName(IP);
		}catch(Exception e){
			System.out.print("FUCKING INET FUCKING SUCKS\n");
		}
		try{
			mainSocket = new Socket(ipAddress, portIn);
			peers = inPeers;
			myPeer = inPeer;
			System.out.print(mainSocket);
		}catch(Exception e){
			System.out.print(e);
		}
		
	}
	public void run(){
		BufferedReader serverInput;
		try {
			serverInput = new BufferedReader(new InputStreamReader(mainSocket.getInputStream()));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		while(true){
			int rank = -1;
			int size = 0;
			try {
				rank = serverInput.read();
				size = serverInput.read();
			} catch (IOException e) {
				// 	TODO Auto-generated catch block
				e.printStackTrace();
			}	
			myPeer.setSpeed(rank);
			peers.clear();
			for(int i = 0; i < size; i++){
				String IP = "";
				try {
					rank = serverInput.read();
					IP = serverInput.readLine();
					peers.add(new Peer(rank, InetAddress.getByName(IP)));
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
		}
	}
}
