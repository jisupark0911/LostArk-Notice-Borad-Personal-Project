package com.example.LostArkNoticeBoard.service;

import com.example.LostArkNoticeBoard.dto.loginForm;
import com.example.LostArkNoticeBoard.entity.LoginEntity;
import com.example.LostArkNoticeBoard.repository.LoginRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Getter
@Setter
@Service
@RequiredArgsConstructor
public class LoginService {
    @Autowired
    private LoginRepository loginRepository;

    public List<loginForm> findAllLogin() {
        List<LoginEntity> loginEntityList = loginRepository.findAll();
        List<loginForm> loginFormList = new ArrayList<>();
        for (LoginEntity loginEntity : loginEntityList) {
            loginFormList.add(loginForm.toform(loginEntity));
        }
        return loginFormList;
    }

    public void save(loginForm form) {
        //1. dto를 엔티티로 변환
        //2. 레파지터리의 save메서드 호출
        LoginEntity loginEntity = LoginEntity.toLoginEntity(form);
        loginRepository.save(loginEntity);
        //레파지터리의 save메서드 호출(조건: entity객체를 넘겨줘야함)
    }

    public loginForm login(loginForm form) {
        /*
        1. 회원이 입력한 이메일로 DB에서 조회를함
        2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀번호가 일치하는지 판단

         */
        Optional<LoginEntity> byuserEmail = loginRepository.findByuserEmail(form.getUserEmail());
        if(byuserEmail.isPresent()){
            //해당 이메일이 있을때
            LoginEntity loginEntity = byuserEmail.get(); //엔티티 가져오기
            if(loginEntity.getUserPassword().equals(form.getUserPassword())) {
                //비밀번호 일치
                //entity를 dto로 변환 후 리턴
                 loginForm loginFormdto= loginForm.toform(loginEntity);
                 return loginFormdto;
            }else{
                //비밀번호 불일치
                return null;
            }
        }else{
            return null;
        }
    }

    public loginForm findById(Long id) {
        Optional<LoginEntity> optionalLoginEntity = loginRepository.findById(id);
        if(optionalLoginEntity.isPresent()){
            return loginForm.toform(optionalLoginEntity.get());
        }else{
            return null;
        }
    }

    public loginForm updateForm(String myEmail) {
        Optional<LoginEntity> optionalLoginEntity = loginRepository.findByuserEmail(myEmail);
        if(optionalLoginEntity.isPresent()){
            return loginForm.toform(optionalLoginEntity.get());
        }else{
            return null;
        }
    }


    public void update(loginForm form) {
        loginRepository.save(LoginEntity.toUpdateLoginEntity(form));
    }



    public void deleteById(Long id) {
        loginRepository.deleteById(id);
    }

    public String emailCheck(String userEmail) {
        Optional<LoginEntity> byuserEmail = loginRepository.findByuserEmail(userEmail);
        if(byuserEmail.isPresent()){
            //조회결과가 있다. 중복된 이메일이 있다.
            return null;
        }
        else{
            return "ok";
        }
    }
}
