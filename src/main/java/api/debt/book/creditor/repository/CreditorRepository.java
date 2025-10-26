package api.debt.book.creditor.repository;

import api.debt.book.creditor.entity.CreditorEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditorRepository extends JpaRepository<CreditorEntity, String> {
    @Query("SELECT c FROM CreditorEntity c WHERE c.id = :id AND c.visible = TRUE")
    Optional<CreditorEntity> findByIdAndVisibleTrue(String id);
    
    @Query("SELECT c FROM CreditorEntity c WHERE c.visible = TRUE")
    Page<CreditorEntity> findAllPage(PageRequest of);

}
