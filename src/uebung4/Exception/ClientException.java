package uebung4.Exception;

public class ClientException extends Exception{
    private ClientExceptionType clientExceptionType;

    public ClientException(ClientExceptionType clientExceptionType, String message){
        super(message);
        this.clientExceptionType = clientExceptionType;
    }

    public enum ClientExceptionType{
        WrongFormatFirstname,
        WrongFormatLastname,
        IdAlreadyDefined,
        IdIsNotSet,
        DeletionNotPossibleWrongId,
        Failure
    }
}
