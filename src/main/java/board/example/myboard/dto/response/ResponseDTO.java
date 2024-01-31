package board.example.myboard.dto.response;

import board.example.myboard.base.code.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResponseDTO<T> {
    private Integer status;

    private String code;
    private String message;
    private T data;

    public ResponseDTO(ResponseCode responseCode, T data){
        this.status = responseCode.getStatus().value();
        this.code = responseCode.name();
        this.message=responseCode.getMessage();
        this.data=data;
    }
}
