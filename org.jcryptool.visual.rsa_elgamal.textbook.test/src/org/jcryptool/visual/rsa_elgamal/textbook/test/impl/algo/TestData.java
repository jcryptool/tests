package org.jcryptool.visual.rsa_elgamal.textbook.test.impl.algo;

import java.math.BigInteger;

public class TestData {
	public static class RSA1 {
		public static BigInteger p = new BigInteger("5700734181645378434561188374130529072194886062117");
		public static BigInteger q = new BigInteger("35894562752016259689151502540913447503526083241413");
        
		public static BigInteger n = p.multiply(q);

		public static BigInteger e = new BigInteger("33445843524692047286771520482406772494816708076993"); // public

        public static BigInteger phi = (p.subtract(BigInteger.ONE)).multiply(q.subtract(BigInteger.ONE));
        public static BigInteger d = e.modInverse(phi); 
	}
}
