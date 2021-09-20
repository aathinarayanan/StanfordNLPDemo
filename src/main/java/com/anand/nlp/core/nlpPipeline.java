package com.anand.nlp.core;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import edu.stanford.nlp.pipeline.StanfordCoreNLP;

@Service
public class nlpPipeline {
	private static Properties properties;
	private static String propertiesName ="tokenize, ssplit, pos, lemma, ner, parse, sentiment";
	private static StanfordCoreNLP stanfordCoreNLP;
	
	private nlpPipeline() {
		
	}
	
	//Initialize the variable globally
	static {
		properties = new Properties();
		properties.setProperty("annotators", propertiesName);
	}
	
	//Returns single instance of StanfordCoreNLP obj 
	@Bean(name="stanfordCodeNLP")
	public static StanfordCoreNLP getNlpPipeLine() {
		if (stanfordCoreNLP == null) {
			stanfordCoreNLP = new StanfordCoreNLP(properties);
		}
		return stanfordCoreNLP;
	}
	
}
