package com.cst8277.messages.dao;

import java.nio.ByteBuffer;
import java.util.UUID;

public class BytesConverter {
    public static UUID bytesToUuid(byte[] bytes) {
        ByteBuffer byteBuffer = ByteBuffer.wrap(bytes);
        long high = byteBuffer.getLong();
        long low = byteBuffer.getLong();
        return new UUID(high, low);
    }


}
