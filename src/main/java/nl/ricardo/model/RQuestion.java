package nl.ricardo.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="question")
public class RQuestion {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String question;

    @OneToMany(mappedBy = "id")
    private List<RAnswer> answers;

    public RQuestion(){}

    public RQuestion(Long id, String question, List<RAnswer> answers){
        this.id = id;
        this.question = question;
        this.answers = answers;
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getQuestion(){
        return this.question;
    }

    public void setQuestion(String question){
        this.question = question;
    }

    public List<RAnswer> getAnswers(){
        return this.answers;
    }

    public void setAnswers(List<RAnswer> answers){
        this.answers = answers;
    }

    public void addAnswer(RAnswer answer){
        if(this.answers == null)
            this.answers = new ArrayList<RAnswer>();
        answers.add(answer);
    }

}
