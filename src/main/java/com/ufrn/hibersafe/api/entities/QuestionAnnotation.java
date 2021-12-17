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
@Table(name = "question_annotation")
public class QuestionAnnotation {
	
	@Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_QUESTION_A")
	@SequenceGenerator(name="SEQ_QUESTION_A", sequenceName="id_seq_question_a", allocationSize=1)
    private long id;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    private Question question;
	
	@Column
	private String annotation;
	
	public QuestionAnnotation(Question question, String annotation) {
		this.question = question;
		this.annotation = annotation;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getAnnotation() {
		return annotation;
	}

	public void setAnnotation(String annotation) {
		this.annotation = annotation;
	}
	
	
	
}
