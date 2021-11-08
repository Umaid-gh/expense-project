package com.example.databaselink.service;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.server.ResponseStatusException;

import com.example.databaselink.domain.ExpenseDTO;
import com.example.databaselink.domain.request.ExpenseRequest;
import com.example.databaselink.domain.request.ExpenseUpdateRequest;
import com.example.databaselink.repository.ExpenseRepository;

@RunWith(MockitoJUnitRunner.class)
public class ExpenseServiceImplTest {

	@InjectMocks
	private ExpenseServiceImpl expenseServiceImpl;

	@Mock
	ExpenseRepository repository;

	@Test
	public void shouldCreateExpense() {

		Mockito.when(repository.save(Mockito.any(ExpenseDTO.class))).thenReturn(new ExpenseDTO());
		ExpenseRequest request = new ExpenseRequest();
		request.setItem("Book");
		request.setAmount(100);
		expenseServiceImpl.createExpense(request);
		Mockito.verify(repository).save(Mockito.any(ExpenseDTO.class));
	}

	@Test
	public void shouldGetAllExpenses() {
		Mockito.when(repository.findAll()).thenReturn(new ArrayList<ExpenseDTO>());
		expenseServiceImpl.getAllExpenses();
		Mockito.verify(repository).findAll();
	}

	@Test(expected = ResponseStatusException.class)
	public void shouldNotGetAllExpenses() {
		Mockito.when(repository.findAll()).thenReturn(null);
		expenseServiceImpl.getAllExpenses();
	}

	@Test
	public void shouldGetExpensebyId() {
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(new ExpenseDTO()));
		expenseServiceImpl.getExpensebyId(Mockito.anyLong());
		Mockito.verify(repository).findById(Mockito.anyLong());
	}
	
	@Test(expected = ResponseStatusException.class)
	public void shouldNotGetExpensebyId() {
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		expenseServiceImpl.getExpensebyId(Mockito.anyLong());
	}
	
	@Test
	public void shouldGetExpensebyItem(){
		Mockito.when(repository.findByItem(Mockito.anyString())).thenReturn(Optional.of(new ExpenseDTO()));
		expenseServiceImpl.getExpensebyItem(Mockito.anyString());
		Mockito.verify(repository).findByItem(Mockito.anyString());
	}
	
	@Test(expected = ResponseStatusException.class)
	public void shouldNotGetExpensebyItem() {
		Mockito.when(repository.findByItem(Mockito.anyString())).thenReturn(Optional.empty());
		expenseServiceImpl.getExpensebyItem(Mockito.anyString());
	}
	
	@Test
	public void shoulddeleteExpensebyId(){
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(new ExpenseDTO()));
		expenseServiceImpl.deleteExpensebyId(Mockito.anyLong());
		Mockito.verify(repository).findById(Mockito.anyLong());
	}
	
	@Test(expected = ResponseStatusException.class)
	public void shouldNotdeleteExpensebyId() {
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		expenseServiceImpl.deleteExpensebyId(Mockito.anyLong());
	}
	
	@Test
	public void shouldUpdateExpensebyId(){
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(new ExpenseDTO()));
		ExpenseRequest request = new ExpenseRequest();
		request.setItem("Story Book");
		request.setAmount(250);
		expenseServiceImpl.updateExpense(request, Mockito.anyLong());
		Mockito.verify(repository).findById(Mockito.anyLong());
	}
	
	@Test(expected = ResponseStatusException.class)
	public void ShouldNotUpdateExpensebyId(){
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		ExpenseRequest request = new ExpenseRequest();
		request.setItem("Story Book");
		request.setAmount(250);
		expenseServiceImpl.updateExpense(request, Mockito.anyLong());
	}
	
	@Test
	public void ShouldUpdateAmountbyId(){
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.of(new ExpenseDTO()));
		ExpenseUpdateRequest updaterequest = new ExpenseUpdateRequest();
		updaterequest.setAmount(500);
		expenseServiceImpl.updateAmountbyId(updaterequest, Mockito.anyLong());
		Mockito.verify(repository).findById(Mockito.anyLong());
	}
	
	@Test(expected = ResponseStatusException.class)
	public void ShouldNotUpdateAmountbyId(){
		Mockito.when(repository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		ExpenseUpdateRequest updaterequest = new ExpenseUpdateRequest();
		updaterequest.setAmount(500);
		expenseServiceImpl.updateAmountbyId(updaterequest, Mockito.anyLong());
		
	}
	
	@Test
	public void ShouldUpdateAmountbyItem(){
		Mockito.when(repository.findByItem(Mockito.anyString())).thenReturn(Optional.of(new ExpenseDTO()));
		ExpenseUpdateRequest updaterequest = new ExpenseUpdateRequest();
		updaterequest.setAmount(500);
		expenseServiceImpl.updateAmountbyItem(updaterequest, Mockito.anyString());
		Mockito.verify(repository).findByItem(Mockito.anyString());
	}
	
	@Test(expected = ResponseStatusException.class)
	public void ShouldNotUpdateAmountbyItem(){
		Mockito.when(repository.findByItem(Mockito.anyString())).thenReturn(Optional.empty());
		ExpenseUpdateRequest updaterequest = new ExpenseUpdateRequest();
		updaterequest.setAmount(500);
		expenseServiceImpl.updateAmountbyItem(updaterequest, Mockito.anyString());
		
	}
}
