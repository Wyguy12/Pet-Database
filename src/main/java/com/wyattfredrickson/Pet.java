package com.wyattfredrickson;


/**
 * Pet class that will hold all the pet objects.
 */
public class Pet {
    // Intiliaze instance variables, set to private fields..
    private String name;
    private int age;
 
 
    /**
     * Constructor for the pet class.
     * @param name The name of the pet.
     * @param age The age of the pet.
     * @throws IllegalArgumentException If the age is not between 1 and 20.
    */
    public Pet(String name, int age) throws IllegalArgumentException {
        this.name = name; // Set name to name instance 
        setAge(age); // Call setAge to initialize age to handle age validation.
    }
 
 
    /**
     * Returns the name of the pet.
     * @return The name of the pet.
    */
    public String getName() {
        return name; 
    }
 
 
    /**
     * Sets the name of the pet.
     * @param name The new name for the pet. 
    */
    public void setName(String name) {
        this.name = name; 
    }
 
 
    /**
     * Returns the age of the pet.
     * @return The age of the pet.
    */
    public int getAge() {
        return age; 
    }
 
 
    /**
     * Sets the age of the pet.
     * Validates the age before setting. 
     * @param age The age to set.
     * @throws IllegalArgumentException If the age is not between 1 and 20.
    */
    public void setAge(int age) throws IllegalArgumentException {
        if (age < 1 || age > 20) {
            throw new IllegalArgumentException("Error: " + age + "is not a valid age.");
        }
        this.age = age; 
    }
}
