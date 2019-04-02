package com.hyecheon.ppmtool.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;


@NoArgsConstructor
@Data
@Entity
public class ProjectTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(updatable = false)
    private String projectSequence;
    @NotBlank(message = "please include a project summary")
    private String summary;
    private String acceptanceCriteria;
    private String status = "TODO"; // default
    private Integer priority = 3; // default
    private Date dueDate;

    @Column(updatable = false)
    private String projectIdentifier;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "backlog_id", updatable = false, nullable = false)
    @JsonIgnore
    @ToString.Exclude
    private Backlog backlog;

    @Column(updatable = false)
    @CreationTimestamp
    private Date createAt;

    @UpdateTimestamp
    private Date updateAt;

    public void setPriority(Integer priority) {
        if (priority > 0)
            this.priority = priority;
    }
}
