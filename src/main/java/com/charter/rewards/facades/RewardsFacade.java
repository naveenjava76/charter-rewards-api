package com.charter.rewards.facades;

import com.charter.rewards.beans.CustomerReward;

import java.util.List;

public interface RewardsFacade {
    List<CustomerReward> getRewardSummary();
    List<CustomerReward> getRewardSummaryByCustomerId(String customerId);
}
