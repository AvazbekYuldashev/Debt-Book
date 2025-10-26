package api.debt.book.debtor.service;

import api.debt.book.app.enums.AppLanguage;
import api.debt.book.app.service.ResourceBoundleService;
import api.debt.book.debtor.dto.core.DebtorResponseDTO;
import api.debt.book.debtor.entity.DebtorEntity;
import api.debt.book.debtor.mapper.DebtorMapper;
import api.debt.book.debtor.repository.DebtorRepository;
import api.debt.book.exception.exps.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DebtorService {
    @Autowired
    private DebtorMapper debtorMapper;
    @Autowired
    private DebtorRepository debtorRepository;
    @Autowired
    private ResourceBoundleService boundleService;

    public DebtorEntity save(DebtorEntity entity){
        return debtorRepository.save(entity);
    }

    public DebtorEntity findById(String id, AppLanguage lang){
        Optional<DebtorEntity> optional = debtorRepository.findByIdAndVisibleTrue(id);
        if (optional.isEmpty()){
            throw new ResourceNotFoundException(boundleService.getMessage("profile.not.found", lang) + ": " + id);
        }
        return optional.get();
    }

    public PageImpl<DebtorResponseDTO> findAll(int page, int size, AppLanguage lang) {
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<DebtorEntity> pageObj = debtorRepository.findAllPage(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        List<DebtorResponseDTO> response = pageObj.getContent().stream().map(debtorMapper::toResponseDTO).collect(Collectors.toList());
        long total = pageObj.getTotalElements();
        return new PageImpl<>(response, pageable, total);
    }
}
