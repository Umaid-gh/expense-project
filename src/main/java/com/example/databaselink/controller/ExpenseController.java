package com.example.databaselink.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.databaselink.domain.ExpenseDTO;
import com.example.databaselink.domain.request.ExpenseRequest;
import com.example.databaselink.domain.response.ExpenseResponse;
import com.example.databaselink.service.ExpenseService;

@RestController
@RequestMapping("/expense")
public class ExpenseController {

	@Autowired
	ExpenseService expenseService;

	@PostMapping
	public ExpenseResponse<ExpenseDTO> createExpense(@RequestBody @Valid ExpenseRequest expenseRequest) {
		return expenseService.createExpense(expenseRequest);
	}
	
	@PutMapping("/{id}")
	public ExpenseResponse<ExpenseDTO> updateExpense(@RequestBody ExpenseRequest expenseRequest,@PathVariable Long id) {
		return expenseService.updateExpense(expenseRequest,id);
	}

	@GetMapping
	public ExpenseResponse<List<ExpenseDTO>> getAllExpenses() {
		return expenseService.getAllExpenses();
	}

	// TODO Work in progress. Returns null when id not found
	@GetMapping("id/{id}")
	public ExpenseResponse<ExpenseDTO> getExpensebyId(@PathVariable Long id) {
		return expenseService.getExpensebyId(id);
	}

	@GetMapping("item/{item}")
	public ExpenseResponse<ExpenseDTO> getExpensebyItem(@PathVariable String item) {
		return expenseService.getExpensebyItem(item);
	}
	
	@DeleteMapping("/{id}")
	public ExpenseResponse<ExpenseDTO> deleteExpensebyId(@PathVariable Long id) {
		return expenseService.deleteExpensebyId(id);
	}
	
	@PatchMapping("/{id}/{amount}")
	public ExpenseResponse<ExpenseDTO> updatebyId(@PathVariable float amount,@PathVariable Long id){
		return expenseService.updatebyId(amount,id);
	}

}