package reflection.fieldsinspection;

import java.util.List;

public record Employee(
        long id,
        String name,
        boolean isActive,
        Address address,
        EmployeeType employeeType,
        List<Integer> recentConcepts,
        String[] partners,
        List<Job> jobs,
        int age,
        int[] hashing
) {
}
