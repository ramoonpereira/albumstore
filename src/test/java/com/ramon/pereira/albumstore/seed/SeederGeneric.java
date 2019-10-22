package com.ramon.pereira.albumstore.seed;

import com.github.javafaker.Faker;

public abstract class SeederGeneric {

    private static final Faker faker = new Faker();

    protected static Faker faker(){
        return faker;
    }

}
