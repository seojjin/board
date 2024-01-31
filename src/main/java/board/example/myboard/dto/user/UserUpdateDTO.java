package board.example.myboard.dto.user;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdateDTO {

    private Integer userId;

    private String profileImage;

    private String password;

    private String introduction;

    private Integer goal;
}
