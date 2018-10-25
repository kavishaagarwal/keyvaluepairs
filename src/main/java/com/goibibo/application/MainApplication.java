package com.goibibo.application;

import com.goibibo.config.ApplicationConfig;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import com.goibibo.resources.KeyValueResource;

public class MainApplication extends Application<ApplicationConfig> {
    public static void main(String[] args) throws Exception {
        new MainApplication().run(args);
    }

    @Override
    public void run(ApplicationConfig applicationConfig, Environment environment) throws Exception {
        final KeyValueResource keyValueResource = new KeyValueResource();
        environment.jersey().register(keyValueResource);
        //my comment
    }
}
