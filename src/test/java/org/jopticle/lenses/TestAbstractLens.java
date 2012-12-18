package org.jopticle.lenses;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import fixtures.Address;
import fixtures.Person;

public class TestAbstractLens {
	private Person p1;
	private Lens<Person, String> nameLens;

	@Before
	public void setUp() throws Exception {
		p1 = new Person("Foo", new Address("123123", "Somewhere"));
		nameLens = new Lens<Person, String>() {

			@Override
			public Person set(Person a, String b) {
				return new Person(b, a.getAddress());
			}

			@Override
			public String get(Person a) {
				return a.getName();
			}
		};
	}

	@Test
	public void testGetPutLaw() {
		Person p2 = nameLens.set(p1, nameLens.get(p1));
		Assert.assertTrue(p1.equals(p2));
	}

	@Test
	public void testPutGetLaw() {
		String expected = "Bar";
		String actual = nameLens.get(nameLens.set(p1, expected));
		Assert.assertTrue(expected.equals(actual));
	}
}
