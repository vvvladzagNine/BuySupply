package ru.zagshak.buySupply.serviceTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.zagshak.buySupply.domain.Estimate;
import ru.zagshak.buySupply.service.EstimateService;
import ru.zagshak.buySupply.util.exception.NoAccessException;
import ru.zagshak.buySupply.util.exception.NotFoundException;

import java.time.LocalDateTime;

import static ru.zagshak.buySupply.EstimateTestData.*;
import static ru.zagshak.buySupply.UserTestData.USER1_ID;
import static ru.zagshak.buySupply.UserTestData.USER2_ID;

@RunWith(SpringRunner.class)
@SpringBootTest
@Sql( scripts = "classpath:db/populate_db.sql", config = @SqlConfig(encoding = "UTF-8"))
public class EstimateServiceTest {

    @Autowired
    private EstimateService estimateService;

    @Test
    public void create() throws Exception {
        Estimate newEstimate = new Estimate(null, 5, "Not bad", LocalDateTime.now());
        Estimate created = estimateService.create(newEstimate,USER1_ID,USER2_ID);
        newEstimate.setId(created.getId());
        assertMatch(created, newEstimate);
    }

    @Test
    public void update() {
        Estimate updated = new Estimate(ESTIMATE1);
        updated.setComment("Na i think okay");
        estimateService.update(updated,updated.getEstimated().getId(),updated.getEstimator().getId());
        assertMatch(estimateService.get(ESTIMATE1_ID), updated);
    }

    @Test(expected = NoAccessException.class)
    public void updateNotOwn() {
        Estimate updated = new Estimate(ESTIMATE1);
        updated.setComment("Na i think okay");
        estimateService.update(updated,updated.getEstimated().getId(),updated.getEstimator().getId() + 1);
        assertMatch(estimateService.get(ESTIMATE1_ID), updated);
    }

    @Test
    public void delete() throws Exception {
        estimateService.delete(ESTIMATE1_ID, USER1_ID);
        assertMatch(estimateService.getAll(), ESTIMATE2);
    }

    @Test(expected = NoAccessException.class)
    public void deleteNotOwn() throws Exception {
        estimateService.delete(ESTIMATE1_ID, USER2_ID);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() throws Exception {
        estimateService.get(0);
    }

    @Test
    public void get() throws Exception {
        assertMatch(estimateService.get(ESTIMATE1_ID), ESTIMATE1);
    }

    @Test
    public void getAllByEstimator() throws Exception {
        assertMatch(estimateService.getAllByEstimator(USER1_ID), ESTIMATE1);
    }

    @Test
    public void getAllByEstimated() throws Exception {
        assertMatch(estimateService.getAllByEstimated(USER1_ID), ESTIMATE2);
    }




}
