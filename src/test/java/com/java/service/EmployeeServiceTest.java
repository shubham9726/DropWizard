package com.java.service;

import com.java.domain.Employee;
import com.mongodb.client.MongoCollection;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.bson.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

public class EmployeeServiceTest {

  private static MongoCollection<Document> collection;
  private static Employee employee;

  @ClassRule
  public static final ResourceTestRule resources = ResourceTestRule.builder()
    .addResource(new EmployeeService(collection))
    .build();

  @Before
  public void setUp() throws Exception {
    collection = mock(MongoCollection.class);
    employee = mock(Employee.class);


  }

  @Test
  public void insertOne() {
    final Document saveEmployee =new Document("name", employee.getClass());
      /*.append("address", employee.getAddress())
      .append("employeeId", employee.getEmployeeId());*/
    Mockito.doNothing().when(collection).insertOne(saveEmployee);
    final ResourceTestRule responseTest = resources;
    Assert.assertNotNull(responseTest);
    /*Assert.assertEquals(Response.Status.CREATED.getStatusCode(), responseTest.getStatus());*/
    Mockito.verify(collection).insertOne(saveEmployee);
  }

  @Test
  public void getAll() {
  }

  @Test
  public void getOne() {
  }

  @Test
  public void update() {
  }

  @Test
  public void delete() {
  }
}