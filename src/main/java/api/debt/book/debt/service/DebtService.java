package api.debt.book.debt.service;

import api.debt.book.app.dto.AppResponse;
import api.debt.book.app.enums.AppLanguage;
import api.debt.book.app.service.ResourceBoundleService;
import api.debt.book.app.util.AppResponseUtil;
import api.debt.book.debt.dto.core.DebtResponseDTO;
import api.debt.book.debt.entity.DebtEntity;
import api.debt.book.debt.mapper.DebtMapper;
import api.debt.book.debt.repository.DebtRepository;
import api.debt.book.exception.exps.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DebtService {
    @Autowired
    private DebtMapper debtMapper;
    @Autowired
    private DebtRepository debtRepository;
    @Autowired
    private ResourceBoundleService boundleService;

    public DebtEntity save(DebtEntity entity){
        return debtRepository.save(entity);
    }

    public DebtEntity findById(String id, AppLanguage lang) {
        return debtRepository.findByIdAndVisibleTrue(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        boundleService.getMessage("profile.not.found", lang) + ": " + id
                ));
    }
    
    public Page<DebtResponseDTO> findAll(int page, int size, AppLanguage lang) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<DebtEntity> pageObj = debtRepository.findAllByVisibleTrue(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        return mapToDTOPage(pageObj, pageable);
    }

    public List<DebtResponseDTO> findAllByDebtBookId(String id, AppLanguage lang) {
        return debtRepository.findAllByDebtBookId(id).stream()
                .map(debtMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public Page<DebtResponseDTO> findAllByCreditorId(String id, int page, int size, AppLanguage lang) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<DebtEntity> pageObj = debtRepository.findAllByCreditorId(id, pageable);
        return mapToDTOPage(pageObj, pageable);
    }

    public Page<DebtResponseDTO> findAllByDebtorId(String id, int page, int size, AppLanguage lang) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<DebtEntity> pageObj = debtRepository.findAllByDebtorId(id, pageable);
        return mapToDTOPage(pageObj, pageable);
    }

    public AppResponse<String> updateCreditorCheck(String id, AppLanguage lang) {
        int effectedRow = debtRepository.updateCreditorCheck(id);
        return AppResponseUtil.chek(effectedRow > 0);
    }

    public AppResponse<String> updateDebtorCheck(String id, AppLanguage lang) {
        int effectedRow = debtRepository.updateDebtorCheck(id);
        return AppResponseUtil.chek(effectedRow > 0);
    }


    private Page<DebtResponseDTO> mapToDTOPage(Page<DebtEntity> entities, Pageable pageable) {
        List<DebtResponseDTO> response = entities.getContent()
                .stream()
                .map(debtMapper::toResponseDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(response, pageable, entities.getTotalElements());
    }

}
