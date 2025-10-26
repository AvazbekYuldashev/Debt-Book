package api.debt.book.debtor.dto.core;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class DebtorCreatedDTO {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String address;
    private String phone;
    private String email;
    private LocalDate birthDate;
    private String profileId;
}
