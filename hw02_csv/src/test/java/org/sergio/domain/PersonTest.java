package org.sergio.domain;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PersonTest {

    @DataProvider
    private Object[][] initDataTest(){
        Object[][] objects = {
                {"Sergey", "Matveev"},
                {"Misha", "Petrov"}
        };
        return objects;
    }

    @Test(dataProvider = "initDataTest")
    public void testGetName(String name, String surName) {
        Person p1 = new Person(name, surName);
        Person p2 = new Person(name, surName);
        assertEquals(p1.getName(), p2.getName());
    }

    @Test(dataProvider = "initDataTest")
    public void testGetSurName(String name, String surName) {
        Person p1 = new Person(name, surName);
        Person p2 = new Person(name, surName);
        assertEquals(p1.getSurName(), p2.getSurName());
    }

    @Test(dataProvider = "initDataTest")
    public void testTestEquals(String name, String surName) {
        Person p1 = new Person(name, surName);
        Person p2 = new Person(name, surName);
        assertEquals(p1, p2);
    }
}