package api.debt.book.debt.dto.core;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DebtCreatedDTO {
    private Double amount;
    private String debtorId;
    private String creditorId;
    private String debtBookId;
}
