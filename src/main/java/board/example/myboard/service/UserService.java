package board.example.myboard.service;

import board.example.myboard.base.projection.GetUser;
import board.example.myboard.dto.user.UserLoginDTO;
import board.example.myboard.dto.user.UserRegisterDTO;
import board.example.myboard.dto.user.UserUpdateDTO;
import board.example.myboard.entity.UserEntity;

public interface UserService {
    GetUser register(UserRegisterDTO userRegisterDTO);

    GetUser login(UserLoginDTO userLoginDTO);

    void logout(String loginId);

    UserEntity update(UserUpdateDTO mapperUploadPostDTO);
}
