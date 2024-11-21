package com.example.LostArkNoticeBoard.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
public class FreeBoard {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    @JsonProperty("username")
    private String username;

    @Column
    private String userEmail;

    @CreatedDate  // 자동으로 현재 날짜 및 시간으로 설정, 이상하게 자동으로 컬럼삽입이 안되어서 직접 삽입을 시켜줬음
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private Long viewCount = 0L;

    @Column(nullable = false)
    private Long likeCount = 0L;

}
