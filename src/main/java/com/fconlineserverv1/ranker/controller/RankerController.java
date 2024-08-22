package com.fconlineserverv1.ranker.controller;


import com.fconlineserverv1.global.response.ApiResponse;
import com.fconlineserverv1.global.response.Status;
import com.fconlineserverv1.ranker.service.RankerService;
import com.fconlineserverv1.ranker.dto.RankerResponseDto;
import com.fconlineserverv1.ranker.dto.SquadResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class RankerController {
    private final RankerService rankerService;
    @GetMapping("/ranker")
    public ApiResponse<?> getRankerName(){
        List<RankerResponseDto> rankers = rankerService.getRankerName();
        return ApiResponse.onSuccess(Status.CREATED.getCode(), Status.CREATED.getMessage(), rankers);
    }
    @GetMapping("/ranker/squad/{ouid}")
    public ApiResponse<?> getRankerSquad(@PathVariable("ouid") String ouid){
        List<SquadResponseDto> squads = rankerService.getRankerSquad(ouid);
        return ApiResponse.onSuccess(Status.CREATED.getCode(), Status.CREATED.getMessage(), squads);
    }

}
