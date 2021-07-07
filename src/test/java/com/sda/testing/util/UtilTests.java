package com.sda.testing.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UtilTests {

    @Test
    void testSumMethod_2_3() {
        final int result = CalculatingUtility.sum(1, 2);
        Assertions.assertEquals(3, result);
    }

    @Test
    void testSumMethod_throwsException(){
        Assertions.assertThrows(RuntimeException.class, () -> {
            final int result = CalculatingUtility.sum(1, 1);
        });
    }
}
