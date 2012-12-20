# JOpticle

Implementation of functional lenses in Java.
Contains imlementation that uses Java bean properies to reflect the get and set methods for a specific field.

### Creating and using BeanLens
```Java
class Address {
  private String zipCode;
  public String getZipCode() {return zipCode;}
  public void setZipCode(String zipCode) {this.zipCode = zipCode;}
}

class Person {
  private Address address;
  public Address getAddress() {return address;}
  public void setAddress(Address address) {this.address = address;}
}

...

Lens<Person, Address> addressLens = new BeanLens<Person, Address>("address", Person.class);
Lens<Address, String> zipCodeLens = new BeanLens<Address, String>("zipCode", Address.class);

// Lenses can compose
Lens<Person, String> composedLens = addressLens.andThen(zipCodeLens);

Person p = addressLens.set(new Person(), zipCodeLens.set(new Address(), "123456"));

String zipCode = composedLens.get(p); //"123456"
```
The ```BeanLens``` will modify the field of the given source and return it.

The ```BeanDeepCopyLens``` will copy the source, modity the field of the copy and return the copy. The source will remain unchanged.

### Creating FunctionalLens
```Java
import static org.jopticle.lenses.FunctionalLens.lens;
import fj.F;
import fj.F2;

...

Lens<Person, Address> addressLens = lens(new F<Person, Address>() {
  		@Override
			public Address f(Person arg0) {
				return arg0.getAddress();
			}
		}, new F2<Person, Address, Person>() {

			@Override
			public Person f(Person arg0, Address arg1) {
				Person p = new Person();
				p.setAddress(arg1);
				return p;
			}
		});

Lens<Address, String> zipCodeLens = lens(new F<Address, String>() {
			@Override
			public String f(Address arg0) {
				return arg0.getZipCode();
			}
		}, new F2<Address, String, Address>() {

			@Override
			public Address f(Address arg0, String arg1) {
				Address address = new Address();
				address.setZipCode(arg1);
				return address;
			}
		});
```
