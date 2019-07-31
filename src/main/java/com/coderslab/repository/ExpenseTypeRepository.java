package com.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.coderslab.entity.ExpenseType;

@Repository
public interface ExpenseTypeRepository extends JpaRepository<ExpenseType, Long>{

}
