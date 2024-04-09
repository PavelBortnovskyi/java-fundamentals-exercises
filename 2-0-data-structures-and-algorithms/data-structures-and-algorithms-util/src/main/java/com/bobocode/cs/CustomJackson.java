package com.bobocode.cs;

import lombok.Data;
import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;

public class CustomJackson {

    @Data
    private static class User{
        String firstName;
        String lastName;
        String email;
    }

    public static void main(String[] args) {
        String jsn = "{\n" +
                "  \"firstName\": \"Barmaglot\",\n" +
                "  \"lastName\": \"Ambraxol\",\n" +
                "  \"email\": \"barmaglot@gmail.com\"\n" +
                "}";

        User user = jsonToObj(jsn, User.class);
        System.out.println(user);
    }

    @SneakyThrows
    private static <T> T jsonToObj(String json, Class<T> tClass) {
        T instance = tClass.getConstructor().newInstance();
        instance = tClass.cast(instance);
        Field[] fields = tClass.getDeclaredFields();
        for (Field field: fields) {
            String fieldName = field.getName();
            String[] lines = json.split("\n");
            String fieldValue = "";
            for (String line : lines) {
                if (line.contains(fieldName)) {
                    fieldValue = line.substring(line.indexOf(":") + 1);
                    fieldValue = fieldValue.trim();
                    fieldValue = fieldValue.replaceAll("[\",]","");
                    field.setAccessible(true);
                    field.set(instance, fieldValue);
                    break;
                }
            }
        }
        return instance;
    }
}
