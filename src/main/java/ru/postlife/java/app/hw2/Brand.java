package ru.postlife.java.app.hw2;

public enum Brand {
    LENOVO(4),
    ASUS(3),
    MSI(2),
    ACER(1),
    MACBOOK(0);

    private final int priority;

    Brand(int priority) {
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }
}
