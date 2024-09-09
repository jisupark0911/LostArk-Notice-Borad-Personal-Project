package com.example.LostArkNoticeBoard.service;

import com.example.LostArkNoticeBoard.entity.FreeBoard;
import com.example.LostArkNoticeBoard.repository.FreeBoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FreeBoardService {
    @Autowired
    private FreeBoardRepository freeBoardRepository;

    public List<FreeBoard> searchByTitle(String title) {
        return freeBoardRepository.findByTitleContaining(title);
    }

    public List<FreeBoard> searchByContent(String content) {
        return freeBoardRepository.findByContentContaining(content);
    }

    public List<FreeBoard> searchByTitleOrContent(String keyword) {
        List<FreeBoard> byTitle = searchByTitle(keyword);
        List<FreeBoard> byContent = searchByContent(keyword);

        // 중복 제거 (제목과 내용이 겹치면 중복으로 출력됨)
        Set<FreeBoard> resultSet = new HashSet<>();
        resultSet.addAll(byTitle);
        resultSet.addAll(byContent);


        return resultSet.stream().collect(Collectors.toList());
    }
}
