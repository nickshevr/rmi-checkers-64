package checkers;

import java.net.InetAddress;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * A bridge for communication between the server and the instantiated player or observer
 * NO game functionality should take place within this class. Instead, it forwards all 
 * messages to the player or observer object within.
 */
public class CheckersClient extends UnicastRemoteObject implements Player{
	private static final long serialVersionUID = 1L;
	
	public final PlayerInfo myID;
	
	public static final int PLAYER 		= 0;
	public static final int OBSERVER 	= 1;
	
	private Server server;
	private CheckersPlayer player;
	private static CheckersClient 	client;

	public static void printStatus(String message){
		String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		System.out.println("\t" + date + " >> " + message);
	}
	
	
	public static void main(String[] args) throws RemoteException, Exception {
		Scanner 		keyboard;
		String 			serverAddress;
		
		
		try {
			client = new CheckersClient();
			
			// connect to the server
			serverAddress = null;
			if(args.length > 0)
				serverAddress = args[0];
			client.connect(serverAddress);
				
				// Testing stuffs
//				keyboard = new Scanner(System.in);
//				keyboard.nextLine();
//				keyboard.close();
			
		} catch (Exception e) {e.printStackTrace();}

	}
	
	

}

