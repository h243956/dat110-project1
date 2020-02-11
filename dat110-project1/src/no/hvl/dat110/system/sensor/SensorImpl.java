package no.hvl.dat110.system.sensor;

import java.util.Random;

import no.hvl.dat110.rpc.RPCImpl;
import no.hvl.dat110.rpc.RPCUtils;

public class SensorImpl implements RPCImpl {

	static final int RANGE = 20;

	public int read() {

//		long seconds = System.currentTimeMillis();
//		double temp = RANGE * Math.sin(seconds / 1000);
//		
//		try {
//			Thread.sleep(1500);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		// la inn en random gen for å få ulike "temperaturer"
		// implementasjonen kunne gi samme temp og gi feil bilde av korleis det funka
		Random r = new Random();
		int random = r.nextInt(50) - 20;

		return random;
	}
	
	public byte[] invoke(byte[] request) {
				
		RPCUtils.unmarshallVoid(request); 
		
		int temp = read();
		
		byte rpcid = request[0];
		
		byte[] reply = RPCUtils.marshallInteger(rpcid,temp);
		
		return reply;
	}
}
