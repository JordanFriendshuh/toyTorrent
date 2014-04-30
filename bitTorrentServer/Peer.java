package bitTorrentServer;

import java.net.InetAddress;
import java.net.Socket;


class Peer {
	private int speedValue;
	private InetAddress ipAddress;
	Socket mainSocket;
	Peer(int speed, InetAddress ip, Socket sock){
		speedValue = speed;
		ipAddress = ip;
		mainSocket = sock;
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
	Socket getSocket(){
		return mainSocket;
	}
}
