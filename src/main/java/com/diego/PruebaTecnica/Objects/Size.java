package com.diego.PruebaTecnica.Objects;

public class Size {
	private int id;
	private int stock;
	private boolean backSoon;
	private boolean special;
	
	public Size(int id) {
		this.id = id;
		this.stock = 0;
		this.backSoon = false;
		this.special = false;
	}
	public Size(int id, int stock, boolean backSoon, boolean special) {
		this.id = id;
		this.stock = stock;
		this.backSoon = backSoon;
		this.special = special;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public boolean isBackSoon() {
		return backSoon;
	}
	public void setBackSoon(boolean backSoon) {
		this.backSoon = backSoon;
	}
	public boolean isSpecial() {
		return special;
	}
	public void setSpecial(boolean special) {
		this.special = special;
	}
	@Override
	public boolean equals(Object obj) {
		Size otherSize = (Size) obj;
		return this.id == otherSize.getId();
	}
}
