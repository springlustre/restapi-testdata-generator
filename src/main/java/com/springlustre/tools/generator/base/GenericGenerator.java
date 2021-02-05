package com.springlustre.tools.generator.base;

import java.util.Date;
import java.util.Random;

/**
 * @author springlustre
 * 2020.12.17
 */
public abstract class GenericGenerator {
    public abstract String generate();

    private static Random random = null;

    protected Random getRandomInstance() {
        if (random == null) {
            random = new Random(new Date().getTime());
        }

        return random;
    }
}
