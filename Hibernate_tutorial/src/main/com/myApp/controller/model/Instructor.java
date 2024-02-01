package main.com.myApp.controller.model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="instructor")
@Setter
@Getter
@NoArgsConstructor
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="instructor_name")
    private String instructorName;

    @OneToMany(mappedBy = "instructor",fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
   // @JoinColumn(name="instructor_id")
    private List<Course> courses;

    public void addCourse(Course course){
        if(courses == null){
            courses = new ArrayList<>();
        }
        courses.add(course);
        course.setInstructor(this);
    }

    @Override
    public String toString(){
        String str="";
        str="Instructor{"+ instructorName+'}';
        return str;
    }
    public Instructor(String instructorName) {
        this.instructorName = instructorName;
    }
}
