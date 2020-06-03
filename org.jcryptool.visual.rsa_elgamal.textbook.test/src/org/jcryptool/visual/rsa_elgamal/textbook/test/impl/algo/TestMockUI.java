package org.jcryptool.visual.rsa_elgamal.textbook.test.impl.algo;

import org.jcryptool.visual.rsa_elgamal.textbook.process.RSAKeygen;
import org.jcryptool.visual.rsa_elgamal.textbook.process.RSAKeygen.RSAKeygenState;

public class TestMockUI {

	public static class RSA1 {

		public static class ProcessSpecs {

			public static RSAKeygen keygen_testdata1() {
				RSAKeygenState state = new RSAKeygenState();
				state.p.set(TestData.RSA1.p);
				state.q.set(TestData.RSA1.q);
				state.e.set(TestData.RSA1.e);
				return RSAKeygen.from(state);
			}
			
			public static RSAKeygen keygen_default() {
				return RSAKeygen.createDefault();
			}

			
		}
		
	}

}
