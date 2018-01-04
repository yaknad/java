package com.tutorialspoint.annotations;

import org.springframework.beans.factory.annotation.Autowired;


public class TextEditorAnnotatedAutowiredConstructor {

	private SpellCheckerAnnotated spellChecker;

	@Autowired
	public TextEditorAnnotatedAutowiredConstructor(SpellCheckerAnnotated spellChecker) {
		System.out.println("Inside TextEditorAnnotatedAutowiredConstructor constructor." );
		this.spellChecker = spellChecker;
	}
	
//	public void setSpellChecker(SpellCheckerAnnotated spellChecker){
//		this.spellChecker = spellChecker;
//	}
//	
//	public SpellCheckerAnnotated getSpellChecker( ) {
//		return spellChecker;
//	}
	
	public void spellCheck() {
		spellChecker.checkSpelling();
	}
}
