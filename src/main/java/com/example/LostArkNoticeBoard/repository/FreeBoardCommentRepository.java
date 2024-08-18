package com.example.LostArkNoticeBoard.repository;

import com.example.LostArkNoticeBoard.entity.FreeBoardComment;
import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FreeBoardCommentRepository extends JpaRepository<FreeBoardComment, Long> {


    @Query(value = "SELECT * FROM FreeBoardComment WHERE free_board_id = :freeBoardId", nativeQuery = true) //nativeQuery = true JPQL 사용시 SQL 문을 그대로 사용가능 WHERE 사용시 ":" 사용
    List<FreeBoardComment> findByFreeBoardId(Long freeBoardId);


    List<FreeBoardComment> findByNickname(String nickname);
}
