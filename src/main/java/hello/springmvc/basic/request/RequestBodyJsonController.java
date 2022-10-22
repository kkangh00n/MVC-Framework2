package hello.springmvc.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


/**
 * {"username":"hello", "age":20}
 * content-type: application/json
 */
@Slf4j
@Controller
public class RequestBodyJsonController {
    private ObjectMapper objectMapper = new ObjectMapper();

    /**
     * {"username":"hello", "age":20}
     * content-type: application/json
     */
    //V1
    @PostMapping("request-body-json-v1")
    public void requestBodyJsonV1(HttpServletRequest request, HttpServletResponse response) throws IOException{
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody = {}", messageBody);
        //JSON형태의 메시지
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        //객체로 변환
        log.info("username = {}, age={}", helloData.getUsername(), helloData.getAge());

        response.getWriter().write("ok");
    }

    //V2
    @ResponseBody
    @PostMapping("request-body-json-v2")
    public String requestBodyJsonV1(@RequestBody String messageBody) throws IOException{

        log.info("messageBody = {}", messageBody);
        //JSON형태의 메시지
        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);
        //객체로 변환
        log.info("username = {}, age={}", helloData.getUsername(), helloData.getAge());

        return "ok";
    }

    //V3 (우리가 제일 많이 쓸거)
    @ResponseBody
    @PostMapping("request-body-json-v3")
    //@RequestBody를 통해 json메시지를 객체(helloData)에다가 바로 저장!!
    //###################################################
    // @ModelAttribute HelloData helloData : 파라미터 변수를 받아서 클래스 객체를 만들기 위함
    // @RequestBody HelloData helloData : Json을 메시지를 받아서 객체로 변환 (@RequestBody 생략 불가)
    public String requestBodyJsonV1(@RequestBody HelloData helloData){

        //JSON형태의 메시지
        log.info("username = {}, age={}", helloData.getUsername(), helloData.getAge());
        return "ok";
    }
}
