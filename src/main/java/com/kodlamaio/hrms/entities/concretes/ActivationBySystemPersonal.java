package com.kodlamaio.hrms.entities.concretes;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Calendar;

@Entity
@Table(name = "activation_by_sp")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivationBySystemPersonal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "employe_id")
    private int employeId;

    @Column(name = "sp_id")
    private Integer staffId;

    @Column(name = "verifyed")
    private boolean verifyed;

    @Column(name = "verify_date")
    private LocalDate verifyDate;
}
