package com.charter.rewards.dao;

import com.charter.rewards.beans.Transaction;

import java.util.List;

public interface TransactionDAO {
    List<Transaction> fetchTransactions();
    List<Transaction> fetchTransactionsByCustomerId(String customerId);
}
