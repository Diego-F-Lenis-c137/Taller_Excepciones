package utils;

public class Booking {
    public String passengerName;
    public String fligthCode;
    public String getPassengerName() {
        return passengerName;
    }
    public String getFligthCode() {
        return fligthCode;
    }
    public Booking(String fligthCode, String passengerName) {
        this.fligthCode = fligthCode;
        this.passengerName = passengerName;
    }

}
