package ec.com.sofka.aggregate.account.events;

import ec.com.sofka.generics.domain.DomainEvent;

import java.math.BigDecimal;

public class AccountUpdated extends DomainEvent {

    private final String accountNumber;
    private final BigDecimal accountBalance;
    private final String name;

    public AccountUpdated(String accountNumber, BigDecimal accountBalance, String name) {
        super(EventsEnum.ACCOUNT_UPDATED.name());
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
        this.name = name;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public String getName() {
        return name;
    }


}
