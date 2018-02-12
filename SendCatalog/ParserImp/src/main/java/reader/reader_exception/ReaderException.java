package reader.reader_exception;

public class ReaderException extends Exception {

    public ReaderException(){}

    public ReaderException(Exception e){
        super(e);
    }

    public ReaderException(String cause , Exception e ){
        super(cause,e);
    }

    public ReaderException(String cause){
        super(cause);
    }

}
