package org.table;

import java.io.Serializable;

public class LanguageDTO implements Serializable{

	private static final long serialVersionUID = -2826217977037976979L;
	private String language;
	private String oralSkill;
	private String writingSkill;
	
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getOralSkill() {
		return oralSkill;
	}
	public void setOralSkill(String oralSkill) {
		this.oralSkill = oralSkill;
	}
	public String getWritingSkill() {
		return writingSkill;
	}
	public void setWritingSkill(String writingSkill) {
		this.writingSkill = writingSkill;
	}

}
