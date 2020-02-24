package com.todoist.developer.rest.acceptance;

import com.todoist.developer.rest.acceptance.enums.Endpoint;
import com.todoist.developer.rest.acceptance.exception.InvalidTestPropertiesException;
import lombok.Setter;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.UUID;

@Setter
public abstract class BaseTest {
    private String propertyFilePath = "src/main/resources/config/config.properties";
    private String scheme;
    private String host;
    private String port;
    private String pathTasks;
    private String token;

    @BeforeClass
    protected void loadProperties() {
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream(propertyFilePath));
            setScheme(prop.getProperty("com.todoist.developer.scheme"));
            setHost(prop.getProperty("com.todoist.developer.host"));
            setPort(prop.getProperty("com.todoist.developer.port"));
            setPathTasks(prop.getProperty("com.todoist.developer.tasks.path"));
            setToken(prop.getProperty("com.todoist.developer.token"));
        } catch (IOException e) {
            throw new InvalidTestPropertiesException(String.format("%s properties file could not be found.", propertyFilePath));
        }
    }

    protected String getToken() {
        if (token == null) {
            throw new InvalidTestPropertiesException("Authorization request token could not be found.");
        }
        return token;
    }

    protected String getUUID() {
        return UUID.randomUUID().toString().substring(0, 35);
    }

    protected URI getURI(Endpoint endpoint) {
        String path = "";
        switch (endpoint) {
            case CREATE_A_NEW_TASK:
            case GET_ACTIVE_TASKS:
                path = pathTasks;
                break;
        }
        try {
            return new URI(scheme, host, path, null);
        } catch (URISyntaxException e) {
            throw new InvalidTestPropertiesException(String.format("URI for '%s' endpoint could not be built.", endpoint));
        }
    }
}
