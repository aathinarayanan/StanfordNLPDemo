package com.anand.nlp.RESTController;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.anand.nlp.model.Type;

import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.CoreDocument;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;

@RestController
@RequestMapping("api/v1")
public class RESTController {
	
	//NLP pipeline is injected
	@Autowired
	private StanfordCoreNLP stanfordCoreNLP;
	
	
	@PostMapping
	@RequestMapping("/nlp")
	public Set<String> nlp (@RequestBody final String input, @RequestParam Type type ){
		
		CoreDocument coreDocument = new CoreDocument(input);
		stanfordCoreNLP.annotate(coreDocument);

		List<CoreLabel> coreLabelList = coreDocument.tokens();
		
		return new HashSet<>(getFilteredListBasedOnType(coreLabelList, type));
	}
	
	
	//Get filtered list based on type
	private List<String> getFilteredListBasedOnType(List<CoreLabel> coreLabels, final Type type) {
		
		return coreLabels
			.stream()
			.filter(coreLabel -> type.getName().equalsIgnoreCase(coreLabel.get(CoreAnnotations.NamedEntityTagAnnotation.class)))
			.map(CoreLabel::originalText)
			.collect(Collectors.toList());
		
	}
	

}
