package api.debt.book.credit.repository;

import api.debt.book.credit.entity.CreditEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditRepository extends JpaRepository<CreditEntity, String> {
    @Query("SELECT c FROM CreditEntity c WHERE c.id = :id AND c.visible = TRUE")
    Optional<CreditEntity> findByIdAndVisibleTrue(@Param("id")String id);
    @Query("SELECT c FROM CreditEntity c WHERE c.visible = TRUE")
    Page<CreditEntity> findAllPage(PageRequest of);

    @Query("SELECT c FROM CreditEntity c WHERE c.creditorId = :creditorId AND c.visible = TRUE")
    Page<CreditEntity> findByCreditorId(@Param("creditorId")String creditorId, PageRequest of);

    @Modifying
    @Transactional
    @Query("UPDATE CreditEntity c SET c.debtorCheck = TRUE WHERE c.id = :id")
    int updateDebtorCheck(@Param("id") String id);

    @Modifying
    @Transactional
    @Query("UPDATE CreditEntity c SET c.creditorCheck = TRUE WHERE c.id = :id")
    int updateCreditorCheck(String id);

    @Modifying
    @Transactional
    @Query("UPDATE CreditEntity c SET c.visible = FALSE WHERE c.id = :id")
    int deleteSoft(@Param("id") String id);
}
