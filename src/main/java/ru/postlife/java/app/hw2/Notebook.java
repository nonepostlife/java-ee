package ru.postlife.java.app.hw2;

import org.jetbrains.annotations.NotNull;

public class Notebook implements Comparable<Notebook> {

    private Brand brand;
    private int ram;
    private int price;

    public Notebook(Brand brand, int ram, int price) {
        this.brand = brand;
        this.ram = ram;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Notebook [" +
                "brand ='" + brand + '\'' +
                ", ram =" + ram +
                "GB, price =" + price +
                "$]";
    }

    @Override
    public int compareTo(@NotNull Notebook notebook) {
        if (this.price > notebook.price) {
            return 1;
        } else if (this.price == notebook.price) {
            if (this.ram > notebook.ram) {
                return 1;
            } else if (this.ram == notebook.ram) {
                if (this.brand.getPriority() > notebook.brand.getPriority()) {
                    return 1;
                } else if (this.brand.getPriority() == notebook.brand.getPriority()) {
                    return 0;
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }
}
