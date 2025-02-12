package com.testingacademy.utils;

import com.github.javafaker.Faker;

public class FakerUtil {

    static Faker faker ;

    public static String getUsername (){

        faker = new Faker();
        String name = faker.name().firstName();
        return name;
    }
}
