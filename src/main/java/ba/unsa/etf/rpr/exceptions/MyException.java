package ba.unsa.etf.rpr.exceptions;

public class MyException extends Exception{

    public MyException(String message, Exception error){ super(message, error); }

    public MyException(String message){ super(message); }

}
