package com.kudo;

import com.kudo.core.User;
import com.kudo.dao.UserDao;
import com.kudo.resources.UserResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by brenda on 22/07/2018.
 */
public class KudosApplication extends Application<KudosConfiguration> {

    private static final Logger LOG = LoggerFactory.getLogger(KudosApplication.class);

    public static void main(String[] args) throws Exception {
        new KudosApplication().run(args);
    }

    private final HibernateBundle<KudosConfiguration> hibernate = new HibernateBundle<KudosConfiguration>(User.class) {
        public DataSourceFactory getDataSourceFactory(KudosConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    @Override
    public void initialize(Bootstrap<KudosConfiguration> bootstrap) {
        bootstrap.addBundle(hibernate);
    }

    @Override
    public void run(KudosConfiguration configuration, Environment environment) throws ClassNotFoundException {
        final UserDao userDao = new UserDao(hibernate.getSessionFactory());
        final UserResource userResource = new UserResource(userDao);
        environment.jersey().register(userResource);
    }
}