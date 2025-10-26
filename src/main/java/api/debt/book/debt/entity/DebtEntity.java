package api.debt.book.debt.entity;


import api.debt.book.creditor.entity.CreditorEntity;
import api.debt.book.debtBook.entity.DebtBookEntity;
import api.debt.book.debtor.entity.DebtorEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "debt")
public class DebtEntity {
    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "amount")
    private Double amount;

    @Column(name = "visible")
    private Boolean visible;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "debtor_id")
    private String debtorId;

    @Column(name = "creditor_id")
    private String creditorId;

    @Column(name = "creditor_check")
    private Boolean creditorCheck;

    @Column(name = "debtor_check")
    private Boolean debtorCheck;

    @Column(name = "debt_book_id")
    private String debtBookId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creditor_id", insertable = false, updatable = false)
    private CreditorEntity creditor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "debtor_id", insertable = false, updatable = false)
    private DebtorEntity debtor;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "debt_book_id", insertable = false, updatable = false)
    private DebtBookEntity debtBook;

    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
        this.visible = Boolean.TRUE;
    }
}
