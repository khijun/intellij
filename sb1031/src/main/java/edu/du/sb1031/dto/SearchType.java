package edu.du.sb1031.dto;

public enum SearchType {
    RECOMMEND("recommend"),
    MOST_SELL("mostSell"),
    LOW_PRICE("lowPrice"),
    HIGH_PRICE("highPrice"),
    NEWEST("newest"),
    MOST_REVIEWED("mostReviewed");

    private final String value;

    SearchType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    // 필요시 특정 동작을 추가할 수 있습니다.
    public static SearchType fromValue(String value) {
        for (SearchType type : values()) {
            if (type.getValue().equalsIgnoreCase(value)) {
                return type;
            }
        }
        return MOST_SELL;
    }
}
