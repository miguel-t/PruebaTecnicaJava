package sat.recruitment.api.user.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import sat.recruitment.api.user.enums.CalculateStrategyNameEnum;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
@Slf4j
public class NormalCalculateMoneyStrategy extends CalculateMoneyStategyAbstract{

    @Override
    public Double calculateMoney(Double amount) {
        Double gif = 0.00;

        if (amount > 100) {
            // If new user is normal and has more than USD100
             gif = amount * Double.valueOf("0.12");
        }
        if(amount < 100 && amount >10 ){
            gif = amount * Double.valueOf("0.8");
        }
        return  BigDecimal.valueOf(amount + gif).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }

    @Override
    CalculateStrategyNameEnum getStrategyName() {
        return CalculateStrategyNameEnum.NORMAL;
    }
}
