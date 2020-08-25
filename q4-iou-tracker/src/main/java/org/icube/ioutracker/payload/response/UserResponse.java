package org.icube.ioutracker.payload.response;

import org.icube.ioutracker.models.IOU;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class UserResponse {

    private String name;
    private Map<String, BigDecimal> owed_by;
    private Map<String, BigDecimal> owes;
    private BigDecimal balance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, BigDecimal> getOwed_by() {
        return owed_by;
    }

    public void setOwed_by(Map<String, BigDecimal> owed_by) {
        this.owed_by = owed_by;
    }

    public void setOwes(Map<String, BigDecimal> owes) {
        this.owes = owes;
    }

    public Map<String, BigDecimal> getOwes() {
        return owes;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getBalance() {
        return balance;
    }
}
