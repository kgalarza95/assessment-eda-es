package ec.com.sofka.customer.values.objects;

import ec.com.sofka.account.values.objects.Balance;
import ec.com.sofka.generics.interfaces.IValueObject;

import java.math.BigDecimal;

public class Identification implements IValueObject<String> {

    private final String value;

    private Identification(final String value) {
        this.value = validate(value);
    }

    public static Identification of(final String value) {
        return new Identification(value);
    }

    private String validate(String value) {
        if (value == null || !value.matches("\\d+")) {
            throw new IllegalArgumentException("Identification must be numeric.");
        }
        return value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
