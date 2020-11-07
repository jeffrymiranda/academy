package com.jmiranda.academy.solid_principles.question_03;

import org.hibernate.cfg.NotYetImplementedException;

import java.util.ArrayList;
import java.util.List;

public class Question {

    public static void main(String[] args) {

        List<Shape> shapes = new ArrayList<>();
        shapes.add(new CircleShape(5));
        shapes.add(new SquareShape(7));
        shapes.add(new LineShape());

        shapes.forEach(shape -> System.out.println("Area of shape: " + shape.getArea()));

        // What do you think is bad here?
        // Based on SOLID principle, what principle was broken?
        // What can be a better solution?
    }
}

abstract class Shape {
    abstract double getArea();
}

class CircleShape extends Shape {

    private final double radius;

    CircleShape(double radius) {
        this.radius = radius;
    }

    @Override
    double getArea() {
        return Math.PI * Math.pow(this.radius, 2);
    }
}

class SquareShape extends Shape {

    private final double width;

    public SquareShape(double width) {
        this.width = width;
    }

    @Override
    double getArea() {
        return Math.pow(this.width, 2);
    }
}

class LineShape extends Shape {

    @Override
    double getArea() {
        throw new NotYetImplementedException();
    }
}