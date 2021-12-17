package com.ufrn.hibersafe.api.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "question_exception")
public class QuestionException {
	
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_QUESTION_E")
	@SequenceGenerator(name="SEQ_QUESTION_E", sequenceName="id_seq_question_e", allocationSize=1)
    private long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Question question;
	
	@Column
	private String exception;
	
	public QuestionException(Question question, String exception) {
		this.question = question;
		this.exception = exception;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}
	
	
	
}
