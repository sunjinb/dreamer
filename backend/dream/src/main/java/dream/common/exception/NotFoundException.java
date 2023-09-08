package dream.common.exception;

public class NotFoundException extends RuntimeException {
    public static final String USER_NOT_FOUND = "존재하지 않는 회원입니다.";
    public static final String CARD_LIST_NOT_FOUND = "생성된 꿈 카드가 없습니다.";

    public NotFoundException(String message) {
        super(message);
    }
}