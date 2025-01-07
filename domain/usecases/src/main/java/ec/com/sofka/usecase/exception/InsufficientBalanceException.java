package ec.com.sofka.usecase.exception;

public class InsufficientBalanceException extends RuntimeException {
    public InsufficientBalanceException(String mensaje) {
        super(mensaje);
    }
}
