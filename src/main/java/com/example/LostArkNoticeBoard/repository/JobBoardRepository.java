package com.example.LostArkNoticeBoard.repository;

import com.example.LostArkNoticeBoard.entity.JobBoard;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface JobBoardRepository extends CrudRepository<JobBoard , Long> {
    @Override
    ArrayList<JobBoard> findAll();
}
