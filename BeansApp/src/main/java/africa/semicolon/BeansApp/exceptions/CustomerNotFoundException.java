package africa.semicolon.BeansApp.exceptions;

public class CustomerNotFoundException extends BeansAppException{
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
