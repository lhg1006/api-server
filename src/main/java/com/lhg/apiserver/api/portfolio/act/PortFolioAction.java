package com.lhg.apiserver.api.portfolio.act;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lhg.apiserver.api.portfolio.biz.PortfolioService;
import com.lhg.apiserver.api.portfolio.vo.projects.ProjectDataResVO;
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

    @GetMapping("/project/data/count")
    public int projectTotalCnt(){
        return portfolioService.getProjectTotalCnt();
    }

    @GetMapping("/project/data")
    public List<ProjectDataResVO> resumeData(@RequestParam int pageNo){
        return portfolioService.getProjectData(pageNo);
    }

    @GetMapping("/project/data/single")
    public ProjectDataResVO resumeData(@RequestParam String projectId) throws JsonProcessingException {
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
