package com.java.service;

import com.java.domain.Employee;
import com.mongodb.client.MongoCollection;
import io.dropwizard.testing.junit.DropwizardClientRule;
import org.bson.Document;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Arrays;
import java.util.List;

public class employeeServiceImplTest {

    private static final Employee employee;
    private static final MongoCollection<Document> collection = null;

    /*public static final String Name = "507f1f77bcf86cd799439011";*/

    static {
        employee = new Employee();
        employee.setName("Shubham");
        employee.setAddress("Agra");
        employee.setEmployeeId("1");
    }


    @Path("/donuts")
    @Produces(MediaType.APPLICATION_JSON)
    public static class DonutResource {

        @GET
        public List<Employee> getAll(){
            return Arrays.asList(employee);
        }

        @GET
        @Path("/{name}")
        public Employee getOne(@PathParam("name") @NotNull final String name) {
            if (name.equals("Shubham")) {
                final Employee employee = new Employee();
                employee.setName("Shubham");
                employee.setAddress("Agra");
                employee.setEmployeeId("1");
                return employee;
            } else {
                return null;
            }
        }

        @POST
        @Consumes(MediaType.APPLICATION_JSON)
        public void insertOne(final Employee employee) {
            if (employee == null) {
                throw new IllegalArgumentException("Information employee not valid.");
            }
        }

        @PUT
        @Path("/{name}")
        public void update(@PathParam("name") @NotNull final String name, @NotNull final Employee employee) {
            if (name != null) {
                //process the update.
            } else {
                throw new IllegalArgumentException("The information for update can not be null.");
            }
        }

        @DELETE
        @Path("/{name}")
        public void delete(@PathParam("name") @NotNull final String name) {
            if (name == null) {
                throw new IllegalArgumentException("Information name not valid.");
            }
        }
    }

    @ClassRule
    public static final DropwizardClientRule DROPWIZARD_CLIENT_RULE = new DropwizardClientRule(new DonutResource());

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void insertOne() {
    }

   /* @Test
    public void getAll() {
        final employeeServiceImpl employeeService = new employeeServiceImpl("/employee");
        final List<Employee> employees = employeeService.getAll();
        Assert.assertNotNull(employees);
        Assert.assertFalse(employees.isEmpty());
    }*/

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