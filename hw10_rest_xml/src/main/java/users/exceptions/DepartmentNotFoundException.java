package users.exceptions;

public class DepartmentNotFoundException extends Exception{
    public DepartmentNotFoundException(String id) {
        super("Department not found by id: " + id);
    }
}
