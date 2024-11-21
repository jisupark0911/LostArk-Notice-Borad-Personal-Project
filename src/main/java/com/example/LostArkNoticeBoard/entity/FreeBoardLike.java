package com.example.LostArkNoticeBoard.entity;

import jakarta.persistence.*;
import lombok.*;



@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@Entity
    public class FreeBoardLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String userEmail;

    @ManyToOne
    @JoinColumn(name = "free_board_id")
    private FreeBoard freeBoard;
    }

