import java.net.InetAddress;
import java.net.Socket;


public class Peer {
	private int speedValue;
	private InetAddress ipAddress;
	public Peer(int speed, InetAddress ip){
		speedValue = speed;
		ipAddress = ip;
	}
	public int getSpeed(){
		return speedValue;
	}
	public InetAddress getAddress(){
		return ipAddress;
	}
	public void setSpeed(int speed){
		speedValue = speed;
	}
}
