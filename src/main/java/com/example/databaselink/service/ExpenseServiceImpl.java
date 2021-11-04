package com.example.databaselink.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.databaselink.domain.ExpenseDTO;
import com.example.databaselink.domain.request.ExpenseRequest;
import com.example.databaselink.domain.request.ExpenseUpdateRequest;
import com.example.databaselink.domain.response.ExpenseResponse;
import com.example.databaselink.repository.ExpenseRepository;

@Service
public class ExpenseServiceImpl implements ExpenseService {

	private Logger LOGGER = LoggerFactory.getLogger(ExpenseServiceImpl.class);

	@Autowired
	ExpenseRepository repository;

	@Override
	public ExpenseResponse<ExpenseDTO> createExpense(ExpenseRequest request) {
		float amount = request.getAmount();
		String item = request.getItem();

		LOGGER.info("Item is {} ", item);
		LOGGER.info("Amount is {} ", amount);

		ExpenseDTO expenseDTO = new ExpenseDTO();
		expenseDTO.setItem(item);
		expenseDTO.setAmount(amount);

		repository.save(expenseDTO);

		ExpenseResponse<ExpenseDTO> response = new ExpenseResponse<>();
		response.setStatus(200);
		response.setMessage("expense saved successfully!!");
		response.setData(expenseDTO);

		return response;
	}

	@Override
	public ExpenseResponse<List<ExpenseDTO>> getAllExpenses() {

		List<ExpenseDTO> expenseDTO = null;
		ExpenseResponse<List<ExpenseDTO>> response = new ExpenseResponse<>();

		expenseDTO = (List<ExpenseDTO>) repository.findAll();

		if (expenseDTO == null) {
			response.setStatus(404);
			response.setMessage("No Expense found");
		} else {
			response.setData(expenseDTO);
			response.setStatus(200);
			response.setMessage("Expenses retrieved successfully.");
		}

		return response;
	}

	@Override
	public ExpenseResponse<ExpenseDTO> getExpensebyId(Long id) {
		ExpenseResponse<ExpenseDTO> response = new ExpenseResponse<>();
		Optional<ExpenseDTO> optionalExpenseDTO = repository.findById(id);
		if (optionalExpenseDTO.isPresent()) {
			ExpenseDTO expenseDTO = optionalExpenseDTO.get();
			response.setData(expenseDTO);
			response.setMessage("Expense retrieved successfully");
			response.setStatus(200);
		} else {
			response.setMessage("No Expense found with the given Id");
			response.setStatus(404);
		}
		return response;
	}

	@Override
	public ExpenseResponse<ExpenseDTO> getExpensebyItem(String item) {
		ExpenseResponse<ExpenseDTO> response = new ExpenseResponse<>();
		Optional<ExpenseDTO> optionalExpenseDTO = repository.findByItem(item);
		if (optionalExpenseDTO.isPresent()) {
			ExpenseDTO expenseDTO = optionalExpenseDTO.get();
			response.setData(expenseDTO);
			response.setMessage("Expense retrived successfully");
			response.setStatus(200);
		} else {
			response.setMessage("No Expense found with the given item");
			response.setStatus(404);
		}
		return response;
	}

	@Override
	public ExpenseResponse<ExpenseDTO> deleteExpensebyId(Long id) {

		ExpenseResponse<ExpenseDTO> response = new ExpenseResponse<>();
		Optional<ExpenseDTO> expenseDTO = repository.findById(id);

		if (expenseDTO.isPresent()) {
			response.setData(expenseDTO.get());
			repository.deleteById(id);
			response.setStatus(200);
			response.setMessage("Expense deleted successfully.");
		} else {
			response.setStatus(404);
			response.setMessage("No Expense found");
		}
		return response;

	}

	@Override
	public ExpenseResponse<ExpenseDTO> updateExpense(ExpenseRequest request, Long id) {

		ExpenseResponse<ExpenseDTO> response = new ExpenseResponse<>();
		Optional<ExpenseDTO> optionalExpenseDTO = repository.findById(id);

		if (optionalExpenseDTO.isPresent()) {
			float amount = request.getAmount();
			String item = request.getItem();
			ExpenseDTO expenseDTO = optionalExpenseDTO.get();
			expenseDTO.setItem(item);
			expenseDTO.setAmount(amount);

			repository.save(expenseDTO);

			response.setStatus(200);
			response.setMessage("expense updated successfully!!");
			response.setData(expenseDTO);

		} else {
			response.setStatus(404);
			response.setMessage("No Expense found");
		}

		return response;
	}

	@Override
	public ExpenseResponse<ExpenseDTO> updateAmountbyId(ExpenseUpdateRequest request,Long id) {
		
		ExpenseResponse<ExpenseDTO> response = new ExpenseResponse<>();
		Optional<ExpenseDTO> optionalExpenseDTO = repository.findById(id);
		
		float amount = request.getAmount();
		
		if (optionalExpenseDTO.isPresent()) {
			
			
			
			
			ExpenseDTO expenseDTOFromDB = optionalExpenseDTO.get();
			expenseDTOFromDB.setAmount(amount);

			repository.save(expenseDTOFromDB);

			response.setStatus(200);
			response.setMessage("expense updated successfully!!");
			response.setData(expenseDTOFromDB);

		} else {
			response.setStatus(404);
			response.setMessage("No Expense found");
		}

		return response;
	}
	
	

}
