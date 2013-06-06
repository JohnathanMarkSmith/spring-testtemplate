package com.johnathanmarksmith.springresttemplate;


import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.log4j.Logger;
import org.springframework.http.client.CommonsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import static org.apache.log4j.Logger.getLogger;


public class Main
{

    private static final Logger LOGGER = getLogger(Main.class);


    public static void main(String[] args)
    {
        LOGGER.debug("Starting REST Client!!!!");

        // UsernamePasswordCredentials credentials = new UsernamePasswordCredentials("test", "test");


        HttpClient client = new HttpClient();

        UsernamePasswordCredentials credentials =
                new UsernamePasswordCredentials("test","test");

        client.getState().setCredentials(
                new AuthScope("127.0.0.1", 8080, AuthScope.ANY_REALM),
                credentials);

        CommonsClientHttpRequestFactory commons = new CommonsClientHttpRequestFactory(client);

        RestTemplate restTemplate = new RestTemplate(commons);


        String jsonreturn = restTemplate.getForObject("http://127.0.0.1:8080/springmvc-rest-secured-test/json/Regan", String.class);

        LOGGER.debug(jsonreturn);
    }
}
