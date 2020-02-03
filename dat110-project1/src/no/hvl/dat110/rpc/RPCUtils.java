package no.hvl.dat110.rpc;

import java.math.BigInteger;
import java.util.Arrays;

import no.hvl.dat110.TODO;

public class RPCUtils {

	// Utility methods for marshalling and marshalling of parameters and return values
	// in RPC request and RPC responses
	// data bytearrays and return byte arrays is according to the 
	// RPC message syntax [rpcid,parameter/return value]
	
	public static byte[] marshallString(byte rpcid, String str) {

		byte[] encoded;

		// TODO: marshall RPC identifier and string into byte array
		
		byte[] text = str.getBytes();
		//under kan vi vel bruke byte-arrayet "text" i staden for str.getBytes()?
		//encoded = new byte[str.getBytes().length + 1];
		encoded = new byte[text.length + 1];
		encoded[0] = rpcid;
		
		/*for(int i = 0; i < str.getBytes().length; i++) {
			encoded[i+1] = str.getBytes()[i];
		}*/
		for(int i = 0; i < text.length; i++) {
			encoded[i+1] = text[i];
		}
		
		/*if (true) {
			throw new UnsupportedOperationException(TODO.method());
		}*/

		return encoded;
	}

	public static String unmarshallString(byte[] data) {

		String decoded;

		// TODO: unmarshall String contained in data into decoded

		decoded = new String(Arrays.copyOfRange(data, 1, data.length)); 
		
		/*if (true) {
			throw new UnsupportedOperationException(TODO.method());
		}*/

		return decoded;
	}

	public static byte[] marshallVoid(byte rpcid) {

		byte[] encoded = new byte[1];
		encoded[0] = rpcid;
		
		// TODO: marshall RPC identifier in case of void type
		/*if (true) {
			throw new UnsupportedOperationException(TODO.method());
		}*/

		return encoded;

	}

	public static void unmarshallVoid(byte[] data) {

		// TODO: unmarshall void type
	}

	public static byte[] marshallBoolean(byte rpcid, boolean b) {

		byte[] encoded = new byte[2];

		encoded[0] = rpcid;

		if (b) {
			encoded[1] = 1;
		} else {
			encoded[1] = 0;
		}

		return encoded;
	}

	public static boolean unmarshallBoolean(byte[] data) {

		return (data[1] > 0);

	}

	public static byte[] marshallInteger(byte rpcid, int x) {

		byte[] encoded;
		byte[] number = BigInteger.valueOf(x).toByteArray();
		encoded = new byte[number.length + 1];
		// TODO: marshall RPC identifier and string into byte array

		encoded[0] = rpcid;
		for(int i = 0; i < number.length; i++) {
			encoded[i+1] = number[i];
		}
		
		/*if (true) {
			throw new UnsupportedOperationException(TODO.method());
		}*/

		return encoded;
	}

	public static int unmarshallInteger(byte[] data) {

		int decoded;
		byte[] decdata = new byte[data.length - 1];
		for(int i = 0; i < data.length - 1; i++) {
			decdata[i] = data[i + 1];
		}
		decoded = new BigInteger(decdata).intValue();
		// TODO: unmarshall integer contained in data

		/*if (true) {
			throw new UnsupportedOperationException(TODO.method());
		}*/

		return decoded;

	}
}
