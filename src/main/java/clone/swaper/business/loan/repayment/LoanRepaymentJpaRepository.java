package clone.swaper.business.loan.repayment;

import clone.swaper.infrastructure.persistence.JpaRepository;
import clone.swaper.infrastructure.persistence.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
class LoanRepaymentJpaRepository {
    private final EntityManager entityManager;
    
    LoanRepaymentJpaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Bean
    Repository<LoanRepayment, LoanRepaymentId> loanRepaymentRepository() {
        return new JpaRepository<>(entityManager, LoanRepayment.class);
    }
}