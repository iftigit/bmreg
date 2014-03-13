package org.controller.regToken;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.model.AddressDAO;
import org.model.LotteryDAO;
import org.model.RegTokenDAO;
import org.table.LotteryDTO;
import org.table.RegTokenDTO;
import org.table.SelectedEmpDTO;
import org.table.SelectionParamDTO;
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

public class RegTokenReportAction extends ActionSupport implements ServletContextAware{
	
	private static final long serialVersionUID = 9203852155056386824L;
	private InputStream inputStream;
	private int tokenId;
	
	public String execute() throws Exception
	{	
		UserDTO loggedInUser=(UserDTO) ServletActionContext.getRequest().getSession().getAttribute("loggedInUser");
		
		HttpServletResponse response = ServletActionContext.getResponse();
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ServletOutputStream out = response.getOutputStream();		
		Document document = new Document();
		document.setPageSize(PageSize.A4);
		document.setMargins(10, 10, 10, 10);
		

		
		

		if(!loggedInUser.getUserType().equalsIgnoreCase("DEMO_REG_ADMIN"))
		{
			return "logout";
		}
		
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd__HH-mm-ss",Locale.getDefault());
		Calendar cal = Calendar.getInstance();
		
		
		String fileName="RegToken_Report_"+tokenId+"_"+dateFormat.format(cal.getTime()).toUpperCase()+".pdf";
		
		document.setMargins(40, 40, 20, 20);
		
		
		document.addHeader("Token Report", "");
		
		
		RegTokenDAO regTokenDAO=new RegTokenDAO();
		ArrayList<RegTokenDTO> tokenList=regTokenDAO.getTokenList(tokenId);
		RegTokenDTO tokenBasicInfo=regTokenDAO.getTokenBasicInfo(tokenId);
		

		PdfPTable ptable = null;
		PdfPCell pcell=null;
		
		try{
			
			RegTokenReportEvent eEvent = new RegTokenReportEvent(servlet);
			
			Font font1 = new Font(Font.COURIER, 8, Font.BOLD); 
			font1.setColor(new Color(0x92, 0x90, 0x83));
			Font fontT = FontFactory.getFont("Helvetica", 9, Font.NORMAL,Color.BLACK);			
			Font fontb = FontFactory.getFont("Helvetica", 10, Font.BOLD,Color.BLACK);
			
			eEvent.setDisplayValue(tokenBasicInfo.getTokenId()+"#"+tokenBasicInfo.getAssignedTo()+"#"+tokenBasicInfo.getInsertedOn());
			
			int counter=0;
			for(int i=0;i<tokenList.size();i++)
			{
				
					
					
				if(i==0){
					PdfWriter.getInstance(document, baos).setPageEvent(eEvent);
					document.open();

					ptable = new PdfPTable(2);
					ptable.setHeaderRows(1);
					ptable.setWidthPercentage(100);
					ptable.setWidths(new float[]{10,90});

					pcell=new PdfPCell(new Paragraph("Serial",fontb));
					pcell.setMinimumHeight(25f);
					pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
					pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);			
					ptable.addCell(pcell);
					
					pcell=new PdfPCell(new Paragraph("Token",fontb));
					pcell.setMinimumHeight(25f);
					pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
					pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
					ptable.addCell(pcell);
					
					
					counter=0;
				
				}
		
				counter++;
				ptable.setWidthPercentage(100);
				ptable.setWidths(new float[]{10,90});

				
				RegTokenDTO tokenDTO =(RegTokenDTO)tokenList.get(i);
				
								
				pcell = new PdfPCell(new Paragraph(String.valueOf(counter),fontT));
				pcell.setMinimumHeight(20f);
				pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				
				ptable.addCell(pcell);
				
				pcell = new PdfPCell(new Paragraph(tokenDTO.getRegToken(),fontT));
				pcell.setMinimumHeight(20f);
				pcell.setHorizontalAlignment(Element.ALIGN_CENTER);
				pcell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				ptable.addCell(pcell);
				
				
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

	public int getTokenId() {
		return tokenId;
	}

	public void setTokenId(int tokenId) {
		this.tokenId = tokenId;
	}

	
	

}

class RegTokenReportEvent extends PdfPageEventHelper
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
	public RegTokenReportEvent(ServletContext servlet) {
		
		this.servlet = servlet;
		
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
		footer.setTotalWidth(650);
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
			
			
			String header1="Token Id : "+valArr[0]+", Assigned To :"+valArr[1]+", Assigned On :"+valArr[2];
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
