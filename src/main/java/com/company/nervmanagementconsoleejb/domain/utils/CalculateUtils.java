package com.company.nervmanagementconsoleejb.domain.utils;

import javax.ejb.Stateless;
import java.util.List;
import java.util.Random;
//da controllare l'injezione
@Stateless
public class CalculateUtils {

    public Integer calculateAverage(List<Integer> list) {
        if (list.isEmpty()) {
            return 0;
        }
        int sum = 0;
        for (Integer value : list) {
            sum += value;
        }
        return sum / list.size();
    }

    public boolean calculateWinLoseProbability(Integer missionSr, Integer missionSa, Integer missionTa, List<Integer> syncRateToAvg, List<Integer> tactAbilityToAvg, List<Integer> suppAbilityToAvg) {
        Integer avgSyncRate = calculateAverage(syncRateToAvg);
        Integer avgTactAbility = calculateAverage(tactAbilityToAvg);
        Integer avgSuppAbility = calculateAverage(suppAbilityToAvg);

        Integer defeatProbability = Math.max(0, missionSr - avgSyncRate)
                + Math.max(0, missionSa - avgSuppAbility)
                + Math.max(0, missionTa - avgTactAbility)
                + 25;

        Random random = new Random();
        int winPossibility = random.nextInt(101);

        boolean result = winPossibility >= defeatProbability;

        return result;
    }

    public Integer MinMaxStat (Integer stat) {
        if (stat < 0) {
            return 0;
        } else if (stat > 100) {
            return 100;
        }
        return stat;
    }

    public int randomizeStats(Integer attrbMax) {
        Random random = new Random ();
        int valueRandom=0;
        if(attrbMax<0) {
            valueRandom= random.nextInt(-attrbMax + 1) + attrbMax;
            return valueRandom;
        }else {
            valueRandom= random.nextInt(attrbMax)+1;
            return valueRandom;
        }
    }
}