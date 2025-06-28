package com.example.AI_Vue.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InterviewRequest {

    private String JobTitle;
    private String CompanyName;
    private String JobDescription;
   private MultipartFile resume;

}
