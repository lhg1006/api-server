package com.lhg.apiserver.api.portfolio.vo;

import lombok.Data;

@Data
public class AuthVO {
    private int existsFlag;
    private String userNo;
    private String isAdmin;
}
