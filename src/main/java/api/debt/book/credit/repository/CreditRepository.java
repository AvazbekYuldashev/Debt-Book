package api.debt.book.credit.repository;

import api.debt.book.credit.entity.CreditEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditRepository extends JpaRepository<CreditEntity, String> {
    @Query("SELECT c FROM CreditEntity c WHERE c.id = :id AND c.visible = TRUE")
    Optional<CreditEntity> findByIdAndVisibleTrue(String id);
    @Query("SELECT c FROM CreditEntity c WHERE c.visible = TRUE")
    Page<CreditEntity> findAllPage(PageRequest of);
}
