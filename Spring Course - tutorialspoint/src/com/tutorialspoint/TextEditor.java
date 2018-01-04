package com.tutorialspoint;

public class TextEditor {

	private SpellChecker spellChecker;
	private DummyClass dummyClass;

	public TextEditor() {
		System.out.println("Inside TextEditor default constructor." );
	}
	
	public TextEditor(SpellChecker spellChecker, DummyClass dummyClass) {
		System.out.println("Inside TextEditor constructor with params." );
		this.spellChecker = spellChecker;
		this.dummyClass = dummyClass;
	}
	
	// ***** For setter based dependency injection *****
	// a setter method to inject the dependency - note the naming convention: setX for injected parameter x.
	public void setSpellChecker(SpellChecker spellChecker) {
		System.out.println("Inside setSpellChecker." );
		this.spellChecker = spellChecker;
	}
	public SpellChecker getSpellChecker() {
		return spellChecker;
	}
	public void setDummyClass(DummyClass dummyClass) {
		System.out.println("Inside setDummyClass." );
		this.dummyClass = dummyClass;
	}
	public DummyClass getDummyClass() {
		return dummyClass;
	}
	// *************************************************
	
	public void spellCheck() {
		spellChecker.checkSpelling();
	}
	public void doNothing() {
		dummyClass.doNothing();
	}
}
