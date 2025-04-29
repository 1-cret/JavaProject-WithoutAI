import java.io.*;
import java.util.ArrayList;

public class FileDataStore {
    private static final String DATA_DIRECTORY = "data/";
    private static final String STUDENTS_FILE = DATA_DIRECTORY + "students.txt";
    private static final String ADMINS_FILE = DATA_DIRECTORY + "admins.txt";
    private static final String TEACHERS_FILE = DATA_DIRECTORY + "teachers.txt";

    static {
        File directory = new File(DATA_DIRECTORY);
        if (!directory.exists()) {
            directory.mkdirs();
        }
    }

    public static ArrayList<Student> loadStudents() {
        ArrayList<Student> students = new ArrayList<>();
        try {
            File file = new File(STUDENTS_FILE);
            if (!file.exists()) {
                file.createNewFile();
                return students;
            }

            FileInputStream fis = new FileInputStream(file);
            int content;
            StringBuilder sb = new StringBuilder();
            while ((content = fis.read()) != -1) {
                sb.append((char) content);
            }
            fis.close();

            String[] lines = sb.toString().split("\n");
            for (String line : lines) {
                String[] data = line.split(",");
                if (data.length >= 5) {
                    int studentID = Integer.parseInt(data[0]);
                    String name = data[1];
                    String email = data[2];
                    String password = data[3];
                    float annualFee = Float.parseFloat(data[4]);
                    int year = Integer.parseInt(data[5]);

                    Student student = new Student(name, email, annualFee, year, password);
                    students.add(student);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading students: " + e.getMessage());
        }
        return students;
    }

    public static ArrayList<Admin> loadAdmins() {
        ArrayList<Admin> admins = new ArrayList<>();
        try {
            File file = new File(ADMINS_FILE);
            if (!file.exists()) {
                file.createNewFile();
                return admins;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 5) {
                    String name = data[0];
                    String email = data[1];
                    String role = data[2];
                    StaffStatus status = StaffStatus.valueOf(data[3]);
                    String password = data[4];

                    Admin admin = new Admin(name, email, role, status, password);
                    admins.add(admin);
                }
            }
            br.close();
        } catch (IOException e) {
            System.err.println("Error loading admins: " + e.getMessage());
        }
        return admins;
    }

    public static ArrayList<Teacher> loadTeachers() {
        ArrayList<Teacher> teachers = new ArrayList<>();
        try {
            File file = new File(TEACHERS_FILE);
            if (!file.exists()) {
                file.createNewFile();
                return teachers;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 5) {
                    String name = data[0];
                    String email = data[1];
                    String role = data[2];
                    StaffStatus status = StaffStatus.valueOf(data[3]);
                    String password = data[4];

                    Teacher teacher = new Teacher(name, email, role, status, password);
                    teachers.add(teacher);
                }
            }
            br.close();
        } catch (IOException e) {
            System.err.println("Error loading teachers: " + e.getMessage());
        }
        return teachers;
    }

    public static void saveStudents(ArrayList<Student> students) {
        try {
            FileOutputStream fos = new FileOutputStream(STUDENTS_FILE);
            for (Student student : students) {
                String line = student.getStudentID() + "," +
                              student.getName() + "," +
                              student.getEmail() + "," +
                              student.getPassword() + "," +
                              student.getAnnualFee() + "," +
                              student.getYear() + "\n";
                fos.write(line.getBytes());
            }
            fos.close();
        } catch (IOException e) {
            System.err.println("Error saving students: " + e.getMessage());
        }
    }

    public static void saveClassrooms(ArrayList<Classroom> classrooms) {
        try {
            FileOutputStream fos = new FileOutputStream(DATA_DIRECTORY + "classrooms.txt");
            for (Classroom classroom : classrooms) {
                String line = classroom.getClassroomId() + "," +
                              classroom.getRoomName() + "," +
                              classroom.getCapacity() + "\n";
                fos.write(line.getBytes());
            }
            fos.close();
        } catch (IOException e) {
            System.err.println("Error saving classrooms: " + e.getMessage());
        }
    }

    public static void saveEnrollments(ArrayList<Enrollment> enrollments) {
        try {
            FileOutputStream fos = new FileOutputStream(DATA_DIRECTORY + "enrollments.txt");
            for (Enrollment enrollment : enrollments) {
                String line = enrollment.getEnrollmentID() + "," +
                              enrollment.getStudent().getStudentID() + "," +
                              enrollment.getModule().getModuleID() + "," +
                              enrollment.getEnrollmentStatus() + "," +
                              enrollment.getGrade() + "\n";
                fos.write(line.getBytes());
            }
            fos.close();
        } catch (IOException e) {
            System.err.println("Error saving enrollments: " + e.getMessage());
        }
    }

    public static void saveModules(ArrayList<Module> modules) {
        try {
            FileOutputStream fos = new FileOutputStream(DATA_DIRECTORY + "modules.txt");
            for (Module module : modules) {
                String line = module.getModuleID() + "," +
                              module.getModuleName() + "," +
                              module.getMaxCapacity() + "," +
                              module.getModuleYear() + "\n";
                fos.write(line.getBytes());
            }
            fos.close();
        } catch (IOException e) {
            System.err.println("Error saving modules: " + e.getMessage());
        }
    }

    public static void savePayments(ArrayList<Payment> payments) {
        try {
            FileOutputStream fos = new FileOutputStream(DATA_DIRECTORY + "payments.txt");
            for (Payment payment : payments) {
                String line = payment.getPaymentID() + "," +
                              payment.getAmount() + "," +
                              payment.getPayeeId().getStudentID() + "," +
                              payment.getDescription() + "," +
                              payment.getDate() + "\n";
                fos.write(line.getBytes());
            }
            fos.close();
        } catch (IOException e) {
            System.err.println("Error saving payments: " + e.getMessage());
        }
    }

    public static void saveSessions(ArrayList<Session> sessions) {
        try {
            FileOutputStream fos = new FileOutputStream(DATA_DIRECTORY + "sessions.txt");
            for (Session session : sessions) {
                String line = session.getSessionID() + "," +
                              session.getModule().getModuleID() + "," +
                              session.getSessionName() + "," +
                              session.getStartTime() + "," +
                              session.getEndTime() + "," +
                              session.getClassroom().getClassroomId() + "," +
                              session.getStatus() + "\n";
                fos.write(line.getBytes());
            }
            fos.close();
        } catch (IOException e) {
            System.err.println("Error saving sessions: " + e.getMessage());
        }
    }

    public static void saveTeachers(ArrayList<Teacher> teachers) {
        try {
            FileOutputStream fos = new FileOutputStream(DATA_DIRECTORY + "teachers.txt");
            for (Teacher teacher : teachers) {
                String line = teacher.getStaffId() + "," +
                              teacher.getName() + "," +
                              teacher.getEmail() + "," +
                              teacher.getPassword() + "," +
                              teacher.getStatus() + "\n";
                fos.write(line.getBytes());
            }
            fos.close();
        } catch (IOException e) {
            System.err.println("Error saving teachers: " + e.getMessage());
        }
    }
}