package Task10;

public class Student {
    public String name;
    public String gender;
    public int course;
    public double gpa;

    public Student(String name, String gender, int course, double gpa) {
        this.name = name;
        this.gender = gender;
        this.course = course;
        this.gpa = gpa;
    }

    public double getGpa() {
        return gpa;
    }

    public String getGender() {
        return gender;
    }

    public int getCourse() {
        return course;
    }

    public String getName() {
        return name;
    }
}
