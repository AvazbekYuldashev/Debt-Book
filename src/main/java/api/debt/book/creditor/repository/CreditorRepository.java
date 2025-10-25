package api.debt.book.creditor.repository;

import api.debt.book.creditor.entity.CreditorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditorRepository extends JpaRepository<CreditorEntity, String> {
}
