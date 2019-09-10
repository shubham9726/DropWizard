package com.java.service;

import com.java.domain.Employee;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.bson.Document;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

public class EmployeeServiceTest {

  private static final MongoCollection collection = Mockito.mock(MongoCollection.class);
  private static Employee employee;
  private static EmployeeService employeeService;
  MongoCursor<Document> employees;

  @ClassRule
  public static final ResourceTestRule resources = ResourceTestRule.builder()
    .addResource(new EmployeeService(collection))
    .build();

  @Before
  public void setUp() throws Exception {
    employee = mock(Employee.class);
    employeeService = mock(EmployeeService.class);
    employees = mock(MongoCursor.class);
    employee.setName("Shubham");
    employee.setAddress("Agra");
    employee.setEmployeeId("1");
  }


  @Test
  public void insertOne() {
    final Document saveEmployee = new Document("name", employee.getName())
      .append("address", employee.getAddress())
      .append("employeeId", employee.getEmployeeId());
    Mockito.doNothing().when(collection).insertOne(saveEmployee);
    /*final Response responseTest;*/
    /*= resources.target(CONTEXT).path("/createEmployee").request().post(Entity.entity(employee, MediaType.APPLICATION_JSON));*/
    /*Assert.assertNotNull(responseTest);*/
    /*Assert.assertEquals(Response.Status.CREATED.getStatusCode());*/
   /* Mockito.verify(collection, Mockito.times(1)).insertOne(saveEmployee);*/
    /*Assert.assertEquals("Shubham", employee.getName());
    Assert.assertEquals("Agra", employee.getAddress());
    Assert.assertEquals("1", employee.getEmployeeId());*/
  }

  @Test
  public void getAll() {

  }

  @Test
  public void getOne() {
    /* when(collection.find()).thenReturn(new Document("shubham","Agra","1"));*/
    /*when(dao.getEmployeeById(1)).thenReturn(new EmployeeVO(1,"Lokesh","Gupta","user@email.com"));

    EmployeeVO emp = manager.getEmployeeById(1);

    assertEquals("Lokesh", emp.getFirstName());
    assertEquals("Gupta", emp.getLastName());
    assertEquals("user@email.com", emp.getEmail());*/
  }

  @Test
  public void update() {
    final Document update = (new Document("name", employee.getName()));
    final Document document = new Document("$set", new Document("address", employee.getAddress())
      .append("employeeId", employee.getEmployeeId()));
    Mockito.when(collection.updateOne(update, document));
  }

  @Test
  public void delete() {
    Mockito.when(collection.deleteOne(new Document("name", employee.getName())));
  }
}