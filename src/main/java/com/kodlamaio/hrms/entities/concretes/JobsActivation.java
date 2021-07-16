package com.kodlamaio.hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "job_activation")
public class JobsActivation {

    @Id
    @Column(name = "job_id")
    private int jobId;

    @Column(name = "sp_id")
    private Integer spİd;//System Personal

    @Column(name = "confirm")
    private boolean confirm;

    @Column(name = "confirm_date")
    private LocalDate confirmDate;
}
