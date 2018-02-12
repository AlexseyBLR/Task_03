package analizator.AnalizatorException;

public class AnalizatorException extends Exception {

    public AnalizatorException(){}

    public AnalizatorException(Exception e){
        super(e);
    }

    public AnalizatorException(String cause , Exception e ){
        super(cause,e);
    }

    public AnalizatorException(String cause){
        super(cause);
    }
}
