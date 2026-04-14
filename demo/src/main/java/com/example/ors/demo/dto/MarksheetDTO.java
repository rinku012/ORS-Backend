package com.example.ors.demo.dto;


import jakarta.persistence.*;

@Entity
@Table(name = "st_marksheet") // Sets the table name in MySQL
public class MarksheetDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "ROLL_NO", length = 20, unique = true, nullable = false)
    private String rollNo;

    @Column(name = "STUDENT_ID")
    private long studentId;

    @Column(name = "NAME", length = 50)
    private String name;

    @Column(name = "PHYSICS")
    private int physics;

    @Column(name = "CHEMISTRY")
    private int chemistry;

    @Column(name = "MATHS")
    private int maths;

    // Default Constructor
    public MarksheetDTO() {}

    // Getters and Setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public String getRollNo() { return rollNo; }
    public void setRollNo(String rollNo) { this.rollNo = rollNo; }

    public long getStudentId() { return studentId; }
    public void setStudentId(long studentId) { this.studentId = studentId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getPhysics() { return physics; }
    public void setPhysics(int physics) { this.physics = physics; }

    public int getChemistry() { return chemistry; }
    public void setChemistry(int chemistry) { this.chemistry = chemistry; }

    public int getMaths() { return maths; }
    public void setMaths(int maths) { this.maths = maths; }

    public int getTotal() {
    return physics + chemistry + maths;
}

public String getGrade() {
    int total = getTotal();
    if (total >= 225) return "A+";
    if (total >= 180) return "A";
    if (total >= 150) return "B";
    return "C";
}
}