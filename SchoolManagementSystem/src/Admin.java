import java.util.ArrayList;

public class Admin extends Staff {

    public Admin(int staffId) {
        super(staffId);
    } 
    public Admin(String name, String email, String role, StaffStatus status, String password) {
        super(name, email, role, status, password);
    }
    public static Admin login(String email, String password) {
        ArrayList<Admin> admins = FileDataStore.loadAdmins();
        for (Admin admin : admins) {
            if (admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
                return admin;
            }
        }
        return null;
    }
}
