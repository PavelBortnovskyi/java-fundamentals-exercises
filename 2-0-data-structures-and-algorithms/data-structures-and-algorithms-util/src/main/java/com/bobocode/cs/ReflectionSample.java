package com.bobocode.cs;

import lombok.SneakyThrows;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class ReflectionSample {
    @SneakyThrows
    public static void main(String[] args) {
        //Get class and constructor
        Class<Randomizer> randomizerClass = Randomizer.class;
        Class<?> randomizerClassByName = Class.forName("com.bobocode.cs.Randomizer");
        Constructor<Randomizer> randomizerConstructor = randomizerClass.getConstructor();

        //Get declared class methods
        Method[] methods = randomizerClass.getDeclaredMethods();
        System.out.println(Arrays.toString(methods));

        //Get arguments of declared method
        Method randomizeMethod = methods[0];
        System.out.println(Arrays.toString(randomizeMethod.getParameters()));

        //Method invoke
        System.out.println(randomizeMethod.invoke(randomizerConstructor.newInstance(), List.of(1, 2, 3, 4, 5, 6)));

        //Get interface
        Class<?> randomizerInterface = Class.forName("com.bobocode.cs.RandomizerInterface");
        ClassLoader randomizerInterfaceClassLoader = randomizerInterface.getClassLoader();
        Class<?>[] interfacesToImplement = new Class<?>[]{randomizerInterface};

        InvocationHandler invocationHandler = ((proxy, method, args1) -> {
            if (method.getName().equals("randomize")) {
                List<?> list = (List<?>) args1[0];
                int index = ThreadLocalRandom.current().nextInt(list.size());
                return list.get(index);
            }
            throw new UnsupportedOperationException();
        });

        Object randomizerInstance = Proxy.newProxyInstance(randomizerInterfaceClassLoader, interfacesToImplement, invocationHandler);
        Method randomizerInterfaceMethod = randomizerInstance.getClass().getMethod("randomize", List.class);
        System.out.println(randomizerInterfaceMethod.invoke(randomizerInstance, List.of(11, 155, 2, 8, 99)));

        //Method invoke
        System.out.println(randomizeMethod.invoke(randomizerConstructor.newInstance(), List.of(1, 2, 3, 4, 5, 6)));
    }
}
