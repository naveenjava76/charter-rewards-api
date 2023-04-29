package com.charter.rewards.services;

import com.charter.rewards.beans.CustomerReward;
import com.charter.rewards.beans.Reward;
import com.charter.rewards.beans.Transaction;
import com.charter.rewards.facades.RewardsFacade;
import com.charter.rewards.repositories.TransactionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.charter.rewards.utils.RewardsUtil.CONVERT_AMOUNT_TO_POINTS;

@Service
@Slf4j
public class RewardsImpl implements RewardsFacade {

    @Autowired
    TransactionRepository transactionRepository;

    @Override
    public List<CustomerReward> getRewardSummary() {
        final String METHOD_NAME = "getRewards";

        log.debug("Entered method {}",METHOD_NAME);
        List<Transaction> transactions = transactionRepository.findAll();

        List<CustomerReward> customerRewards = groupedByCustomerAndMonth(transactions);

        log.debug("Exit method {}",METHOD_NAME);

        return customerRewards;

    }

    @Override
    public List<CustomerReward> getRewardSummaryByCustomerId(String customerId) {
        final String METHOD_NAME = "getRewards";

        log.debug("Entered method {}",METHOD_NAME);

        List<Transaction> transactions = transactionRepository.findByCustomerId(customerId);

        List<CustomerReward> customerRewards = groupedByCustomerAndMonth(transactions);

        log.debug("Exit method {}",METHOD_NAME);
        return customerRewards;
    }

    private static List<CustomerReward> groupedByCustomerAndMonth(List<Transaction> transactions){
        final String METHOD_NAME = "groupedByCustomerAndMonth";

        Map<String, Map<Object, Double>> rewardsGroupedByCustomerAndMonth =
                transactions
                        .stream()
                        .collect(
                                Collectors.groupingBy(
                                        Transaction::getCustomerId,
                                        Collectors.groupingBy(
                                                transaction -> transaction.getTransactionDate().getMonth(),
                                                Collectors.mapping(transaction -> CONVERT_AMOUNT_TO_POINTS.apply(transaction.getTransactionAmount()),
                                                        Collectors.collectingAndThen(
                                                                Collectors.toList(),
                                                                amountList -> amountList.stream().mapToDouble(Double::doubleValue).sum()))
                                        )
                                )
                        );

        log.debug("Created rewardsGroupedByCustomerAndMonth as {}",rewardsGroupedByCustomerAndMonth);

        List<CustomerReward> customerRewards = new ArrayList<>();
        rewardsGroupedByCustomerAndMonth
                .forEach((key, mapValue) -> {
                    CustomerReward customerReward = new CustomerReward();
                    customerReward.setCustomerId(key);

                    List<Reward> pointsByMonth = new ArrayList<>();
                    double totalPoints = 0.0;
                    for (Map.Entry<Object, Double> entry : mapValue.entrySet()) {
                        Object o = entry.getKey();
                        Double monthlyPoints = entry.getValue();

                        Reward reward = new Reward();
                        reward.setMonth(o.toString());
                        reward.setPoints(monthlyPoints);

                        pointsByMonth.add(reward);
                        totalPoints = totalPoints + monthlyPoints;
                    }
                    customerReward.setPointsByMonth(pointsByMonth);
                    customerReward.setTotalPoints(totalPoints);

                    customerRewards.add(customerReward);
                });

        log.debug("Exit method {}",METHOD_NAME);

        return customerRewards;
    }
}
