package board.example.myboard.base.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ResponseCode {
    SUCCESS_REGISTER(HttpStatus.OK, "회원가입을 성공했습니다."),
    SUCCESS_LOGIN(HttpStatus.OK, "로그인을 성공했습니다."),
    SUCCESS_LOGOUT(HttpStatus.OK, "로그아웃을 성공했습니다."),

    SUCCESS_UNIQUE_ID(HttpStatus.OK, "중복된 아이디가 없습니다."),
    SUCCESS_FRIENDS_LIST(HttpStatus.OK, "그룹 사용자 목록 가져오기 성공"),

    SUCCESS_UPDATE_PROFILE(HttpStatus.OK, "프로필 수정에 성공했습니다."),
    SUCCESS_GET_PROFILE(HttpStatus.OK, "프로필을 성공적으로 가져왔습니다."),

    ;

    private final HttpStatus status;
    private final String message;
}
