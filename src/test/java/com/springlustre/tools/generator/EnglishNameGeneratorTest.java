package com.springlustre.tools.generator;

import com.springlustre.tools.generator.generator.EnglishNameGenerator;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;

public class EnglishNameGeneratorTest {

    @Test
    public void testGenerate() {
        String generatedName = EnglishNameGenerator.getInstance().generate();
        assertNotNull(generatedName);
        System.err.println(generatedName);
    }

}
