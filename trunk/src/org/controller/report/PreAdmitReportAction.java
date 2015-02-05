package org.controller.report;


import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.util.ServletContextAware;
import org.model.RegistrationDAO;
import org.table.PersonalInfoDTO;
import org.util.PassPhrase;
import org.util.ReportUtil;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.pdf.Barcode128;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfImportedPage;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.PdfStamper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;
import com.opensymphony.xwork2.ActionSupport;

public class PreAdmitReportAction extends ActionSupport implements ServletContextAware{

	private static final long serialVersionUID = 8854240739341830184L;
	private ServletContext servlet;
	private String registrationId;
	private String captchaCode;
	private String requestType;
	
	public ServletContext getServlet() {
		return servlet;
	}

	public void setServlet(ServletContext servlet) {
		this.servlet = servlet;
	}

	public void setServletContext(ServletContext servlet) {
		this.servlet = servlet;
	}


	public String execute() throws Exception
	{	
		
		/*
		String sessionRegId=(String)ServletActionContext.getRequest().getSession().getAttribute("sessionObj_regId");
		
		if(registrationId==null && sessionRegId!=null)
		{
			registrationId=sessionRegId;
		}
		*/
		/*
		if(sessionRegId==null && (registrationId==null || captchaCode==null))
		{
			return "registration_home";
		}
		
		else if(registrationId==null && sessionRegId!=null)
		{
			registrationId=sessionRegId;
		}
		*/

		
		ServletActionContext.getRequest().getSession().setAttribute("sessionObj_regId",null);
		RegistrationDAO regDAO=new RegistrationDAO();
		
		
		String generatedCode = (String) ServletActionContext.getRequest().getSession().getAttribute("captchaText");
		

		if(registrationId!=null && requestType!=null && !requestType.equalsIgnoreCase("A"))
		{
		if(captchaCode==null || !captchaCode.equalsIgnoreCase(generatedCode))
			{
				addFieldError( "Err_captchaError", " Please Write Correctly" );
				return "admit_home";
			}
		else
		{
			ServletActionContext.getRequest().getSession().setAttribute("captchaText",PassPhrase.getNext());
		}
		}
		
		PersonalInfoDTO personalInfoDto= regDAO.getPersonalInformation(registrationId);
		if(personalInfoDto==null)
		{
			addFieldError( "Err_regId", " Invalid Registration Id" );
			return "admit_home";
		}

		
		HttpServletResponse response = ServletActionContext.getResponse();
		PdfReader reader =null;
		ByteArrayOutputStream certificate = null;
		List<PdfReader> readers = new ArrayList<PdfReader>();
		String paymentStatus=personalInfoDto.getPaymentStatus();
		String realPath="";
		if(paymentStatus.equalsIgnoreCase("PAID"))
			realPath = servlet.getRealPath("/resources/staticpdf/application.pdf");
		else
			realPath = servlet.getRealPath("/resources/staticpdf/tmpRegistration.pdf");
		
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		document.setPageSize(PageSize.A4);
		document.setMargins(10, 10, 10, 10);
		
		
		try
		{
			reader = new PdfReader(new FileInputStream(realPath));
			certificate = new ByteArrayOutputStream();
			PdfStamper stamp = new PdfStamper(reader,certificate);
			PdfContentByte over;
			BaseFont bf = BaseFont.createFont(BaseFont.TIMES_ROMAN,BaseFont.WINANSI,BaseFont.EMBEDDED);
			over = stamp.getOverContent(1);
			
			if(paymentStatus.equalsIgnoreCase("PAID")){
			Barcode128 code128 = new Barcode128(); 
			code128.setCode(registrationId); 
			PdfTemplate tp128= 
			code128.createTemplateWithBarcode(over, null, null); 
			over.addTemplate(tp128, 405, 758);
			
			over.beginText();
			
			
			over.setFontAndSize(bf, 12);
			over.setTextMatrix(200, 592);
			over.showText(personalInfoDto.getJobseekerNumber());
			
			
			over.setFontAndSize(bf, 12);
			over.setTextMatrix(200, 570);
			over.showText(personalInfoDto.getEmpFullName());
			
			over.setFontAndSize(bf, 12);
			over.setTextMatrix(200, 545);
			over.showText(personalInfoDto.getEmpMobileNumber());
			
			
			over.setFontAndSize(bf, 8);
			over.setTextMatrix(90, 515);
			over.showText("Printed on : "+personalInfoDto.getPrintDateTime()+"    IP Address : "+personalInfoDto.getIpAddress()+" , Submitted on :"+personalInfoDto.getRegDateTime());
			
			over.setFontAndSize(bf, 12);
			over.setTextMatrix(200, 450);
			over.showText(personalInfoDto.getEmpFullName());
			
			over.setFontAndSize(bf, 12);
			over.setTextMatrix(200, 427);
			over.showText(personalInfoDto.getEmpGivenName());
			
			over.setFontAndSize(bf, 12);
			over.setTextMatrix(200, 404);
			over.showText(personalInfoDto.getEmpLastName());
			
			over.setFontAndSize(bf, 12);
			over.setTextMatrix(200, 381);
			over.showText(personalInfoDto.getEmpFatherName());
			
			over.setFontAndSize(bf, 12);
			over.setTextMatrix(200, 358);
			over.showText(personalInfoDto.getEmpMotherName());
			
			
			over.setFontAndSize(bf, 12);
			over.setTextMatrix(365, 322);
			over.showText(personalInfoDto.getEmpBirthDistrictName());
			
			if(personalInfoDto.getEmpBirthDistrictName()!=null && !personalInfoDto.getEmpBirthDistrictName().equalsIgnoreCase("")){
			over.setFontAndSize(bf, 12);
			over.setTextMatrix(185, 322);
			over.showText("Bangladesh");
			}
			
			
			over.setFontAndSize(bf, 12);
			over.setTextMatrix(200, 292);
			over.showText(personalInfoDto.getEmpBirthDate());
			
			
			
			over.setFontAndSize(bf, 12);
			over.setTextMatrix(200, 269);
			over.showText(personalInfoDto.getEmpGender());
			
			over.setFontAndSize(bf, 12);
			over.setTextMatrix(300, 246);
			over.showText(personalInfoDto.getBirthRegId());
			
			over.setFontAndSize(bf, 12);
			over.setTextMatrix(300, 223);
			over.showText(personalInfoDto.getNationalId());
			
			over.setFontAndSize(bf, 12);
			over.setTextMatrix(200, 154);
			over.showText(personalInfoDto.getPermanentAddress().getVillageName());
			
			over.setFontAndSize(bf, 12);
			over.setTextMatrix(200, 131);
			over.showText(personalInfoDto.getPermanentAddress().getRoadNumber());			
			
			over.setFontAndSize(bf, 12);
			over.setTextMatrix(200, 108);
			over.showText(personalInfoDto.getPermanentAddress().getPostOffice());
			
			over.setFontAndSize(bf, 12);
			over.setTextMatrix(200, 85);
			over.showText(personalInfoDto.getPermanentAddress().getPostCode());
			
			over.setFontAndSize(bf, 12);
			over.setTextMatrix(200, 62);
			over.showText(personalInfoDto.getPermanentAddress().getDistrictName());
			
			}
			else
			{
				Barcode128 code128 = new Barcode128(); 
				code128.setCode(personalInfoDto.getTmpRegId()); 
				PdfTemplate tp128= 
				code128.createTemplateWithBarcode(over, null, null); 
				over.addTemplate(tp128, 405, 758);
				
				over.beginText();
				
				
				over.setFontAndSize(bf, 12);
				over.setTextMatrix(250, 575);
				over.showText(personalInfoDto.getTmpRegId());
				
				
				over.setFontAndSize(bf, 12);
				over.setTextMatrix(170, 543);
				over.showText(personalInfoDto.getEmpFullName());
				
				over.setFontAndSize(bf, 12);
				over.setTextMatrix(170, 515);
				over.showText(personalInfoDto.getEmpMobileNumber());
				
				
				over.setFontAndSize(bf, 8);
				over.setTextMatrix(90, 480);
				over.showText("Printed on : "+personalInfoDto.getPrintDateTime()+"    IP Address : "+personalInfoDto.getIpAddress()+" , Submitted on :"+personalInfoDto.getRegDateTime());

			}
			over.endText();
			stamp.close();
			readers.add(new PdfReader(certificate.toByteArray()));
			
			
						
		}catch(Exception ex)
		{
			ex.printStackTrace();
		}
		
		if(readers.size()>0)
		{
			PdfWriter writer = PdfWriter.getInstance(document, out);
			document.open();
			
			PdfContentByte cb = writer.getDirectContent();
			PdfReader pdfReader = null;
			PdfImportedPage page;
			
			for(int k=0;k<readers.size();k++)
			{
				document.newPage();
				pdfReader = readers.get(k);
				page = writer.getImportedPage(pdfReader, 1);
				cb.addTemplate(page, 0, 0);
			}
			

			document.close();
			ReportUtil rptUtil = new ReportUtil();
			if(paymentStatus.equalsIgnoreCase("PAID"))
				rptUtil.downloadPdf(out, response,"REG_"+personalInfoDto.getJobseekerNumber()+".pdf");
			else
				rptUtil.downloadPdf(out, response,"TMP_"+personalInfoDto.getTmpRegId()+".pdf");
			document=null;	
					
		}
		
		ServletActionContext.getRequest().getSession().setAttribute("captchaText",PassPhrase.getNext());
		
		return null;	
		
	}

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public String getCaptchaCode() {
		return captchaCode;
	}

	public void setCaptchaCode(String captchaCode) {
		this.captchaCode = captchaCode;
	}

	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	
	

}
