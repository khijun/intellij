package edu.du.sb1031.config;


import lombok.Getter;

@Getter
public enum ReviewStatus {
    NORMAL("N"),  // 짧은 코드 값
    DELETED("D"),
    PENDING("P");

    // DB에 저장할 코드 값 반환
    private final String code; // DB에 저장할 짧은 코드 값

    // 생성자
    ReviewStatus(String code) {
        this.code = code;
    }

    // 코드로 enum을 가져오는 메서드
    public static ReviewStatus fromCode(String code) {
        for (ReviewStatus status : ReviewStatus.values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown code: " + code);
    }
}
