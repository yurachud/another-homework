package clone.swaper.business.investor;

import com.querydsl.core.types.dsl.BooleanExpression;

public class InvestorExpressions {
    public static BooleanExpression with(String email) {
        return QInvestor.investor.email.eq(email);
    }
}