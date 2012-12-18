package org.jopticle.lenses;

import org.junit.Before;

import fixtures.Address;
import fixtures.Person;

public class TestBeanDeapCopyLens extends TestAbstractLens {
	@Before
	public void setUp() throws Exception {
		nameLens = new BeanDeepCopyLens<Person, String>("name", Person.class);
		addressLens = new BeanDeepCopyLens<Person, Address>("address",
				Person.class);
		zipCodeLens = new BeanDeepCopyLens<Address, String>("zipCode",
				Address.class);
	}
}
