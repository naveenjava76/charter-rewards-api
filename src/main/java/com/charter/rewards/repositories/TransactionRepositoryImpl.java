package com.charter.rewards.repositories;

import com.charter.rewards.beans.Transaction;
import com.charter.rewards.dao.TransactionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TransactionRepositoryImpl implements TransactionRepository{

    @Autowired
    TransactionDAO transactionDAO;

    @Override
    public List<Transaction> findAll() {
        return transactionDAO.fetchTransactions();
    }

    @Override
    public List<Transaction> findByCustomerId(String customerId) {
        return transactionDAO.fetchTransactionsByCustomerId(customerId);
    }
}
