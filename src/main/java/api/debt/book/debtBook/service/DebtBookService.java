package api.debt.book.debtBook.service;

import api.debt.book.app.enums.AppLanguage;
import api.debt.book.app.service.ResourceBoundleService;
import api.debt.book.debtBook.dto.DebtBookResponseDTO;
import api.debt.book.debtBook.entity.DebtBookEntity;
import api.debt.book.debtBook.mapper.DebtBookMapper;
import api.debt.book.debtBook.repository.DebtBookRepository;
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

    public DebtBookEntity save(DebtBookEntity entity){
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


}
