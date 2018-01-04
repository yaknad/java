package com.tutorialspoint.annotations;

import org.springframework.beans.factory.annotation.Autowired;


public class TextEditorAnnotatedNoSetters {

	@Autowired
	private SpellCheckerAnnotated spellChecker;

	public TextEditorAnnotatedNoSetters() {
		System.out.println("Inside TextEditorAnnotatedNoSetters constructor." );
	}
	
	public SpellCheckerAnnotated getSpellChecker( ) {
		return spellChecker;
	}
	
	public void spellCheck() {
		spellChecker.checkSpelling();
	}
}
