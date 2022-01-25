package com.ufrn.dtos;

import java.util.List;

public class ResponseDTO {
	
	List<TopSimilarityDTO> topSimilarity;
	List<ReturnDTO> returnDTO;
	
	public ResponseDTO(List<TopSimilarityDTO> topSimilarity, List<ReturnDTO> returnDTO) {
		this.topSimilarity = topSimilarity;
		this.returnDTO = returnDTO;
	}

	public List<TopSimilarityDTO> getTopSimilarity() {
		return topSimilarity;
	}
	
	public void setTopSimilarity(List<TopSimilarityDTO> topSimilarity) {
		this.topSimilarity = topSimilarity;
	}
	
	public List<ReturnDTO> getReturnDTO() {
		return returnDTO;
	}
	
	public void setReturnDTO(List<ReturnDTO> returnDTO) {
		this.returnDTO = returnDTO;
	}
	
	

}
