package com.lhg.apiserver.db.portfolio;

import com.lhg.apiserver.api.portfolio.vo.AuthVO;
import com.lhg.apiserver.api.portfolio.vo.CareerVO;
import com.lhg.apiserver.api.portfolio.vo.ResumeVO;

import java.util.List;
import java.util.Map;

public interface PortfolioDB {
    List<ResumeVO> getResumeData();

    List<CareerVO> getCareerData();

    AuthVO loginAuth(Map<String,Object> param);
}
