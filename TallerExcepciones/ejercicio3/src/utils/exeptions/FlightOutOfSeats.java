package utils.exeptions;

public class FlightOutOfSeats extends Exception {
    public FlightOutOfSeats(String errorMsg) {
        super(errorMsg);
    }
}
