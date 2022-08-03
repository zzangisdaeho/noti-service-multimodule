package com.example.noticore;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class ByteArrayConvertTest {

    @Test
    void testConvert(){
        String target = "1";

        byte[] bytes = target.getBytes(StandardCharsets.UTF_8);

        String convert = new String(bytes);

        Assertions.assertThat(target).isEqualTo(convert);
    }
}
