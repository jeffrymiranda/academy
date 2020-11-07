package com.jmiranda.academy.solid_principles.question_05;

import java.io.FileWriter;
import java.io.IOException;

public class Question {
    // What do you think is bad here?
    // Based on SOLID principle, what principle was broken?
    // What can be a better solution?

    public static void main(String[] args) {
        ScreenLogger screenLogger = new ScreenLogger();
        MathOperations mathOperations = new MathOperations(screenLogger);

        final double result = mathOperations.add(3, 5);
        mathOperations.subtract(result, 12);
    }
}

class MathOperations {

    private final ScreenLogger logger;

    public MathOperations(ScreenLogger logger) {
        this.logger = logger;
    }

    double add(double a, double b) {
        double result = a + b;
        this.logger.log(a + " + " + b + " = " + result);
        return result;
    }

    double subtract(double a, double b) {
        double result = a - b;
        this.logger.log(a + " - " + b + " = " + result);
        return result;
    }
}

class ScreenLogger {
    void log(String message) {
        System.out.println(message);
    }
}

class FileLogger {
    private final String filePath;

    public FileLogger(String filePath) {
        this.filePath = filePath;
    }

    void log(String message) {
        try (FileWriter fileWriter = new FileWriter(this.filePath, true)) {
            fileWriter.write(message);
            fileWriter.write(System.lineSeparator());
        } catch (IOException e) {
            // handle exception here
        }
    }
}
