package com.jmiranda.academy.solid_principles.question_03.solution;

import java.util.ArrayList;
import java.util.List;

/**
 * Characteristics of the Liskov Substitution Principle are:
 * - This principle states that objects of the superclass shall be replaceable with objects of its subclasses.
 * - It relies on method signatures between a super-class and its sub-classes matching
 * - Sub-classes can be used in place of super-classes
 * - It makes code more reusable
 * <p>
 * - What do you think is bad here?
 * LineShape is not implementing getArea(), technically because the line doesn't have area.
 * <p>
 * - Based on SOLID principle, what principle was broken?
 * That's the problem with not following the Liskov Substitution Principle. If I go to {@link com.jmiranda.academy.solid_principles.question_03.Question} in the main program,
 * it's confusing because I can't treat these as shapes. I should be able to but I can't because one of them doesn't fully implement all the shape.
 * <p>
 * - What can be a better solution?
 * I can implement some new classes because really the problem here is that we have two types of shapes. We have two dimensional shapes and one dimensional shapes.
 * So what we need to do here is we need to extend our class hierarchy.
 * <p>
 * - Removes the abstract method to the {@link Shape} class.
 * - Creates a new abstract class called {@link OneDimensionalShape} and, extends {@link Shape} class, because it is a shape.
 * - Creates a new abstract class called {@link TwoDimensionalShape} and, extends {@link Shape} class, because it is a shape.
 * - Creates the abstract method getArea in the {@link TwoDimensionalShape}.
 * - Extends {@link OneDimensionalShape} in {@link LineShape}.
 * - Extends {@link TwoDimensionalShape} in {@link CircleShape} and {@link SquareShape}.
 * - Make the respective filter in the main program.
 */
public class Solution {

    public static void main(String[] args) {
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new CircleShape(5));
        shapes.add(new SquareShape(7));
        shapes.add(new LineShape());

        shapes.stream()
                .filter(shape -> shape instanceof TwoDimensionalShape)
                .forEach(shape -> System.out.println("Area of shape: " + ((TwoDimensionalShape) shape).getArea()));
    }
}

abstract class Shape {
}

abstract class OneDimensionalShape extends Shape {
}

abstract class TwoDimensionalShape extends Shape {
    abstract double getArea();
}

class CircleShape extends TwoDimensionalShape {

    private final double radius;

    CircleShape(double radius) {
        this.radius = radius;
    }

    @Override
    double getArea() {
        return Math.PI * Math.pow(this.radius, 2);
    }
}

class SquareShape extends TwoDimensionalShape {

    private final double width;

    public SquareShape(double width) {
        this.width = width;
    }

    @Override
    double getArea() {
        return Math.pow(this.width, 2);
    }
}

class LineShape extends OneDimensionalShape {

}
