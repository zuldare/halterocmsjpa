package com.jho.halterocmsjpa.enums;

/**
 * Gender type.
 */
public enum GenderType {

    MALE(0),
    FEMALE(1);

    private int value;

    GenderType(int value) {
        this.value = value;
    }

    /**
     * Return the gender type correspondent to arg value.
     *
     * @param value of transfer.
     * @return gender type.
     * @throws IllegalArgumentException if value is not in enum.
     */
    public static GenderType fromValue(int value) {
        for (GenderType genderType : values()) {
            if (genderType.getValue() == value) {
                return genderType;
            }
        }
        throw new IllegalArgumentException("Cannot create enum from " + value + " value!");
    }

    /**
     * Return gender type value.
     *
     * @return value
     */
    public int getValue() {
        return this.value;
    }
}
