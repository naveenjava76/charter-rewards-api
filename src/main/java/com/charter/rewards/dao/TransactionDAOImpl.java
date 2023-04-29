package com.charter.rewards.dao;

import com.charter.rewards.beans.Transaction;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Repository
public class TransactionDAOImpl implements TransactionDAO{

    @Override
    public List<Transaction> fetchTransactions() {
        //Hardcoding transactions . But ideally these would come from database or backend service with start date & end date filters
        Transaction transaction1 = new Transaction();
        transaction1.setTransactionDate(LocalDate.of(2023, 1,10));
        transaction1.setTransactionAmount(BigDecimal.valueOf(122));
        transaction1.setCustomerId("1234");

        Transaction transaction2 = new Transaction();
        transaction2.setTransactionDate(LocalDate.of(2023, 2,12));
        transaction2.setTransactionAmount(BigDecimal.valueOf(111));
        transaction2.setCustomerId("1234");

        Transaction transaction3 = new Transaction();
        transaction3.setTransactionDate(LocalDate.of(2023, 2,9));
        transaction3.setTransactionAmount(BigDecimal.valueOf(300.50));
        transaction3.setCustomerId("3456");

        Transaction transaction4 = new Transaction();
        transaction4.setTransactionDate(LocalDate.of(2023, 2,10));
        transaction4.setTransactionAmount(BigDecimal.valueOf(100));
        transaction4.setCustomerId("3456");

        Transaction transaction5 = new Transaction();
        transaction5.setTransactionDate(LocalDate.of(2022, 12,15));
        transaction5.setTransactionAmount(BigDecimal.valueOf(122));
        transaction5.setCustomerId("7896");

        return Arrays.asList(
                        transaction1,
                        transaction2,
                        transaction3,
                        transaction4,
                        transaction5
                );
    }

    @Override
    public List<Transaction> fetchTransactionsByCustomerId(String customerId) {

        //Hardcoding transactions . But ideally these would come from database or backend service with start date & end date filters
        Transaction transaction1 = new Transaction();
        transaction1.setTransactionDate(LocalDate.of(2023, 1,10));
        transaction1.setTransactionAmount(BigDecimal.valueOf(122));
        transaction1.setCustomerId("1234");

        Transaction transaction2 = new Transaction();
        transaction2.setTransactionDate(LocalDate.of(2023, 2,12));
        transaction2.setTransactionAmount(BigDecimal.valueOf(111));
        transaction2.setCustomerId("1234");

        return Stream.of(
                transaction1,
                transaction2
                )
                .filter(transaction -> customerId.equals(transaction.getCustomerId()))
                .toList();
    }
}
