package com.irg.project.util;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

import com.irg.project.entity.CitizenPlan;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.CMYKColor;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

@Component
public class PdfGenerator {
	
	public void generate(HttpServletResponse response, List<CitizenPlan> plans) throws Exception{
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		document.open();
		
		//for font styling (not mandatory)
		Font fontTiltle = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		fontTiltle.setSize(20);
		Paragraph paragraph = new Paragraph("List Of Students", fontTiltle);
		
		// Aligning the paragraph in document
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);
		
		document.add(paragraph);
		
		PdfPTable table = new PdfPTable(8);
		
		// Setting width of table, its columns and spacing
				table.setWidthPercentage(100f);
				table.setWidths(new int[] { 8, 8, 8,8, 8, 8,8, 8 });
				table.setSpacingBefore(5);
				
				// Create Table Cells for table header
				PdfPCell cell = new PdfPCell();

				// Setting the background color and padding
				cell.setBackgroundColor(CMYKColor.MAGENTA);
				cell.setPadding(5);

				// Creating font
				// Setting font style and size
				Font font = FontFactory.getFont(FontFactory.TIMES_ROMAN);
				font.setColor(CMYKColor.WHITE);

				// Adding headings in the created table cell/ header
				// Adding Cell to table
				cell.setPhrase(new Phrase("ID", font));
				table.addCell(cell);
				cell.setPhrase(new Phrase("Citizen Name", font));
				table.addCell(cell);
				cell.setPhrase(new Phrase("Gender", font));
				table.addCell(cell);
				cell.setPhrase(new Phrase("Plan Name", font));
				table.addCell(cell);
				cell.setPhrase(new Phrase("Plan Status", font));
				table.addCell(cell);
				cell.setPhrase(new Phrase("Plan Start Date", font));
				table.addCell(cell);
				cell.setPhrase(new Phrase("Plan end Date", font));
				table.addCell(cell);
				cell.setPhrase(new Phrase("Beneficial Amount", font));
				table.addCell(cell);
		
//		table.addCell("ID");
//		table.addCell("Citizen Name");
//		table.addCell("Gender");
//		table.addCell("Plan Name");
//		table.addCell("Plan Status");
//		table.addCell("Plan Start Date");
//		table.addCell("Plan End Date");
//		table.addCell("Beneficial Amount");
		
		for(CitizenPlan plan : plans) {
			table.addCell(String.valueOf(plan.getCitizenId()));
			table.addCell(plan.getCitizenName());
			table.addCell(plan.getGender());
			table.addCell(plan.getPlanName());
			table.addCell(plan.getPlanStatus());
			if(null!=plan.getBenefitAmount()) {
				table.addCell(plan.getPlanStartDate()+"");
			}else {
				table.addCell("N/A");
			}
			if(null!=plan.getBenefitAmount()) {
				table.addCell(plan.getPlanEndDate()+"");
			}else {
				table.addCell("N/A");
			}
			if(null!=plan.getBenefitAmount()) {
				table.addCell(plan.getBenefitAmount());
			}else {
				table.addCell("N/A");
			}
		}
		
		document.add(table);
		document.close();
	}

}
