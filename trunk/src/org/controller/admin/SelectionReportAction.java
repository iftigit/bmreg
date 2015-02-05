package org.controller.admin;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.model.AddressDAO;
import org.model.JobCategoryDAO;
import org.model.LotteryDAO;
import org.table.LotteryDTO;
import org.table.SelectedEmpDTO;
import org.table.SelectionParamDTO;
import org.table.SelectionReportFieldDTO;
import org.table.UserDTO;

import util.connection.ConnectionManager;

import com.csvreader.CsvWriter;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.ExceptionConverter;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import com.opensymphony.xwork2.ActionSupport;

public class SelectionReportAction extends ActionSupport implements ServletContextAware{
	
	private static final long serialVersionUID = 9203852155056386824L;
	private InputStream inputStream;
	AddressDAO addressDAO=new AddressDAO();
	private String selectionId;
	private String reportType;
	
	public String execute() throws Exception
	{	
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");		
		HttpServletResponse response = ServletActionContext.getResponse();
		
		if(!loggedInUser.getUserType().equalsIgnoreCase("SYSTEM_ADMIN"))
		{
			return "logout";
		}
				
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd__HH-mm-ss",Locale.getDefault());
		Calendar cal = Calendar.getInstance();
		String fileName="Selection_Report_"+selectionId+"_"+dateFormat.format(cal.getTime()).toUpperCase()+"."+reportType;

		JobCategoryDAO jcDAO=new JobCategoryDAO();
		LotteryDAO lotteryDAO=new LotteryDAO();
		ArrayList<SelectedEmpDTO> jobseekerList=lotteryDAO.getSelectionDetail(Integer.parseInt(selectionId));
		SelectionParamDTO selectionParam=lotteryDAO.getSelectionCriteria(Integer.parseInt(selectionId));
		ArrayList<SelectionReportFieldDTO> fieldList=lotteryDAO.getSelectionReportFields();
		
		Connection conn = ConnectionManager.getConnection();
		selectionParam.setJobPreferenceDesc(jcDAO.getJobPreferenceDescription(conn,selectionParam.getJobPreference()));
		selectionParam.setJobExperienceDesc(jcDAO.getJobExperienceDescription(conn,selectionParam.getJobExperience()));
		ConnectionManager.closeConnection(conn);

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ServletOutputStream out = response.getOutputStream();		
		
		if(reportType.equalsIgnoreCase("pdf")){
		Document document = new Document(PageSize.A4.rotate());
		document.setMargins(40, 40, 20, 20);
		document.addHeader("Selection Report", "");
		
		
		PdfPTable ptable = null;
		PdfPCell pcell=null;
		float[]  fieldWidthArrF=null;
		try{
			
			DCLotteryReportEvent eEvent = new DCLotteryReportEvent(servlet);
			
			Font font1 = new Font(Font.COURIER, 8, Font.BOLD); 
			font1.setColor(new Color(0x92, 0x90, 0x83));
			Font fontT = FontFactory.getFont("Helvetica", 9, Font.NORMAL,Color.BLACK);			
			Font fontb = FontFactory.getFont("Helvetica", 10, Font.BOLD,Color.BLACK);
			
			eEvent.setDisplayValue(selectionParam.getAgentCompanyName()+"#"+selectionParam.getWorkOrder()+
					"#"+selectionParam.getSelectionId()+"#"+selectionParam.getJobPreferenceDesc()+"#"+selectionParam.getJobExperienceDesc()+"#"+
					selectionParam.getLanguages()+"#"+selectionParam.getCountryPreference()+"#"+selectionParam.getGender()+"#"+selectionParam.getYearOfExperience()+"#");
			
			int counter=0;
			for(int i=0;i<jobseekerList.size();i++)
			{
					
				if(i==0){	
					PdfWriter.getInstance(document, baos).setPageEvent(eEvent);
					document.open();

					ptable = new PdfPTable(fieldList.size()+1);
					ptable.setHeaderRows(1);
					ptable.setWidthPercentage(100);
					String fieldWidthStr="8,";
					for(int f=0;f<fieldList.size();f++){
						fieldWidthStr+=fieldList.get(f).getFieldWidth()+",";
					}
					fieldWidthStr=fieldWidthStr.substring(0, fieldWidthStr.length()-1);
					
					String[] fieldWidthArrS=fieldWidthStr.split(",");
					fieldWidthArrF=new float[fieldWidthArrS.length];
					for(int a=0;a<fieldWidthArrS.length;a++){
						fieldWidthArrF[a]=Float.parseFloat(fieldWidthArrS[a]);
					}
					
					ptable.setWidths(fieldWidthArrF);

					pcell=new PdfPCell(new Paragraph("Sl. No.",fontb));
					pcell.setMinimumHeight(25f);
					pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
					pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);			
					ptable.addCell(pcell);
					
					for(int f=0;f<fieldList.size();f++){
						pcell=new PdfPCell(new Paragraph(fieldList.get(f).getFieldCaption(),fontb));
						pcell.setMinimumHeight(25f);
						pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
						pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
						ptable.addCell(pcell);
					}
					
					counter=0;
				
				}
		
				counter++;
				ptable.setWidthPercentage(100);
				ptable.setWidths(fieldWidthArrF);

				
				SelectedEmpDTO seekerDTO =(SelectedEmpDTO)jobseekerList.get(i);
				
				
				pcell = new PdfPCell(new Paragraph(String.valueOf(counter),fontT));
				pcell.setMinimumHeight(20f);
				pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);				
				ptable.addCell(pcell);

				for(int f=0;f<fieldList.size();f++){
					pcell = new PdfPCell(new Paragraph( seekerDTO.getDesireFieldValue(fieldList.get(f).getFieldName()),fontT));
					pcell.setMinimumHeight(20f);
					if(fieldList.get(f).getAlignment().equalsIgnoreCase("LEFT"))
						pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
					else if(fieldList.get(f).getAlignment().equalsIgnoreCase("RIGHT"))
						pcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
					else if(fieldList.get(f).getAlignment().equalsIgnoreCase("CENTER"))
						pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
					else
						pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
					
					pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					ptable.addCell(pcell);
				}

			}
			
		document.add(ptable);
		document.close();		
		response.setHeader("Content-Disposition", "attachment;filename="+fileName);
		out.write(baos.toByteArray());
		out.flush();
		baos.close();  //Newly Added
		out.close();   //Newly Added

	
	}catch(Exception e){e.printStackTrace();}
	} //End of PDF If
		else if(reportType.equalsIgnoreCase("csv")){
							
			try {
				// use FileWriter constructor that specifies open for appending
				//CsvWriter csvOutput = new CsvWriter(new FileWriter(outputFile, true), ',');
				CsvWriter csvOutput = new CsvWriter(baos, ',',Charset.forName("ISO-8859-1"));
				
				
				csvOutput.write("Agency Name");
				csvOutput.write(selectionParam.getAgentCompanyName());				
				csvOutput.write("Demand Letter");
				csvOutput.write(selectionParam.getWorkOrder());				
				csvOutput.write("Selection Id");
				csvOutput.write(String.valueOf(selectionParam.getSelectionId()));
				csvOutput.endRecord();
				
				csvOutput.write("Job Preference");
				csvOutput.write(selectionParam.getJobPreferenceDesc());				
				csvOutput.write("Job Experience");
				csvOutput.write(selectionParam.getJobExperienceDesc());				
				csvOutput.write("Language");
				csvOutput.write(selectionParam.getLanguages());
				csvOutput.endRecord();
				
				csvOutput.write("Country Preference");
				csvOutput.write(selectionParam.getCountryPreference());				
				csvOutput.write("Gender");
				csvOutput.write(selectionParam.getGender());				
				csvOutput.write("Years of Experience");
				csvOutput.write(selectionParam.getYearOfExperience());
				csvOutput.endRecord();
				
					csvOutput.write("Sl. No.");
					for(int f=0;f<fieldList.size();f++){
						csvOutput.write(fieldList.get(f).getFieldCaption());
					}
					
					csvOutput.endRecord();
				
				// write out a few records
				int counter=1;
				for(int i=0;i<jobseekerList.size();i++)
				{
					SelectedEmpDTO seekerDTO =(SelectedEmpDTO)jobseekerList.get(i);
					
					csvOutput.write(String.valueOf(counter));
					for(int f=0;f<fieldList.size();f++){						
						csvOutput.write(seekerDTO.getDesireFieldValue(fieldList.get(f).getFieldName()));
					}
					counter++;
					
					csvOutput.endRecord();
				}
				
				csvOutput.close();
				
				
				response.setHeader("Content-Disposition", "attachment;filename="+fileName);
				response.setContentType("application/csv");
				out.write(baos.toByteArray());
				out.flush();

			} catch (IOException e) {
				e.printStackTrace();
			}
			catch(Exception ex){
				ex.printStackTrace();
			}
			
	       
	        
		} //End of CSV Else if
    
		return null;
	}
	
	
	private ServletContext servlet;
	
	public ServletContext getServlet() {
		return servlet;
	}

	public void setServlet(ServletContext servlet) {
		this.servlet = servlet;
	}

	public void setServletContext(ServletContext servlet) {
		this.servlet = servlet;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getSelectionId() {
		return selectionId;
	}

	public void setSelectionId(String selectionId) {
		this.selectionId = selectionId;
	}

	public String getReportType() {
		return reportType;
	}

	public void setReportType(String reportType) {
		this.reportType = reportType;
	}

	
	

}

class DCLotteryReportEvent extends PdfPageEventHelper
{
	protected ServletContext servlet =null;
	protected PdfTemplate total;
	protected BaseFont helv;
	protected PdfPTable footer;
	
	private String header="";
	 private String DisplayValue=null;
	 
	public void addheader(String header)
	{
		this.header = header;
	}
//	String cBean;
//	String projectname;
//	String monyear;
	
	
	
	public DCLotteryReportEvent(ServletContext servlet) {
		
		this.servlet = servlet;
//		this.cBean = cBean;
//		String [] st=cBean.split("#");
//		this.projectname=st[1];
//		this.monyear=st[0];
		
	}

	@Override
	public void onOpenDocument(PdfWriter writer, Document document) {
		// TODO Auto-generated method stub

		
		
		total = writer.getDirectContent().createTemplate(100, 100);
		total.setBoundingBox(new Rectangle(-20,-20,100,100));
		
		try{
			helv=BaseFont.createFont(BaseFont.TIMES_ROMAN, BaseFont.WINANSI,BaseFont.NOT_EMBEDDED);
		}catch(Exception e){
			throw new ExceptionConverter(e);
		}
		
	}
	
	@Override
	public void onEndPage(PdfWriter writer, Document document) {
		// TODO Auto-generated method stub
		
		PdfContentByte cb = writer.getDirectContent();
		
		footer = new PdfPTable(5);
		footer.setTotalWidth(700);
		footer.setWidthPercentage(80);

		try
		{
			footer.setWidths(new float[] {20,14,30,14,20});
			footer.setHorizontalAlignment(Element.ALIGN_CENTER);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		PdfPCell pcell=null;
		
		Font f10nornal = new Font(Font.TIMES_ROMAN,10,Font.NORMAL);
		
		pcell=new PdfPCell(new Paragraph("Prepared by",f10nornal));		
		pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
		pcell.setMinimumHeight(50f);
		pcell.setBorderWidth(0);
		footer.addCell(pcell);
		
		
		pcell=new PdfPCell(new Paragraph("\n Page"+document.getPageNumber(),f10nornal));
		pcell.setColspan(3);
		pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		pcell.setMinimumHeight(50f);
		pcell.setBorderWidth(0);
		footer.addCell(pcell);
		
		
		pcell=new PdfPCell(new Paragraph("Verified by",f10nornal));		
		pcell.setHorizontalAlignment(Element.ALIGN_RIGHT);
		pcell.setMinimumHeight(50f);
		pcell.setBorderWidth(0);
		footer.addCell(pcell);
		
		
		
		footer.writeSelectedRows(0, -1, (document.right()-document.left()-600)/2-document.leftMargin(),document.bottom()+5,cb);
		
		
	}
	
	@Override
	public void onStartPage(PdfWriter writer, Document document) {
		
		PdfContentByte cb = writer.getDirectContent();
		
		cb.saveState();
		String text = header;
		float textBase = document.top()+5;
		float textSize = helv.getWidthPoint(text, 12);
		cb.beginText();
		cb.setFontAndSize(helv, 10);
		if ((writer.getPageNumber() > 1))
		{
			cb.setTextMatrix((document.getPageSize().width()/2)-(textSize/2), textBase);
			cb.showText(text);
			cb.endText();
			cb.addTemplate(total,(document.getPageSize().width()/2)-(textSize/2), textBase);
		}
		else
		{
		cb.setTextMatrix(document.left()+50, textBase);
		cb.showText("");
		cb.endText();
		cb.addTemplate(total, document.right() + textSize, textBase);
			
		}
	
		cb.restoreState();
		
		String realpath = servlet.getRealPath("");
		String filepath = "/resources/images/bagladesh_logo.gif";
		String bmetLogo=realpath+filepath;

		bmetLogo=realpath+filepath;
		

		try{
			
			URL  url = new URL("file", "localhost",bmetLogo);
			
			Image jpg = Image.getInstance(url);
			jpg.scalePercent(100f);

			
			Paragraph pg =null;
			
			
			PdfPTable ptable = new PdfPTable(2);
			ptable.setWidthPercentage(90);
			ptable.setWidths(new float[] {15f,80f });
			ptable.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			
			PdfPCell pcell = new PdfPCell();
			
			
			pcell.addElement(jpg);
			//pcell.setPaddingBottom(-3f);
			pcell.setHorizontalAlignment(Element.ALIGN_CENTER);		
			pcell.setBorderColor(Color.WHITE);
			pcell.setFixedHeight(50f);
			ptable.addCell(pcell);
			
			String dValue=getDisplayValue();
			String[] valArr=dValue.split("#");
			
			
			String header1="Company Name : "+valArr[0]+", Demand Note :"+valArr[1]+", Selection Id :"+valArr[2]+"\n"+"Job Preference : "+valArr[3]+". Job Experience :"+valArr[4]+"\n Language: "+valArr[5]+", Country :"+valArr[6]+"\n Gender :"+valArr[7]+", Exp. Years :"+valArr[8];
			pcell = new PdfPCell();
			pg = new Paragraph(header1,new Font(Font.TIMES_ROMAN,13,Font.BOLD));
			pg.setAlignment(Element.ALIGN_LEFT);
			pcell.setColspan(4);
			pcell.addElement(pg);
			pcell.setPaddingBottom(5f);
			pcell.setBorderColor(Color.WHITE);
			pcell.setHorizontalAlignment(Element.ALIGN_CENTER);		
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);		
			ptable.addCell(pcell);
			
			
			/*
			
			String header1="G2G Project Lottery Result for Upazilla : "+valArr[0]+", Union :"+valArr[1]+"[Total Quota= "+valArr[2]+"]";
			pcell = new PdfPCell();
			pg = new Paragraph(header1,new Font(Font.TIMES_ROMAN,13,Font.BOLD));
			pg.setAlignment(Element.ALIGN_LEFT);
			pcell.setColspan(4);
			pcell.addElement(pg);
			pcell.setPaddingBottom(5f);
			pcell.setBorderColor(Color.WHITE);
			pcell.setHorizontalAlignment(Element.ALIGN_CENTER);		
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);		
			ptable.addCell(pcell);
			
			ptable.setSpacingBefore(40f);
			ptable.setSpacingAfter(10f);
			
			
			
			pcell = new PdfPCell();
			String header2="Upazilla : "+valArr[1];
			
			pg = new Paragraph(header2,new Font(Font.TIMES_ROMAN,9,Font.NORMAL));
			pg.setAlignment(Element.ALIGN_CENTER);
			pcell.setColspan(5);
			pcell.addElement(pg);
			pcell.setPaddingBottom(-3f);
			pcell.setBorderColor(Color.WHITE);
			pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			ptable.addCell(pcell);
			
			pcell = new PdfPCell();
			String header3="Total Quota : "+valArr[2];
			
			pg = new Paragraph(header3,new Font(Font.TIMES_ROMAN,9,Font.NORMAL));
			pg.setAlignment(Element.ALIGN_CENTER);
			pg.font().setStyle(Font.UNDERLINE);			
			pcell.setColspan(5);
			pcell.addElement(pg);
			pcell.setPaddingBottom(-3f);
			pcell.setBorderColor(Color.WHITE);
			pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			ptable.addCell(pcell);
			
			*/
			
			document.add(ptable);
			
			
		}catch(Exception ex){
			
		}
		
		
	}

	public ServletContext getServlet() {
		return servlet;
	}

	public void setServlet(ServletContext servlet) {
		this.servlet = servlet;
	}



	public String getDisplayValue() {
		return DisplayValue;
	}



	public void setDisplayValue(String displayValue) {
		DisplayValue = displayValue;
	}

	
}
