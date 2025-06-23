package Exceptions;

public class DuplicateEmployeeException extends Exception{

    public DuplicateEmployeeException(String msg){
        super(msg);
    }
}
