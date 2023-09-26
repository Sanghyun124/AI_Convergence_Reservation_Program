package CSS.ReservationSystem.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum Role {
    ROLE_STUDENT("ROLE_STUDENT"),
    ROLE_PROFESSOR("ROLE_PROFESSOR"),
    ROLE_ADMIN("ROLE_ADMIN");

    String role;

    Role(String role) {
        this.role = role;
    }

    @JsonValue
    public String value() {
        return role;
    }

    @JsonCreator
    public static Role parsing(String inputValue) {
        return Arrays.stream(Role.values()).filter(type -> type.value().equals(inputValue)).findFirst().orElse(null);
    }
}
