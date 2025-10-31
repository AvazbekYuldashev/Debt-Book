package api.debt.book.credit.service;

import api.debt.book.app.dto.AppResponse;
import api.debt.book.app.enums.AppLanguage;
import api.debt.book.app.service.ResourceBoundleService;
import api.debt.book.app.util.AppResponseUtil;
import api.debt.book.credit.dto.core.CreditResponseDTO;
import api.debt.book.credit.entity.CreditEntity;
import api.debt.book.credit.mapper.CreditMapper;
import api.debt.book.credit.repository.CreditRepository;
import api.debt.book.debt.dto.core.DebtResponseDTO;
import api.debt.book.debt.entity.DebtEntity;
import api.debt.book.exception.exps.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CreditService {
    @Autowired
    private CreditMapper creditMapper;
    @Autowired
    private CreditRepository creditRepository;
    @Autowired
    private ResourceBoundleService boundleService;

    public CreditEntity save(CreditEntity entity){
        return creditRepository.save(entity);
    }

    public CreditEntity findById(String id, AppLanguage lang) {
        return creditRepository.findByIdAndVisibleTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        boundleService.getMessage("profile.not.found", lang) + ": " + id
                ));
    }

    public Page<CreditResponseDTO> findAll(int page, int size, AppLanguage lang) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<CreditEntity> pageObj = creditRepository.findAllPage(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        return mapToDTOPage(pageObj, pageable);
    }

    public List<CreditResponseDTO> findAllByDebtBookId(String id, AppLanguage lang) {
        return creditRepository.findAllByDebtBookId(id).stream()
                .map(creditMapper::toResponseDTO)
                .collect(Collectors.toList());
    }


    public Page<CreditResponseDTO> findAllByCreditorId(String id, int page, int size, AppLanguage lang) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<CreditEntity> pageObj = creditRepository.findAllByCreditorId(id, pageable);
        return mapToDTOPage(pageObj, pageable);
    }

    public Page<CreditResponseDTO> findAllByDebtorId(String id, int page, int size, AppLanguage lang) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<CreditEntity> pageObj = creditRepository.findAllByDebtorId(id, pageable);
        return mapToDTOPage(pageObj, pageable);
    }

    public AppResponse<String> updateCreditorCheck(String id, AppLanguage lang) {
        int effectedRow = creditRepository.updateCreditorCheck(id);
        return AppResponseUtil.chek(effectedRow > 0);
    }

    public AppResponse<String> updateDebtorCheck(String id, AppLanguage lang) {
        int effectedRow = creditRepository.updateDebtorCheck(id);
        return AppResponseUtil.chek(effectedRow > 0);
    }

    public AppResponse<String> deleteById(String id, AppLanguage lang) {
        int effectedRow = creditRepository.deleteSoft(id);
        return AppResponseUtil.chek(effectedRow > 0);
    }

    private Page<CreditResponseDTO> mapToDTOPage(Page<CreditEntity> entities, Pageable pageable) {
        List<CreditResponseDTO> response = entities.getContent()
                .stream()
                .map(creditMapper::toResponseDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(response, pageable, entities.getTotalElements());
    }
}
