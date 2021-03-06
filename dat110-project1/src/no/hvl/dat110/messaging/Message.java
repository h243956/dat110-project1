package no.hvl.dat110.messaging;

import java.util.Arrays;

import no.hvl.dat110.TODO;

public class Message {

	private byte[] payload;

	public Message(byte[] payload) {
		this.payload = payload; // TODO: check for length within boundary
	}

	public Message() {
		super();
	}

	public byte[] getData() {
		return this.payload; 
	}

	public byte[] encapsulate() {
		
		// TODO
		// encapulate/encode the payload of this message in the
		// encoded byte array according to message format
		
		byte[] encoded = new byte[128];
		encoded[0]=(byte)this.payload.length;
		
		for (int i = 0; i < this.payload.length; i++) {
			encoded[i+1]=this.payload[i];
		}
		
		return encoded;
	}
	
	
	public void decapsulate(byte[] received) {

		// TODO
		// decapsulate the data contained in the received byte array and store it 
		// in the payload of this message
		
		this.payload = new byte[received[0]];
		for (int i = 0; i < received[0]; i++) {
			this.payload[i]=received[i+1];
		}
		
		
		
	}
}
