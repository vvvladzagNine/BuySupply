package ru.zagshak.buySupply.serviceTests;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.zagshak.buySupply.domain.Offer;
import ru.zagshak.buySupply.service.OfferService;
import ru.zagshak.buySupply.util.exception.NoAccessException;
import ru.zagshak.buySupply.util.exception.NotFoundException;

import java.time.LocalDateTime;

import static ru.zagshak.buySupply.OfferTestData.*;
import static ru.zagshak.buySupply.UserTestData.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql( scripts = "classpath:db/populate_db.sql", config = @SqlConfig(encoding = "UTF-8"))
public class OfferServiceTest {

    @Autowired
    private OfferService offerService;

    @Test
    public void create() throws Exception {
        Offer newOffer = new Offer(null, "Elite gold-pumpkin cheesecake",100,25000,false,CATEGORY1, LocalDateTime.now());
        Offer created = offerService.create(newOffer, USER1_ID);
        newOffer.setId(created.getId());
        assertMatch(newOffer, created);
    }

    @Test
    public void update() throws Exception {
        Offer updated = new Offer(OFFER2);
        updated.setAmount(10000000);
        offerService.update(updated, updated.getOfferer().getId());
        assertMatch(offerService.get(OFFER2_ID),updated);
    }

    @Test(expected = NoAccessException.class)
    public void updateNotOwn() throws Exception {
        Offer updated = new Offer(OFFER2);
        updated.setAmount(10000000);
        offerService.update(updated, USER2_ID);
    }

    @Test
    public void delete() throws Exception {
        offerService.delete(OFFER2_ID, OFFER2.getOfferer().getId());
        assertMatch(offerService.getAll(), OFFER1);
    }

    @Test(expected = NotFoundException.class)
    public void deleteNotFound() throws Exception {
        offerService.delete(1,5);
    }

    @Test
    public void get() throws Exception {
        assertMatch(offerService.get(OFFER1_ID),OFFER1);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        offerService.get(1);
    }

    @Test
    public void getAll() throws Exception {
        assertMatch(offerService.getAll(), OFFER1, OFFER2);
    }

}
