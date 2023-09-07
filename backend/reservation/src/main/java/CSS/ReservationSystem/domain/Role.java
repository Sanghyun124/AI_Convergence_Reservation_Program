package CSS.ReservationSystem.domain;

public enum Role {
    ROLE_STUDENT("ROLE_STUDENT"),
    ROLE_PROFESSOR("ROLE_PROFESSOR"),
    ROLE_ADMIN("ROLE_ADMIN");

    String role;

    Role(String role) {
        this.role = role;
    }

    public String value() {
        return role;
    }
}
