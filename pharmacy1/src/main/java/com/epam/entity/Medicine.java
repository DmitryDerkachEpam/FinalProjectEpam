package com.epam.entity;

import com.epam.dao.marker.Identifable;

public class Medicine implements Identifable{

	private int id;
	private String name;
	private int dose;
	private boolean isReceiptRequired;
	private int price;
	private String medicineImageKey;
	
	public static final String TABLE = "medicines";
	
	public Medicine() {
		
	}
	
	public Medicine(int id) {
		this.id = id;
	}

	public Medicine(int id, String name, int dose, boolean isReceiptRequired, int price, String medicineImageKey) {
		this.id = id;
		this.name = name;
		this.dose = dose;
		this.isReceiptRequired = isReceiptRequired;
		this.price = price;
		this.medicineImageKey = medicineImageKey;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDose() {
		return dose;
	}

	public void setDose(int dose) {
		this.dose = dose;
	}

	public boolean getIsReceiptRequired() {
		return isReceiptRequired;
	}

	public void setIsReceiptRequired(boolean isReceiptRequired) {
		this.isReceiptRequired = isReceiptRequired;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getMedicineImageKey() {
		return medicineImageKey;
	}

	public void setMedicineImageKey(String medicineImageKey) {
		this.medicineImageKey = medicineImageKey;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		Medicine other = (Medicine) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Medicine [id=" + id + ", name=" + name + ", dose=" + dose + ", isReceiptRequired=" + isReceiptRequired
				+ ", price=" + price + ", medicineImageKey=" + medicineImageKey + "]";
	}
	
}
