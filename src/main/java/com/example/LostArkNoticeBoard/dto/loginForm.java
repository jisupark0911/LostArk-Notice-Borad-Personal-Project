package com.example.LostArkNoticeBoard.dto;


import com.example.LostArkNoticeBoard.entity.LoginEntity;
import lombok.*;
import lombok.extern.java.Log;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class loginForm {
    private Long id;
    private String userEmail;
    private String userPassword;
    private String userName;

    public static loginForm toform (LoginEntity loginEntity){ //엔티티를 dto로 변환
        loginForm form = new loginForm();
        form.setId(loginEntity.getId());
        form.setUserEmail(loginEntity.getUserEmail());
        form.setUserPassword(loginEntity.getUserPassword());
        form.setUserName(loginEntity.getUserName());
        return form;
    }


}
