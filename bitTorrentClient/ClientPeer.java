package bitTorrentClient;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ClientPeer extends Peer {
	private List<Peer> top4Peers;
	private List<Integer> currFile;
	private int thisPeerSpeed;
	private InetAddress thisPeerAddr;

	public ClientPeer(Peer p) {
		super(p.getSpeed(), p.getAddress());
		thisPeerSpeed = p.getSpeed();
		thisPeerAddr = p.getAddress();
		top4Peers = new ArrayList<Peer>();
		currFile = Collections.synchronizedList(new ArrayList<Integer>());
	}

	public void setTop4Peers(List<Peer> allPeers) {
		int thisPeerIndex = 0;
		boolean foundThisPeer = false;
		int i = 0;
		while (i < allPeers.size() && !foundThisPeer) {
			Peer currPeer = allPeers.get(i);
			if (currPeer.getSpeed() == thisPeerSpeed
					&& currPeer.getAddress() == thisPeerAddr) {
				foundThisPeer = true;
				thisPeerIndex = i;
			}
			i++;
		}
		// Add two peers to the left
		int indexToAddLeft = thisPeerIndex;
		for (int a = 0; a < 2; a++) {
			if ((indexToAddLeft - 1) < 0)
				indexToAddLeft = allPeers.size() - 1;
			else
				indexToAddLeft -= 1;
			top4Peers.add(allPeers.get(indexToAddLeft));
		}
		// Add two peers to the right
		int indexToAddRight = thisPeerIndex;
		for (int a = 0; a < 2; a++) {
			if ((indexToAddRight + 1) >= allPeers.size())
				indexToAddRight = 0;
			else
				indexToAddRight += 1;
			top4Peers.add(allPeers.get(indexToAddRight));
		}
	}

	public List<Peer> getTop4Peers() {
		return top4Peers;
	}

	public List<Integer> getFileIndicesAlreadyDLed() {
		return currFile; 
	}
}