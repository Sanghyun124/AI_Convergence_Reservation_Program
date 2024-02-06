package CSS.ReservationSystem.jwt;

import lombok.Getter;

@Getter
public enum ErrorCode {
    UNKNOWN_ERROR(4001, "There is no Token"),
    WRONG_TYPE_TOKEN(4002, "Wrong type Token"),
    EXPIRED_TOKEN(4003, "Expired Token"),
    UNSUPPORTED_TOKEN(4004, "Unsupported Token"),
    ACCESS_DENIED(4005, "Unauthenticated User");

    private int status;
    private String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
