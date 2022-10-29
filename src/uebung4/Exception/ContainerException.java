package uebung4.Exception;

public class ContainerException extends Exception {
    private ContainerExceptionType exceptionType;
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

