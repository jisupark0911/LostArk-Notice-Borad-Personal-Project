package com.example.LostArkNoticeBoard.entity;

import com.example.LostArkNoticeBoard.dto.loginForm;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

@Entity
@Getter
@Setter
@Table(name ="member")
public class LoginEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String userEmail;

    @Column
    private String userPassword;

    @Column
    private String userName;

    public static LoginEntity toLoginEntity (loginForm form){ //dto를 엔티티로 변환
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setUserEmail(form.getUserEmail());
        loginEntity.setUserPassword(form.getUserPassword());
        loginEntity.setUserName(form.getUserName());
        return loginEntity;
    }
    public static LoginEntity toUpdateLoginEntity (loginForm form){ //화면에 알맞는 id정보들 가져오기
        LoginEntity loginEntity = new LoginEntity();
        loginEntity.setId(form.getId());
        loginEntity.setUserEmail(form.getUserEmail());
        loginEntity.setUserPassword(form.getUserPassword());
        loginEntity.setUserName(form.getUserName());
        return loginEntity;
    }
}
