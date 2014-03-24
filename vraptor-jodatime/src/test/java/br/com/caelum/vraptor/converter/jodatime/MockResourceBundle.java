package br.com.caelum.vraptor.converter;

import java.util.Collections;
import java.util.Enumeration;
import java.util.ResourceBundle;

public class MockResourceBundle extends ResourceBundle {

	@Override
	public Enumeration<String> getKeys() {
		return Collections.emptyEnumeration();
	}

	@Override
	protected Object handleGetObject(String key) {
		return key;
	}
}
