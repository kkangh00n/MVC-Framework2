package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
//=> 안에 @ResponseBody가 내장되어있음 => @ResponseBody는 return값을 뷰 템플릿이 아닌, http 메시지 body부에 들어감
public class LogTestController {
    //private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";

        log.trace("trace log={}", log);
        log.debug("debug log={}", log);
        log.info("info log={}", log);
        log.warn("warn log={}", log);
        log.error("error log={}", log);

        //log.debug("String concat log=" + name);
        //=> 전체 로그 레벨 설정은 info이다. 하지만 이렇게 +연산을 쓰면, 사용하지도 않는 debug의 +연산이 일어난다.
        // 실무에서 절대 x

        return "ok";

    }
}
