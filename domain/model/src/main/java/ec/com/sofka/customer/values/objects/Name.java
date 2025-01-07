package ec.com.sofka.customer.values.objects;

import ec.com.sofka.generics.interfaces.IValueObject;

public class Name implements IValueObject<String> {
    private final String value;

    private Name(final String value) {
        this.value = validate(value);
    }

    public static Name of(final String value) {
        return new Name(value);
    }

    @Override
    public String getValue() {
        return this.value;
    }

    private String validate(final String value) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException("The name can't be null or empty");
        }

        if (!value.matches("^[a-zA-Z\\s]+$")) {
            throw new IllegalArgumentException("The name must contain only letters and spaces");
        }

        return value.trim();
    }
}
