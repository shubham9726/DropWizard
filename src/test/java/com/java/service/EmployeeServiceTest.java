package com.java.service;

import com.java.domain.Employee;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

public class EmployeeServiceTest {

  private static final MongoCollection collection = Mockito.mock(MongoCollection.class);
  private static EmployeeService employeeService;
//  private static List<Employee> employees;
//  private static EmployeeMapper employeeMapper;
  private static Employee employee;

  @Before
  public void setUp() throws Exception {
    employee = mock(Employee.class);
    employee = new Employee();
    employeeService = mock(EmployeeService.class);
  }

  @Test
  public void insertOne() {
    final Document saveEmployee = new Document("name", employee.getName())
      .append("address", employee.getAddress())
      .append("employeeId", employee.getEmployeeId());
    Mockito.doNothing().when(collection).insertOne(saveEmployee);
    Assert.assertNotNull(saveEmployee);

  }

  @Test
  public void getAll() {
    final Document employees = new Document("name", employee.getName())
      .append("address", employee.getAddress())
      .append("employeeId", employee.getEmployeeId());
    Mockito.when(collection.find(employees));
    Assert.assertNotNull(employees);
  }

  @Test
  public void getOne() {
    final Document getOne = new Document("name", employee.getName());
    Mockito.when(collection.find(getOne));
    Assert.assertNotNull(getOne);
  }

  @Test
  public void update() {
    final Document update = (new Document("name", employee));
    final Document document = new Document("$set", new Document("address", employee.getAddress())
      .append("employeeId", employee.getEmployeeId()));
    Mockito.when(collection.updateOne(update,document));
    Assert.assertNotNull(update);
  }

  @Test
  public void delete() {
    Mockito.when(collection.deleteOne(new Document("name", employee.getName())));
    Assert.assertNull(employee.getName());
  }
}