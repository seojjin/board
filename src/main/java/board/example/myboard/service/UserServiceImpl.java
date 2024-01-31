package board.example.myboard.service;

import board.example.myboard.base.code.ErrorCode;
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
    GetUser login(UserLoginDTO userLoginDTO) {
        Optional<UserEntity> findUser = userRepository.findByLoginId(userLoginDTO.loginId);

        //아이디가 존재하는지 확인
        if(!findUser.isPresent()) throw new LoginIdNotFoundException(ErrorCode.USERID_NOT_FOUND);
        //비밀번호가 같은지 확인

        GetUser user = EntityToProjectionUser(findUser.get());
        return super.login(userLoginDTO);
    }

    @Override
    public void logout(String loginId) {
        Optional<UserEntity> optionalUser = userRepository.findByLoginId(loginId);
        optionalUser.ifPresent(user ->{
            userRepository.delete(user);
        });
    }

    @Override
    UserEntity update(UserUpdateDTO mapperUploadPostDTO) {

        return super.update(mapperUploadPostDTO);
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
