package com.fconlineserverv1.ranker.service;

import com.fconlineserverv1.global.config.WebUtil;
import com.fconlineserverv1.ranker.dto.RankerResponseDto;
import com.fconlineserverv1.ranker.dto.SquadResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Slf4j
@Service
public class RankerService {
    private final WebUtil webutil;

    public List<RankerResponseDto> getRankerName()  {
        ArrayList<RankerResponseDto> list = new ArrayList<>();
        try {
            WebDriver driver = webutil.getDriver();
            log.info("페이지 접속");
            driver.get("https://fconline.nexon.com/datacenter/rank");
            log.info("5초대기");
            Thread.sleep(5000);  // 5초 대기 (대기 시간이 충분한지 확인 필요)
            List<WebElement> spanElements = driver.findElements(By.cssSelector("span.name.profile_pointer"));
            for (WebElement e : spanElements) {
                list.add(new RankerResponseDto(e.getAttribute("data-sn"), e.getText()));
                log.info(e.getAttribute("data-sn"));
                log.info(e.getText());
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return list;

    }
    public List<SquadResponseDto> getRankerSquad(String ouid) {
        WebDriver driver = webutil.getDriver();
        //ouid가 비정상, 매치되는 유저가 없을 경우에 예외상황 해결해야함
        List<SquadResponseDto> re = new ArrayList<>();
        try {

            log.info("페이지 접속");
            driver.get("https://fconline.nexon.com/profile/squad/popup/"+ouid);
            log.info("5초대기");
            Thread.sleep(5000);  // 5초 대기 (대기 시간이 충분한지 확인 필요)
            String player = "";
            for(int i=1; i<12; i++){
                player = "formationPlayer" + i;
                WebElement divElements = driver.findElement(By.id(player));

                String style = divElements.getAttribute("style");
                String regex = "(\\d+\\.\\d+)|(\\d+)(?=%)";

                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(style);

                List<String> extractedNumbers = new ArrayList<>();

                while (matcher.find()) {
                    // group()으로 매칭된 부분 추출
                    extractedNumbers.add(matcher.group());
                }

                WebElement firstdiv = divElements.findElement(By.tagName("div"));
                re.add(new SquadResponseDto(firstdiv.getAttribute("title"), extractedNumbers.get(0), extractedNumbers.get(1)));
                log.info(i+"번 째 선수 이름: "+firstdiv.getAttribute("title"));
                log.info(i+"번 째 선수 포지션: "+style);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return re;
    }
}
