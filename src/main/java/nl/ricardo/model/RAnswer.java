package nl.ricardo.model;


import javax.persistence.*;

@Entity
@Table(name="answer")
public class RAnswer {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    private String answer;

    private boolean correct;

    @ManyToOne
    private RQuestion question;

    public RAnswer(){}

    public RAnswer(Long id, String answer, boolean correct){
        this.id = id;
        this.answer = answer;
        this.correct = correct;
    }

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getAnswer(){
        return this.answer;
    }

    public void setAnswer(String answer){
        this.answer = answer;
    }

    public boolean isCorrect(){
        return this.correct;
    }

    public void setBoolean(boolean correct){
        this.correct = correct;
    }

    public RQuestion getQuestion(){
        return this.question;
    }

    public void setQuestion(RQuestion question){
        this.question = question;
    }

}
