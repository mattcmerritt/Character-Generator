# Character-Generator

## What is this?
This project is a simple console application that can generate clients and requests for a game where the player is an interior designer and must build out rooms requested by the clients. The parameters for creating characters and the room requests are stored in CSV files in the `Resources` directory, and the characters that are created will be printed out to a JSON file in the `out` directory. It was completed as the third project for CSC109.

## How can I use the program?
This application is intended to be run in a terminal, and it will require a Java compiler to run. Begin by navigating to the the `src` directory.

Once there, you will need to first compile the Java code to run it on your machine. To do this, run the following command:

```javac App.java```

This should create three Java class files in the `src` directory. With these created, run the program with the following command:

```java App <InputFileName> <OutputFileName>```

where <InputFileName> is the CSV file to read the parameters out of, and <OutputFileName> is the name of the JSON file will be created to store the character data.

A CSV file is provided already for demonstration purposes, and you can generate a character using that one by running the following command:

```java App GeneralData character```

This will create a file called `character.json` in the `out` directory.