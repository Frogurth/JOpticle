JOpticle
========

Implementation of functional lenses in Java.
Contains imlementation that uses Java bean properies to reflect the get and set methods for a specific field.

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
