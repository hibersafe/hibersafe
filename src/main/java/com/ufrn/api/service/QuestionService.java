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

//	public List<ReturnDTO> getQuestionsByExceptionAlternative(ExceptionsEnum exceptionEnum, String message) {
//		List<Object[]> result = questionRepository.findQuestionByException(exceptionEnum.getName());
//		
//		List<ReturnDTO> returnDTO  = new ArrayList<ReturnDTO>();
//		
//		Session session = sessionFactory.openSession();
//        session.beginTransaction();
//		
//		for (Object[] r : result) {
//			List<String> idsList = Arrays.asList(r[1].toString().split(", "));
//			idsList = idsList.stream().map( id -> (id != null) ? "https://stackoverflow.com/questions/"+id : null ).collect(Collectors.toList());
//			if (r[2] != null && message.length() > 0 && (
//					(r[2].toString().toUpperCase().contains("CAUSED BY:") && message.toUpperCase().contains("CAUSED BY:")) || 
//					(r[2].toString().toUpperCase().contains(exceptionEnum.getExPackage().toUpperCase()) && message.toUpperCase().contains(exceptionEnum.getExPackage().toUpperCase())) ||
//					(r[2].toString().toUpperCase().contains("EXCEPTION IN THREAD: ") && message.toUpperCase().contains("EXCEPTION IN THREAD: "))
//					)) {
//				returnDTO.add(new ReturnDTO(r[0].toString(), idsList.size(), idsList, r[2].toString()));
//				session.save(new Log(Calendar.getInstance(), r[1].toString(), idsList.size(), r[0].toString(), exceptionEnum.getName()));
//			}
//		}
//		
//		if (result.isEmpty()) {
//			session.save(new Log(Calendar.getInstance(), null, 0, null, exceptionEnum.getName()));
//		}
//        
//        session.getTransaction().commit();
//        session.close();
//		
//		return returnDTO;
//	}
	
	public ResponseDTO getQuestionsByException(ExceptionsEnum exceptionEnum, String message) {
		List<Object[]> result = questionRepository.findQuestionByExceptionAlternative(exceptionEnum.getName());
		
		List<QuestionResponseDTO> questionResponseDTO  = new ArrayList<QuestionResponseDTO>();
		List<String> annotations = new ArrayList<String>();
		List<ReturnDTO> returnDTO  = new ArrayList<ReturnDTO>();
		List<TopSimilarityDTO> topSimilarityDTO = new ArrayList<TopSimilarityDTO>();
		
		Session session = sessionFactory.openSession();
        session.beginTransaction();
        
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
		
		for (Object[] r : result) {
			String subsequence = String.valueOf(lcs.longestCommonSubsequence(message, r[2].toString()));
			if ((r[2] != null && message != null && message.length() > 0 && (
					//(r[2].toString().toUpperCase().contains("CAUSED BY:") && message.toUpperCase().contains("CAUSED BY:")) || 
					//(r[2].toString().toUpperCase().contains(exceptionEnum.getExPackage().toUpperCase()) && message.toUpperCase().contains(exceptionEnum.getExPackage().toUpperCase())) ||
					//(r[2].toString().toUpperCase().contains("EXCEPTION IN THREAD: ") && message.toUpperCase().contains("EXCEPTION IN THREAD: "))
					//r[2].toString().toUpperCase().contains(message.toUpperCase()) || 
					subsequence.contains(exceptionEnum.getExPackage()))
					)
					|| r[2] != null && (message == null || message.length() == 0)) {
				questionResponseDTO.add(new QuestionResponseDTO(r[0].toString(), ((BigInteger) r[1]).intValue(), subsequence.length()));
				if (!annotations.contains(r[0].toString())) {
					annotations.add(r[0].toString());
				}
			}
		}
		
		Collections.sort(questionResponseDTO, (q1, q2) -> q2.getSimilarityLength().compareTo(q1.getSimilarityLength()));
		
		List<QuestionResponseDTO> topTen = new ArrayList<QuestionResponseDTO>();
		if (questionResponseDTO.size() > 0) {
			topTen = questionResponseDTO.subList(0, questionResponseDTO.size() > 10 ? 10 : questionResponseDTO.size());
			for (QuestionResponseDTO q : topTen) {
				topSimilarityDTO.add(new TopSimilarityDTO(q.getAnnotation(), "https://stackoverflow.com/questions/"+q.getId()));
			}
		}
		
		for (String annotation : annotations) {
			List<String> idsList = new ArrayList<String>();
			idsList = questionResponseDTO.stream().map( q -> (q.getAnnotation().equals(annotation)) ? "https://stackoverflow.com/questions/"+q.getId() : null ).collect(Collectors.toList());
			idsList.removeIf(i -> i == null);
			returnDTO.add(new ReturnDTO(annotation, idsList.size(), idsList));
			session.save(new Log(Calendar.getInstance(), String.join(", ", idsList), idsList.size(), annotation, exceptionEnum.getName()));
		}
		
		if (result.isEmpty()) {
			session.save(new Log(Calendar.getInstance(), null, 0, null, exceptionEnum.getName()));
		}
		
		Collections.sort(returnDTO, (r1, r2) -> r2.getCount().compareTo(r1.getCount()));
        
        session.getTransaction().commit();
        session.close();
		
		return new ResponseDTO(topSimilarityDTO, returnDTO);
	}

	
	
}
