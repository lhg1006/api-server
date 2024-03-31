package com.lhg.apiserver.api.portfolio.vo.projects;

import lombok.Data;

@Data
public class ProjectDataResVO {
    private int autoNo;
    private String title;
    private String[] images;
    private String[] texts;
}
