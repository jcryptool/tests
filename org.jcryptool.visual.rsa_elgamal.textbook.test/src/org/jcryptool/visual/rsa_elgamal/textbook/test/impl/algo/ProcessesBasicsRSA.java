package org.jcryptool.visual.rsa_elgamal.textbook.test.impl.algo;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import org.jcryptool.mvc.IProcess;
import org.jcryptool.mvc.IState;
import org.jcryptool.visual.rsa_elgamal.textbook.control.RSAKeygen;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.diffplug.common.base.Errors;
import com.diffplug.common.rx.Rx;
import com.diffplug.common.rx.RxBox;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

class ProcessesBasicsRSA {

	public String toString(Object o) {
		return stringtable.getOrDefault(o, o.toString());
	}

	public Map<Object, String> stringtable = new HashMap<>();
	public IProcess<? extends IState> process;

	private RSAKeygen keygen;
	
	final Consumer<Throwable> logErrors(IProcess<? extends IState> process) {
		return error -> {
 			System.err.println(String.format("Error `%s` arrived for process %s", toString(error), toString(process)));
// 			Errors.log().accept(error);
		};
	};
	final Consumer<IState> logValues(IProcess<? extends IState> process) {
		return state -> {
			System.out.println(String.format("New state arrived for %s: %s", toString(process), toString(state)));
		};
	};
	
	@BeforeEach
	void setUp() {

//  		RxJavaPlugins.setErrorHandler(error -> System.err.println(error.getMessage()));
// 		keygen = TestMockUI.RSA1.ProcessSpecs.keygen_default();
//  		keygen.asObservable().subscribe(Rx.onValueOnFailure(logValues(keygen), logErrors(keygen)));
//  		Rx.subscribe(keygen.asObservable(), Rx.onValueOnFailure(logValues(keygen), logErrors(keygen)));
		
		BehaviorSubject<BigInteger> subj1 = BehaviorSubject.createDefault(BigInteger.TWO);
		Observable<BigInteger> obs = subj1;
		Rx.subscribe(obs, Rx.onValueOnFailure(bi -> System.out.println(bi), Errors.log()));

//		io.reactivex.functions.Function<? super Throwable, ? extends ObservableSource<? extends BigInteger>> resumeFun;
//		resumeFun = throwable -> BigInteger.valueOf(100);

// 		obs = obs.onErrorReturnItem(BigInteger.valueOf(100));

		RxBox<BigInteger> rec = RxBox.of(BigInteger.ZERO);

		
		subj1.onNext(BigInteger.valueOf(5));
		subj1.onError(new RuntimeException("test1"));
		subj1.onNext(BigInteger.valueOf(6));
		
		Observable.error(() -> new RuntimeException("bla")).onErrorReturnItem("Hello world!").subscribe(System.out::println);
		

	}
	void setUpStrings() {
		this.stringtable.clear();
	}
	

	
	@Test
	void encrypt1() {
// 		keygen.getState().p.set(TestData.RSA1.p);
// 		keygen.getState().q.set(TestData.RSA1.q);
// 		keygen.getState().e.set(TestData.RSA1.e);
		
		
		
// 		RSAKeys generated = keygen.finish();
		
// 		EncryptSequenceProcess<RSAPublicKey> encrypt = new EncryptSequenceProcess<>();

// 		process = new EncryptSequenceProcess();
		
	}

}

//	public static class LoggingObserver implements Observer<Object> {
//
//		private IProcess<? extends IState> process;
//		private Function<Object, String> stringify;
//
//		public LoggingObserver(Function<Object, String> toString) {
//			this.process = process;
//			this.stringify = toString;
//		}
//
//		@Override
//		public void onSubscribe(Disposable d) {
//			System.out.println(String.format("onSubscribe: [%s] %s", stringify.apply(process), stringify.apply(d)));
//		}
//
//		@Override
//		public void onNext(Object value) {
//			System.out.println(String.format("onNext:      [%s] %s", stringify.apply(process), stringify.apply(value)));
//		}
//
//		@Override
//		public void onError(Throwable e) {
//			System.out.println(String.format("onError:     [%s] %s", stringify.apply(process), stringify.apply(e)));
//		}
//
//		@Override
//		public void onComplete() {
//			System.out.println(String.format("onComplete   [%s]", stringify.apply(process)));
//		}
//	};