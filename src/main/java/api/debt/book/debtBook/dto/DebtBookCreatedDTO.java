package api.debt.book.debtBook.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DebtBookCreatedDTO {
    private String title;
    private String description;

    private String debtorId;
    private String creditorId;
}
