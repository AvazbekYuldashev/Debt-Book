package api.debt.book.debtBook.service;

import api.debt.book.app.dto.AppResponse;
import api.debt.book.app.enums.AppLanguage;
import api.debt.book.app.service.ResourceBoundleService;
import api.debt.book.app.util.AppResponseUtil;
import api.debt.book.credit.dto.core.CreditResponseDTO;
import api.debt.book.credit.entity.CreditEntity;
import api.debt.book.debtBook.dto.DebtBookResponseDTO;
import api.debt.book.debtBook.entity.DebtBookEntity;
import api.debt.book.debtBook.mapper.DebtBookMapper;
import api.debt.book.debtBook.repository.DebtBookRepository;
import api.debt.book.exception.exps.AppBadException;
import api.debt.book.exception.exps.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DebtBookService {
    @Autowired
    private DebtBookMapper debtBookMapper;
    @Autowired
    private DebtBookRepository debtBookRepository;
    @Autowired
    private ResourceBoundleService boundleService;

    public DebtBookEntity save(DebtBookEntity entity, AppLanguage lang) {
        if (entity.getDebtorId().equals(entity.getCreditorId())){
            throw new AppBadException("Debtor and creditor cannot be the same user.");
        }
        return debtBookRepository.save(entity);
    }

    public DebtBookEntity findById(String id, AppLanguage lang){
        Optional<DebtBookEntity> optional = debtBookRepository.findByIdAndVisibleTrue(id);
        if (optional.isEmpty()){
            throw new ResourceNotFoundException(boundleService.getMessage("profile.not.found", lang) + ": " + id);
        }
        return optional.get();
    }

    public PageImpl<DebtBookResponseDTO> findAll(int page, int size, AppLanguage lang) {
        Sort sort = Sort.by("createdDate").descending();
        Pageable pageable = PageRequest.of(page, size, sort);
        Page<DebtBookEntity> pageObj = debtBookRepository.findAllPage(PageRequest.of(pageable.getPageNumber(), pageable.getPageSize()));
        List<DebtBookResponseDTO> response = pageObj.getContent().stream().map(debtBookMapper::toResponseDTO).collect(Collectors.toList());
        long total = pageObj.getTotalElements();
        return new PageImpl<>(response, pageable, total);
    }


    public Page<DebtBookResponseDTO> findByCreditorId(String id, int page, int size, AppLanguage lang) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<DebtBookEntity> pageObj = debtBookRepository.findAllByCreditorId(id, pageable);
        return mapToDTOPage(pageObj, pageable);
    }

    public Page<DebtBookResponseDTO> findByDebtorId(String id, int page, int size, AppLanguage lang) {
        PageRequest pageable = PageRequest.of(page, size, Sort.by("createdDate").descending());
        Page<DebtBookEntity> pageObj = debtBookRepository.findAllByDebtorId(id, pageable);
        return mapToDTOPage(pageObj, pageable);
    }

    private Page<DebtBookResponseDTO> mapToDTOPage(Page<DebtBookEntity> entities, Pageable pageable) {
        List<DebtBookResponseDTO> response = entities.getContent()
                .stream()
                .map(debtBookMapper::toResponseDTO)
                .collect(Collectors.toList());
        return new PageImpl<>(response, pageable, entities.getTotalElements());
    }


}
