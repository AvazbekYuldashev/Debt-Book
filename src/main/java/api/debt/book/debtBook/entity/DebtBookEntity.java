package api.debt.book.debtBook.entity;

import api.debt.book.creditor.entity.CreditorEntity;
import api.debt.book.debtor.entity.DebtorEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "debt_book")
public class DebtBookEntity {
    @Id
    @GeneratedValue(generator = "uuid-hibernate-generator")
    @GenericGenerator(name = "uuid-hibernate-generator", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "title")
    private String title;
    @Column(name = "description")
    private String description;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "visible")
    private Boolean visible;


    @Column(name = "amount")
    private Double amount;


    @Column(name = "debtor_id")
    private String debtorId;

    @Column(name = "creditor_id")
    private String creditorId;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "creditor_id", insertable = false, updatable = false)
    private CreditorEntity creditor;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "debtor_id", insertable = false, updatable = false)
    private DebtorEntity debtor;


    @PrePersist
    protected void onCreate() {
        this.createdDate = LocalDateTime.now();
        this.visible = Boolean.TRUE;
    }
}
