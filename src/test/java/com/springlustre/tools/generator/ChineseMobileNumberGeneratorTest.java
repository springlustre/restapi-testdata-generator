package com.springlustre.tools.generator;

import static org.testng.AssertJUnit.assertNotNull;

import com.springlustre.tools.generator.generator.ChineseMobileNumberGenerator;
import org.testng.annotations.Test;

@Test
public class ChineseMobileNumberGeneratorTest {

    public void testGenerate() {
        String generatedMobileNum = ChineseMobileNumberGenerator.getInstance()
            .generate();
        assertNotNull(generatedMobileNum);
        System.err.println(generatedMobileNum);
    }

    public void testGgenerateFake() {
        String generatedMobileNum = ChineseMobileNumberGenerator.getInstance()
            .generateFake();
        assertNotNull(generatedMobileNum);
        System.err.println(generatedMobileNum);
    }
}
