package it.contrader.dao;


import it.contrader.model.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.Optional;
import static java.util.Optional.ofNullable;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNull;


@RunWith(SpringRunner.class)
@DataJpaTest
public class TestUser {


    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByName() {
        entityManager.persist(new User("user", "user"));
        Optional<User> user = ofNullable(userRepository.findByUsernameAndPassword("user", "user"));
        assertEquals("user", user.get().getUsername());
        assertEquals("user", user.get().getPassword());
    }

    @Test
    public void testInsert() {
        entityManager.persist(new User("ciao", "user"));
        User us = new User();
        us.setUsername("cacca");
        us.setPassword("pipi");
        Optional<User> user = Optional.of(userRepository.save(us));
        assertEquals("cacca", user.get().getUsername());
        assertEquals("pipi", user.get().getPassword());
    }

    @Test
    public void testDelete() {
        User us = new User();
        us.setId(4);
        userRepository.save(us);
        long c = 0;
        userRepository.deleteById((long) 4);
        Iterable<User> a = userRepository.findAll();
        for ( User b : a ){
            if (b.getId() == 4){
                c = b.getId();
            }
        }
        assertEquals(0, c);
    }
}
