package com.anand.nlp.model;


//Available annotator type in NLP as enum
public enum Type {
	
	PERSON("Person"),
	CITY("City"),
	STATE_OR_PROVINCE("State_Or_Province"),
	COUNTRY("Country"),
	TITLE("Title"),
	EMAIL("Email"),
	SENTIMENT("Sentiment");
	
	private String type;
	
	Type(String type){
		this.type = type;
	}
	
	public String getName() {
		return type;
	}

}
