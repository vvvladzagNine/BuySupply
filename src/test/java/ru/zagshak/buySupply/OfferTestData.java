package ru.zagshak.buySupply;

import ru.zagshak.buySupply.domain.Category;
import ru.zagshak.buySupply.domain.Offer;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.zagshak.buySupply.UserTestData.USER1;
import static ru.zagshak.buySupply.UserTestData.USER2;
import static ru.zagshak.buySupply.domain.AbstractBaseEntity.START_SEQ;

public class OfferTestData {
    public static final int OFFER1_ID = START_SEQ + 5;
    public static final int OFFER2_ID = START_SEQ + 6;

    public static final int CATEGORY1_ID = START_SEQ + 3;

    public static final Category CATEGORY1 = new Category( CATEGORY1_ID,"Food","Box","KG");

    public static final Offer OFFER1 = new Offer(OFFER1_ID, "Pizza",100,300,false, CATEGORY1, USER2, LocalDateTime.of(2015,5,31,10,0));
    public static final Offer OFFER2 = new Offer(OFFER2_ID, "Cake",40,100,true,CATEGORY1,USER1, LocalDateTime.of(2015,5,30,10,0));


    public static void assertMatch(Offer actual, Offer expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "offerer","category");
    }

    public static void assertMatch(Iterable<Offer> actual, Iterable<Offer> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("offerer","category").isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Offer> actual, Offer... expected) {
        assertMatch(actual, List.of(expected));
    }
}
