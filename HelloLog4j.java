package com.example.demo02;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;
@RestController
public class HelloLog4j {
    private Logger  Logger = LogManager.getLogger(HelloLog4j.class);

    @RequestMapping(value = "/login" , method = {RequestMethod.POST})
    public String login(@RequestBody Map body) {
        String user = body.get("user").toString();
        String password = body.get("password").toString();
        System.setProperty("com.sun.jndi.ldap.object.trustURLCodebase", "true");
        Logger.error("user:{}, password:{}" , user ,password);
        return "login";
    }
}

