package com.example.ApiJpBarbearia.enums;

import lombok.Getter;

@Getter
public enum AgendamentoStatusEnum {

	AGENDADO("agendado"),
	REALIZADO("realizado"),
	PENDENTE("pendente"),
	DEVENDO("devendo");
	
	 private String status;

	 private AgendamentoStatusEnum(String status){
	      this.status = status;
	    }

	 public String getStatus(){
	        return status;
	  }
}
