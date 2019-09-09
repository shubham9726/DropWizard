package com.java.resources;

import com.java.domain.Employee;
import com.java.service.EmployeeService;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.Assert;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;
import org.mockito.Mockito;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;

public class EmployeeResourceTest {

  private static final EmployeeService dao = Mockito.mock(EmployeeService.class);
  public static final String CONTEXT = "/employee";
  private static Employee employee;

  @ClassRule
  public static final ResourceTestRule resources = ResourceTestRule.builder()
    .addResource(new EmployeeResource(dao))
    .build();

  @Before
  public void setUp() throws Exception {
    employee = new Employee();
  }

  @Test
  public void createEmployee() {
    Mockito.doNothing().when(dao).insertOne(employee);
    final Response responseTest = resources.target(CONTEXT).path("/createEmployee").request().post(Entity.entity(employee, MediaType.APPLICATION_JSON));
    Assert.assertNotNull(responseTest);
    Assert.assertEquals(Response.Status.CREATED.getStatusCode(), responseTest.getStatus());
    Mockito.verify(dao, Mockito.times(1)).insertOne(employee);
  }

  @Test
  public void getEmployee() {
    Mockito.when(dao.getOne("shub")).thenReturn(employee);
    final Response responseTest = resources.target(CONTEXT).path("/findOne/shub").request().get();
    Assert.assertNotNull(responseTest);
    Assert.assertEquals(Response.Status.OK.getStatusCode(), responseTest.getStatus());
    Assert.assertEquals(employee, responseTest.readEntity(Employee.class));
  }

  @Test
  public void getAllEmployees() {
    Mockito.when(dao.getAll()).thenReturn(Collections.singletonList(employee));
    final Response responseTest = resources.target(CONTEXT).path("/getAllEmployee").request().get();
    Assert.assertNotNull(responseTest);
    Assert.assertEquals(Response.Status.OK.getStatusCode(), responseTest.getStatus());
  }

  @Test
  public void editEmployee() {
    Mockito.doNothing().when(dao).update("shub", employee);
    final Response responseTest = resources.target(CONTEXT).path("/updateEmployee/shub").request().put(Entity.entity(employee, MediaType.APPLICATION_JSON));
    Assert.assertNotNull(responseTest);
    Assert.assertEquals(Response.Status.OK.getStatusCode(), responseTest.getStatus());
  }

  @Test
  public void deleteEmployee() {
    Mockito.doNothing().when(dao).delete("shub");
    final Response responseTest = resources.target(CONTEXT).path("/deleteEmployee/shub").request().delete();
    Assert.assertNotNull(responseTest);
    Assert.assertEquals(Response.Status.OK.getStatusCode(), responseTest.getStatus());
  }
}