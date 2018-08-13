package com.stat;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.stat.rabbit.RabbitConsumer;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import com.stat.module.MainBundle;
import ru.vyarus.dropwizard.guice.GuiceBundle;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.io.IOException;
import java.util.EnumSet;
import java.util.concurrent.TimeoutException;

public class StatsApplication extends Application<StatsConfiguration> {

    public static void main(final String[] args) throws Exception {
        new StatsApplication().run(args);
    }

    @Override
    public String getName() {
        return "stats";
    }

    @Override
    public void initialize(final Bootstrap<StatsConfiguration> bootstrap) {
        bootstrap.addBundle(GuiceBundle.builder()
                .enableAutoConfig(getClass().getPackage().getName())
                .modules(new MainBundle())
                .build());
    }

    @Override
    public void run(final StatsConfiguration configuration,
                    final Environment environment) {
        environment.getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
        this.setCORSconfiguration(environment);
        this.configUniRest();
        this.startRabbitConsumer();
    }

    private void startRabbitConsumer() {
        RabbitConsumer consumer = null;
        try {
            consumer = new RabbitConsumer("user-kudos");
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
        }

        Thread consumerThread = new Thread(consumer);
        consumerThread.start();
    }

    private void setCORSconfiguration(Environment environment) {
        FilterRegistration.Dynamic filter = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
        filter.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,PUT,POST,DELETE,OPTIONS");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        filter.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        filter.setInitParameter(CrossOriginFilter.EXPOSED_HEADERS_PARAM,
                "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin,Location,username,password,token,admin,message,branch_office");
        filter.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM,
                "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin,Location,username,password,token,admin,message,branch_office");
        filter.setInitParameter(CrossOriginFilter.ALLOW_CREDENTIALS_PARAM, "true");
    }

    private void configUniRest() {
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}
