package board.example.myboard.service;

import board.example.myboard.base.code.ErrorCode;
import board.example.myboard.base.exception.LoginIdNotFoundException;
import board.example.myboard.base.exception.LoginPasswordNotMatchException;
import board.example.myboard.base.projection.GetUser;
import board.example.myboard.dto.user.UserLoginDTO;
import board.example.myboard.dto.user.UserRegisterDTO;
import board.example.myboard.dto.user.UserUpdateDTO;
import board.example.myboard.entity.UserEntity;
import board.example.myboard.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public GetUser register(UserRegisterDTO userRegisterDTO) {
        UserEntity user = UserEntity.builder()
                .loginId(userRegisterDTO.getLoginId())
                .profileImage(userRegisterDTO.getProfileImage())
                .password(userRegisterDTO.getPassword())
                .introduction(userRegisterDTO.getIntroduction())
                .goal(userRegisterDTO.getGoal())
                .build();
        UserEntity createUser = userRepository.save(user);
        return EntityToProjectionUser(createUser);
    }

    @Override
    public GetUser login(UserLoginDTO userLoginDTO) {
        Optional<UserEntity> findUser = userRepository.findByLoginId(userLoginDTO.loginId);

        //아이디가 존재하는지 확인
        if(!findUser.isPresent()) throw new LoginIdNotFoundException(ErrorCode.USERID_NOT_FOUND);
        //비밀번호가 같은지 확인
        else if(!findUser.get().getPassword().equals(userLoginDTO.password)) throw new LoginPasswordNotMatchException(ErrorCode.PASSWORD_NOT_MATCH);

        GetUser user = EntityToProjectionUser(findUser.get());
        return user;
    }

    @Override
    public void logout(String loginId) {
        Optional<UserEntity> optionalUser = userRepository.findByLoginId(loginId);
        optionalUser.ifPresent(user ->{
            userRepository.delete(user);
        });
    }

    @Override
    public UserEntity update(UserUpdateDTO userUpdateDTO) {
        Optional<UserEntity> updateUser = userRepository.findByUserId(userUpdateDTO.getUserId());
        updateUser.get().setProfileImage(userUpdateDTO.getProfileImage());
        updateUser.get().setIntroduction(userUpdateDTO.getIntroduction());
        updateUser.get().setGoal(userUpdateDTO.getGoal());

        return userRepository.save(updateUser.get());
    }

    private GetUser EntityToProjectionUser(UserEntity user){
        GetUser userInfo = new GetUser() {

            @Override
            public Integer getUserId() {
                return user.getUserId();
            }

            @Override
            public String GetUserProfile() {
                return user.getProfileImage();
            }

            @Override
            public String getLoginId() {
                return user.getLoginId();
            }

            @Override
            public String getPassword() {
                return user.getPassword();
            }

            @Override
            public String getIntroduction() {
                return user.getIntroduction();
            }

            @Override
            public Integer getGoal() {
                return user.getGoal();
            }

        };
           return userInfo;
    }
}
