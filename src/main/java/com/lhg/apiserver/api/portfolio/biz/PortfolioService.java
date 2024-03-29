package com.lhg.apiserver.api.portfolio.biz;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lhg.apiserver.api.portfolio.vo.*;
import com.lhg.apiserver.api.portfolio.vo.projects.*;
import com.lhg.apiserver.db.portfolio.PortfolioDB;
import com.lhg.apiserver.utills.CookieUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class PortfolioService {
    private final PortfolioDB portfolioDB;
    private final CookieUtil cookieUtil;

    public Map<String, Object> getResumeData(){
        Map<String, Object> resMap = new HashMap<>();
        try {
            // portfolioDB에서 ResumeVO 리스트 가져오기
            List<ResumeVO> resList = portfolioDB.getResumeData();

            Map<String, List<ResumeVO>> groupedResumes = new HashMap<>();

            // 주어진 그룹별 ResumeVO 리스트를 Map에 담기
            for (ResumeVO resume : resList) {
                String headCode = resume.getHeadCode();
                if (!groupedResumes.containsKey(headCode)) {
                    groupedResumes.put(headCode, new ArrayList<>());
                }
                groupedResumes.get(headCode).add(resume);
            }

            // Map<String, Object>에 그룹별 리스트 담기
            for (Map.Entry<String, List<ResumeVO>> entry : groupedResumes.entrySet()) {
                resMap.put(entry.getKey(), entry.getValue());
            }
        }catch (Exception e){
            log.error("PortfolioService getResumeData ERROR =======> {}", e);
        }

        return resMap;
    }

    public Map<String, Object> getCareerData(){
        Map<String, Object> resMap = new HashMap<>();
        try {
            List<CareerVO> resList = portfolioDB.getCareerData();

            resMap.put("careerData", resList);
        }catch (Exception e){
            log.error("PortfolioService getCareerData ERROR =======> {}", e);
        }

        return resMap;
    }

    public List<ProjectParamVO> getProjectData(Map<String, Object> param){
        List<ProjectParamVO> resList = null;
        try {
            int pageNo = (int) param.get("pageNo");
            int offset = (pageNo - 1) * 4;
            param.put("offset", offset);

            resList = portfolioDB.getProjectData(param);

            if(resList == null){
                ProjectParamVO dummy = new ProjectParamVO();
                List<ProjectParamVO> dummyList = new ArrayList<>();
                dummyList.add(dummy);
                resList = dummyList;
            }
        }catch (Exception e){
            log.error("");
        }
        return resList;
    }

    public Map<String, Object> getSingleProjectData(String projectId) throws JsonProcessingException {
        Map<String, Object> resMap = new HashMap<>();
        ProjectDataVO projectDataVO;
        projectDataVO = portfolioDB.getSingleProjectData(projectId);

        if(projectDataVO != null){
            String jsonImages = projectDataVO.getImages();
            String jsonTexts = projectDataVO.getTexts();

            ObjectMapper objectMapper = new ObjectMapper();
            String[] imageArray = objectMapper.readValue(jsonImages, String[].class);
            String[] textArray = objectMapper.readValue(jsonTexts, String[].class);

            resMap.put("title", projectDataVO.getTitle());
            resMap.put("images", imageArray);
            resMap.put("texts", textArray);
        }

        return resMap;
    }

    public int loginAuth(Map<String, Object> param, HttpServletResponse httpServletResponse){
        int result = 0;
        try {
            AuthVO authVO = portfolioDB.loginAuth(param);

            if(authVO != null  && authVO.getExistsFlag() == 1) {
                Map<String, Object> cookieParam = new HashMap<>();

                cookieParam.put("userId", param.get("email"));
                cookieParam.put("userNo", authVO.getUserNo());
                cookieParam.put("isAdmin", authVO.getIsAdmin());

                cookieUtil.setCookie(httpServletResponse, cookieParam);
                result = 1;
            }else{
                return result;
            }
        }catch (Exception e){
            log.error("PortfolioService loginAuth ERROR ========> {}", e);
        }
        return result;
    }

    public int projectUpload(ProjectParamVO projectParamVO){
        int result = 0;
        try {
            if( !projectParamVO.getTitle().isEmpty() ){
                savePost(projectParamVO); // savePost 메서드가 예외를 던지지 않으면 성공한 것으로 간주
                result = 1; // 성공했으므로 1을 반환
            }else{
                result = 99; // title 이 빈 값..
            }
        } catch (Exception e) {
            log.error("PortfolioService projectUpload ERROR ========> {}", e);
        }
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public void savePost(ProjectParamVO projectParamVO) {
        // 게시물 저장
        ProjectsVO projectsVO = new ProjectsVO();
        projectsVO.setTitle(projectParamVO.getTitle());

        portfolioDB.saveProject(projectsVO);

        Long projectId = projectsVO.getId();

        // 이미지 저장
        List<String> imagePaths = List.of(projectParamVO.getImages());
        for (String imagePath : imagePaths) {
            ProjectImgVO projectImgVO = new ProjectImgVO();
            projectImgVO.setProjectId(projectId);
            projectImgVO.setImagePath(imagePath);

            portfolioDB.saveProjectImg(projectImgVO);
        }

        // 텍스트 저장
        List<String> texts = List.of(projectParamVO.getTexts());
        for (String text : texts) {
            ProjectTxtVO projectTxtVO = new ProjectTxtVO();
            projectTxtVO.setProjectId(projectId);
            projectTxtVO.setContent(text);

            portfolioDB.saveProjectTxt(projectTxtVO);
        }
    }
}
