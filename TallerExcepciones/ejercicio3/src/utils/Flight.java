package utils;
public class Flight {
    public String origin;
    public String destiny;
    public String code;
    public int capacity;
    public int seatsOcupied;


    public Flight(String code, String origin, String destiny, int capacity, int seatsOcupied) {
        this.code = code;
        this.origin = origin;
        this.destiny = destiny;
        this.capacity = capacity;
        this.seatsOcupied = seatsOcupied;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestiny() {
        return destiny;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getSeatsOcupied() {
        return seatsOcupied;
    }

    public String getCode() {
        return code;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

}  
