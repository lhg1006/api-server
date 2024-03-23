package com.lhg.apiserver.api.portfolio.vo;

import lombok.Data;

@Data
public class CareerVO {
    private int autoNo;
    private int orderNo;
    private String projectName;
    private String mainTasks;
    private String companyName;
    private String role;
    private String techStack;
    private String duration;
}
