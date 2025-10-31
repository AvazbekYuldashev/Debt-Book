package api.debt.book.credit.dto.core;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class CreditResponseDTO {
    private String id;
    private BigDecimal amount;
    private Boolean visible;
    private LocalDateTime createdDate;
    private String debtorId;
    private String creditorId;
    private Boolean creditorCheck;
    private Boolean debtorCheck;
    private String debtBookId;
}
