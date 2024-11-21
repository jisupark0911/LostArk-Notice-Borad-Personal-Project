package com.example.LostArkNoticeBoard.repository;

import com.example.LostArkNoticeBoard.entity.FreeBoardLike;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FreeBoardLikeRepository extends JpaRepository<FreeBoardLike, Long> {
    Optional<FreeBoardLike> findByUserEmailAndFreeBoardId(String userEmail, Long freeBoardId);
}
