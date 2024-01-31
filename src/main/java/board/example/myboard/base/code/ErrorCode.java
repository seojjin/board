package board.example.myboard.base.code;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    /*
     * 400 BAD_REQUEST: 잘못된 요청
     */
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    DUPLICATE_ID_REQUEST(HttpStatus.BAD_REQUEST, "중복된 아이디가 있습니다."),

    /*
     * 404 NOT_FOUND: 리소스를 찾을 수 없음
     */
    USERID_NOT_FOUND(HttpStatus.NOT_FOUND, "아이디가 존재하지 않습니다."),
    PASSWORD_NOT_MATCH(HttpStatus.NOT_FOUND, "비밀번호가 올바르지 않습니다."),

    PROFILE_NOT_FOUND(HttpStatus.NOT_FOUND, "프로필 정보를 찾을 수 없습니다."),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "아이디에 해당하는 유저를 찾을 수 없습니다."),

    ;
    private final HttpStatus status;
    private final String message;
}
