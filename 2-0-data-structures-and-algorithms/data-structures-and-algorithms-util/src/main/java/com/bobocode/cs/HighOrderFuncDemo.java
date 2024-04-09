package com.bobocode.cs;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.function.IntUnaryOperator;
import java.util.function.Predicate;

public class HighOrderFuncDemo {
    public static void main(String[] args) {
        //1.High order function
        Function<Function<String, Integer>, Predicate<String>> highOrderFunc = strToIntFunc -> str -> strToIntFunc.apply(str) > 0;

        Predicate<String> positiveNumber = highOrderFunc.apply(Integer::parseInt);
        System.out.println(positiveNumber.test("123"));

        //2.Function composition
        // (f(x), g(x)) -> f(g(x))
        IntUnaryOperator square = x -> x * x;
        IntUnaryOperator increment = x -> x + 1;
        IntUnaryOperator incrementAndSquare = square.compose(increment);
        IntUnaryOperator squareAndIncrement = square.andThen(increment);
        System.out.println(incrementAndSquare.applyAsInt(3));
        System.out.println(squareAndIncrement.applyAsInt(3));

        Predicate<String> startsWithA = str -> str.startsWith("A");
        Predicate<String> longWord = str -> str.length() > 12;
        Predicate<String> longWordStartsWithA = startsWithA.and(longWord);
        System.out.println(longWordStartsWithA.test("Ambroxolopitek"));

        ArrayList<Integer> intList = new ArrayList<>();
    }
}
