package Service;

import Entities.Car;

import java.util.List;

public interface CarService {
    Car saveCar(Car car);

    Car getCarById(int id);

    List<Car> getCars();

    Car rentCar(int id);
    Car returnCar(int id);
    void deleteCar(int id);
}
