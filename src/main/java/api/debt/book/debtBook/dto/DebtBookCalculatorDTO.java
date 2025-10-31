package api.debt.book.debtBook.dto;

import api.debt.book.credit.dto.core.CreditResponseDTO;
import api.debt.book.debt.dto.core.DebtResponseDTO;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DebtBookCalculatorDTO {
    private String id;
    private String title;
    private String description;
    private LocalDateTime createdDate;
    private Boolean visible;

    private String debtorId;
    private String creditorId;

    private List<DebtResponseDTO> debts;
    private List<CreditResponseDTO> credits;

    private BigDecimal amount;
}
