package Entities;

public enum Status {
    AVAILABLE("Вільна"),
    BORROWED("Зайнята");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
