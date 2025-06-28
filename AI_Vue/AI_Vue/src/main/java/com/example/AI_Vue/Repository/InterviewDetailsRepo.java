package com.example.AI_Vue.Repository;

import com.example.AI_Vue.Models.InterviewDetails;
import com.example.AI_Vue.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


public interface InterviewDetailsRepo extends JpaRepository<InterviewDetails,Long> {
    List<InterviewDetails> findByStudent_Id(Long userId);
}
