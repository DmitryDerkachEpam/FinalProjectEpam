package com.epam.entity;

import java.time.LocalDate;
import com.epam.dao.marker.Identifable;

public class Order implements Identifable{

	private int id;
	private LocalDate date;
	private User associatedUser;
	private boolean isPaidStatus;
	
	public static final String TABLE = "orders";
	
	public Order() {
		
	}

	public Order(Integer id) {
		this.id = id;
	}
	
	public Order(int id, LocalDate date, User associatedUser, boolean isPaid) {
		this.id = id;
		this.date = date;
		this.associatedUser = associatedUser;
		this.isPaidStatus = isPaid;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public User getAssociatedUser() {
		return associatedUser;
	}

	public void setAssociatedUser(User associatedUser) {
		this.associatedUser = associatedUser;
	}

	public boolean getIsPaidStatus() {
		return isPaidStatus;
	}

	public void setIsPaidStatus(boolean isPaid) {
		this.isPaidStatus = isPaid;
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
		Order other = (Order) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", date=" + date + ", associatedUser=" + associatedUser + ", isPaid=" + isPaidStatus + "]";
	}


	
}
