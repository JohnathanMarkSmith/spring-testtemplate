package com.johnathanmarksmith.springresttemplate;


import com.johnathanmarksmith.springresttemplate.model.User;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.log4j.Logger;
import org.springframework.http.client.CommonsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.apache.log4j.Logger.getLogger;


public class Main
{

    private static final Logger LOGGER = getLogger(Main.class);


    public static void main(String[] args)
    {
        LOGGER.debug("Starting REST Client!!!!");

        /*
            setting up user credentials
         */

        HttpClient client = new HttpClient();

        UsernamePasswordCredentials credentials =
                new UsernamePasswordCredentials("test", "test");

        client.getState().setCredentials(
                new AuthScope("127.0.0.1", 8080, AuthScope.ANY_REALM),
                credentials);

        CommonsClientHttpRequestFactory commons = new CommonsClientHttpRequestFactory(client);


        /*
            setting up data to sent over
         */
        Map<String, String> vars = new HashMap<String, String>();
        vars.put("name", "JohnathanMarkSmith");

        /*
            doing the code and displaying the return
         */
        RestTemplate restTemplate = new RestTemplate(commons);
        restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        User jsonreturn = restTemplate.getForObject("http://127.0.0.1:8080/springmvc-rest-secured-test/json/{name}", User.class, vars);

        LOGGER.debug("return object:  " + jsonreturn.toString());
    }
}
