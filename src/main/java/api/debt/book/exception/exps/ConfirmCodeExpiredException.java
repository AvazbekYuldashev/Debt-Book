package api.debt.book.exception.exps;

public class ConfirmCodeExpiredException extends RuntimeException {
  public ConfirmCodeExpiredException(String message) {
    super(message);
  }
}
