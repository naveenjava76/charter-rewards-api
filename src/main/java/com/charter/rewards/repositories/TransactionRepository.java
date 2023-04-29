package com.charter.rewards.repositories;

import com.charter.rewards.beans.Transaction;

import java.util.List;

public interface TransactionRepository {
    List<Transaction> findAll();
    List<Transaction> findByCustomerId(String customerId);
}
