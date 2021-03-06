package bitTorrentServer;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class topLevel {

	/**
	 * @param args
	 */
	private static int clientAddPort = 9001;
	private static int clientNormPort = 9999;
	private static List<Peer> peers;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		peers = Collections.synchronizedList(new ArrayList<Peer>());
		acceptRequests blah = new acceptRequests(clientAddPort, peers);
		blah.start();
		Timer resend = new Timer();
		resend.schedule(new TimerTask(){
			public void run(){
				updateClients();
			}
		}, 1000*10, 1000*10);
	}
	public static void updateClients(){
		long seed = System.nanoTime();
		int index = 0;
		while(index < peers.size()){
			if(peers.get(index).getSocket().isClosed()){
				peers.remove(index);
			}else{
				index++;
			}
		}
		Collections.shuffle(peers, new Random(seed));
		for(int i = 0; i < peers.size(); i++){
			peers.get(i).setSpeed(i);
		}
		for(int i = 0; i < peers.size(); i++){
			Peer temp = peers.get(i);
			try {
				DataOutputStream out = new DataOutputStream(temp.mainSocket.getOutputStream());
				out.writeByte(temp.getSpeed());
				out.writeByte(peers.size());
				for(int j = 0; j < peers.size(); j++){
					if(j != i){
						out.writeByte(peers.get(i).getSpeed());
						out.writeBytes(peers.get(i).getAddress().toString() + "\n");
					}
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				peers.remove(i);
				i--;
				//e.printStackTrace();
			}
		}
	}
}
