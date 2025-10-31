package api.debt.book.debt.dto.core;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class DebtCreatedDTO {
    private BigDecimal amount;
    private String debtorId;
    private String creditorId;
    private String debtBookId;
}
