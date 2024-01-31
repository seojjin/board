package board.example.myboard.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder // DTO -> Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer userId; //유저 아이디

    @Column(length=200)
    private String profileImage; // 프로필 이미지 링크

    @Column(unique = true, nullable = false, length = 45)
    private String loginId; // 로그인 아이디

    @Column(nullable = false, length = 100)
    private String password; // 비밀번호

    @Column(nullable = false, length = 100)
    private String introduction; // 한줄 소개

    @Column(nullable = false)
    private Integer goal; // 목표 타수

}
