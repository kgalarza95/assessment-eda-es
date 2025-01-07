package ec.com.sofka.aggregate.account;

import ec.com.sofka.account.Account;
import ec.com.sofka.aggregate.account.events.AccountCreated;
import ec.com.sofka.aggregate.account.events.AccountUpdated;
import ec.com.sofka.aggregate.account.values.CustomerId;
import ec.com.sofka.generics.domain.DomainEvent;
import ec.com.sofka.generics.utils.AggregateRoot;

import java.math.BigDecimal;
import java.util.List;

//2. Creation of the aggregate class - The communication between the entities and the external world.
public class AccountAggregate extends AggregateRoot<CustomerId> {
    //5. Add the Account to the aggregate: Can't be final bc the aggregate is mutable by EventDomains
    private Account account;

    //To create the Aggregate the first time, ofc have to set the id as well.
    public AccountAggregate() {
        super(new CustomerId());
        //Add the handler to the aggregate
        setSubscription(new AccountAggregateHandler(this));
    }

    //To rebuild the aggregate
    private AccountAggregate(final String id) {
        super(CustomerId.of(id));
        //Add the handler to the aggregate
        setSubscription(new AccountAggregateHandler(this));
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


    //Remember that User as Aggregate is the open door to interact with the entities
    public void createAccount(BigDecimal accountBalance, String accountNumber, String name ) {
        //Add the event to the aggregate
        addEvent(new AccountCreated(accountNumber,accountBalance, name)).apply();

    }

    public void updateAccount(BigDecimal accountBalance, String accountNumber, String name ) {
        //Add the event to the aggregate
        addEvent(new AccountUpdated(accountNumber,accountBalance, name)).apply();

    }

    //To rebuild the aggregate
    public static AccountAggregate from(final String id, List<DomainEvent> events) {
        AccountAggregate customer = new AccountAggregate(id);
        events.forEach((event) -> customer.addEvent(event).apply());
        return customer;
    }


}
