package main.com.myApp.controller.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="question")
@Setter
@Getter
@NoArgsConstructor
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="question_name")
    private String questionName;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name="qid")
    private List<Answer> answers;

    public Question(String questionName) {
        this.questionName = questionName;
    }
}
