package com.java;

import com.java.health.MongoManaged;
import com.java.health.TemplateHealthCheck;
import com.java.resources.EmployeeResource;
import com.java.service.employeeServiceImpl;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class hellodropwizardApplication extends Application<hellodropwizardConfiguration> {
  private static final Logger logger = LoggerFactory.getLogger(hellodropwizardApplication.class);

  public static void main(final String[] args) throws Exception {
    new hellodropwizardApplication().run(args);
  }

  /*@Override
  public String getName() {
    return "hellodropwizard";
  }*/

  @Override
  public void initialize(final Bootstrap<hellodropwizardConfiguration> bootstrap) {
    // TODO: application initialization
  }

  @Override
  public void run(hellodropwizardConfiguration configuration,Environment environment) {
    MongoClient mongoClient = new MongoClient(configuration.getMongoHost(), configuration.getMongoPort());
    MongoManaged mongoManaged = new MongoManaged(mongoClient);
    environment.lifecycle().manage(mongoManaged);
    MongoDatabase db = mongoClient.getDatabase(configuration.getMongoDB());
    MongoCollection<Document> collection = db.getCollection(configuration.getCollectionName());
    logger.info("Registering RESTful API resources");
    final employeeServiceImpl employeeServiceImpl = new employeeServiceImpl(collection);
/*
    environment.jersey().register(new employeeServiceImpl(collection, new EmployeeRepositry()));
*/
    environment.jersey().register(new EmployeeResource(employeeServiceImpl));
    environment.healthChecks().register("TemplateHealthCheck",
      new TemplateHealthCheck(mongoClient));
    }
}