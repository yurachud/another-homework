package clone.swaper.business.investor.registration;

import clone.swaper.business.investor.Investor;
import clone.swaper.business.investor.InvestorId;
import clone.swaper.business.investor.QInvestor;
import clone.swaper.infrastructure.persistence.DomainEntityNotFound;
import clone.swaper.infrastructure.persistence.JpaRepository;
import clone.swaper.infrastructure.persistence.Repository;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class InvestorRepositoryTest {
    String JACK_MAIL = "jack@mail.com";
    String PETER_MAIL = "peter@mail.com";
    Investor customerJack;
    Investor customerPeter;
    
    @Autowired
    TestEntityManager testEntityManager;
    
    Repository<Investor, InvestorId> investors;
    
    @Before
    public void setUp() throws Exception {
        investors = new JpaRepository(testEntityManager.getEntityManager(), Investor.class);
        customerJack = persistedWithAmount(JACK_MAIL);
        customerPeter = persistedWithAmount(PETER_MAIL);
    }
    
    @Test
    public void correctIfNothing() throws Exception {
        BooleanExpression dummyPredicate = QInvestor.investor.email.eq("dummy");
        investors.remove(customerJack);
        investors.remove(customerPeter);
        
        assertThat(investors.all().size(), Is.is(0));
        assertThat(investors.all(dummyPredicate).size(), Is.is(0));
        assertThat(investors.count(), Is.is(0L));
        assertThat(investors.count(dummyPredicate), Is.is(0L));
    }
    
    @Test
    public void all() throws Exception {
        assertThat(investors.all().size(), Is.is(2));
    }
    
    @Test
    public void allByPredicate() throws Exception {
        BooleanExpression jack = QInvestor.investor.email.eq(JACK_MAIL);
        
        List<Investor> all = investors.all(jack);
        assertThat(all.size(), Is.is(1));
    }
    
    @Test
    public void countByPredicate() throws Exception {
        BooleanExpression jack = QInvestor.investor.email.eq(JACK_MAIL);
        
        assertThat(investors.count(jack), Is.is(1L));
    }
    
    @Test
    public void count() throws Exception {
        assertThat(investors.count(), Is.is(2L));
    }
    
    @Test
    public void removeEntity() throws Exception {
        investors.remove(customerJack);
        
        assertThat(investors.count(), Is.is(1L));
        assertThat(investors.all().stream().findFirst().get().email(), Is.is(PETER_MAIL));
    }
    
    @Test
    public void uniqueByPredicate() throws Exception {
        BooleanExpression jack = QInvestor.investor.email.eq(JACK_MAIL);
        
        assertThat(investors.unique(jack).email(), Is.is(JACK_MAIL));
    }
    
    @Test(expected = DomainEntityNotFound.class)
    public void throwsExceptionIfNotExists() throws Exception {
        BooleanExpression notExistent = QInvestor.investor.email.eq("");
        investors.unique(notExistent);
    }
    
    @Test
    public void uniqueById() throws Exception {
        assertThat(investors.unique(customerPeter.typesafeId()), Is.is(customerPeter));
    }
    
    @Test(expected = DomainEntityNotFound.class)
    public void throwsExceptionIfNotExistsById() throws Exception {
        InvestorId notPersistedId = new Investor("", "").typesafeId();
        investors.unique(notPersistedId);
    }
    
    Investor persistedWithAmount(String mail) {
        Investor investor = new Investor(mail, "parole1");
        investors.add(investor);
        return investor;
    }
}
