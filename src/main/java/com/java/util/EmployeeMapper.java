package com.java.util;

import com.java.domain.Employee;
import org.bson.Document;

public class EmployeeMapper {
    /**
     * Map objects {@link Document} to {@link Employee}.
     *
     * @param employeeDocument the information document.
     * @return A object {@link Employee}.
     */
    public static Employee map(final Document employeeDocument) {
      final Employee employee = new Employee();
        employee.setName(employeeDocument.getString("name"));
      employee.setAddress(employeeDocument.getString("address"));
      employee.setEmployeeId(employeeDocument.getString("employeeId"));
      return employee;
    }
  }
