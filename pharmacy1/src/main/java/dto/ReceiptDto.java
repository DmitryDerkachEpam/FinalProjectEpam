package dto;

import java.time.LocalDate;
import com.epam.entity.receipt_state.ReceiptState;

public class ReceiptDto {

	private int id;
	private LocalDate expirationDate;
	private ReceiptState receiptState;
	private String userName;
	private String medicineName;
	
	public ReceiptDto() {
		
	}

	public ReceiptDto(int id, LocalDate expirationDate, ReceiptState receiptState, String userName,
			String medicineName) {
		this.id = id;
		this.expirationDate = expirationDate;
		this.receiptState = receiptState;
		this.userName = userName;
		this.medicineName = medicineName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}

	public ReceiptState getReceiptState() {
		return receiptState;
	}

	public void setReceiptState(ReceiptState receiptState) {
		this.receiptState = receiptState;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
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
		ReceiptDto other = (ReceiptDto) obj;
		if (id != other.id) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "ReceiptDto [id=" + id + ", expirationDate=" + expirationDate + ", receiptState=" + receiptState
				+ ", userName=" + userName + ", medicineName=" + medicineName + "]";
	}
	
}
