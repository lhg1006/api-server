package com.lhg.apiserver.db.portfolio;

import com.lhg.apiserver.api.portfolio.vo.CareerVO;
import com.lhg.apiserver.api.portfolio.vo.ResumeVO;

import java.util.List;

public interface PortfolioDB {
    List<ResumeVO> getResumeData();

    List<CareerVO> getCareerData();
}
