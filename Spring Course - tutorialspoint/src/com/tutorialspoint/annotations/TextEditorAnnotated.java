package com.tutorialspoint.annotations;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;


public class TextEditorAnnotated {

	private SpellCheckerAnnotated spellChecker;

	@Autowired
	public void setSpellChecker(SpellCheckerAnnotated spellChecker){
		this.spellChecker = spellChecker;
	}
	
	public SpellCheckerAnnotated getSpellChecker( ) {
		return spellChecker;
	}
	
	public void spellCheck() {
		spellChecker.checkSpelling();
	}
	
	@PostConstruct
	public void init(){
		System.out.println("TextEditorAnnotated: Post init");
	}
	
	@PreDestroy
	public void destroy(){
		System.out.println("TextEditorAnnotated: Pre Destroy");
	}
}
