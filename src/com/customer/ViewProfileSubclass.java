package com.customer;

import com.login.Login;

public class ViewProfileSubclass extends ViewProfile{

	@Override
	public void viewProfile() {
		CustomerDAO customerFunc = new CustomerDAO();
		customerFunc.GetCustomerDetails(Login.customerName);
	}

}
