package com.epam.entity;

import java.time.LocalDate;
import com.epam.dao.marker.Identifable;
import com.epam.entity.receipt_state.ReceiptState;

public class Receipt implements Identifable{
	
	private int id;
	private User associatedUser;
	private Medicine associatedMedicine;
	private LocalDate date;
	private ReceiptState state;
	
	public static final String TABLE = "receipts";

	public Receipt() {
		
	}

	public Receipt(int id, User userId, Medicine medicineId, LocalDate date, ReceiptState state) {
		this.id = id;
		this.associatedUser = userId;
		this.associatedMedicine = medicineId;
		this.date = date;
		this.state = state;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public User getAssociatedUser() {
		return associatedUser;
	}

	public void setAssociatedUser(User associatedUser) {
		this.associatedUser = associatedUser;
	}

	public Medicine getAssociatedMedicine() {
		return associatedMedicine;
	}

	public void setAssociatedMedicine(Medicine associatedMedicine) {
		this.associatedMedicine = associatedMedicine;
	}
	
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}
	
	public ReceiptState getState() {
		return state;
	}

	public void setState(ReceiptState state) {
		this.state = state;
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
		Receipt other = (Receipt) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Receipt [id=" + id + ", userId=" + associatedUser + ", medicineId=" + associatedMedicine + ", date=" + date + ", state="
				+ state + "]";
	}
	
}
