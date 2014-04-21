package bitTorrentClient;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class Client {

	/**
	 * @param args
	 */
	private static ClientPeer myPeer;
	private static List<Peer> otherPeers;
	private static List<Integer> data;
	private static List<ClinetRecieve> recivers;
	private static int count;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		count = 0;
		data = Collections.synchronizedList(new ArrayList<Integer>(10000));
		for(int i=0; i<data.size(); i++){
			data.set(i, -1);
		}
		otherPeers = Collections.synchronizedList(new ArrayList<Peer>());
		serverTalk servCon = new serverTalk(args[1], Integer.parseInt(args[2]), otherPeers, myPeer);
		servCon.start();
		recivers.add(new ClinetRecieve(Integer.parseInt(args[2]), myPeer));
		recivers.add(new ClinetRecieve(Integer.parseInt(args[2]), myPeer));
		Timer resend = new Timer();
		resend.schedule(new TimerTask(){
			public void run(){
				clientTalk();
			}
		}, 1000*10, 1000*10);
		
	}
	
	public static void clientTalk(){
		myPeer.setTop4Peers(otherPeers);
		List<Peer> myPeers = myPeer.getTop4Peers();
		for(ClinetRecieve n : recivers){
			n.reset();
		}
		
	}
	
}
