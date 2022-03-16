package com.epam.pagemanager;

public enum PageMapper {
	USER_MAIN_PAGE_KEY ("user.main.page"),
	USER_SHOPPING_CART_PAGE_KEY("user.cart.page"),
	DOCTOR_MAIN_PAGE_KEY ("doctor.main.page"),
	PHARMACIST_MAIN_PAGE_KEY ("pharmacist.main.page"),
	ADMIN_MAIN_PAGE_KEY ("admin.main.page"),
	LOGIN_PAGE_KEY ("login.page"),
	LOGOUT_PAGE_KEY ("logout.page"),
	REGISTRATION_PAGE_KEY ("registration.page"),
	USER_RECEIPTS_PAGE_KEY("user.receipts.page");
	
	private String pagekey;
	
	PageMapper(String pagekey) {
	this.pagekey = pagekey;	
	}

	public String getPageName() {
		return pagekey;
	}

	public void setPageName(String pagekey) {
		this.pagekey = pagekey;
	}
	
}
