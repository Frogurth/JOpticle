package org.jopticle.lenses;

import org.junit.Before;

import fixtures.Address;
import fixtures.Person;

public class TestBeanLens extends TestAbstractLens {
	@Before
	public void setUp() throws Exception {
		nameLens = new BeanDeepCopyLens<Person, String>("name", Person.class);
		addressLens = new BeanLens<Person, Address>("address", Person.class);
		zipCodeLens = new BeanLens<Address, String>("zipCode", Address.class);
	}
}
