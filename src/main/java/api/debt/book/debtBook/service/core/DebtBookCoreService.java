package api.debt.book.debtBook.service.core;

import api.debt.book.app.enums.AppLanguage;
import api.debt.book.credit.dto.core.CreditResponseDTO;
import api.debt.book.credit.service.CreditService;
import api.debt.book.debt.dto.core.DebtResponseDTO;
import api.debt.book.debt.service.DebtService;
import api.debt.book.debtBook.dto.DebtBookCalculatorDTO;
import api.debt.book.debtBook.dto.DebtBookCreatedDTO;
import api.debt.book.debtBook.dto.DebtBookResponseDTO;
import api.debt.book.debtBook.entity.DebtBookEntity;
import api.debt.book.debtBook.mapper.DebtBookMapper;
import api.debt.book.debtBook.service.DebtBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class DebtBookCoreService extends DebtBookService {
    @Autowired
    private DebtBookMapper debtBookMapper;
    @Autowired
    private DebtService debtService;
    @Autowired
    private CreditService creditService;

    public DebtBookResponseDTO create(DebtBookCreatedDTO dto, AppLanguage lang) {
        DebtBookEntity entity = debtBookMapper.toCreatedEntity(dto);
        return debtBookMapper.toResponseDTO(save(entity, lang));
    }

    public DebtBookResponseDTO getById(String id, AppLanguage lang) {
        return debtBookMapper.toResponseDTO(findById(id, lang));
    }

    public Page<DebtBookResponseDTO> getAll(int page, int size, AppLanguage lang) {
        return findAll(page, size, lang);

    }

    public DebtBookCalculatorDTO calculator(String id, AppLanguage lang) {
        DebtBookEntity entity = findById(id, lang);

        List<CreditResponseDTO> creditResponseList =
                Optional.ofNullable(creditService.findAllByDebtBookId(id, lang))
                        .orElse(Collections.emptyList());

        List<DebtResponseDTO> debtResponseList =
                Optional.ofNullable(debtService.findAllByDebtBookId(id, lang))
                        .orElse(Collections.emptyList());

        // BigDecimal summalar
        BigDecimal totalCredit = creditResponseList.stream()
                .map(CreditResponseDTO::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal totalDebt = debtResponseList.stream()
                .map(DebtResponseDTO::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal balance = totalDebt.subtract(totalCredit);

        return DebtBookCalculatorDTO.builder()
                .id(id)
                .title(entity.getTitle())
                .description(entity.getDescription())
                .createdDate(entity.getCreatedDate())
                .visible(entity.getVisible())
                .creditorId(entity.getCreditorId())
                .debtorId(entity.getDebtorId())
                .credits(creditResponseList)
                .debts(debtResponseList)
                .amount(balance)
                .build();
    }

    public Page<DebtBookResponseDTO> findAllByCreditorId(String id, int page, int size, AppLanguage lang) {
        return findByCreditorId(id, page, size, lang);
    }

    public Page<DebtBookResponseDTO> findAllByDebtorId(String id, int page, int size, AppLanguage lang) {
        return findByDebtorId(id, page, size, lang);
    }

}
