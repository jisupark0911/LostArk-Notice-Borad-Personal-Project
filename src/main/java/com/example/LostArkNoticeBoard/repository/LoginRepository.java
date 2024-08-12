package com.example.LostArkNoticeBoard.repository;

import com.example.LostArkNoticeBoard.entity.LoginEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface LoginRepository extends JpaRepository<LoginEntity, Long> {
    static List<LoginEntity> findAllLogin() {
        return new ArrayList<>();
    }

    //이메일로 회원 정보를 조회(selet * from member where userEmail=?)
    Optional<LoginEntity> findByuserEmail(String userEmail); // Optional은 일종의 null방지


}
