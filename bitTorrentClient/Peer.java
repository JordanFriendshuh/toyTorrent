package bitTorrentClient;

import java.net.InetAddress;


class Peer {
	private int speedValue;
	private InetAddress ipAddress;
	Peer(int speed, InetAddress ip){
		speedValue = speed;
		ipAddress = ip;
	}
	int getSpeed(){
		return speedValue;
	}
	InetAddress getAddress(){
		return ipAddress;
	}
	void setSpeed(int speed){
		speedValue = speed;
	}
}
