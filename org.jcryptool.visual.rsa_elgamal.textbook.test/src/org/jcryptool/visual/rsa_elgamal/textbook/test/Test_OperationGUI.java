package org.jcryptool.visual.rsa_elgamal.textbook.test;
import org.immutables.value.Value.*;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.security.interfaces.RSAKey;
import java.util.Formatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.jcryptool.mvc.ProcessException;
import org.jcryptool.mvc.validation.IExpectation;
import org.jcryptool.mvc.validation.ImmutableUnmetExpectation;
import org.jcryptool.mvc.validation.IExpectation.Assertion;
import org.jcryptool.mvc.validation.IExpectation.Parseable;
import org.jcryptool.mvc.validation.IExpectation.UnmetExpectation;
import org.jcryptool.mvc.validation.ImmutableParseable;
import org.jcryptool.visual.rsa_elgamal.textbook.control.ImmutableRSAKeygenState;
import org.jcryptool.visual.rsa_elgamal.textbook.control.RSAKeygen;
import org.jcryptool.visual.rsa_elgamal.textbook.control.RSAKeygen.RSAKeygenState;
import org.jcryptool.visual.rsa_elgamal.textbook.impl.algo.RSA;
import org.jcryptool.visual.rsa_elgamal.textbook.stepstone.RSA_App;
import org.jcryptool.visual.rsa_elgamal.textbook.test.impl.algo.TestData.RSA1;
import org.jcryptool.visual.rsa_elgamal.textbook.test.util.GUIUtil;
import org.jcryptool.visual.rsa_elgamal.view.ui.ISWTDocument;
import org.jcryptool.visual.rsa_elgamal.view.ui.SmartText;
import org.jcryptool.visual.rsa_elgamal.view.ui.SmartText.TextfieldState;

import org.junit.jupiter.api.*;

import com.diffplug.common.swt.Coat;
import com.diffplug.common.swt.Layouts;
import com.google.common.util.concurrent.Futures;

import fj.control.parallel.Promise;
import fj.data.Validation;

class Test_OperationGUI {

	public static class SmartTextGUI {
		
		public LinkedList<Consumer<SmartTextGUI>> smartTextActions;
		public LinkedList<Consumer<SmartTextGUI>> beforeOpen;

		public Coat testshellCoat;
	
		// eventually populated; use hooks!
		public SmartText smarttext = null;
		private Composite acceptedRoot;
		
		// implementation
		public ISWTDocument document = new ISWTDocument() {
			@Override
			public Composite getRoot() {
				return acceptedRoot;
			}
		};
		
		public void typeIntoSmarttext(String inputstring) {
			this.smarttext.text.setText(inputstring);
			//TODO: this is not enough...
		}

		// Constructor
		public SmartTextGUI() {
			this.smartTextActions = new LinkedList<>();
			this.beforeOpen = new LinkedList<>();
			this.setUp();
		}
		
		// GUI setup
		private void setUp() {
			this.testshellCoat = GUIUtil.defaultTestshellCoat(this::coatImplementation);
		}
		
		private void coatImplementation(Composite parent) {
			this.acceptedRoot = parent;
			
			this.smarttext = new SmartText(parent);
			Layouts.setGridData(smarttext).grabAll();
			this.smarttext.layoutRoot = parent.getShell();
			this.smarttext.text.setFocus();

			this.smartTextActions.forEach(consumer -> consumer.accept(this));

			parent.getShell().setSize(new Point(200, 20));
			
			this.beforeOpen.forEach(consumer -> consumer.accept(this));
		}
		
		public void open() {
			GUIUtil.showShell(this.testshellCoat);
		}
		
	}
	
	@BeforeEach void setUp() throws Exception { }
	@AfterEach void tearDown() throws Exception { } 
	
	public Validation<NumberFormatException, BigInteger> parseBigInt(String bi) {
		try {
			return Validation.success(new BigInteger(bi));
		} catch (NumberFormatException e) {
			return Validation.fail(e);
		}
	}
	
	
// 	private Validation<Object, > validate_textinput_1
	
	@Test
	void test_validation1() {
		String input1 = "123a";
		
		Validation<NumberFormatException, BigInteger> bigintValidation = parseBigInt(input1);
		org.junit.Assert.fail();

		
	}

 	@Disabled
	@Test
	void test_smarttext_1() {
		
		ImmutableRSAKeygenState keygenstate = ImmutableRSAKeygenState.of( RSA1.p, RSA1.q, RSA1.e);
		RSAKeygen keygen = new RSAKeygen(keygenstate);

		Parseable<TextfieldState> expectation_parseableInteger = 
				ImmutableParseable.<TextfieldState>of("input is an integer");
		
		IExpectation<TextfieldState> compoundExpectation = (textfieldState) -> {
			String content = textfieldState.content();
			
			// check if text is an integer
			BigInteger parsed;
			try {
				parsed = new BigInteger(content);
			} catch (NumberFormatException e) {
				return Optional.of(IExpectation.unmet(expectation_parseableInteger));
			}
			
			ImmutableRSAKeygenState newKeygenState = keygenstate.withP(parsed);
			
			// check if the keygen likes the value
			IExpectation[] keygenExpectations = new IExpectation[] {
//					RSAKeygen.expectation_n_gt_p, 
//					RSAKeygen.expectation_primeP
					};
			for(IExpectation<RSAKeygenState> expectation: keygenExpectations) {
				Optional<UnmetExpectation<RSAKeygenState>> unmetExpectation = expectation.check(newKeygenState);
				if (unmetExpectation.isPresent()) {
					return Optional.of(IExpectation.unmetHere(unmetExpectation.get(), textfieldState));
				}
			}

			// all expectations were met (parseable from string, and of the keygen)
			return Optional.empty();
		};

		SmartTextGUI stg = new SmartTextGUI();
		stg.beforeOpen.add(gui -> { 
				gui.smarttext.validator = compoundExpectation::check;
			});
		stg.open();

	}


 	@Disabled
	@Test
	void test_encrypt_tab() throws ProcessException {
		ImmutableRSAKeygenState keygenstate = ImmutableRSAKeygenState.of(
				RSA1.p.subtract(BigInteger.ONE), 
				RSA1.q, 
				RSA1.e);
		RSAKeygen keygen = new RSAKeygen(keygenstate);
		
// 		keygen.verify();
	}
	
}
