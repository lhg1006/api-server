package com.lhg.apiserver.db.portfolio;

import com.lhg.apiserver.api.portfolio.vo.AuthVO;
import com.lhg.apiserver.api.portfolio.vo.CareerVO;
import com.lhg.apiserver.api.portfolio.vo.projects.*;
import com.lhg.apiserver.api.portfolio.vo.ResumeVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface PortfolioDB {
    // GET
    // 이력서데이터
    List<ResumeVO> getResumeData();
    // 경력기술서 데이터
    List<CareerVO> getCareerData();
    // 프로젝트 데이터 총 개수
    int getProjectTotalCnt();
    // 프로젝트 데이터 페이지별 호출
    List<ProjectDataVO> getProjectData(@Param("pagePerCnt") int pagePerCnt, @Param("offset") int offset);
    // 프로젝트 데이터 싱글
    ProjectDataVO getSingleProjectData(String projectId);
    // POST
    // 로그인 인증
    AuthVO loginAuth(Map<String,Object> param);
    // 프로젝트 저장관련
    // 프로젝트 저장
    void saveProject(ProjectsVO projectsVO);
    // 프로젝트 이미지 저장
    void saveProjectImg(ProjectImgVO projectImgVO);
    // 프로젝트 텍스트 저장
    void saveProjectTxt(ProjectTxtVO projectImgVO);
}
