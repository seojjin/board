package board.example.myboard.controller;

import board.example.myboard.base.projection.GetUser;
import board.example.myboard.base.code.ResponseCode;
import board.example.myboard.dto.response.ResponseDTO;
import board.example.myboard.dto.user.UserLoginDTO;
import board.example.myboard.dto.user.UserRegisterDTO;
import board.example.myboard.dto.user.UserUpdateDTO;
import board.example.myboard.entity.UserEntity;
import board.example.myboard.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@Controller
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDTO> register(
            @RequestBody UserRegisterDTO userRegisterDTO,
            @RequestPart(value = "imgFile", required = false) String imgFile
            )throws IOException {

        if(imgFile==null){
            imgFile="";
        }
        userRegisterDTO.setProfileImage(imgFile);
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
            @RequestParam(value="image", required = false) MultipartFile profileImage,
            @RequestParam(value="info") String userUpdateDTO) throws IOException, ParseException {

        // mapper
        ObjectMapper mapper = new ObjectMapper();
        UserUpdateDTO mapperUploadPostDTO = mapper.readValue(userUpdateDTO, UserUpdateDTO.class);

        // 이미지 등록
        if (profileImage != null && profileImage.getResource().contentLength() != 0){
            // parse
            JSONParser parser = new JSONParser();
            JSONObject object = (JSONObject) parser.parse(userUpdateDTO);
            String userId = object.get("userId").toString();

            // 예전 유저의 프로필 이미지 삭제


            // 이미지 업로드


            // updateUserProfileDTO 객체에 프로필 정보 설정

        }
        UserEntity res = userService.update(mapperUploadPostDTO);
        return ResponseEntity
                .status(ResponseCode.SUCCESS_UPDATE_PROFILE.getStatus().value())
                .body(new ResponseDTO(ResponseCode.SUCCESS_UPDATE_PROFILE, res));
    }
}
