package api.debt.book.debt.repository;

import api.debt.book.debt.entity.DebtEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebtRepository extends JpaRepository<DebtEntity, String> {
}
