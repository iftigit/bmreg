package org.controller.registration;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.model.AddressDAO;
import org.model.LotteryDAO;
import org.table.LotteryDTO;
import org.table.UserDTO;

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

public class MinistryLotteryResult  extends ActionSupport implements ServletContextAware{
	
	private static final long serialVersionUID = 9203852155056386824L;
	private InputStream inputStream;
	AddressDAO addressDAO=new AddressDAO();
	private String divisionId;
	
	
	public String execute() throws Exception
	{	
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");
		String divisionName=addressDAO.getDivisionNameFromId(Integer.parseInt(this.divisionId));
		HttpServletResponse response = ServletActionContext.getResponse();
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ServletOutputStream out = response.getOutputStream();		
		Document document = new Document(PageSize.A4.rotate());
		LotteryDAO lotteryDAO=new LotteryDAO();
		

		if(!loggedInUser.getUserType().equalsIgnoreCase("LOTTERY_MINISTRY_ADMIN"))
		{
			return "logout";
		}
		else if(loggedInUser.getAccessRight()==0)
		{
			return "timeOver";	
		}
		
		
		String fileName="Lottery_G2G_P1_"+divisionName+".pdf";
		
		document.setMargins(40, 40, 20, 20);
		
		
		document.addHeader("G2G Lottery", "");
		
		
		ArrayList<LotteryDTO> selectedList=lotteryDAO.getP1LotteryArrayListForReport(this.divisionId);

		PdfPTable ptable = null;
		PdfPCell pcell=null;
		
		try{
			
			MinistryLotteryReportEvent eEvent = new MinistryLotteryReportEvent(servlet);
			
			Font font1 = new Font(Font.COURIER, 8, Font.BOLD); 
			font1.setColor(new Color(0x92, 0x90, 0x83));
			Font fontT = FontFactory.getFont("Helvetica", 9, Font.NORMAL,Color.BLACK);			
			Font fontb = FontFactory.getFont("Helvetica", 10, Font.BOLD,Color.BLACK);
			
			ptable = new PdfPTable(6);
			ptable.setHeaderRows(1);
			ptable.setWidthPercentage(100);
			ptable.setWidths(new float[]{8,13,19,20,20,20});
			

			pcell=new PdfPCell(new Paragraph("Sl. No.",fontb));
			pcell.setMinimumHeight(25f);
			pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);			
			ptable.addCell(pcell);
			
			pcell=new PdfPCell(new Paragraph("Jobseeker Id",fontb));
			pcell.setMinimumHeight(25f);
			pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			ptable.addCell(pcell);
			
			
			pcell=new PdfPCell(new Paragraph("Jobseeker Name",fontb));
			pcell.setMinimumHeight(25f);
			pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			ptable.addCell(pcell);
			
			
			
			pcell=new PdfPCell(new Paragraph("Father Name",fontb));
			pcell.setMinimumHeight(25f);
			pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			ptable.addCell(pcell);
						
			pcell=new PdfPCell(new Paragraph("Mother Name",fontb));
			pcell.setMinimumHeight(25f);
			pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			ptable.addCell(pcell);
			
			pcell=new PdfPCell(new Paragraph("Union",fontb));
			pcell.setMinimumHeight(25f);
			pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			ptable.addCell(pcell);
			
			/**
			 * DivisionId#DivisionName#DIVISION_QUOTA#DIVISION_SELECTED#
			   DIST_ID#DIST_NAME#DIST_QUOTA#DIST_SELECTED#
			   THANA_ID#THANA_NAME#THANA_SELECTED
			 */
						
			String preUnion="";
			String preUpazilla="";
			int counter=0;
			for(int i=0;i<selectedList.size();i++)
			{
				
				if(i==0)
				{
					eEvent.setDisplayValue(selectedList.get(i).getDivisionId()+"#seperator#"+selectedList.get(i).getDivisionName()+"#seperator#"+selectedList.get(i).getDivQuota()+"#seperator#"+selectedList.get(i).getDivSelected()+"#seperator#"+selectedList.get(i).getDistrictId()+"#seperator#"+selectedList.get(i).getDistrictName()+"#seperator#"+selectedList.get(i).getDistQuota()+"#seperator#"+selectedList.get(i).getDistSelected()+"#seperator#"+selectedList.get(i).getUpazillaId()+"#seperator#"+selectedList.get(i).getUpazillaName()+"#seperator#"+selectedList.get(i).getUpazillaQuota()+"#seperator#"+selectedList.get(i).getUpazillaSelected());
					PdfWriter.getInstance(document, baos).setPageEvent(eEvent);
					document.open();
				}
				
				if(!preUpazilla.equalsIgnoreCase(selectedList.get(i).getUpazillaId()) && i!=0)
				{
					document.add(ptable);
					
					eEvent.setDisplayValue(selectedList.get(i).getDivisionId()+"#seperator#"+selectedList.get(i).getDivisionName()+"#seperator#"+selectedList.get(i).getDivQuota()+"#seperator#"+selectedList.get(i).getDivSelected()+"#seperator#"+selectedList.get(i).getDistrictId()+"#seperator#"+selectedList.get(i).getDistrictName()+"#seperator#"+selectedList.get(i).getDistQuota()+"#seperator#"+selectedList.get(i).getDistSelected()+"#seperator#"+selectedList.get(i).getUpazillaId()+"#seperator#"+selectedList.get(i).getUpazillaName()+"#seperator#"+selectedList.get(i).getUpazillaQuota()+"#seperator#"+selectedList.get(i).getUpazillaSelected());					
					document.newPage();
					
					
					
					ptable = new PdfPTable(6);
					ptable.setHeaderRows(1);
					ptable.setWidthPercentage(100);
					ptable.setWidths(new float[]{8,13,19,20,20,20});

					pcell=new PdfPCell(new Paragraph("Sl. No.",fontb));
					pcell.setMinimumHeight(25f);
					pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
					pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);			
					ptable.addCell(pcell);
					
					pcell=new PdfPCell(new Paragraph("Jobseeker Id",fontb));
					pcell.setMinimumHeight(25f);
					pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
					pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					ptable.addCell(pcell);
					
					
					pcell=new PdfPCell(new Paragraph("Jobseeker Name",fontb));
					pcell.setMinimumHeight(25f);
					pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
					pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					ptable.addCell(pcell);
					
					
					
					pcell=new PdfPCell(new Paragraph("Father Name",fontb));
					pcell.setMinimumHeight(25f);
					pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
					pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					ptable.addCell(pcell);
								
					pcell=new PdfPCell(new Paragraph("Mother Name",fontb));
					pcell.setMinimumHeight(25f);
					pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
					pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					ptable.addCell(pcell);
					
					pcell=new PdfPCell(new Paragraph("Union",fontb));
					pcell.setMinimumHeight(25f);
					pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
					pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					ptable.addCell(pcell);
					
					counter=0;
				
				}
		
				counter++;
				ptable.setWidthPercentage(100);
				ptable.setWidths(new float[]{8,13,19,20,20,20});

				
				LotteryDTO seekerDTO =(LotteryDTO)selectedList.get(i);
				
								
				pcell = new PdfPCell(new Paragraph(String.valueOf(counter),fontT));
				pcell.setMinimumHeight(20f);
				pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				
				ptable.addCell(pcell);
				
				pcell = new PdfPCell(new Paragraph(seekerDTO.getJobseekerNumber(),fontT));
				pcell.setMinimumHeight(20f);
				pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				ptable.addCell(pcell);
				
				pcell = new PdfPCell(new Paragraph(seekerDTO.getJobseekerName(),fontT));
				pcell.setMinimumHeight(20f);
				pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
				pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				pcell.setPaddingLeft(5f);
				ptable.addCell(pcell);
				
				pcell = new PdfPCell(new Paragraph(seekerDTO.getFatherName(),fontT));
				pcell.setMinimumHeight(20f);
				pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
				pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				pcell.setPaddingLeft(5f);
				ptable.addCell(pcell);
				
				
				pcell = new PdfPCell(new Paragraph(seekerDTO.getMotherName(),fontT));
				pcell.setMinimumHeight(20f);
				pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
				pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				pcell.setPaddingLeft(5f);
				ptable.addCell(pcell);
				
				pcell = new PdfPCell(new Paragraph(seekerDTO.getUnionName(),fontT));
				pcell.setMinimumHeight(20f);
				pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
				pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				pcell.setPaddingLeft(5f);
				ptable.addCell(pcell);
				
				eEvent.setDisplayValue(selectedList.get(i).getDivisionId()+"#seperator#"+selectedList.get(i).getDivisionName()+"#seperator#"+selectedList.get(i).getDivQuota()+"#seperator#"+selectedList.get(i).getDivSelected()+"#seperator#"+selectedList.get(i).getDistrictId()+"#seperator#"+selectedList.get(i).getDistrictName()+"#seperator#"+selectedList.get(i).getDistQuota()+"#seperator#"+selectedList.get(i).getDistSelected()+"#seperator#"+selectedList.get(i).getUpazillaId()+"#seperator#"+selectedList.get(i).getUpazillaName()+"#seperator#"+selectedList.get(i).getUpazillaQuota()+"#seperator#"+selectedList.get(i).getUpazillaSelected());
				
				
				preUpazilla=selectedList.get(i).getUpazillaId();
				
			}
			
			document.add(ptable);
		document.close();
		
		response.setHeader("Content-Disposition", "attachment;filename="+fileName);
		out.write(baos.toByteArray());
		out.flush();

	
	}catch(Exception e){e.printStackTrace();}

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

	public String getDivisionId() {
		return divisionId;
	}

	public void setDivisionId(String divisionId) {
		this.divisionId = divisionId;
	}
	

}

class MinistryLotteryReportEvent extends PdfPageEventHelper
{
	protected ServletContext servlet =null;
	protected PdfTemplate total;
	protected BaseFont helv;
	protected PdfPTable footer;
	
	private String header="";
	private String DisplayValue=null;
	
	Font fontT = FontFactory.getFont("Helvetica", 9, Font.NORMAL,Color.BLACK);			
	Font fontb = FontFactory.getFont("Helvetica", 10, Font.BOLD,Color.BLACK);
	
	public void addheader(String header)
	{
		this.header = header;
	}
//	String cBean;
//	String projectname;
//	String monyear;
	
	
	
	public MinistryLotteryReportEvent(ServletContext servlet) {
		
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
		
		//pcell=new PdfPCell(new Paragraph("Prepared by",f10nornal));
		pcell=new PdfPCell(new Paragraph("",f10nornal));
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
		
		
		//pcell=new PdfPCell(new Paragraph("Verified by",f10nornal));
		pcell=new PdfPCell(new Paragraph("",f10nornal));	
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

			
			PdfPTable ptable = new PdfPTable(2);
			ptable.setWidthPercentage(100);
			ptable.setWidths(new float[] {15f,85f });
			ptable.getDefaultCell().setBorder(0);
			ptable.setHorizontalAlignment(Element.ALIGN_CENTER);
			
			
			PdfPCell pcell = new PdfPCell();
			
			
			pcell.addElement(jpg);
			//pcell.setPaddingBottom(-3f);
			pcell.setHorizontalAlignment(Element.ALIGN_CENTER);		
			pcell.setBorderColor(Color.WHITE);
			pcell.setFixedHeight(60f);
			ptable.addCell(pcell);
			
			String dValue=getDisplayValue();
			String[] valArr=dValue.split("#seperator#");
			
			PdfPTable innerTable=new PdfPTable(9);
			innerTable.setWidthPercentage(100);
			innerTable.setWidths(new float[]{10,2,18,10,2,18,10,2,18});
			//innerTable.getDefaultCell().setBorderColor(Color.WHITE);
			innerTable.getDefaultCell().setBorder(0);

			pcell=new PdfPCell(new Paragraph("Bangladesh-Malaysia G2G Project Final Lottery Result",fontb));
			pcell.setColspan(9);
			pcell.setFixedHeight(30f);
			pcell.setBorderColor(Color.WHITE);
			pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			innerTable.addCell(pcell);
			
			pcell=new PdfPCell(new Paragraph("Division",fontb));
			pcell.setFixedHeight(22f);
			pcell.setBorderColor(Color.WHITE);
			pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);			
			innerTable.addCell(pcell);
			
			pcell=new PdfPCell(new Paragraph(":",fontT));
			pcell.setFixedHeight(22f);
			pcell.setBorderColor(Color.WHITE);
			pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);			
			innerTable.addCell(pcell);
			
			pcell=new PdfPCell(new Paragraph(valArr[1],fontT));
			pcell.setFixedHeight(22f);
			pcell.setBorderColor(Color.WHITE);
			pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);			
			innerTable.addCell(pcell);
			
			pcell=new PdfPCell(new Paragraph("District",fontb));
			pcell.setFixedHeight(22f);
			pcell.setBorderColor(Color.WHITE);
			pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			innerTable.addCell(pcell);
			
			pcell=new PdfPCell(new Paragraph(":",fontT));
			pcell.setFixedHeight(22f);
			pcell.setBorderColor(Color.WHITE);
			pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);			
			innerTable.addCell(pcell);
			
			pcell=new PdfPCell(new Paragraph(valArr[5],fontT));
			pcell.setFixedHeight(22f);
			pcell.setBorderColor(Color.WHITE);
			pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			innerTable.addCell(pcell);
			
			
			pcell=new PdfPCell(new Paragraph("Upazilla",fontb));
			pcell.setFixedHeight(22f);
			pcell.setBorderColor(Color.WHITE);
			pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			innerTable.addCell(pcell);
			
			pcell=new PdfPCell(new Paragraph(":",fontT));
			pcell.setFixedHeight(22f);
			pcell.setBorderColor(Color.WHITE);
			pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);			
			innerTable.addCell(pcell);
			
			pcell=new PdfPCell(new Paragraph(valArr[9],fontT));
			pcell.setFixedHeight(22f);
			pcell.setBorderColor(Color.WHITE);
			pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			innerTable.addCell(pcell);
			
			/* Quota Row*/
			pcell=new PdfPCell(new Paragraph("Quota",fontb));
			pcell.setFixedHeight(22f);
			pcell.setBorderColor(Color.WHITE);
			pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);			
			innerTable.addCell(pcell);
			
			pcell=new PdfPCell(new Paragraph(":",fontT));
			pcell.setFixedHeight(22f);
			pcell.setBorderColor(Color.WHITE);
			pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);			
			innerTable.addCell(pcell);
			
			pcell=new PdfPCell(new Paragraph(valArr[2],fontT));
			pcell.setFixedHeight(22f);
			pcell.setBorderColor(Color.WHITE);
			pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);			
			innerTable.addCell(pcell);
			
			pcell=new PdfPCell(new Paragraph("Quota",fontb));
			pcell.setFixedHeight(22f);
			pcell.setBorderColor(Color.WHITE);
			pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			innerTable.addCell(pcell);
			
			pcell=new PdfPCell(new Paragraph(":",fontT));
			pcell.setFixedHeight(22f);
			pcell.setBorderColor(Color.WHITE);
			pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);			
			innerTable.addCell(pcell);
			
			pcell=new PdfPCell(new Paragraph(valArr[6],fontT));
			pcell.setFixedHeight(22f);
			pcell.setBorderColor(Color.WHITE);
			pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			innerTable.addCell(pcell);
			
			
			pcell=new PdfPCell(new Paragraph("Quota",fontb));
			pcell.setFixedHeight(22f);
			pcell.setBorderColor(Color.WHITE);
			pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			innerTable.addCell(pcell);
			
			pcell=new PdfPCell(new Paragraph(":",fontT));
			pcell.setFixedHeight(22f);
			pcell.setBorderColor(Color.WHITE);
			pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);			
			innerTable.addCell(pcell);
			
			pcell=new PdfPCell(new Paragraph(valArr[10],fontT));
			pcell.setFixedHeight(22f);
			pcell.setBorderColor(Color.WHITE);
			pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			innerTable.addCell(pcell);
			
			/* Selected Row */
			
			pcell=new PdfPCell(new Paragraph("Selected",fontb));
			pcell.setFixedHeight(22f);
			pcell.setBorderColor(Color.WHITE);
			pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);			
			innerTable.addCell(pcell);
			
			pcell=new PdfPCell(new Paragraph(":",fontT));
			pcell.setFixedHeight(22f);
			pcell.setBorderColor(Color.WHITE);
			pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);			
			innerTable.addCell(pcell);
			
			pcell=new PdfPCell(new Paragraph(valArr[3],fontT));
			pcell.setFixedHeight(22f);
			pcell.setBorderColor(Color.WHITE);
			pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);			
			innerTable.addCell(pcell);
			
			pcell=new PdfPCell(new Paragraph("Selected",fontb));
			pcell.setFixedHeight(22f);
			pcell.setBorderColor(Color.WHITE);
			pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			innerTable.addCell(pcell);
			
			pcell=new PdfPCell(new Paragraph(":",fontT));
			pcell.setFixedHeight(22f);
			pcell.setBorderColor(Color.WHITE);
			pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);			
			innerTable.addCell(pcell);
			
			pcell=new PdfPCell(new Paragraph(valArr[7],fontT));
			pcell.setFixedHeight(22f);
			pcell.setBorderColor(Color.WHITE);
			pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			innerTable.addCell(pcell);
			
			
			pcell=new PdfPCell(new Paragraph("Selected",fontb));
			pcell.setFixedHeight(22f);
			pcell.setBorderColor(Color.WHITE);
			pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			innerTable.addCell(pcell);
			
			pcell=new PdfPCell(new Paragraph(":",fontT));
			pcell.setFixedHeight(22f);
			pcell.setBorderColor(Color.WHITE);
			pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);			
			innerTable.addCell(pcell);
			
			pcell=new PdfPCell(new Paragraph(valArr[11],fontT));
			pcell.setFixedHeight(22f);
			pcell.setBorderColor(Color.WHITE);
			pcell.setHorizontalAlignment(Element.ALIGN_LEFT);
			pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
			innerTable.addCell(pcell);
		
			
			ptable.addCell(innerTable);
			
//			ptable.setSpacingBefore(40f);
			ptable.setSpacingAfter(10f);
			
			
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
