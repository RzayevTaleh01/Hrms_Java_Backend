package com.kodlamaio.hrms.entities.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JobsFilter {

    private List<Integer> cityId;
    private List<Integer> jobCategoryId;
    private List<Integer> workPlaceId;
    private List<Integer> workTimeId;
}
