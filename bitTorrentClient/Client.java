import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class Client {

	/**
	 * @param args
	 */
	private static ClientPeer myPeer;
	private static List<Peer> otherPeers;
	private static int data[];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		data = new int[10000];
		otherPeers = Collections.synchronizedList(new ArrayList<Peer>());
		serverTalk servCon = new serverTalk(args[1], Integer.parseInt(args[2]), otherPeers, myPeer);
		servCon.start();
		
	}
	
	public void clientTalk(){
		myPeer.setTop4Peers(otherPeers);
		List<Peer> myPeers = myPeer.getTop4Peers();
		
	}
	
}
