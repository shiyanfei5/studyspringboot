package jdbc_final.exception;


/**
 * 定义一个Dao异常，用于将编译异常转换为运行时异常
 */
public class DaoException extends RuntimeException {

    public DaoException(){

    }

    public DaoException(String message){

    }

    public DaoException(Throwable cause){
        super(cause);
    }

    public DaoException(String message, Throwable cause){
        super(message,cause);
    }

}
