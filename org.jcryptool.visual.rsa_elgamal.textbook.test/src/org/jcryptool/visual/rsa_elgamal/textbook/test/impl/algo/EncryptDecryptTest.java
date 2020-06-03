package org.jcryptool.visual.rsa_elgamal.textbook.test.impl.algo;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

import org.jcryptool.visual.rsa_elgamal.textbook.impl.algo.StringUtil;
import org.jcryptool.visual.rsa_elgamal.textbook.impl.Utils;
import org.jcryptool.visual.rsa_elgamal.textbook.impl.algo.RSA;

class EncryptDecryptTest {

	public RSA rsaParams;
	
	private String message;

	void setUp() throws Exception {
		rsaParams = new RSA(
				TestData.RSA1.p,
				TestData.RSA1.q,
				TestData.RSA1.e
				);
        message = "test1";
	}

	void test() {
        List<BigInteger> encrypted = StringUtil.encryptMessage(rsaParams, message);
        List<BigInteger> signed = StringUtil.signMessage(rsaParams, message);

        List<BigInteger> decimalMessage = StringUtil.messageToDecimal(rsaParams, message);

        List<BigInteger> decrypted = encrypted.stream().map(x -> rsaParams.decrypt(x)).collect(Collectors.toList());
        List<BigInteger> verified = signed.stream().map(x -> rsaParams.verify(x)).collect(Collectors.toList());

        System.out.println("message(plain text)   = " + Utils.bigIntegerToString(decimalMessage));
        System.out.println("message(decimal)      = " + Utils.bigIntegerSum(decimalMessage));
        System.out.println("encrypted(decimal)    = " + Utils.bigIntegerSum(encrypted));
        System.out.println("decrypted(plain text) = " + Utils.bigIntegerToString(decrypted));
        System.out.println("decrypted(decimal)    = " + Utils.bigIntegerSum(decrypted));
        System.out.println("signed(decimal)       = " + Utils.bigIntegerSum(signed));
        System.out.println("verified(plain text)  = " + Utils.bigIntegerToString(verified));
        System.out.println("verified(decimal)     = " + Utils.bigIntegerSum(verified));
	}
	
	public static void main(String[] args) throws Exception {
		EncryptDecryptTest test = new EncryptDecryptTest();
		test.setUp();
		test.test();
	}

}
