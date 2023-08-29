package atl.bootcamp.e9.savorspot.exceptions;

public class AuthenticationErrorException extends RuntimeException{
    public AuthenticationErrorException(String message) {
        super(message);
    }
}
