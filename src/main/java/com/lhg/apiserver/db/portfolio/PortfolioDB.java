package com.lhg.apiserver.db.portfolio;

import com.lhg.apiserver.api.portfolio.vo.AuthVO;
import com.lhg.apiserver.api.portfolio.vo.CareerVO;
import com.lhg.apiserver.api.portfolio.vo.projects.*;
import com.lhg.apiserver.api.portfolio.vo.ResumeVO;

import java.util.List;
import java.util.Map;

public interface PortfolioDB {
    // GET
    List<ResumeVO> getResumeData();

    List<CareerVO> getCareerData();

    ProjectDataVO getSingleProjectData(String projectId);

    List<ProjectParamVO> getProjectData(Map<String, Object> param);

    // POST

    AuthVO loginAuth(Map<String,Object> param);

    void saveProject(ProjectsVO projectsVO);

    void saveProjectImg(ProjectImgVO projectImgVO);

    void saveProjectTxt(ProjectTxtVO projectImgVO);
}
