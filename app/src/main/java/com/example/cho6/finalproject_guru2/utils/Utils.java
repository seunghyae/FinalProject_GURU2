package com.example.cho6.finalproject_guru2.utils;

import java.util.UUID;

public class Utils {

    public static String getUserIdFromUUID(String userEmail) {
        long val = UUID.nameUUIDFromBytes(userEmail.getBytes()).getMostSignificantBits();
        return String.valueOf(val);
    }

}
