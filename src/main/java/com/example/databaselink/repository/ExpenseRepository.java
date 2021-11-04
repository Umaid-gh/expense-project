package com.example.databaselink.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.databaselink.domain.ExpenseDTO;
 
public interface ExpenseRepository extends CrudRepository<ExpenseDTO, Long> {
     
    public Optional<ExpenseDTO> findByItem(String item);
     
    @Query("SELECT e FROM expense e WHERE e.amount >= :amount")
    public List<ExpenseDTO> listItemsWithPriceOver(@Param("amount") float amount);
   
    @Modifying
    @Query("UPDATE expense SET amount = :amount WHERE id=:id")
    public ExpenseDTO updateAmountbyId(Long id,float amount);
    
    @Modifying
    @Query("UPDATE expense SET amount = :amount WHERE item=:item")
    public ExpenseDTO updateAmountbyItem(float amount, String item);
}