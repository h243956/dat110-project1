package no.hvl.dat110.rpc;

import java.util.HashMap;

import no.hvl.dat110.TODO;
import no.hvl.dat110.messaging.Connection;
import no.hvl.dat110.messaging.Message;
import no.hvl.dat110.messaging.MessagingServer;

public class RPCServer {

	private MessagingServer msgserver;
	private Connection connection;
	
	// hashmap to register RPC methods which are required to implement RPCImpl
	
	private HashMap<Integer,RPCImpl> services;
	
	public RPCServer(int port) {
		
		this.msgserver = new MessagingServer(port);
		this.services = new HashMap<Integer,RPCImpl>();
		
		// the stop RPC methods is built into the server
		services.put((int)RPCCommon.RPIDSTOP,new RPCServerStopImpl());
	}
	
	public void run() {
		
		System.out.println("RPC SERVER RUN - Services: " + services.size());
		
		connection = msgserver.accept(); 
		
		System.out.println("RPC SERVER ACCEPTED");
		
		boolean stop = false;
		
		while (!stop) {
	    
		   int rpcid;
		   
		   // TODO
		   // - receive message containing RPC request
		   
		   Message reqMessage = connection.receive();
		   // - find the identifier for the RPC methods to invoke
		   
		   byte[] rpcRequest = reqMessage.getData();
		   
		   rpcid = rpcRequest[0];
		   
		   // - lookup the method to be invoked
		   
		   RPCImpl methToInvoke = services.get(rpcid);
		   // - invoke the method
		   byte[] rpcReply;
		   
		   if (methToInvoke != null) {
			   rpcReply = methToInvoke.invoke(rpcRequest);
			   
		   } else {
			   rpcReply = new byte[1];
			   rpcReply[0] = 0;
		   }
		   
		   // - send back message containing RPC reply
		   
		   Message replyMessage = new Message(rpcReply);
		   connection.send(replyMessage);
		   
		   
		   if (rpcid == RPCCommon.RPIDSTOP) {
			   stop = true;
		   }
		}
	
	}
	
	public void register(int rpcid, RPCImpl impl) {
		services.put(rpcid, impl);
	}
	
	public void stop() {
		connection.close();
		msgserver.stop();
		
	}
}
