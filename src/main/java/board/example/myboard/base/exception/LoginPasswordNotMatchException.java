package board.example.myboard.base.exception;

import board.example.myboard.base.code.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginPasswordNotMatchException extends RuntimeException{
    private final ErrorCode errorCode;
}
