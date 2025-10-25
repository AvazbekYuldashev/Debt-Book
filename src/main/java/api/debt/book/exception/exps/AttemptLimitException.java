package api.debt.book.exception.exps;

public class AttemptLimitException extends RuntimeException {
    public AttemptLimitException(String message) {
        super(message);
    }
}
