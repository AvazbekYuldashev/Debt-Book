package api.debt.book.credit.dto.core;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreditResponseDTO {
    private String id;
    private Double amount;
    private Boolean visible;
    private LocalDateTime createdDate;
    private String debtorId;
    private String creditorId;
    private Boolean creditorCheck;
    private Boolean debtorCheck;
    private String debtBookId;
}
