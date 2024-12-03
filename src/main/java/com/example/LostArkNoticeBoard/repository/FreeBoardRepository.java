package com.example.LostArkNoticeBoard.repository;

import com.example.LostArkNoticeBoard.entity.FreeBoard;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public interface FreeBoardRepository extends CrudRepository<FreeBoard, Long> {
    @Override
    ArrayList<FreeBoard> findAll();//엔티티클래스명, 엔티티의 대표값

    List<FreeBoard> findByTitleContaining(String title);
    List<FreeBoard> findByContentContaining(String content);
    List<FreeBoard> findAllByOrderByCreatedAtDesc();
    Page<FreeBoard> findAllByOrderByCreatedAtDesc(Pageable pageable);
    Page<FreeBoard> findAll(Pageable pageable);


}
