package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Slf4j
@RestController
public class RequestHeaderController {

    @RequestMapping("/headers")
    public String headers(HttpServletRequest request,
                          HttpServletResponse response,
                          HttpMethod httpMethod,
                          Locale locale,
                          @RequestHeader MultiValueMap<String, String> headerMap,
                          //MultiValueMap -> 하나의 키의 여러가지 value가 들어갈 수 있음
                          @RequestHeader("host") String host,
                          @CookieValue(value = "myCookie", required = false) String cookie
                          ){

        log.info("request={}", request);            //Http 요청정보
        log.info("response={}", response);          //Http 응답정보
        log.info("httpMethod={}", httpMethod);      //Http 메소드
        log.info("locale={}", locale);              //언어 정보
        log.info("headerMap={}", headerMap);        //모든 헤더정보
        log.info("header host={}", host);           //호스트
        log.info("myCookie={}", cookie);            //쿠키정보

        return "ok";

    }
}
