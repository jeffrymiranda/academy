package com.jmiranda.academy.solid_principles.question_01;

import java.util.List;
import java.util.NoSuchElementException;

public class Question {
    // What do you think is bad here?
    // Based on SOLID principle, what principle was broken?
    // What can be a better solution?
}

class WidgetRepository {

    private List<Widget> widgetList;

    void addWidget(Widget widget) {
        this.widgetList.add(widget);
    }

    void changeWidgetName(Widget widget, String name) {
        widget.setName(name);
    }

    Widget createWidget(String name) {
        return new Widget(name);
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