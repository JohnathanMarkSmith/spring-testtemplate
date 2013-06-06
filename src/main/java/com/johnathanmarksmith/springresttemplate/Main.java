package com.johnathanmarksmith.springresttemplate;


import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;

import static org.apache.log4j.Logger.getLogger;


public class Main
{

    private static final Logger LOGGER = getLogger(Main.class);


    public static void main(String[] args)
    {
        LOGGER.debug("Starting REST Client!!!!");

        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("test", "test");


        RestTemplate restTemplate = new RestTemplate();

        String jsonreturn = restTemplate.getForObject("http://127.0.0.1:8080/springmvc-rest-secured-test/json/Regan", String.class);

        LOGGER.debug(jsonreturn);
    }
}
