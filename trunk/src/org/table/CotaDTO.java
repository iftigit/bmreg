package org.table;

public class CotaDTO {
	
	private String distname="";
	private String distid="";
	private String thanaId;
	private String thanaName;
	private float discota=0;
	private int total=0;
	private int available=0;
	
	


	public String getDistname() {
		return distname;
	}
	public void setDistname(String distname) {
		this.distname = distname;
	}
	public String getDistid() {
		return distid;
	}
	public void setDistid(String distid) {
		this.distid = distid;
	}
	public float getDiscota() {
		return discota;
	}
	public void setDiscota(float discota) {
		this.discota = discota;
	}
	public long getTotal() {
		return total;
	}
	public String getThanaId() {
		return thanaId;
	}
	public void setThanaId(String thanaId) {
		this.thanaId = thanaId;
	}
	public String getThanaName() {
		return thanaName;
	}
	public void setThanaName(String thanaName) {
		this.thanaName = thanaName;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	
	
	
	
}
