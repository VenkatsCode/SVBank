package com.svbank.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity (name = "SecurityQuestions")
@Table(name="SECURITY_QUESTIONS")
public class SecurityQuestions {

	@Id
    @Column(name="Q_ID")
    private int questionId;
     
    @Column(name="QUESTION")
    private String question;

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}
    
    
	
}
