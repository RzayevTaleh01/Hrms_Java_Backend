package com.kodlamaio.hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "cv")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cv {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne()
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "github")
    private String github;

    @Column(name = "linkedin")
    private String linkedin;

    @Column(name = "biography")
    private String biography;

    @OneToMany(mappedBy = "cv")
    private List<CvImg> cvImgs;

    @OneToMany(mappedBy = "cv")
    private List<CvExperiance> cvExperiances;

    @OneToMany(mappedBy = "cv")
    private List<Language> languages;

    @OneToMany(mappedBy = "cv")
    private List<CvSchool> cvSchools;

    @OneToMany(mappedBy = "cv")
    private List<CvPrgSkills> cvPrgSkills;
}
