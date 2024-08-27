package com.fconlineserverv1.match.controller;

import com.fconlineserverv1.global.response.ApiResponse;
import com.fconlineserverv1.match.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MatchController {

    private final MatchService matchService;
    @GetMapping("/match/{nickname}")
    public void getMatchs(@PathVariable("nickname") String nickname) {
        matchService.getMatchs(nickname);
    }
}
