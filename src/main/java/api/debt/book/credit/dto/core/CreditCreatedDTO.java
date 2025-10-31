package api.debt.book.credit.dto.core;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
public class CreditCreatedDTO {
    private BigDecimal amount;
    private String debtorId;
    private String creditorId;
    private String debtBookId;
}
