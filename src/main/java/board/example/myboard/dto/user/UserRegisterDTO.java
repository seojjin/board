package board.example.myboard.dto.user;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRegisterDTO {

    private String profileImage;

    @NotNull
    private String userId;

    @NotNull
    private String loginId;

    @NotNull
    private String password;

    @NotNull
    private String introduction;

    @NotNull
    private Integer goal;
}
