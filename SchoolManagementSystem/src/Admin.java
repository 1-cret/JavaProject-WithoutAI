public class Admin extends Staff {

    public Admin(int staffId) {
        super(staffId);
    } 
    public Admin(String name, String email, String role, StaffStatus status, String password) {
        super(name, email, role, status, password);
    }
}
