import Entities.Car;
import Service.CarService;
import Service.CarServiceImpl;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        CarService carService = new CarServiceImpl();
        ConsoleUI consoleUI   = new ConsoleUI(carService);
        consoleUI.run();
    }
}