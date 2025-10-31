package api.debt.book.credit.service;

import api.debt.book.app.dto.AppResponse;
import api.debt.book.app.enums.AppLanguage;
import api.debt.book.app.service.ResourceBoundleService;
import api.debt.book.app.util.AppResponseUtil;
import api.debt.book.credit.dto.core.CreditResponseDTO;
import api.debt.book.credit.entity.CreditEntity;
import api.debt.book.credit.mapper.CreditMapper;
import api.debt.book.credit.repository.CreditRepository;
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

    public CreditEntity findById(String id, AppLanguage lang){
        Optional<CreditEntity> optional = creditRepository.findByIdAndVisibleTrue(id);
        if (optional.isEmpty()){
            throw new ResourceNotFoundException(boundleService.getMessage("profile.not.found", lang) + ": " + id);
        }
        return optional.get();
    }

    public PageImpl<CreditResponseDTO> findAll(int page, int size, AppLanguage lang) {
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<CreditEntity> pageObj = creditRepository.findAllPage(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        List<CreditResponseDTO> response = pageObj.getContent().stream().map(creditMapper::toResponseDTO).collect(Collectors.toList());
        long total = pageObj.getTotalElements();
        return new PageImpl<>(response, pageable, total);
    }

    public PageImpl<CreditResponseDTO> findByCreditorId(String id, int page, int size, AppLanguage lang) {
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<CreditEntity> pageObj = creditRepository.findByCreditorId(id, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        List<CreditResponseDTO> response = pageObj.getContent().stream().map(creditMapper::toResponseDTO).collect(Collectors.toList());
        long total = pageObj.getTotalElements();
        return new PageImpl<>(response, pageable, total);
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

    public List<CreditResponseDTO> findAllByDebtBookId(String id, AppLanguage lang) {
        List<CreditEntity> creditEntities = creditRepository.findAllByDebtBookId(id);
        List<CreditResponseDTO> response = new ArrayList<>();
        for(CreditEntity entity : creditEntities){
            response.add(creditMapper.toResponseDTO(entity));
        }
        return response;
    }

    public Page<CreditResponseDTO> findAllByCreditorId(String id, int page, int size, AppLanguage lang) {
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<CreditEntity> pageObj = creditRepository.findAllByCreditorId(id, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        List<CreditResponseDTO> response = pageObj.getContent().stream().map(creditMapper::toResponseDTO).collect(Collectors.toList());
        long total = pageObj.getTotalElements();
        return new PageImpl<>(response, pageable, total);
    }

    public Page<CreditResponseDTO> findAllByDebtorId(String id, int page, int size, AppLanguage lang) {
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<CreditEntity> pageObj = creditRepository.findAllByDebtorId(id, PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        List<CreditResponseDTO> response = pageObj.getContent().stream().map(creditMapper::toResponseDTO).collect(Collectors.toList());
        long total = pageObj.getTotalElements();
        return new PageImpl<>(response, pageable, total);
    }
}
