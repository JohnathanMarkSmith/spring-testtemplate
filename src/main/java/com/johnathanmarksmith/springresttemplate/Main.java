package com.johnathanmarksmith.springresttemplate;


import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class Main
{

    private static final Logger logger = LoggerFactory.getLogger(Main.class);


    private RestTemplate restTemplate =null;




    public static void main(String[] args)
    {
        logger.debug("Starting REST Client!!!!");

        UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("test", "test");



        /*

            In this example we need to get data from http://127.0.0.1:8080/springmvc-rest-secured-test/json/Regan

         */
    }
}
