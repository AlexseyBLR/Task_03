package analizator.AnalizatorException;//правила именования пакетов СЛЕДУЕТ соблюдать, даже если ты пишешь библиотеку
// а верхний регистр в пакетах в третьем таске - это уже отсутствие чего?

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
