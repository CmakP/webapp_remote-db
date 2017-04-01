/**
 * Project: COMP3613_A00977249Assignment01
 * File: Product.java
 * Date: Oct 12, 2016
 * Time: 10:59:21 PM
 */
package a00977249.assignment.data.product;

/**
 * @author Siamak Pourian
 *
 * Product Class
 */
public class Product {
	
	private int id;
	private int code;
	
	private double price;
	private double weight;
	
	private String name;
	private String Manufacturer;
	private String madeIn;
	private String description;
	
	/**
	 * Default constructor
	 */
	public Product() {}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the Manufacturer
	 */
	public String getManufacturer() {
		return Manufacturer;
	}

	/**
	 * @param Manufacturer the Manufacturer to set
	 */
	public void setManufacturer(String Manufacturer) {
		this.Manufacturer = Manufacturer;
	}

	/**
	 * @return the madeIn
	 */
	public String getMadeIn() {
		return madeIn;
	}

	/**
	 * @param madeIn the madeIn to set
	 */
	public void setMadeIn(String madeIn) {
		this.madeIn = madeIn;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Product [id=" + id + ", code=" + code + ", price=" + price + ", weight=" + weight + ", name=" + name + ", Manufacturer=" + Manufacturer + ", madeIn=" + madeIn
				+ ", description=" + description + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + code;
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		result = prime * result + id;
		result = prime * result + ((Manufacturer == null) ? 0 : Manufacturer.hashCode());
		result = prime * result + ((madeIn == null) ? 0 : madeIn.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		long temp;
		temp = Double.doubleToLongBits(price);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(weight);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (code != other.code)
			return false;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id != other.id)
			return false;
		if (Manufacturer == null) {
			if (other.Manufacturer != null)
				return false;
		} else if (!Manufacturer.equals(other.Manufacturer))
			return false;
		if (madeIn == null) {
			if (other.madeIn != null)
				return false;
		} else if (!madeIn.equals(other.madeIn))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (Double.doubleToLongBits(price) != Double.doubleToLongBits(other.price))
			return false;
		if (Double.doubleToLongBits(weight) != Double.doubleToLongBits(other.weight))
			return false;
		return true;
	}
}
