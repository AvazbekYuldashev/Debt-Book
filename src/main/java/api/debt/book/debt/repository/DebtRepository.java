package api.debt.book.debt.repository;

import api.debt.book.credit.entity.CreditEntity;
import api.debt.book.debt.entity.DebtEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DebtRepository extends JpaRepository<DebtEntity, String> {

    @Query("SELECT d FROM DebtEntity d WHERE d.id = :id AND d.visible = TRUE")
    Optional<DebtEntity> findByIdAndVisibleTrue(String id);


    @Query("SELECT d FROM DebtEntity d WHERE d.visible = TRUE")
    Page<DebtEntity> findAllPage(PageRequest of);


    @Query("SELECT d FROM DebtEntity d WHERE d.debtBookId = :id AND d.visible = TRUE ")
    List<DebtEntity> findAllByDebtBookId(@Param("id") String id);

    @Query("SELECT d FROM DebtEntity d WHERE d.debtorId = :id AND d.visible = TRUE ")
    Page<DebtEntity> findAllByDebtorId(@Param("id") String id, PageRequest of);

    @Query("SELECT d FROM DebtEntity d WHERE d.creditorId = :id AND d.visible = TRUE ")
    Page<DebtEntity> findAllByCreditorId(@Param("id") String id, PageRequest of);

    @Modifying
    @Transactional
    @Query("UPDATE DebtEntity d SET d.creditorCheck = TRUE WHERE d.id = :id")
    int updateCreditorCheck(String id);

    @Modifying
    @Transactional
    @Query("UPDATE DebtEntity d SET d.debtorCheck = TRUE WHERE d.id = :id")
    int updateDebtorCheck(String id);

    @Modifying
    @Transactional
    @Query("UPDATE DebtEntity d SET d.visible = FALSE WHERE d.id = :id")
    int deleteSoft(String id);

    @Query("SELECT d FROM DebtEntity d WHERE d.visible = TRUE")
    List<DebtEntity> findAllByVisibleTrue();

}
