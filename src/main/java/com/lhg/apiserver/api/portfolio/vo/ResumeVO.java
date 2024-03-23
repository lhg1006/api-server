package com.lhg.apiserver.api.portfolio.vo;

import lombok.Data;

@Data
public class ResumeVO {
    private int autoNo;
    private String headName;
    private String headCode;
    private String bodyTitle;
    private String bodyCont;
    private int orderNo;
}
