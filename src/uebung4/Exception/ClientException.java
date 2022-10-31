package uebung4.Exception;

public class ClientException extends Exception{
    private final ClientExceptionType clientExceptionType;

    public ClientException(ClientExceptionType clientExceptionType, String message){
        super(message);
        this.clientExceptionType = clientExceptionType;
    }

    public ClientExceptionType getClientExceptionType(){
        return clientExceptionType;
    }

    public enum ClientExceptionType{
        WrongFormatFirstname,
        WrongFormatLastname,
        IdAlreadyDefined,
        IdIsNotSet,
        DeletionNotPossibleWrongId,
        Failure,
        ErrorWhileLoading,
        ErrorWhileSaving
    }
}
