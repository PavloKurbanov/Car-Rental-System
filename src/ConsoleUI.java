import Entities.Car;
import Service.CarService;
import Exception.CarAlreadyReservedException;
import Exception.EmptyArrayException;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private final CarService carService;
    private final Scanner scanner;

    public ConsoleUI(CarService carService) {
        this.carService = carService;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (true) {
            System.out.println("\n--- ОРЕНДА АВТО ---");
            System.out.println("1. Додати автомобіль");
            System.out.println("2. Взяти автомобіль (Оренда)");
            System.out.println("3. Повернути автомобіль");
            System.out.println("4. Видалити автомобіль");
            System.out.println("5. Показати всі автомобілі");
            System.out.println("0. Вихід");
            System.out.print("Ваш вибір: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> addCar();
                case "2" -> rentCar();
                case "3" -> returnCar();
                case "4" -> deleteCar();
                case "5" -> getAllCars();
                case "0" -> {
                    System.out.println("До побачення!");
                    return;
                }
                default -> System.out.println("⚠️ Невірний вибір! Спробуйте ще раз.");
            }
        }
    }

    private void addCar() {
        System.out.println("\n--- Додавання авто ---");
        try {
            int id = readInt("Введіть ID автомобіля: ");
            String model = readString("Введіть модель: ");
            double price = readDouble("Введіть ціну за годину: ");

            // Створюємо і зберігаємо
            Car newCar = new Car(id, model, price);
            carService.saveCar(newCar);

            System.out.println("✅ Автомобіль " + model + " успішно додано!");

        } catch (IllegalArgumentException e) {
            System.err.println("❌ Помилка валідації: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("❌ Невідома помилка: " + e.getMessage());
        }
    }

    private void rentCar() {
        System.out.println("\n--- Оренда авто ---");
        // Спочатку показуємо список, щоб користувач бачив ID
        getAllCars();

        int id = readInt("Введіть ID авто для оренди: ");

        try {
            // МИ НЕ ПЕРЕВІРЯЄМО ТУТ СТАТУС! Це робота сервісу.
            // Ми просто пробуємо орендувати.
            Car rentedCar = carService.rentCar(id);
            System.out.println("✅ Успішно! Ви орендували: " + rentedCar.getModel());

        } catch (CarAlreadyReservedException e) {
            System.err.println("⛔ Оренда неможлива: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("❌ Помилка: " + e.getMessage()); // Наприклад, невірний ID
        }
    }

    private void returnCar() {
        System.out.println("\n--- Повернення авто ---");
        int id = readInt("Введіть ID авто для повернення: ");

        try {
            Car returnedCar = carService.returnCar(id);
            System.out.println("✅ Автомобіль " + returnedCar.getModel() + " прийнято назад.");

        } catch (IllegalArgumentException e) {
            System.err.println("❌ Помилка: " + e.getMessage());
        }
    }

    private void deleteCar() {
        System.out.println("\n--- Видалення авто ---");
        int id = readInt("Введіть ID для видалення: ");

        try {
            carService.deleteCar(id);
            System.out.println("✅ Автомобіль видалено з бази.");
        } catch (IllegalArgumentException e) {
            System.err.println("❌ Помилка: " + e.getMessage());
        }
    }

    private void getAllCars() {
        try {
            List<Car> cars = carService.getCars();
            System.out.println("\nСписок автомобілів:");
            for (Car car : cars) {
                System.out.println(car);
            }
        } catch (EmptyArrayException e) {
            System.out.println("ℹ️ " + e.getMessage());
        }
    }

    // --- Допоміжні методи (щоб не дублювати код Scanner) ---

    private String readString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Це не число! Спробуйте ще раз.");
            }
        }
    }

    private double readDouble(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String input = scanner.nextLine();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("⚠️ Це не дробове число! Спробуйте ще раз (наприклад 50.5).");
            }
        }
    }
}