package org.icube.ioutracker.payload.response;

import java.math.BigDecimal;

public class IOUBorrowerResponse {

    private BigDecimal amount;
    private String borrower;

    public IOUBorrowerResponse(BigDecimal amount, String borrower){
        this.amount = amount;
        this.borrower = borrower;
    }

/*    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setBorrower(String borrower) {
        this.borrower = borrower;
    }

    public String getBorrower() {
        return borrower;
    }*/
}
