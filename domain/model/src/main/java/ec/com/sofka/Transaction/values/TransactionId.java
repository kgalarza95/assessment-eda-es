package ec.com.sofka.Transaction.values;

import ec.com.sofka.generics.utils.Identity;

public class TransactionId extends Identity {

    public TransactionId(){super();}

    private TransactionId(String id){super(id);}

    public static TransactionId of(String id){ return new TransactionId(id);}
}
