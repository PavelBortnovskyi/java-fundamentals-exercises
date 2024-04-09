package com.bobocode.cs;

import java.sql.SQLOutput;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RandomizerProxy extends Randomizer {

    @Override
    public <T> T randomize(List<T> list) {
        System.out.println("Calling randomize from RandomizerProxy");
        return super.randomize(list);
    }
}
