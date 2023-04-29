package com.charter.rewards.contollers;

import com.charter.rewards.beans.CustomerReward;
import com.charter.rewards.exceptions.RewardsException;
import com.charter.rewards.facades.RewardsFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.charter.rewards.utils.RewardsConstants.*;

@RestController
@ComponentScan(basePackages = "com.charter.rewards.*")
@Slf4j
public class RewardsController {

    @Autowired
    RewardsFacade rewardsFacade;

    @GetMapping(REWARDS_RESOURCE)
    public List<CustomerReward> getRewards(){
        final String METHOD_NAME = "getAllRewards";

        log.debug("Entered method {}",METHOD_NAME);
        List<CustomerReward> rewards = rewardsFacade.getRewardSummary();
        log.info("Response : {}",rewards.toString());

        log.debug("Exit method {}",METHOD_NAME);
        return rewards;
    }

    @GetMapping(REWARDS_BY_CUSTOMER_ID_RESOURCE)
    public List<CustomerReward> getRewardsByCustomerId(@PathVariable String customerId){
        final String METHOD_NAME = "getRewardsByCustomerId";

        log.debug("Entered method {}",METHOD_NAME);

        if(customerId == null || customerId.isEmpty())
            throw new RewardsException(400,"Customer ID cannot be null or empty");

        List<CustomerReward> rewards = rewardsFacade.getRewardSummaryByCustomerId(customerId);
        log.info("Response : {}",rewards.toString());

        log.debug("Exit method {}",METHOD_NAME);
        return rewards;
    }
}
