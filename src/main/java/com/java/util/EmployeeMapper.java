package com.java.util;

import com.java.domain.Employee;
import org.bson.Document;

public class EmployeeMapper {
    /**
     * Map objects {@link Document} to {@link Employee}.
     *
     * @param donutDocument the information document.
     * @return A object {@link Employee}.
     */
    public static Employee map(final Document donutDocument) {
      final Employee employee = new Employee();
        employee.setName(donutDocument.getString("name"));
      employee.setAddress(donutDocument.getString("address"));
      employee.setEmployeeId(donutDocument.getString("employeeId"));
      return employee;
    }
  }
