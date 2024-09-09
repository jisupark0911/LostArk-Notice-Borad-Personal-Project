package com.example.LostArkNoticeBoard.repository;

import com.example.LostArkNoticeBoard.entity.FreeBoard;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.List;

public interface FreeBoardRepository extends CrudRepository<FreeBoard, Long> {
    @Override
    ArrayList<FreeBoard> findAll();//엔티티클래스명, 엔티티의 대표값

    List<FreeBoard> findByTitleContaining(String title);
    List<FreeBoard> findByContentContaining(String content);
}
