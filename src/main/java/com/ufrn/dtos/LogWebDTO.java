package com.ufrn.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LogWebDTO {

    @JsonProperty("estrategia")
    private String estrategia;

    @JsonProperty("dados")
    private String[] dados;

    @JsonProperty("id")
    private Integer idUsuario;
    
    public LogWebDTO() {
    	
    }

	public LogWebDTO(String estrategia, String[] dados, Integer idUsuario) {
		super();
		this.estrategia = estrategia;
		this.dados = dados;
		this.idUsuario = idUsuario;
	}

	public String getEstrategia() {
		return estrategia;
	}

	public void setEstrategia(String estrategia) {
		this.estrategia = estrategia;
	}

	public String[] getDados() {
		return dados;
	}

	public void setDados(String[] dados) {
		this.dados = dados;
	}

	public Integer getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Integer idUsuario) {
		this.idUsuario = idUsuario;
	}
    
    
}
