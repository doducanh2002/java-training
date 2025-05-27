package org.aibles.enumiration;

public enum RoleEnum {
    AUTHENTICATED("AUTHENTICATED"),
    PERMIT_ALL("PERMIT_ALL");
    private final String value;

    RoleEnum(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
