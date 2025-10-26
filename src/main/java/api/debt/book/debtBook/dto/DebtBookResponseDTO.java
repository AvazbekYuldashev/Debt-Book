package api.debt.book.debtBook.dto;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DebtBookResponseDTO {
    private String id;
    private String title;
    private String description;
    private LocalDateTime createdDate;
    private Boolean visible;

    private Double amount;
    private String debtorId;
    private String creditorId;

}
