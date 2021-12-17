package com.ufrn.hibersafe.api.controller;

import java.io.IOException;

import org.hibernate.SessionFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ufrn.hibersafe.dtos.PageDTO;
import com.ufrn.hibersafe.utils.HibernateUtil;
import com.ufrn.hibersafe.utils.StackOverflowUtil;

@RestController
@RequestMapping("/api/stackoverflow")
public class StackOverflowController {
	
	@GetMapping("/miner")
	public ResponseEntity<?> getQuestionsAndAnswers() throws IOException, InterruptedException{
		
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

        StackOverflowUtil stackOverflowUtil = new StackOverflowUtil(sessionFactory);

        int pageNumber = 1;

        PageDTO page = stackOverflowUtil.sendHttpRequest(pageNumber);

        while (page.hasMore()) {
            // process data
            stackOverflowUtil.process(page);

            System.out.println("Processed page " + pageNumber);

            pageNumber++;
            page = stackOverflowUtil.sendHttpRequest(pageNumber);
        }

        //sessionFactory.close();
		
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
