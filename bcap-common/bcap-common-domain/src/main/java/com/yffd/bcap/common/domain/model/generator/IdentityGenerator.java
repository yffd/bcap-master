package com.yffd.bcap.common.domain.model.generator;

import java.util.UUID;

public class IdentityGenerator {

    public static String getId() {
        return getId32();
    }

    public static String getId32() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getId36() {
        return UUID.randomUUID().toString();
    }

}
