package clone.swaper.business.investor;

import clone.swaper.infrastructure.command.Reaction;
import clone.swaper.infrastructure.persistence.Repository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class InvestorsQueryReaction implements Reaction<InvestorsQuery, InvestorsQuery.InvestorsBean> {
    private Repository<Investor, InvestorId> investors;
    
    public InvestorsQueryReaction(Repository<Investor, InvestorId> investors) {
        this.investors = investors;
    }
    
    @Override
    public InvestorsQuery.InvestorsBean on(InvestorsQuery $) {
        List<InvestorBean> investorBeans = investors.all()
                .stream()
                .map(this::investorBean)
                .collect(Collectors.toList());
        return new InvestorsQuery.InvestorsBean(investorBeans);
    }
    
    private InvestorBean investorBean(Investor investor) {
        return new InvestorBean(investor.typesafeId().uuid(), investor.email());
    }
    
}
