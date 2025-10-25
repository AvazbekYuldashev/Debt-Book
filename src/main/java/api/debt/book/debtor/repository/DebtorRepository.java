package api.debt.book.debtor.repository;

import api.debt.book.debtor.entity.DebtorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebtorRepository extends JpaRepository<DebtorEntity, String> {
}
