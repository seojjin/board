package board.example.myboard.repository;

import board.example.myboard.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUserId(Integer loginId);
    Optional<UserEntity> findByLoginId(String loginId);
}
