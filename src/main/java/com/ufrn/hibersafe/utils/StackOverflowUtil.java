package com.ufrn.hibersafe.utils;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ufrn.hibersafe.api.entities.Question;
import com.ufrn.hibersafe.api.entities.QuestionAnnotation;
import com.ufrn.hibersafe.api.entities.QuestionException;
import com.ufrn.hibersafe.dtos.PageDTO;
import com.ufrn.hibersafe.dtos.QuestionDTO;

public class StackOverflowUtil {

    SessionFactory sessionFactory;

    public StackOverflowUtil(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public PageDTO sendHttpRequest(int page) throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder().GET()
                .uri(URI.create("https://api.stackexchange.com/2.3/questions?" + "fromdate=1598918400&"
                        + "todate=1630454400&" + "page=" + page + "&" + "pagesize=100&" + "order=asc&"
                        + "sort=creation&" + "tagged=hibernate&" + "site=stackoverflow&"
                        + "filter=!b*Ar.5VD4mpdg2L*VkuIiXMYmkK*r5"))
                .build();

        HttpResponse<byte[]> response = client.send(request, BodyHandlers.ofByteArray());

        GZIPInputStream gzipInputStream = new GZIPInputStream(new ByteArrayInputStream(response.body()));

        String jsonString = new String(gzipInputStream.readAllBytes(), StandardCharsets.UTF_8);

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();

        JsonNode jsonNode = mapper.readTree(jsonString);

        return mapper.readValue(jsonNode.toString(), PageDTO.class);
    }

    public void process(PageDTO page) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        // Iterate through all pages
        for (QuestionDTO item : page.getItems()) {

            // Check question body for annotations
            Pattern patternAnnotation = Pattern.compile(Annotations.HIBERNATE_ANNOTATIONS + "|" + Annotations.JPA_ANNOTATIONS);

            Pattern patternException = Pattern.compile(Exceptions.HIBERNATE_EXCEPTIONS);
            
            Matcher matcher1 = patternAnnotation.matcher(item.getBody());
            Matcher matcher2 = patternException.matcher(item.getBody());
            
            List<String> annotations = new ArrayList<String>();
            List<String> exceptions = new ArrayList<String>();

            // If there's an annotation and an exception on the question
            if (matcher1.find() && matcher2.find()) {
            	matcher1.reset();
            	matcher2.reset();
            	
            	// Convert questionDTO to Question entity
                Question question = item.toQuestion();

                // Save Question entity
                session.save(question);
                
                // If QuestionDTO has answers, convert them to entities and save them
                if (item.getAnswers() != null) {
                    item.getAnswers().stream().map(answer -> answer.toAnswer(question)).forEach(session::save);
                }
            	
            	while(matcher1.find()){
            		if (!annotations.contains(matcher1.group())){
            			annotations.add(matcher1.group());
            		}
            	}
            	
            	while(matcher2.find()){
            		if (!exceptions.contains(matcher2.group())){
            			exceptions.add(matcher2.group());
            		}
            	}
            	
            	for (String annotation : annotations) {
            		session.save(new QuestionAnnotation(question, annotation));
            	}
            	
            	for (String exception : exceptions) {
        			session.save(new QuestionException(question, exception));
                    
        		}
            }
            
        }

        session.getTransaction().commit();

        session.close();
    }
}
