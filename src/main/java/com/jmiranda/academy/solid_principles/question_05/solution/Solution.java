package com.jmiranda.academy.solid_principles.question_05.solution;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Dependency inversion: The implementation of this principle relies on a level of abstraction in the form of a common interface
 * that's placed between the high level class and the low level class to break their dependency on one another.
 * Characteristics:
 * - Decouples high-level and low-level modules
 * - It makes code more reusable
 * - It relies on interfaces to decouple class dependencies
 * <p>
 * - What do you think is bad here?
 * If I go back to {@link MathOperations}, I want to point out that we're talking about coupling or dependencies here between the
 * high level and low level classes ({@link MathOperations}). This high level class has a dependency on a low level class {@link ScreenLogger}.
 * <p>
 * - Based on SOLID principle, what principle was broken?
 * Now we have two loggers. {@link MathOperations} is not dependent on {@link FileLogger}, but it is dependent on {@link ScreenLogger}.
 * What that means is if I want to use any other logger in this  {@link MathOperations}  class, I'm going to have to change this code of  {@link MathOperations}.
 * And that actually breaks another SOLID principle, which is the open/closed principle. I shouldn't have to change  {@link MathOperations}
 * simply to change the functionality of it's logger. I should be able to extend that without actually changing that code.
 * <p>
 * - What can be a better solution?
 * Add an interface in between the high level class {@link MathOperations} and the low level classes {@link FileLogger} and {@link ScreenLogger}.
 * This interface is a level of abstraction that will allow this class {@link MathOperations} to not be dependent on a specific implementation, but instead only rely on the interface.
 * And that's what dependency inversion principle is all about, it's decoupling those classes so that one high level class no longer depends on the implementations of a lower level class.
 * It only has an interface in between which decouples them. So in summary, the dependency inversion principle breaks the dependencies between high level and low level classes.
 * Low level classes are often restricted in their functionality and so they restrict the functionality of the high level classes that they're coupled to.
 * This principle breaks that coupling, freeing your high level classes to be more flexible and extendable.
 */
public class Solution {
    public static void main(String[] args) {
        // Login in the console
        ScreenLogger screenLogger = new ScreenLogger();

        MathOperations mathOperations = new MathOperations(screenLogger);
        mathOperations.add(3, 5);
        mathOperations.subtract(8, 12);

        // Login in the a file
        FileLogger fileLogger = new FileLogger("out/production/resources/logs/today.log");
        mathOperations = new MathOperations(fileLogger);
        mathOperations.add(3, 5);
        mathOperations.subtract(8, 12);
    }
}


class MathOperations {

    private final ILogger logger;

    public MathOperations(ILogger logger) {
        this.logger = logger;
    }

    double add(final double a, final double b) {
        double result = a + b;
        this.logger.log(a + " + " + b + " = " + result);
        return result;
    }

    double subtract(final double a, final double b) {
        double result = a - b;
        this.logger.log(a + " - " + b + " = " + result);
        return result;
    }
}

@FunctionalInterface
interface ILogger {
    void log(final String message);
}

class ScreenLogger implements ILogger {
    @Override
    public void log(String message) {
        System.out.println(message);
    }
}

class FileLogger implements ILogger {
    private final String filePath;

    FileLogger(String filePath) {
        this.filePath = filePath;
    }


    @Override
    public void log(final String message) {
        try (FileWriter fileWriter = new FileWriter(this.filePath, true)) {
            fileWriter.write(message);
            fileWriter.write(System.lineSeparator());
        } catch (IOException e) {
            // handle exception here
        }
    }
}
