package com.ufrn.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufrn.api.service.QuestionService;
import com.ufrn.dtos.AnnotationsEnum;
import com.ufrn.dtos.ExceptionsEnum;
import com.ufrn.dtos.QuestionDTO;
import com.ufrn.dtos.ResponseDTO;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/question")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	@GetMapping("/all")
	@ApiOperation(value = "Get all questions from database")
	public ResponseEntity<List<QuestionDTO>> getQuestions() {
		
		List<QuestionDTO> questions = questionService.getQuestions();
		
		return new ResponseEntity<List<QuestionDTO>>(questions, HttpStatus.OK);
	}
	
	@GetMapping("/annotation/{annotation}")
	@ApiOperation(value = "Get questions by annotation", notes = "Select an annotation to find questions from StackOverflow according to the following criteria: \n1. Annotation appears on question and at least one answer texts \n2. At least one answer was validated or upvoted ")
	public ResponseEntity<List<QuestionDTO>> getQuestionsByAnnotation(@PathVariable AnnotationsEnum annotation) {
		
		List<QuestionDTO> questions = questionService.getQuestionsByAnnotation(annotation.toString());
		
		return new ResponseEntity<List<QuestionDTO>>(questions, HttpStatus.OK);
	}
	
	@PostMapping("/exceptionEnum/{exceptionEnum}")
	@ApiOperation(value = "Get questions by exceptionEnum", notes = "Select an exceptionEnum to find questions from StackOverflow according to the following criteria: \n1. Exception appears on question text \n2. At least one answer was validated or upvoted ")
	public ResponseEntity<ResponseDTO> getQuestionsByException(@PathVariable ExceptionsEnum exceptionEnum, @RequestBody(required=false) String message) {
		
		ResponseDTO questions = questionService.getQuestionsByException(exceptionEnum, message);
		
		return new ResponseEntity<ResponseDTO>(questions, HttpStatus.OK);
	}

}
