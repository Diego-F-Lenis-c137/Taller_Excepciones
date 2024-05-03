package exceptions;

public class NotEnoughFunds extends Exception{
    public NotEnoughFunds(String errorMsg) {
        super(errorMsg);
    }       
}