package com.johnathanmarksmith.springresttemplate;


import com.johnathanmarksmith.springresttemplate.model.RESTServer;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.client.CommonsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

import static org.apache.log4j.Logger.getLogger;

@PropertySource("classpath:application.properties")
public class Main
{

    /**
     *
     * Setting up logger
     *
     */
    private static final Logger LOGGER = getLogger(Main.class);


    public static void main(String[] args)
    {
        LOGGER.debug("Starting REST Client!!!!");

        /**
         *
         * This is going to setup the REST server configuration in the applicationContext
         * you can see that I am using the new Spring's Java Configuration style and not some OLD XML file
         *
         */
        ApplicationContext context = new AnnotationConfigApplicationContext(RESTConfiguration.class);

        /**
         *
         * We now get a RESTServer bean from the ApplicationContext which has all the data we need to
         * log into the REST service with.
         *
         */
        RESTServer mRESTServer = context.getBean(RESTServer.class);

        /**
         *
         * setting up user credentials using the RESTServer bean we just got
         *
         */
        HttpClient client = new HttpClient();
                UsernamePasswordCredentials credentials =
                new UsernamePasswordCredentials(mRESTServer.getUser(), mRESTServer.getPassword());

        client.getState().setCredentials(
                new AuthScope(mRESTServer.getHost(), 8080, AuthScope.ANY_REALM),
                credentials);

        CommonsClientHttpRequestFactory commons = new CommonsClientHttpRequestFactory(client);


        /**
         *
         * Setting up data to be sent to REST service
         *
         */
        Map<String, String> vars = new HashMap<String, String>();
        vars.put("name", "JohnathanMark2Smith");


        /**
         *
         * Doing the REST call and then displaying the data/user object
         *
         */
        RestTemplate restTemplate = new RestTemplate(commons);
        restTemplate.getMessageConverters().add(new MappingJacksonHttpMessageConverter());
        restTemplate.getMessageConverters().add(new StringHttpMessageConverter());

        try
        {
            String jsonreturn = restTemplate.getForObject("http://" + mRESTServer.getHost() + ":8080/springmvc-rest-secured-test/json/{name}", String.class, vars);
            LOGGER.debug("return object:  " + jsonreturn.toString());
        }
        catch(HttpClientErrorException e)
        {
            LOGGER.error("error:  " + e.getResponseBodyAsString());
        }

    }
}
