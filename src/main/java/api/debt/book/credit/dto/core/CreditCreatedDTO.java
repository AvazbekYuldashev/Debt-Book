package api.debt.book.credit.dto.core;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreditCreatedDTO {
    private Double amount;
    private String debtorId;
    private String creditorId;
    private String debtBookId;
}
