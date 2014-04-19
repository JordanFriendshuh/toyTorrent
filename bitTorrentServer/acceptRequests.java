import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;


public class acceptRequests extends Thread{
	int clientAddPort;
	List<Peer> peers;
	public acceptRequests(int clientAddPortIn, List<Peer> peersIn){
		clientAddPort = clientAddPortIn;
		peers = peersIn;
	}
	@SuppressWarnings("resource")
	public void run(){
		ServerSocket welcomeSocket;
		try {
			welcomeSocket = new ServerSocket(clientAddPort);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return;
		}
		while(true){
			Socket connection;
			try {
				connection = welcomeSocket.accept();
				peers.add(new Peer(-1, connection.getInetAddress(), connection));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}