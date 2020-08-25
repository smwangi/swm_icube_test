package org.icube.ioutracker.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Map;

@Entity
@Table(name = "iou")
public class IOU {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull(message = "Please provide lender id")
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lender_id",nullable = false)
    private User lenderId;

    @NotNull(message = "Please provide borrower id")
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "borrower_id",nullable = false)
    private User borrowerId;

    @Column(name = "amount")
    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

//    public String getAl(){
//        return borrowerId.getName()+": "+amount;
//    }

    public String getBorrower() {
        return borrowerId.getName();
    }

    public void setBorrower(User borrower) {
        this.borrowerId = borrower;
    }

    public String getLender() {
        return lenderId.getName();
    }

    public void setLender(User lender) {
        this.lenderId = lender;
    }
}
