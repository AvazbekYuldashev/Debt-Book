package api.debt.book.debtor.repository;

import api.debt.book.debtor.entity.DebtorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DebtorRepository extends JpaRepository<DebtorEntity, String> {

    @Query("SELECT d FROM DebtorEntity d WHERE d.id = :id AND d.visible = TRUE")
    Optional<DebtorEntity> findByIdAndVisibleTrue(String id);

    @Query("SELECT d FROM DebtorEntity d WHERE d.visible = TRUE")
    Page<DebtorEntity> findAllPage(PageRequest of);
}
