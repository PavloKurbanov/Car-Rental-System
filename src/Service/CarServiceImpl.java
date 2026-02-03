package Service;

import Entities.Car;
import Entities.Status;
import Exception.EmptyArrayException;
import Exception.CarAlreadyReservedException;

import java.util.ArrayList;
import java.util.List;

public class CarServiceImpl implements CarService {
    private List<Car> cars;

    public CarServiceImpl() {
        this.cars = new ArrayList<>();
    }

    @Override
    public Car saveCar(Car car) {
        if(car == null) {
            throw  new IllegalArgumentException("Введіть коректні дані");
        }
        cars.add(car);
        return car;
    }

    @Override
    public Car getCarById(int id) {
        Car car = getCar(id);
        if (car != null) return car;
        throw new  IllegalArgumentException("Не має автомобіля з ID: " + id);
    }

    @Override
    public List<Car> getCars() {
        if(cars.isEmpty()) {
            throw new EmptyArrayException("Масив пустий");
        }
        return cars;
    }

    @Override
    public Car rentCar(int id) {
        Car car = getCar(id);
        if(car.getStatus() == Status.BORROWED) {
                throw new CarAlreadyReservedException("Автомобіль " + car.getModel() + " зайнятий");
        }
        car.setStatus(Status.BORROWED);
        return car;
    }

    @Override
    public Car returnCar(int id) {
        Car car = getCar(id);
        if(car.getStatus() == Status.BORROWED) {
            car.setStatus(Status.AVAILABLE);
        }
        return car;
    }

    @Override
    public void deleteCar(int id) {
        Car car = getCar(id);
        cars.remove(car);
    }

    private Car getCar(int id) {
        for (Car car : cars) {
            if(car.getId() == id) {
                return car;
            }
        }
        return null;
    }
}
