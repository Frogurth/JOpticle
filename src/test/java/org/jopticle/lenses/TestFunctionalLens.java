package org.jopticle.lenses;

import static org.jopticle.lenses.FunctionalLens.lens;

import org.junit.Before;

import fixtures.Address;
import fixtures.Person;
import fj.F;
import fj.F2;

public class TestFunctionalLens extends TestAbstractLens {
	@Before
	public void setUp() throws Exception {
		nameLens = lens(new F<Person, String>() {
			@Override
			public String f(Person arg0) {
				return arg0.getName();
			}
		}, new F2<Person, String, Person>() {

			@Override
			public Person f(Person arg0, String arg1) {
				return new Person(arg1, arg0.getAddress().copy());
			}
		});

		addressLens = lens(new F<Person, Address>() {
			@Override
			public Address f(Person arg0) {
				return arg0.getAddress();
			}
		}, new F2<Person, Address, Person>() {

			@Override
			public Person f(Person arg0, Address arg1) {
				return new Person(arg0.getName(), arg1);
			}
		});

		zipCodeLens = lens(new F<Address, String>() {
			@Override
			public String f(Address arg0) {
				return arg0.getZipCode();
			}
		}, new F2<Address, String, Address>() {

			@Override
			public Address f(Address arg0, String arg1) {
				return new Address(arg1, arg0.getTown());
			}
		});
	}
}
