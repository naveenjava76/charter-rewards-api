package com.charter.rewards.utils;

import com.charter.rewards.beans.CustomerReward;
import com.charter.rewards.beans.Transaction;

import java.math.BigDecimal;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class RewardsUtil {

    public static Function<BigDecimal, Double> CONVERT_AMOUNT_TO_POINTS = amount ->{
        double points = 0.0;
        if(amount != null){
            if(amount.compareTo(BigDecimal.valueOf(100)) > 0){
                points = amount.subtract(BigDecimal.valueOf(100)).multiply(BigDecimal.valueOf(2)).doubleValue();
                amount = BigDecimal.valueOf(100);
            }
            if(amount.compareTo(BigDecimal.valueOf(50)) > 0){
                points = points + amount.subtract(BigDecimal.valueOf(50)).doubleValue();
            }
        }
        return points;
    };
}
