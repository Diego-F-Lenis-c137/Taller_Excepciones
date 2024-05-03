package exceptions;

public class UserNotFound extends Exception {
   public UserNotFound(String errorMsg) {
        super(errorMsg);
   } 
}
