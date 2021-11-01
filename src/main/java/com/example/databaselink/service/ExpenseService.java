package com.example.databaselink.service;

import java.util.List;

import com.example.databaselink.domain.ExpenseDTO;
import com.example.databaselink.domain.request.ExpenseRequest;
import com.example.databaselink.domain.response.ExpenseResponse;

public interface ExpenseService {

	public ExpenseResponse<ExpenseDTO> createExpense(ExpenseRequest expense);

	public ExpenseResponse<ExpenseDTO> getExpensebyId(Long id);

	public ExpenseResponse<List<ExpenseDTO>> getAllExpenses();

}
