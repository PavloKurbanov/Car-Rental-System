package Entities;

import java.util.Objects;

public class Car {
    private final int id;
    private final String model;
    private final double pricePerDay;
    private Status status;

    public Car(int id, String model, double pricePerDay) {
        this.id = id;
        this.model = model;
        this.pricePerDay = pricePerDay;
        this.status = Status.AVAILABLE;
    }

    public int getId() {
        return id;
    }

    public String getModel() {
        return model;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return id == car.id && Double.compare(pricePerDay, car.pricePerDay) == 0 && Objects.equals(model, car.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, model, pricePerDay);
    }

    @Override
    public String toString() {
        return String.format("ID: %-2d | Модель: %-5s | Ціна/год: %-5.2f $ | Статус: %-5s ", id, model, pricePerDay, status.getStatus());
    }
}
