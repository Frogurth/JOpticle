package fixtures;

public class Address {
	private String zipCode;
	private String town;

	public Address(String zipCode, String town) {
		this.zipCode = zipCode;
		this.town = town;
	}

	public String getZipCode() {
		return zipCode;
	}

	public String getTown() {
		return town;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public Address copy() {
		return new Address(zipCode, town);
	}

	public boolean equals(Address a) {
		return zipCode.equals(a.getZipCode()) && town.equals(a.getTown());
	}
}
