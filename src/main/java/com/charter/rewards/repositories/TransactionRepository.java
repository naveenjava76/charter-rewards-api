package com.charter.rewards.repositories;

import com.charter.rewards.beans.Reward;
import com.charter.rewards.beans.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TransactionRepository {
    List<Transaction> findAll();
    List<Transaction> findByCustomerId(String customerId);
}
