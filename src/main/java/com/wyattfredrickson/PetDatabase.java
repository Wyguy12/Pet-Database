package com.wyattfredrickson;


// Import necessary libraries
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;


/* 


I certify, that this computer program submitted by me is all of my own work, Wyatt Fredrickson.

Wyatt Fredrickson

07-14-2024

CSC 222 

Assigment Description: 
Program manages a database of pets, allowing users to interact with it through a console based menu setup.


Sources: 

https://docs.oracle.com/javase/8/docs/api/java/io/FileWriter.html
https://www.geeksforgeeks.org/filewriter-class-in-java/
https://docs.oracle.com/javase/8/docs/api/java/util/InputMismatchException.html

*/ 


/**
 * PetDatabase class that will hold all the pet objects.
 * Display all pets, add a new pet, remove a pet and exit the program.
 * Each pet will have a name and age with a unique ID tied to each pet.
 */
public class PetDatabase {
    // Create an array called 'pets' that will hold all '5 pet' Objects.
    static final int PETCAPACITY = 5; // Declaring/initializing a variable to set the pet capacity entries to 5.                      
    static Pet[] pets = new Pet[PETCAPACITY]; // Declaring an array variable, creating an array, and assigning the reference of the array to the variable.

    static int petCount = 0; // Initialize the pet count to 0.
    static final String FILENAME = "pets.txt"; // Set the file name to pets.txt.
    static Scanner scanner = new Scanner(System.in); // Create a new scanner object to read user prompts.


    /**
     * Method to load the database from the file.
     * @throws IllegalArgumentException If there is an error reading the file.
     * @throws InputMismatchException If the user enters an invalid choice.
     * @throws IOException If there is an error reading the file.
     * @param args The arguments passed to the program.
     */
    public static void main(String[] args) {
        // TRY block to catch any exceptions.
        try {
            loadDatabase(); // Load the database at the start. 
            boolean running = true; // Set the running variable to true.

            while (running) { // While the running variable is true.
                try {
                    int userChoice = getUserChoice(); // Get the user choice and store in variable "userChoice"
                    switch (userChoice) {
                        case 1:
                            showAllPets(); // Display all pets.
                            break;
                        case 2:
                            addPets(); // Add a new pet.
                            break;
                        case 3:
                            removePet(); // Remove a pet.
                            break;
                        case 4:
                            saveDatabase(); // Exit program & save the database to the file.
                            running = false; // Set the running variable to false to exit the program.
                            break;
                        default:
                            System.out.println("Invalid choice. Please enter a number between 1 and 4."); // Handle invalid choices.
                            break;
                    }
                } catch (InputMismatchException ime) { // Catch the input mismatch exception.
                        System.out.println("Invalid input. Must enter number between 1 and 4."); // Another error handling event.
                        scanner.next(); // Clear the scanner buffer.
                } catch (Exception exception) { // Catch any other exceptions.
                    System.out.println("An error occurred: " + exception.getMessage()); // Another error handling event.
                }   
            }
        } catch (IOException ioException) { // Catch the input/output exception.
            System.out.println("Failed to save the database: " + ioException.getMessage()); // Inform the user there is an error message.
        } finally { // Finally block.
            System.out.println("Goodbye!"); 
        }
    }  

        /**
         * Method to load the database from the file.
         * @throws IOException If there is an error writing to the file.
         * @throws IllegalArgumentException If the pet ID is invalid.
         */
        static void loadDatabase() throws IOException {
            File file = new File(FILENAME); // Access the file via the String variable "fileName". Create new file "object".
            try (Scanner scanner = new Scanner(file)) { // Open a scanner to read the file.
                while (scanner.hasNextLine()) { // Loop until the end of the file.
                    String line = scanner.nextLine(); // Read the next line from the file.

                    // Pets.txt File format "Name, Age"
                    String[] parts = line.split(","); // Split the line into parts. 
                    String name = parts[0].trim(); // Extract the name and trim any whitespace away.
                    int age = Integer.parseInt(parts[1].trim()); // Extract the age and trim any whitespace away.

                    try {
                        Pet pet = new Pet(name, age); // Create a new pet object.
                        if (petCount >= PETCAPACITY) { // If the pet count is greater than or equal to the pet capacity.
                            System.out.println("The Database is full. Cannot add anymore pets.");
                            break; 
                        }
                        pets[petCount++] = pet; // Add the pet to the array.
                    } catch (IllegalArgumentException exception) { // Catch the illegal argument exception.
                        System.out.println("Invalid age provided for pet: " + name + " with age " + age); // Inform the user there is an error message.
                    }
                }
            } catch (IOException exception) { // Catch the input/output exception.
                System.out.println("An error occurred while reading the data file.");
            }
        }


        /**
         * Displays a menu and returns the user's choice.
         * @return The user's menu choice as an integer.
         */
        static int getUserChoice() {
            // Display the menu options.
            while (true) { // Loop until the user enters a valid choice.
                System.out.println("What would you like to do?");
                System.out.println("1) View all pets");
                System.out.println("2) Add new pets");
                System.out.println("3) Remove a pet");
                System.out.println("4) Exit program");
                System.out.print("Your choice: ");
                if (scanner.hasNextInt()) {
                    int choice = scanner.nextInt(); // Read the user's choice.
                    scanner.nextLine(); // Clear the scanner buffer.
         
                    if (choice >= 1 && choice <= 4) { // If the user choice is between 1 and 4.
                        return choice; // Return the user choice.
                    } else { // Else statement.
                        System.out.println("Invalid choice. Please enter a valid option between 1 and 4."); // Prompt the user they have selected incorrectly. 
                    }
            } else {
                System.out.println("Invalid input. Please enter a number between 1 and 4."); // Inform the user there is an error message.
                scanner.next(); // Clear the scanner buffer.
            }
        }
    }


        /**
         * Displays all pets in a formatted table.
         * @throws IllegalArgumentException If the age is not between 1 and 20.
         */
        static void showAllPets() throws IllegalArgumentException {
            // Print the table header.
            printTableHeader();
            int count = 0; // Initialize a variable in memory called 'count' to keep track of the number of pets displayed.
            for (int i = 0; i < petCount; i++) { // Next we iterate through the 'pets' array and print each pet's details.
                if (pets[i] != null) { // Check if the pet is not null.
                    printTableRow(i, pets[i].getName(), pets[i].getAge()); // Print the pet details.
                    count++; // Increment the count variable.
                }
            }
            printTableFooter(count); // Print the table footer, showing the number of rows displayed next.
        }


        /**
         * Allows the user to add pets until 'done' is entered.
         * @throws IllegalArgumentException If the age is not between 1 and 20.
         * @throws IndexOutOfBoundsException If the pet array is full.
         */
        static void addPets() {
            System.out.println("add pet (name, age): "); // Prompt the user to enter pet details.
            int addedCount = 0; // Initialize a variable to keep track of the number of pets added.

            while (true) { // Loop until the user types 'done'.
                String input = scanner.nextLine().trim(); // Used 'trim' method to remove leading/trailing spaces.
                if (input.equalsIgnoreCase("done")) { // If the user types 'done', the loop will break.
                    System.out.println(addedCount + " pets added."); // Inform the user the loop has ended.
                    break; // Break the loop.
                }
                
                try { // Try block to catch any exceptions.
                    String[] parts = input.split("\\s*,\\s*"); // Split the input by comma, ignoring spaces around it.
                    if (parts.length != 2) { // If the parts length is not equal to 2.
                        throw new IllegalArgumentException("Error: " + input + " is not a valid input."); // Throw an exception.
                    } 
                    String name = parts[0].trim(); // Extract the name and trim any whitespace away.
                    int age = Integer.parseInt(parts[1].trim()); // Extract the age and trim any whitespace away.

                    if (petCount < PETCAPACITY) { // If the pet count is less than the pet capacity then add new pet to database.
                        addPet(name, age); // Add the pet to the database.
                        System.out.println(name + " " + age + " added."); // Inform the user the pet has been added.
                        addedCount++; // Increment the added count variable.
                    } else {
                        System.out.println("Error: Database is full."); // Inform the user there is an error message.
                    }
                } catch (NumberFormatException exception) { // Catch the illegal argument and index out of bounds exceptions.
                    System.out.println("Error: age is not a valid number."); // Inform the user there is an error message.
                } catch (IllegalArgumentException exception) {
                    System.out.println(exception.getMessage());
                }
            }   
        }


        /**
         * Method to add a pet to the database.
         * @param name The name of the pet.
         * @param age The age of the pet.
         * @throws IndexOutOfBoundsException If the pet array is full.
         * @throws IllegalArgumentException If the age is not between 1 and 20.
         */
        static void addPet(String name, int age) { // Add a pet to the database.
            // Check if the database is full.
            if (petCount >= PETCAPACITY) { // If the pet count is greater than or equal to the pet capacity.
                throw new IndexOutOfBoundsException("Error: Database is full."); // Throw an exception.
            }
            // Otherwise, create a pet and store in variable 'newPet' with a new 'name' and 'age' and add it to the pets array.
            Pet newPet = new Pet(name, age); // Create a pet and store in variable 'newPet'.
            pets[petCount++] = newPet; // Add the pet to the array.
        }


        /**
         * Searches for pets by name.
         *  
         */
        static void searchPetsByName() {
            System.out.println("Enter the name to search for: "); // Prompt the user to enter the name.
            String name = scanner.nextLine().trim(); // Read the user input and trim any whitespace.

            printTableHeader(); // Print the table header.
            int count = 0; // Initialize a variable to keep track of the number of pets displayed.

            for (int i = 0; i < petCount; i++) { // Loop through the pets array.
                if (pets[i] != null && pets[i].getName().equalsIgnoreCase(name)) { // Check if the pet is not null and the name matches.
                    printTableRow(i, pets[i].getName(), pets[i].getAge()); // Print the pet details.
                    count++; // Increment the count variable.
                }
            }
            printTableFooter(count); // Print the table footer.
        }


        /**
         * Searches for pets by name.
         * 
         */
        static void searchPetsByAge() {
            System.out.println("Enter the age to search for: "); // Prompt the user to enter the age. 
            int age = scanner.nextInt(); // Read the user input and trim any whitespace.
            scanner.nextLine(); // Clear the scanner buffer.

            printTableHeader(); // Print the table header.
            int count = 0; // Initialize a variable to keep track of the number of pets displayed.

            for (int i = 0; i < petCount; i++) { // Loop through the pets array.
                if (pets[i] != null && pets[i].getAge() == age) { // Check if the pet is not null and the age matches.
                    printTableRow(i, pets[i].getName(), pets[i].getAge()); // Print the pet details.
                    count++; // Increment the count variable.
                }
            }
            printTableFooter(count); // Print the table footer.
        }


        /**
         * Method to update a pet in the database.
         */
        static void updatePet() {
            showAllPets(); // Display all pets.
            System.out.println("Enter the pet ID you want to update:"); // Prompt the user to enter the pet ID.
            int id = scanner.nextInt(); // Read the user input and trim any whitespace.
            scanner.nextLine(); // Clear the scanner buffer.

            if (id >= 0 && id < petCount && pets[id] != null) { // Check if the ID is valid and the pet exists.
                System.out.println("Enter the new name and new age separated by a comma: "); // Prompt the user to enter the new name and age.
                String input = scanner.nextLine().trim(); // Read the user input and trim any whitespace.

                try {
                    String[] parts = input.split("\\s*,\\s*"); // Split the input by comma, ignoring spaces around it.
                    if (parts.length != 2) { // If the parts length is not equal to 2.
                        throw new IllegalArgumentException("Input should be in the format 'name', 'age'."); // Throw an exception.
                    }
                    String name = parts[0].trim(); // Extract the name and trim any whitespace away.
                    int newAge = Integer.parseInt(parts[1].trim()); // Extract the age and trim any whitespace away.

                    pets[id].setName(name); // Set the new name.
                    pets[id].setAge(newAge); // Set the new age.
                    System.out.println("Pet updated successfully."); // Confirm the pet has been updated.
                } catch (Exception e) {
                    System.out.println("Invalid input. Please enter in the format 'name, age'. Example: Monster, 14"); // Inform the user there is an error message.
                }
            } else {
                System.out.println("Invalid pet ID."); // Inform the user there is an error message.
            }
        }

        
        /**
         * Method to remove a pet from the database.
         */
        static void removePet() {
            showAllPets(); // Display all pets.
            System.out.println("Enter the pet ID to remove: "); // Prompt the user to enter the pet ID.
            int id = scanner.nextInt(); // Read the user input and trim any whitespace.
            scanner.nextLine(); // Clear the scanner buffer.

            if (id >= 0 && id < petCount && pets[id] != null) { // Check if the ID is valid and the pet exists.
                System.out.println(pets[id].getName() + " " + pets[id].getAge() + " is removed."); // Inform the user the pet is removed.
                for (int i = id; i < petCount - 1; i++) { // Loop through the pets array.
                    pets[i] = pets[i + 1]; // Move the pets up one index.
                }
                pets[--petCount] = null; // Decrement the pet count and set the pet to null.
            } else { 
                System.out.println("Error: ID " + id + " does not exist."); // Throw an exception.
            }
        }


        /**
         * Prints the header of the pet table.
         * This method outputs the column titles for the pet table, which includes IDs, names, and ages
         * Formatted to align with the data rows that will follow in the output. 
         */
        static void printTableHeader() {
            // Print the header of the table.
            System.out.println("+------------------------+"); // Top border of the table created.
            System.out.printf("| %3s | %-10s | %3s |%n", "ID", "NAME", "AGE"); // Column headers created.
            System.out.println("+------------------------+"); // Bottom separator line of the table created.       
        }   


        /** 
         * Prints a row of the pet table for a given pet.
         * This method formats a single row of pet details including the ID, name, and age using 'printf' to align columns.
         * @param id The index of the pet in the array, used as the pet ID in the table.
         * @param name The name of the pet.
         * @param age The age of the pet. 
         */
        static void printTableRow(int id, String name, int age) {
            // Print the pet details in a formatted row.
            // Format and 'printf' the row with fixed width for each column to align with the header.
            // ID is given 3 spaces, name is given 10 spaces, and age is given 3 spaces.
            System.out.printf("| %3d | %-10s | %3d |%n", id, name, age);
            /*
             * '%3d': Formats the integer 'id' to a minimum width of 3 digits, right-aligned.
             * '%-10s': Formats the string 'name' to a minimum width of 10 characters, left-aligned.
             * '%3d': Formats the integer 'age' to a minimum width of 3 digits, right-aligned.
             */
        }


        /**
         * Prints the footer of the pet table, showing the number of rows.
         * This method outputs the total number of rows displayed in the table which represents the number of pets shown.
         * Uses 'printf' to maintain alignment and format consistency with the rest of the table.
         * @param rowCount The number of rows displayed.
         */
        static void printTableFooter(int rowCount) {
            // Print the footer of the table. 
            System.out.println("+------------------------+"); // Closes the table with the final border line.
            System.out.printf("%d rows in set.%n", rowCount); // Then outputs the number of rows displayed, With the same 'printf' format. 
        }


        /**
         * Saves the current pet data to the file.
         * @throws IOException If an error occurs during file writing.
         */
        static void saveDatabase() throws IOException {
            // Create a FileWriter object to write to "pets.txt".
            // Using try-with-resources, making sure the FileWriter is closed after use. 
            // Access the file via the String variable "fileName". Create new file "object".
            try (FileWriter writer = new FileWriter(FILENAME, false)) {
                for (int i = 0; i < petCount; i++) { // Loop through the pets array.
                    if (pets[i] != null) { // Check if the pet is not null.
                        // Create the string that represents the pet's details
                        String petDetails = pets[i].getName() + ", " + pets[i].getAge() + "\n"; // Add a new line character to separate each pet.
                        writer.write(petDetails); // Write the pet details to the file.
                    }
                }
                // The FileWriter is closed automatically from the try-with-resources.
                }
            }
        }