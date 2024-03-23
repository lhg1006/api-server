package com.lhg.apiserver.api.portfolio.act;

import com.lhg.apiserver.api.portfolio.biz.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
