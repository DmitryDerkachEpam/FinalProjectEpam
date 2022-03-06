package com.epam.entity;

import com.epam.dao.marker.Identifable;

public class Item implements Identifable {
	
	private int id;
	private Order associatedOrder;
	private Medicine associatedMedicine;
	private int quantity;
	
	public static final String TABLE = "order_items";

	public Item() {
		
	}
	
	public Item(int id, Order associatedOrder, Medicine associatedMedicine, int quantity) {
		this.id = id;
		this.associatedOrder = associatedOrder;
		this.associatedMedicine = associatedMedicine;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Order getAssociatedOrder() {
		return associatedOrder;
	}

	public void setAssociatedOrder(Order associatedOrder) {
		this.associatedOrder = associatedOrder;
	}

	public Medicine getAssociatedMedicine() {
		return associatedMedicine;
	}

	public void setAssociatedMedicine(Medicine associatedMedicine) {
		this.associatedMedicine = associatedMedicine;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
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
		Item other = (Item) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Item [id=" + id + ", associatedOrder=" + associatedOrder + ", associatedMedicine=" + associatedMedicine
				+ ", quantity=" + quantity + "]";
	}
	
}
