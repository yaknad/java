package com.tutorialspoint.annotations;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;


public class TextEditorAnnotatedWithResourceAnnotation {

	private SpellCheckerAnnotated spellChecker;

	@Resource(name="spellCheckerAnnotated") // like @Autowire, but uses "ByName" dependency injection (@Autowire starts with ByType
								   //, and if there's more than one bean of this type, it fallbacks to ByName)
	public void setSpellChecker(SpellCheckerAnnotated spellChecker){
		this.spellChecker = spellChecker;
	}
	
	public SpellCheckerAnnotated getSpellChecker( ) {
		return spellChecker;
	}
	
	public void spellCheck() {
		System.out.println("TextEditorAnnotatedWithResourceAnnotation: spellCheck called");
		spellChecker.checkSpelling();
	}
	
	@PostConstruct
	public void init(){
		System.out.println("TextEditorAnnotatedWithResourceAnnotation: Post init");
	}
	
	@PreDestroy
	public void destroy(){
		System.out.println("TextEditorAnnotatedWithResourceAnnotation: Pre Destroy");
	}
}
