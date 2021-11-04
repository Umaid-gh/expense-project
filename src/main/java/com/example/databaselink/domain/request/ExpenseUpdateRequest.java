package com.example.databaselink.domain.request;

import javax.validation.constraints.Positive;

public class ExpenseUpdateRequest {

	private String item;
	@Positive(message = "size should be positive number")
	private float amount;

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

}
