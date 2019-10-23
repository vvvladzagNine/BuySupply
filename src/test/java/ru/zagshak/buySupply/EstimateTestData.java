package ru.zagshak.buySupply;

import ru.zagshak.buySupply.domain.Estimate;
import ru.zagshak.buySupply.domain.Request;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.zagshak.buySupply.UserTestData.USER1;
import static ru.zagshak.buySupply.UserTestData.USER2;
import static ru.zagshak.buySupply.domain.AbstractBaseEntity.START_SEQ;

public class EstimateTestData {
//    INSERT INTO estimate(estimated_id,estimator_id,comment,stars,date_time) VALUES
//    (100001,100000,'It is a forgery :(',1,'2016-05-31'),
//    (100000,100001,'Offer then reject !!!',1,'2016-05-31');

    public static final int ESTIMATE1_ID = START_SEQ + 10;
    public static final int ESTIMATE2_ID = START_SEQ + 11;

    public static final Estimate ESTIMATE1 = new Estimate(ESTIMATE1_ID, USER1, USER2,1,"It is a forgery :(", LocalDateTime.of(2016,5,31,10,0));
    public static final Estimate ESTIMATE2 = new Estimate(ESTIMATE2_ID, USER2, USER1,1,"Offer then reject !!!", LocalDateTime.of(2016,5,31,10,0));

    public static void assertMatch(Estimate actual, Estimate expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "estimator", "estimated");
    }

    public static void assertMatch(Iterable<Estimate> actual, Iterable<Estimate> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("estimator", "estimated").isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Estimate> actual, Estimate... expected) {
        assertMatch(actual, List.of(expected));
    }
}
