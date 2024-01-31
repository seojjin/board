package board.example.myboard.controller;

import board.example.myboard.base.projection.GetUser;
import board.example.myboard.base.code.ResponseCode;
import board.example.myboard.dto.response.ResponseDTO;
import board.example.myboard.dto.user.UserLoginDTO;
import board.example.myboard.dto.user.UserRegisterDTO;
import board.example.myboard.dto.user.UserUpdateDTO;
import board.example.myboard.entity.UserEntity;
import board.example.myboard.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(
            @RequestBody UserRegisterDTO userRegisterDTO
            ) throws IOException
    {

        if(userRegisterDTO.getProfileImage()==null){
            userRegisterDTO.setProfileImage("");
        }

        GetUser res = userService.register(userRegisterDTO);

        return ResponseEntity
                .status(ResponseCode.SUCCESS_REGISTER.getStatus().value())
                .body(new ResponseDTO(ResponseCode.SUCCESS_REGISTER, res));
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseDTO> login(@RequestBody UserLoginDTO userLoginDTO){
        GetUser res = userService.login(userLoginDTO);

        return ResponseEntity
                .status(ResponseCode.SUCCESS_LOGIN.getStatus().value())
                .body(new ResponseDTO(ResponseCode.SUCCESS_LOGIN, res));
    }

    @GetMapping("/logout")
    public ResponseEntity<ResponseDTO> logout(@RequestParam("loginId") String loginId ){
        userService.logout(loginId);

        return ResponseEntity
                .status(ResponseCode.SUCCESS_LOGOUT.getStatus().value())
                .body(new ResponseDTO(ResponseCode.SUCCESS_LOGOUT, null));
    }

    @PutMapping("/update")
    public ResponseEntity<ResponseDTO> update(
            @RequestBody UserUpdateDTO UserUpdateDTO) throws IOException
    {
        UserEntity res = userService.update(UserUpdateDTO);
        return ResponseEntity
                .status(ResponseCode.SUCCESS_UPDATE_PROFILE.getStatus().value())
                .body(new ResponseDTO(ResponseCode.SUCCESS_UPDATE_PROFILE, res));
    }
}
