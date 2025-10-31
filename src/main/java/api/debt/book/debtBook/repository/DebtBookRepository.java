package api.debt.book.debtBook.repository;

import api.debt.book.debtBook.entity.DebtBookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DebtBookRepository extends JpaRepository<DebtBookEntity, String> {

    @Query("SELECT d FROM DebtBookEntity d WHERE d.id = :id AND d.visible = TRUE")
    Optional<DebtBookEntity> findByIdAndVisibleTrue(String id);

    @Query("SELECT d FROM DebtBookEntity d WHERE d.visible = TRUE")
    Page<DebtBookEntity> findAllPage(PageRequest of);

    @Query("SELECT d FROM DebtBookEntity d WHERE d.debtorId = :id AND d.visible = TRUE")
    Page<DebtBookEntity> findAllByDebtorId(@Param("id") String id, PageRequest pageable);

    @Query("SELECT d FROM DebtBookEntity d WHERE d.creditorId = :id AND d.visible = TRUE")
    Page<DebtBookEntity> findAllByCreditorId(@Param("id") String id, PageRequest pageable);
}
