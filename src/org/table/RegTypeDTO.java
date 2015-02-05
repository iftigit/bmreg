package org.table;

public class RegTypeDTO {

	private int typeId;
	private String regIdSuffix;
	private String typeName;
	private String typeDesc;
	private int isActive;
	
	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getRegIdSuffix() {
		return regIdSuffix;
	}
	public void setRegIdSuffix(String regIdSuffix) {
		this.regIdSuffix = regIdSuffix;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	public int getIsActive() {
		return isActive;
	}
	public void setIsActive(int isActive) {
		this.isActive = isActive;
	}
	
}
