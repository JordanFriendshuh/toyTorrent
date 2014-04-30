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
	private static List<ClientRecieve> recivers;
	private static List<ClientTransmit> transmiters;
	private static int count;
	//Arg, IP then port
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		count = 0;
		myPeer = new ClientPeer(new Peer(-1, null));
		data = Collections.synchronizedList(new ArrayList<Integer>(10000));
		recivers = new ArrayList<ClientRecieve>();
		transmiters = new ArrayList<ClientTransmit>();
		for(int i=0; i<10000; i++){
			data.add(-1);
		}
		otherPeers = Collections.synchronizedList(new ArrayList<Peer>());
		serverTalk servCon = new serverTalk(args[0], Integer.parseInt(args[1]), otherPeers, myPeer);
		servCon.start();
		/*recivers.add(new ClientRecieve(Integer.parseInt(args[1]), myPeer));
		recivers.add(new ClientRecieve(Integer.parseInt(args[1]), myPeer));
		transmiters.add(new ClientTransmit(Integer.parseInt(args[1]), myPeer));
		transmiters.add(new ClientTransmit(Integer.parseInt(args[1]), myPeer));
		for(int i = 0; i < 2; i++){
			recivers.get(i).run();
			transmiters.get(i).run();
		}*/
		Timer resend = new Timer();
		resend.schedule(new TimerTask(){
			public void run(){
				clientTalk();
			}
		}, 1000*10, 1000*10);
		
	}
	
	public static void clientTalk(){
		System.out.println(myPeer.getSpeed());
		/*myPeer.setTop4Peers(otherPeers);
		List<Peer> myPeers = myPeer.getTop4Peers();
		for(ClientRecieve n : recivers){
			n.reset();
		}
		for(int i = 0; i < transmiters.size(); i++){
			transmiters.get(i).reset(myPeers.get(i).getAddress());
		}*/
	}
	
}
