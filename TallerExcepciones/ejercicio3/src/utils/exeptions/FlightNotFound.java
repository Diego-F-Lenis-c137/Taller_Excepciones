package utils.exeptions;

public class FlightNotFound extends Exception {
    public FlightNotFound (String errorMsg) {
        super(errorMsg);
    }
}
