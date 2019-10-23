package ru.zagshak.buySupply.serviceTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.zagshak.buySupply.domain.Request;
import ru.zagshak.buySupply.service.RequestService;
import ru.zagshak.buySupply.util.exception.NoAccessException;
import ru.zagshak.buySupply.util.exception.NotFoundException;

import static ru.zagshak.buySupply.OfferTestData.*;
import static ru.zagshak.buySupply.RequestTestData.*;
import static ru.zagshak.buySupply.UserTestData.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql(scripts = "classpath:db/populate_db.sql", config = @SqlConfig(encoding = "UTF-8"))
public class RequestServiceTest {

    @Autowired
    private RequestService requestService;

    @Test
    public void create() throws Exception {
        Request newRequest = new Request(null,"New request",false);
        Request created = requestService.create(newRequest,OFFER2_ID,USER2_ID);
        newRequest.setId(created.getId());
        assertMatch(created, newRequest);
    }

    @Test
    public void update() throws Exception {
        Request updated = new Request(REQUEST1);
        updated.setMessage("Updated, is it okay?");
        requestService.update(updated, updated.getOffer().getId(), updated.getRequester().getId());
    }

    @Test(expected = NoAccessException.class)
    public void updateNotOwn() throws Exception {
        Request updated = new Request(REQUEST1);
        updated.setMessage("Updated, is it okay? (Coz i'ma stranger)");
        requestService.update(updated, updated.getOffer().getId(), updated.getRequester().getId() + 1);
    }


    @Test
    public void delete() throws Exception {
        requestService.delete(REQUEST1_ID, REQUEST1.getRequester().getId());
        assertMatch(requestService.getAll(), REQUEST2, REQUEST3);
    }

    @Test(expected = NoAccessException.class)
    public void deleteNotOwn() throws Exception {
        requestService.delete(REQUEST1_ID, REQUEST1.getRequester().getId() + 1);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception {
        requestService.delete(1,REQUEST1.getRequester().getId() + 1);
    }

    @Test
    public void get() throws Exception {
        assertMatch(requestService.get(REQUEST1_ID, USER1_ID), REQUEST1);
    }

    @Test(expected = NoAccessException.class)
    public void getNotOwn() throws NotFoundException, NoAccessException {
        assertMatch(requestService.get(REQUEST1_ID, USER2_ID), REQUEST1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws NotFoundException {
        assertMatch(requestService.get(1, USER2_ID), REQUEST1);
    }

    @Test
    public void getAll() throws Exception {
        assertMatch(requestService.getAll(),REQUEST1, REQUEST2, REQUEST3);
    }
}
