package com.example.AI_Vue.Controller;

import com.example.AI_Vue.Dto.DashboardResponse;
import com.example.AI_Vue.Models.InterviewDetails;
import com.example.AI_Vue.Models.User;
import com.example.AI_Vue.Repository.InterviewDetailsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/ivdashboard")
public class InterviewDashboardController {

    @Autowired
    private InterviewDetailsRepo interviewDetailsRepo;

    @GetMapping
    public DashboardResponse ivdashboard(@AuthenticationPrincipal User userbyauth) {
        List<InterviewDetails> details = interviewDetailsRepo.findByStudent_Id(userbyauth.getId());

        DashboardResponse response = new DashboardResponse();
        response.setCompanyName(details.get(0).getCompanyName());
        response.setStatus(details.get(0).getStatus());
        response.setJobTitle(details.get(0).getJobTitle());

        return response;
    }
}
