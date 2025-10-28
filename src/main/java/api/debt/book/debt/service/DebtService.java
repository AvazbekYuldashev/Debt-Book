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
import java.util.Optional;
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

    public DebtEntity findById(String id, AppLanguage lang){
        Optional<DebtEntity> optional = debtRepository.findByIdAndVisibleTrue(id);
        if (optional.isEmpty()){
            throw new ResourceNotFoundException(boundleService.getMessage("profile.not.found", lang) + ": " + id);
        }
        return optional.get();
    }

    public PageImpl<DebtResponseDTO> findAll(int page, int size, AppLanguage lang) {
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<DebtEntity> pageObj = debtRepository.findAllPage(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        List<DebtResponseDTO> response = pageObj.getContent().stream().map(debtMapper::toResponseDTO).collect(Collectors.toList());
        long total = pageObj.getTotalElements();
        return new PageImpl<>(response, pageable, total);
    }

    public AppResponse<String> updateCreditorCheck(String id, AppLanguage lang) {
        int effectedRow = debtRepository.updateCreditorCheck(id);
        return AppResponseUtil.chek(effectedRow > 0);
    }
}
