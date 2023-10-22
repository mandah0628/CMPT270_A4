import java.util.ArrayList;


public class Student extends Person {

    private int bedLabel;
    private ArrayList<Manager> studentManagers;
    private String NSID;

    public Student(String name, String sin, String NSID)
    {
        super(name, sin);
        this.bedLabel = -1;
        this.studentManagers = new ArrayList<Manager>();
        this.NSID = NSID;

    }


    public String getNSID()
    {
        return this.NSID;
    }

    public int getBedLabel()
    {
            return this.bedLabel;
    }

    public void setBedLabel(int bedLabel)
    {
        this.bedLabel = bedLabel;
    }

    public void addManager(Manager manager)
    {
        studentManagers.add(manager);
    }


    public void removeManager(String employeeID)
    {
        studentManagers.remove(employeeID);
    }

    public void hasManager(String employeeID)
    {

    }

    public String toString()
    {
        StringBuilder studentInfo = new StringBuilder();
        studentInfo.append("Student name: " + getName() + "\n");
        studentInfo.append("Student SIN: " + getSIN() + "\n");
        studentInfo.append("Student bed label: " + getBedLabel() + "\n");
        if(studentManagers.isEmpty() == false)
        {
            studentInfo.append("Managers are: ");
            for(Manager manager : studentManagers)
            {
                studentInfo.append(manager + ", ");
            }
            studentInfo.append("\n");
        }
        
        else
        {
            studentInfo.append("Managers are: \n");
        }

        return studentInfo.toString();
    }



    public static void main(String[] args)
    {
        Student student1 = new Student("Bruce Dickinson", "123456789", "brd123");
        student1.setBedLabel(5);
        System.out.println(student1.getSIN());
        System.out.println(student1.getNSID());
        System.out.println(student1.getName());
        System.out.println(student1.getBedLabel());
        
        
    }
}
