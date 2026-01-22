package es.upm.grise.profundizacion.subscriptionService;

import static org.junit.Assert.*;
import org.junit.Test;

import es.upm.grise.profundizacion.exceptions.ExistingUserException;
import es.upm.grise.profundizacion.exceptions.LocalUserDoesNotHaveNullEmailException;
import es.upm.grise.profundizacion.exceptions.NullUserException;

public class SubscriptionServiceTest {

    @Test
    public void smokeTest() {
        new SubscriptionService();
    }

    @Test
    public void testAddSubscriberCorrect() throws Exception {
        SubscriptionService service = new SubscriptionService();
        User user = new User(Delivery.DO_NOT_DELIVER, "test@email.com");

        service.addSubscriber(user);

        assertEquals(1, service.getSubscribers().size());
        assertTrue(service.getSubscribers().contains(user));
    }


    @Test(expected = NullUserException.class)
    public void testAddSubscriberNullUser() throws Exception {
        SubscriptionService service = new SubscriptionService();
        service.addSubscriber(null);
    }


    @Test(expected = ExistingUserException.class)
    public void testAddSubscriberExistingUser() throws Exception {
        SubscriptionService service = new SubscriptionService();
        User user = new User(Delivery.DO_NOT_DELIVER, "test@email.com");

        service.addSubscriber(user);
        service.addSubscriber(user); // repetido
    }

    
    @Test(expected = LocalUserDoesNotHaveNullEmailException.class)
    public void testAddSubscriberLocalUserWithEmail() throws Exception {
        SubscriptionService service = new SubscriptionService();
        User user = new User(Delivery.LOCAL, "local@email.com");

        service.addSubscriber(user);
    }

  
    @Test
    public void testAddSubscriberLocalUserWithNullEmail() throws Exception {
        SubscriptionService service = new SubscriptionService();
        User user = new User(Delivery.LOCAL, null);

        service.addSubscriber(user);

        assertEquals(1, service.getSubscribers().size());
    }
}
