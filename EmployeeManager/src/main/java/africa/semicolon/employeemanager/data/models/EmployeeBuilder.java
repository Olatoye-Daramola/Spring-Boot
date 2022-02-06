package africa.semicolon.employeemanager.data.models;

public class EmployeeBuilder {
    private String firstName;
    private String lastName;
    private String email;
    private Employee employee;

    public EmployeeBuilder setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public EmployeeBuilder setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public Employee build() {
        return new Employee(firstName, lastName);
    }
}
