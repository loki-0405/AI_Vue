package com.example.AI_Vue.Controller;

import com.example.AI_Vue.Dto.DashboardResponse;
import com.example.AI_Vue.Dto.InterviewRequest;
import com.example.AI_Vue.Models.InterviewDetails;
import com.example.AI_Vue.Models.Status;
import com.example.AI_Vue.Models.User;
import com.example.AI_Vue.Repository.InterviewDetailsRepo;
import com.example.AI_Vue.Repository.UserRepo;
import com.example.AI_Vue.Service.FileToText;
import com.example.AI_Vue.Service.GeminiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@RestController
@RequestMapping("/api/upload")
public class InterviewController {

    @Autowired
    private FileToText fileToText;

    @Autowired
    private InterviewDetailsRepo interviewDetailsRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
   private GeminiService geminiService;

    @PostMapping("/ok")
    public String uploading(@RequestParam("file") MultipartFile file,
                            @RequestParam("title") String title,
                            @RequestParam("desc") String desc,
                            @RequestParam("companyname") String Companyname,
                            @AuthenticationPrincipal UserDetails userbyauth){

        if (userbyauth == null) return "User not authenticated!";

          String ResumeData =  fileToText.LoadData(file);
          String Prompt = "Give 10 interview Questions and also answers for the question based on job title\n"+title+"job description:\n"+desc
                           +"resume :\n"+ResumeData;

          String responseData = geminiService.getGeminiResponse(Prompt);
          Optional<User> userA = userRepo.findByUsername(userbyauth.getUsername());

          System.out.println(userA.get());
          InterviewDetails details = new InterviewDetails();
          details.setCompanyName(Companyname);
          details.setStatus(Status.IN_PROGRESS);
          details.setJobTitle(title);
          details.setJobDescription(desc);
          details.setResumeData(responseData);
          details.setStudent(userA.get());

          interviewDetailsRepo.save(details);

          return responseData;
    }
}
