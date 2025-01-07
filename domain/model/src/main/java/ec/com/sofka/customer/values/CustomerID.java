package ec.com.sofka.customer.values;

import ec.com.sofka.account.values.AccountId;
import ec.com.sofka.generics.utils.Identity;

public class CustomerID extends Identity {

    public CustomerID() {
        super();
    }

    private CustomerID(final String id) {
        super(id);
    }


    public static CustomerID of(final String id) {
        return new CustomerID(id);
    }

}
