package org.table;

public class SelectionReportFieldDTO {

	private String fieldName;
	private String visibilityYN;
	private int viewOrder;
	private int fieldWidth;
	private String fieldCaption;
	private String alignment;
	
	
	public String getFieldName() {
		return fieldName;
	}
	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}
	public String getVisibilityYN() {
		return visibilityYN;
	}
	public void setVisibilityYN(String visibilityYN) {
		this.visibilityYN = visibilityYN;
	}
	public int getViewOrder() {
		return viewOrder;
	}
	public void setViewOrder(int viewOrder) {
		this.viewOrder = viewOrder;
	}
	public int getFieldWidth() {
		return fieldWidth;
	}
	public void setFieldWidth(int fieldWidth) {
		this.fieldWidth = fieldWidth;
	}
	public String getFieldCaption() {
		return fieldCaption;
	}
	public void setFieldCaption(String fieldCaption) {
		this.fieldCaption = fieldCaption;
	}
	public String getAlignment() {
		return alignment;
	}
	public void setAlignment(String alignment) {
		this.alignment = alignment;
	}
	
	
}
