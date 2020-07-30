package org.sergio.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class PersonTest {
    private static Stream<Arguments> providerStringsData() {
        return Stream.of(
                Arguments.of("Sergey", "Matveev"),
                Arguments.of("Misha", "Petrov")
        );
    }


    @ParameterizedTest
    @MethodSource("providerStringsData")
    public void testGetName(String name, String surName) {
        Person p1 = new Person(name, surName);
        Person p2 = new Person(name, surName);
        assertEquals(p1.getName(), p2.getName());
    }

    @ParameterizedTest
    @MethodSource("providerStringsData")
    public void testGetSurName(String name, String surName) {
        Person p1 = new Person(name, surName);
        Person p2 = new Person(name, surName);
        assertEquals(p1.getSurName(), p2.getSurName());
    }

    @ParameterizedTest
    @MethodSource("providerStringsData")
    public void testTestEquals(String name, String surName) {
        Person p1 = new Person(name, surName);
        Person p2 = new Person(name, surName);
        assertEquals(p1, p2);
    }
}