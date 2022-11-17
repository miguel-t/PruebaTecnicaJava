package sat.recruitment.api.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sat.recruitment.api.user.enums.CalculateStrategyNameEnum;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Component
public class CalculateMoneyStrategyFactory {

    private Map<CalculateStrategyNameEnum, CalculateMoneyStategyAbstract> strategies;

    @Autowired
    public void StrategyFactory(Set<CalculateMoneyStategyAbstract> strategies) {
        this.createStrategy(strategies);
    }


    public CalculateMoneyStrategy findStrategy(CalculateStrategyNameEnum strategyName) {
        return strategies.get(strategyName);
    }

    private void createStrategy(Set<CalculateMoneyStategyAbstract> strategySet) {
        strategies = new HashMap<>();
        strategySet.forEach(s -> strategies.put(s.getStrategyName(),s));
    }
}
