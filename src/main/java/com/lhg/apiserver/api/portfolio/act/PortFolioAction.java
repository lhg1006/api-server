package com.lhg.apiserver.api.portfolio.act;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lhg.apiserver.api.portfolio.biz.PortfolioService;
import com.lhg.apiserver.api.portfolio.vo.projects.ProjectParamVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/portfolio")
@RequiredArgsConstructor
public class PortFolioAction {
    private final PortfolioService portfolioService;

    @GetMapping("/resume")
    public Map<String,Object> resumeData(){
        return portfolioService.getResumeData();
    }

    @GetMapping("/career")
    public Map<String,Object> careerData(){
        return portfolioService.getCareerData();
    }


    @GetMapping("/project/data")
    public List<ProjectParamVO> resumeData(Map<String,Object> param){
        return portfolioService.getProjectData(param);
    }

    @GetMapping("/project/data/single")
    public Map<String, Object> resumeData(@RequestParam String projectId) throws JsonProcessingException {
        return portfolioService.getSingleProjectData(projectId);
    }

    @PostMapping("/login/auth")
    public int loginAuth(@RequestBody Map<String, Object> param, HttpServletResponse httpServletResponse){
        return portfolioService.loginAuth(param, httpServletResponse);
    }

    @PostMapping("/project/upload")
    public int projectUpload(@RequestBody ProjectParamVO projectParamVO){
        return portfolioService.projectUpload(projectParamVO);
    }
}
