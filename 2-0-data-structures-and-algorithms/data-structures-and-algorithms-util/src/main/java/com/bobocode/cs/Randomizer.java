package com.bobocode.cs;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Randomizer {

    public <T> T randomize(List<T> list) {
       return list.get(ThreadLocalRandom.current().nextInt(list.size()));
    }
}
