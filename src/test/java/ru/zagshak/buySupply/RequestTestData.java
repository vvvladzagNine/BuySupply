package ru.zagshak.buySupply;

import ru.zagshak.buySupply.domain.Request;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.zagshak.buySupply.OfferTestData.OFFER1;
import static ru.zagshak.buySupply.OfferTestData.OFFER2;
import static ru.zagshak.buySupply.UserTestData.USER1;
import static ru.zagshak.buySupply.UserTestData.USER2;
import static ru.zagshak.buySupply.domain.AbstractBaseEntity.START_SEQ;

public class RequestTestData {

    public static final int REQUEST1_ID = START_SEQ + 7;
    public static final int REQUEST2_ID = REQUEST1_ID + 1;
    public static final int REQUEST3_ID = REQUEST1_ID + 2;

    public static final Request REQUEST1 = new Request(REQUEST1_ID, USER1, OFFER1, "Hi, I would like to buy your shit", false);
    public static final Request REQUEST2 = new Request(REQUEST2_ID, USER2, OFFER1,"Man, this offer is so cool, i even shitted myself when i saw the price", false);
    public static final Request REQUEST3 = new Request(REQUEST3_ID, USER1, OFFER2,"Ma fat boy wants some cakes and cocks", false);

    public static void assertMatch(Request actual, Request expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "offer", "requester");
    }

    public static void assertMatch(Iterable<Request> actual, Iterable<Request> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("offer", "requester").isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Request> actual, Request... expected) {
        assertMatch(actual, List.of(expected));
    }
}
