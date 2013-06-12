###  Spring's RESTTemplate with Spring Java Configuration (JavaConfig) style, Maven,  Log4J

This is a sample Spring Project to show how to use Spring's Java Configuration (JavaConfig) style with RESTTemplate and Log4J.

This example also will show how to use @PropertySource for reading properties and using the Environment Object to add properties to your objects

and how to make secured REST calls using Spring RESTTemplate.

### How to use Spring's Java Configuration (JavaConfig) style and not XML files for configuation

Consider replacing Spring XML configuration with Spring's Java Configuration (JavaConfig) style is the right way to go right now.


Here is the main code to my sample project

    @PropertySource("classpath:application.properties")
    public class Main
    {

        /**
         * Setting up logger
         */
        private static final Logger LOGGER = getLogger(Main.class);


        public static void main(String[] args) throws IOException
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
            vars.put("name", "JohnathanMarkSmith");


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
            } catch (HttpClientErrorException e)
            {
                /**
                 *
                 * If we get a HTTP Exception display the error message
                 */

                LOGGER.error("error:  " + e.getResponseBodyAsString());

                ObjectMapper mapper = new ObjectMapper();
                ErrorHolder eh = mapper.readValue(e.getResponseBodyAsString(), ErrorHolder.class);

                LOGGER.error("error:  " + eh.errorMessage);

            }

        }


    }

Now lets take a good look at how I setup my bean  with Spring's Java Configuration (JavaConfig) style and not in a XML file.


    @Configuration
    @EnableTransactionManagement
    @ComponentScan(basePackageClasses = {Main.class})
    @PropertySource("classpath:application.properties")
    public class RESTConfiguration
    {

        @Bean
        public RESTServer restServer(Environment env)
        {
               return new RESTServer(env.getProperty("rest.user"),
                                      env.getProperty("rest.password"),
                                      env.getProperty("rest.host"));
        }
    }


You can see how easy it is to use Spring's Java Configuration (JavaConfig) style and Not XML.. The time of using XML files with Springs is over...

checkout the project from github.

    git clone git@github.com:JohnathanMarkSmith/spring-testemplate.git
    cd spring-testemplate

This Project is using Java, Spring, Spring RESTTemplate, Maven,  Log4J and Github.

If you have any questions please email me at john@johnathanmarksmith.com