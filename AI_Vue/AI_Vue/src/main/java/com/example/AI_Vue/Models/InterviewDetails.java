package com.example.AI_Vue.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class InterviewDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "job_title", length = 500)
    private String JobTitle;

    @Column(name = "company", length = 500)
    private String CompanyName;
    @Column(name = "resume_data", columnDefinition = "LONGTEXT")
    private String ResumeData;
    @Column(name = "job_description", columnDefinition = "TEXT")
    private String jobDescription;

    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User student;
}
