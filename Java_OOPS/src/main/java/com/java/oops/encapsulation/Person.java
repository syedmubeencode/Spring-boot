package com.java.oops.encapsulation;

// Encapsulation
public class Person {
    private String name; // private field
    private int age;

    public Person(String name, int age) { // Constructor
        this.name = name;
        this.age = age;
    }

    public String getName() { // Public method to access private data
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

