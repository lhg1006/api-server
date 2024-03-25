package com.lhg.apiserver.api.portfolio.biz;

import com.lhg.apiserver.api.portfolio.vo.AuthVO;
import com.lhg.apiserver.api.portfolio.vo.CareerVO;
import com.lhg.apiserver.api.portfolio.vo.ResumeVO;
import com.lhg.apiserver.db.portfolio.PortfolioDB;
import com.lhg.apiserver.utills.CookieUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}
