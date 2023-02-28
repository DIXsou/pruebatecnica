package com.diego.PruebaTecnica.Objects;

import java.util.HashSet;

public class Product implements Comparable<Product>{
	private int id;
	private int sequence;
	private HashSet<Size> sizes;
	
	public Product(int id, int sequence) {
		this.id = id;
		this.sequence = sequence;
		this.sizes = new HashSet<>();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getSequence() {
		return sequence;
	}
	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	public Size getSize(int searchIdSize) {
		for (Size size : sizes) {
			if (size.getId() == searchIdSize) {
				return size;
			}
		}
		return null;
	}
	public boolean updateSize (int id, int newStock, boolean newBackSoon, boolean newSpecial) {
		for (Size size : sizes) {
			if (size.getId() == id) {
				size.setStock(newStock);
				size.setBackSoon(newBackSoon);
				size.setSpecial(newSpecial);
				return true;
			}
		}
		return false;
	}
	public boolean setSize(Size inSize) {
		if (!this.sizes.add(inSize)) {
			return updateSize(inSize.getId(), inSize.getStock(), inSize.isBackSoon(), inSize.isSpecial());
		}
		return true;
	}
	public boolean dropSize(int idSize) {
		return this.sizes.remove(new Size(idSize));
	}
	public void cleanSizes() {
		this.sizes.clear();
	}
	public HashSet<Size> getAllSizes() {
		return sizes;
	}
	public void setAllSizes(HashSet<Size> sizes) {
		this.sizes = sizes;
	}
	
	@Override
	public boolean equals(Object obj) {
		Product otherProduct;
		try {
			otherProduct = (Product) obj;
		} catch (Exception e) {
			return false;
		}
		return this.id == otherProduct.getId();
	}

	@Override
	public int compareTo(Product o) {
		return this.sequence - o.getSequence();
	}
	
	@Override
	public String toString() {
		return String.valueOf(this.id) ;
	}
}
