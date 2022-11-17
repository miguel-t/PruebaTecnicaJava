package sat.recruitment.api.user.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import sat.recruitment.api.user.enums.CalculateStrategyNameEnum;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@Slf4j
public class SuperUserCalculateMoneyStrategy extends CalculateMoneyStategyAbstract{

    @Override
    public Double calculateMoney(Double amount) {

        Double gif = Double.valueOf("0.00");
        if (amount > 100) {
            gif = amount * Double.valueOf("0.20");;
        }
        return BigDecimal.valueOf(amount + gif).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    @Override
    CalculateStrategyNameEnum getStrategyName() {
        return CalculateStrategyNameEnum.SUPERUSER;
    }
}
