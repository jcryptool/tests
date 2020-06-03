package org.jcryptool.visual.rsa_elgamal.textbook.test.util;

import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

public class UserInput {

	public Map<Consumer<?>, Object> store;
	public Map<Consumer<?>, Object> defaults;
	
	private <T> T getLastInputOrDefault(Consumer<T> receiver) {
		return (T) store.getOrDefault(receiver, defaults.get(receiver));
	}
	
	public <T> void userInput(Consumer<T> consumer, T value) {
		store.put(consumer, value);
	}
	
	public <T> void registerAsConsumer(Consumer<T> consumer, T defaultValue) {
		defaults.put(consumer, defaultValue);
	}
	
}
