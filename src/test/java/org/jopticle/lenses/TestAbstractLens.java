package org.jopticle.lenses;

import junit.framework.Assert;

import org.junit.Ignore;
import org.junit.Test;

import fixtures.Address;
import fixtures.Person;
import fj.F;

@Ignore
public class TestAbstractLens {
	protected Person p1 = new Person("Foo", new Address("123123", "Somewhere"));
	protected Lens<Person, String> nameLens;
	protected Lens<Person, Address> addressLens;
	protected Lens<Address, String> zipCodeLens;

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

	@Test
	public void testComposition() {
		Lens<Person, String> conposedLens = addressLens.andThen(zipCodeLens);
		Assert.assertEquals("123123", conposedLens.get(p1));
		Person p2 = conposedLens.set(p1, "321321");
		Assert.assertEquals("321321", p2.getAddress().getZipCode());
	}

	@Test
	public void testMod() {
		Person p2 = nameLens.mod(p1, new F<String, String>() {
			@Override
			public String f(String arg0) {
				return arg0.toUpperCase();
			}
		});
		Assert.assertEquals("FOO", p2.getName());
	}
}
