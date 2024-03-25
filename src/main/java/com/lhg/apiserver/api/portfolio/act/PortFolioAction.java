package com.lhg.apiserver.api.portfolio.act;

import com.lhg.apiserver.api.portfolio.biz.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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

    @PostMapping("/login/auth")
    public int loginAuth(@RequestBody Map<String, Object> param, HttpServletResponse httpServletResponse){
        return portfolioService.loginAuth(param, httpServletResponse);
    }
}
