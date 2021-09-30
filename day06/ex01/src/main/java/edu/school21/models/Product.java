package edu.school21.models;

import java.util.Objects;

public class Product {
	private Long	id;
	private String	name;
	private int		price;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Product)) return false;
		Product product = (Product) o;
		return id.equals(product.id) && price == product.price && name.equals(product.name);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, name, price);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
}
