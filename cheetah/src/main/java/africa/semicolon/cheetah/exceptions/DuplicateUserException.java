package africa.semicolon.cheetah.exceptions;

public class DuplicateUserException extends CheetahAppException{
    public DuplicateUserException(String message) {
        super(message);
    }
}
