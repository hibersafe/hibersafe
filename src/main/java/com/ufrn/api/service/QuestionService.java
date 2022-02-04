package com.ufrn.api.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.text.similarity.LongestCommonSubsequence;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ufrn.api.entities.Log;
import com.ufrn.api.entities.Question;
import com.ufrn.api.repository.QuestionRepository;
import com.ufrn.dtos.ExceptionsEnum;
import com.ufrn.dtos.QuestionDTO;
import com.ufrn.dtos.QuestionResponseDTO;
import com.ufrn.dtos.ResponseDTO;
import com.ufrn.dtos.ReturnDTO;
import com.ufrn.dtos.TopSimilarityDTO;

@Service
@Transactional
public class QuestionService {
	
	@Autowired
	QuestionRepository questionRepository;
	
	@Autowired
	SessionFactory sessionFactory;

	public List<QuestionDTO> getQuestions() {
		List<Question> questions = questionRepository.findAll();
		
		List<QuestionDTO> questionsDTO = questions.stream().map(QuestionDTO::new).collect(Collectors.toList());
		
		return questionsDTO;
	}
	
	public List<QuestionDTO> getQuestionsByAnnotation(String annotation) {
		List<Question> questions = questionRepository.findQuestionByAnnotation("%@"+annotation+"%");
		
		List<QuestionDTO> questionsDTO = questions.stream().map(QuestionDTO::new).collect(Collectors.toList());
		
		return questionsDTO;
	}

	
	public ResponseDTO getQuestionsByException(String message) {
		List<Object[]> result = questionRepository.findAllQuestions();
		
		List<QuestionResponseDTO> questionResponseDTO  = new ArrayList<QuestionResponseDTO>();
		List<String> annotations = new ArrayList<String>();
		List<ReturnDTO> returnDTO  = new ArrayList<ReturnDTO>();
		List<TopSimilarityDTO> topSimilarityDTO = new ArrayList<TopSimilarityDTO>();
		
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
		
		for (Object[] r : result) {
			String subsequence = String.valueOf(lcs.longestCommonSubsequence(message, r[2].toString()));
			Integer indexCausedBy = r[2].toString().toUpperCase().indexOf("CAUSED BY:");
			Integer indexExceptionInThread = r[2].toString().toUpperCase().indexOf("EXCEPTION IN THREAD");
			
			if (indexCausedBy >= 0 || indexExceptionInThread >= 0) {
				questionResponseDTO.add(new QuestionResponseDTO(r[0].toString(), ((BigInteger) r[1]).intValue(), subsequence.length()));
				if (!annotations.contains(r[0].toString())) {
					annotations.add(r[0].toString());
				}
			}
		}
		
		Collections.sort(questionResponseDTO, (q1, q2) -> q2.getSimilarityLength().compareTo(q1.getSimilarityLength()));
		
		String annotationsList = "";
		List<Integer> idsListTop = new ArrayList<Integer>();
		if (questionResponseDTO.size() > 0) {
			for (QuestionResponseDTO question : questionResponseDTO) {
				if (!idsListTop.contains(question.getId())) {
					idsListTop.add(question.getId());
					List<QuestionResponseDTO> sameAnnotation = questionResponseDTO.stream().filter(q -> q.getId().equals(question.getId())).collect(Collectors.toList());
					for (QuestionResponseDTO s : sameAnnotation) {
						annotationsList = annotationsList.length() == 0 ? annotationsList.concat(s.getAnnotation()) : annotationsList.concat(", " + s.getAnnotation());
					}
					topSimilarityDTO.add(new TopSimilarityDTO(annotationsList, "https://stackoverflow.com/questions/"+question.getId()));
				}
			}
			
			topSimilarityDTO = topSimilarityDTO.subList(0, topSimilarityDTO.size() > 10 ? 10 : topSimilarityDTO.size());
		}
		
		for (String annotation : annotations) {
			List<String> idsList = new ArrayList<String>();
			idsList = questionResponseDTO.stream().map( q -> (q.getAnnotation().equals(annotation)) ? "https://stackoverflow.com/questions/"+q.getId() : null ).collect(Collectors.toList());
			idsList.removeIf(i -> i == null);
			returnDTO.add(new ReturnDTO(annotation, idsList.size(), idsList));
			session.save(new Log(Calendar.getInstance(), String.join(", ", idsList), idsList.size(), annotation, null));
		}
		
		if (result.isEmpty()) {
			session.save(new Log(Calendar.getInstance(), null, 0, null, null));
		}
		
		Collections.sort(returnDTO, (r1, r2) -> r2.getCount().compareTo(r1.getCount()));
        
        session.getTransaction().commit();
        session.close();
		
		return new ResponseDTO(topSimilarityDTO, returnDTO);
	}
}
