package org.icube.ioutracker.payload.requests;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class IOURequest {

    @NotNull(message = "Please provide borrower id")
    private long borrowerId;

    @NotNull(message = "Please provide lender id")
    private long lenderId;

    @DecimalMin(value = "1", message = "Amount must be greater than 0.")
    private BigDecimal amount;

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setBorrowerId(long borrowerId) {
        this.borrowerId = borrowerId;
    }

    public long getBorrowerId() {
        return borrowerId;
    }

    public void setLenderId(long lenderId) {
        this.lenderId = lenderId;
    }

    public long getLenderId() {
        return lenderId;
    }
}
