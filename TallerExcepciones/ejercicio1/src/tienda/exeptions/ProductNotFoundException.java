package tienda.exeptions;

public class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String errorMsg) {
        super(errorMsg);
    }
}
