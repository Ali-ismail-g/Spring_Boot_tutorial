package main.com.myApp.controller.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="student")
@Setter
@Getter
@NoArgsConstructor
//@ToString
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="student_name")
    private String studentName;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name="student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Courses> courses;

    public Student(String studentName) {
        this.studentName = studentName;
    }
}
