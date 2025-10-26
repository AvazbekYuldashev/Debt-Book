package api.debt.book.creditor.service;

import api.debt.book.app.enums.AppLanguage;
import api.debt.book.app.service.ResourceBoundleService;
import api.debt.book.creditor.dto.core.CreditorResponseDTO;
import api.debt.book.creditor.entity.CreditorEntity;
import api.debt.book.creditor.mapper.CreditorMapper;
import api.debt.book.creditor.repository.CreditorRepository;
import api.debt.book.exception.exps.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CreditorService {
    @Autowired
    private CreditorMapper creditorMapper;
    @Autowired
    private CreditorRepository creditorRepository;
    @Autowired
    private ResourceBoundleService boundleService;

    public CreditorEntity save(CreditorEntity entity){
        return creditorRepository.save(entity);
    }

    public CreditorEntity findById(String id, AppLanguage lang){
        Optional<CreditorEntity> optional = creditorRepository.findByIdAndVisibleTrue(id);
        if (optional.isEmpty()){
            throw new ResourceNotFoundException(boundleService.getMessage("profile.not.found", lang) + ": " + id);
        }
        return optional.get();
    }

    public PageImpl<CreditorResponseDTO> findAll(int page, int size, AppLanguage lang) {
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<CreditorEntity> pageObj = creditorRepository.findAllPage(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        List<CreditorResponseDTO> response = pageObj.getContent().stream().map(creditorMapper::toResponseDTO).collect(Collectors.toList());
        long total = pageObj.getTotalElements();
        return new PageImpl<>(response, pageable, total);
    }
}
