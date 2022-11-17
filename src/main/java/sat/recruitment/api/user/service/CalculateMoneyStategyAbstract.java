package sat.recruitment.api.user.service;

import sat.recruitment.api.user.enums.CalculateStrategyNameEnum;

public abstract class CalculateMoneyStategyAbstract implements CalculateMoneyStrategy{

    abstract CalculateStrategyNameEnum getStrategyName();
}
