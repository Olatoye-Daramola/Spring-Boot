package africa.semicolon.cheetah.exceptions;

public class UserNotFoundException extends CheetahAppException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
