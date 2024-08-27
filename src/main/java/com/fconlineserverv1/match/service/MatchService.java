package com.fconlineserverv1.match.service;

import com.fconlineserverv1.global.config.RestTemplateConfig;
import com.fconlineserverv1.global.config.WebUtil;
import com.fconlineserverv1.match.dto.responsedto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

@Slf4j
@RequiredArgsConstructor
@Service
public class MatchService {
    private final WebUtil webUtil;
    private final RestTemplate restTemplate;
    public void getMatchs(String nickname) {
        String uri = "https://open.api.nexon.com/fconline/v1/id?nickname=" + nickname;
        responsedto responsedto = restTemplate.getForObject(uri, responsedto.class);
        String ouid = responsedto.getOuid();
        log.info("ouid =" + ouid);

        UriComponents build = UriComponentsBuilder
                .fromUriString("https://open.api.nexon.com")
                .path("/fconline/v1/user/match")
                .queryParam("ouid", ouid)
                .queryParam("matchtype", 50)
                .queryParam("offset", 0)
                .queryParam("limit", 20)
                .build();
        log.info(build.toString());
        String forObject = restTemplate.getForObject(build.toString(), String.class);
        log.info("매치 20개 :"+forObject);
    }
}
