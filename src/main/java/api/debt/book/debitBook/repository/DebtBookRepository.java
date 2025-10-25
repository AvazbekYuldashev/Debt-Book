package api.debt.book.debitBook.repository;

import api.debt.book.debitBook.entity.DebtBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DebtBookRepository extends JpaRepository<DebtBookEntity, String> {
}
