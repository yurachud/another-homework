package clone.swaper.business.share;

import clone.swaper.infrastructure.persistence.JpaRepository;
import clone.swaper.infrastructure.persistence.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;

@Configuration
class ShareJpaRepository {
    private final EntityManager entityManager;
    
    ShareJpaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    
    @Bean
    Repository<Share, ShareId> shareRepository() {
        return new JpaRepository<>(entityManager, Share.class);
    }
}