package api.debt.book.creditor.dto.core;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CreditorCreatedDTO {
    private String firstName;
    private String lastName;
    private String patronymic;
    private String address;
    private String phone;
    private String email;
    private LocalDate birthDate;
    private String profileId;
}
