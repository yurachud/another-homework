package clone.swaper.business.investor;

import clone.swaper.infrastructure.persistence.JpaRepository;
import clone.swaper.infrastructure.persistence.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
class InvestorJpaRepository {
    private final EntityManager entityManager;
    
    InvestorJpaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Bean
    Repository<Investor, InvestorId> investorRepository() {
        return new JpaRepository<>(entityManager, Investor.class);
    }
}