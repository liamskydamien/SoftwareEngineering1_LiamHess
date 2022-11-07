package uebung4.Exception;
/**
 * @author Liam Hess
 * @version 1.0
 */
public class ContainerException extends Exception {
    private final ContainerExceptionType exceptionType;
    public ContainerException(ContainerExceptionType exceptionType,String message){
        super(message);
        this.exceptionType = exceptionType;
    }

    public ContainerExceptionType getExceptionType() {
        return exceptionType;
    }
    public enum ContainerExceptionType {
        EntityAlreadyAdded,
        EntityNotFound,
        EntityNotValid,
        RuntimeError
    }
}

