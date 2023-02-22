package ba.unsa.etf.rpr.exceptions;

public class AppException extends Exception{

    public AppException(String msg, Exception error){ super(msg, error); }

    public AppException(String msg){ super(msg); }

}
