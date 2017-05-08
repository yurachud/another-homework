package clone.swaper.business.loan;

import clone.swaper.infrastructure.persistence.JpaRepository;
import clone.swaper.infrastructure.persistence.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
class LoanJpaRepository {
    private final EntityManager entityManager;
    
    LoanJpaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Bean
    Repository<Loan, LoanId> loanRepository() {
        return new JpaRepository<>(entityManager, Loan.class);
    }
}