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
     * @throws IllegalArgumentException If the age is not between 1 and 50.
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
        return name; // Getter method.
    }
 
 
    /**
     * Sets the name of the pet.
     * @param name The new name for the pet. 
    */
    public void setName(String name) {
        this.name = name; // Setter method.
    }
 
 
    /**
     * Returns the age of the pet.
     * @return The age of the pet.
     * @throws IllegalArgumentException If the age is not valid.
    */
    public int getAge() throws IllegalArgumentException {
        if (age < 1 || age > 50) {
            throw new IllegalArgumentException("Age must be between 1 and 50.");
        }
        return age; // Getter method.
    }
 
 
    /**
     * Sets the age of the pet.
     * Validates the age before setting. 
     * @param age The age to set.
     * @throws IllegalArgumentException If the age is not between 1 and 50.
    */
    public void setAge(int age) throws IllegalArgumentException {
        if (age < 1 || age > 50) {
            throw new IllegalArgumentException("Age must be between 1 and 50.");
        }
        this.age = age; // Setter method.
    }
}
