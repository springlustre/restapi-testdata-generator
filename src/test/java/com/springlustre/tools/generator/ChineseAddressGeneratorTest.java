package com.springlustre.tools.generator;

import static org.testng.Assert.assertNotNull;

import com.springlustre.tools.generator.generator.ChineseAddressGenerator;
import org.testng.annotations.Test;

public class ChineseAddressGeneratorTest {

    @Test
    public void testGenerate() {
        String generatedAddress = ChineseAddressGenerator.getInstance()
            .generate();
        System.err.println(generatedAddress);
        assertNotNull(generatedAddress);
    }

}
