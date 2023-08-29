package com.restaurante.reviews.service.order.util;

import com.restaurante.reviews.models.OrderStatus;

import java.util.HashMap;
import java.util.Map;

public final class VerifyOrderStatus {

    private final static Map<String, OrderStatus> statusOfOrder = new HashMap<>(){{
        put("ACTIVE",OrderStatus.ACTIVE);
        put("COMPLETE",OrderStatus.COMPLETE);
        put("CANCELED",OrderStatus.CANCELED);
    }};
    private static String status;

    public static boolean statusOfOrder(String verifyStatus) {
        status = verifyStatus;
        return statusOfOrder.containsKey(status);
    }

    public static OrderStatus statusVerified() {
        return statusOfOrder.get(status);
    }
}
