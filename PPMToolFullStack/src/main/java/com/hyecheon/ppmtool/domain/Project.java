package com.hyecheon.ppmtool.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "프로젝트 이름을 입력해 주세요")
    private String projectName;

    @Size(min = 4, max = 10, message = "4~10를 입력해 주세요.")
    @Column(updatable = false, unique = true)
    @NotBlank(message = "프로젝트 아이디 입력해 주세요")
    private String projectIdentifier;

    private String description;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date startDate;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "Asia/Seoul")
    private Date endDate;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "project")
    @JsonIgnore
    private Backlog backlog;

    @org.jetbrains.annotations.Contract(pure = true)
    public Project() {
    }

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "Asia/Seoul")
    @Column(updatable = false)
    private Date createAt;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "Asia/Seoul")
    @UpdateTimestamp
    private Date updatedAt;

    @PrePersist
    public void createAt() {
        createAt = new Date();
    }
}
