package api.debt.book.debt.dto.core;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class DebtResponseDTO {
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
