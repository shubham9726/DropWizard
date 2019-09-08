package com.java.health;

import com.codahale.metrics.health.HealthCheck;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCursor;

import java.util.ArrayList;
import java.util.List;

public class TemplateHealthCheck extends HealthCheck {
  private MongoClient mongoClient;

  public TemplateHealthCheck(MongoClient mongoClient) {
    this.mongoClient = mongoClient;
  }

  @Override
  protected Result check() throws Exception {
    List<String> dbs = new ArrayList<>();
    MongoCursor<String> dbsCursor = mongoClient.listDatabaseNames().iterator();
    while (dbsCursor.hasNext()) {
      dbs.add(dbsCursor.next());
    }
    if (dbs.size() > 0) {
      return Result.healthy("Database names in MongogDB are: " + dbs);
    }
    return Result.unhealthy("DropwizardMongoDB Service is down");
  }
}
