package com.jmiranda.academy.solid_principles.question_01.solution;

import java.util.List;
import java.util.NoSuchElementException;

/**
 *
 * Characteristics:
 * - Results in several smaller classes instead of one big class
 *
 */

public class Solution {

    // I created the WidgetFactory and moved createWidget there. It has one job and on WidgetFactory class, it creates widgets,
    // that's all the factory is responsible for, is creating widgets. So that's good, single responsibility.

    // Then, I created WidgetOperations, and moved changeWidgetName there. This could have multiple operations if we had other operations,
    // but right now it changes a widget's name. That's one operation, that's single responsibility. Even if had other operations,
    // if they were all widget operations, then that would be single responsibility. It's responsible for operations only of widgets.

    // On WidgetRepository. Let's take a look again at the methods. We have addWidget, which adds to the repository, we have getWidgetByName,
    // which gets a widget out of the repository. Finally, we have removeWidget which removes a widget from the repository.
    // So these are all, it's a single purpose. The single purpose is as a WidgetRepository. That's everything on widget repository. That's single purpose.

    // Okay, so in summary, the single responsibility principle is not so much about how your code logic is written, it's how it it's organised.
    // When writing classes, it's important to be aware of the code will be used, and what its purpose is going to be. Separating your classes into
    // single purpose classes follows the object oriented approach of having cohesive logic encapsulated in single objects. And it improves usability
    // as your single purpose classes can be rearranged and reused more easily.
}

/**
 * The Repository is responsible to provide and implement query methods. The single purpose is as a {@link WidgetRepository} class.
 */
class WidgetRepository {

    private List<Widget> widgetList;

    void addWidget(Widget widget) {
        this.widgetList.add(widget);
    }

    Widget getWidgetByName(String name) {
        return this.widgetList
                .stream()
                .filter(widget -> widget.getName().equalsIgnoreCase(name))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);
    }

    void removeWidget(Widget widget) {
        this.widgetList.remove(widget);
    }
}

/**
 * Classes that are responsible for creating resources, such as our widget resource, are called factories.
 * So I'm going to move the createWidget method out of the {@link WidgetRepository} class, and put it on in the new {@link WidgetFactory} class.
 */
class WidgetFactory {
    Widget createWidget(String name) {
        return new Widget(name);
    }
}

/**
 * And I have moved the changeWidgetName method out of {@link WidgetRepository} class, and into {@link WidgetOperations} class.
 * Because it's actually an operation on a widget. And if we had other operations, we could again, add them to this {@link WidgetOperations} class.
 */
class WidgetOperations {
    void changeWidgetName(Widget widget, String name) {
        widget.setName(name);
    }
}

/**
 * Simple POJO class.
 */
class Widget {

    private String name;

    Widget(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }
}
