package clone.swaper.business.loan;

import com.querydsl.core.types.dsl.BooleanExpression;

import java.math.BigDecimal;

public class LoanExpressions {
    public static BooleanExpression with(String externalId) {
        return QLoan.loan.externalId.eq(externalId);
    }
    
    public static BooleanExpression availableForSale() {
        return QLoan.loan.openAmount.gt(BigDecimal.ZERO);
    }
}