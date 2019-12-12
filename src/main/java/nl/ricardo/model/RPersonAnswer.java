package nl.ricardo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="person_answer")
public class RPersonAnswer implements Serializable {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    @Id
    private RPerson user;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    @Id
    private RQuestion question;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "answer_id", referencedColumnName = "id")
    private RAnswer answer;

    public RPersonAnswer(){}

    public RPersonAnswer(RPerson user, RQuestion question, RAnswer answer){
        this.user = user;
        this.question = question;
        this.answer = answer;
    }

    public RPerson getUser(){
        return this.user;
    }

    public void setUser(RPerson user){
        this.user = user;
    }

    public RQuestion getQuestion(){
        return this.question;
    }

    public void setQuestion(RQuestion question){
        this.question = question;
    }

    public RAnswer getAnswer(){
        return this.answer;
    }

    public void setAnswer(RAnswer answer){
        this.answer = answer;
    }

}
