package com.sk.roundedsectionedlist;

public class SectionItem implements Item{

	private final String alphabets;


	public SectionItem(String ch) {
		this.alphabets = ch;
	}

	public String getTitle(){
		return alphabets;
	}

	@Override
	public boolean isSection() {
		return true;
	}

}
