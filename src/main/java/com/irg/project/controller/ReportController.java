package com.irg.project.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.irg.project.dto.SearchRequest;
import com.irg.project.entity.CitizenPlan;
import com.irg.project.service.ReportService;

@Controller
public class ReportController {
	
	@Autowired
	private ReportService reportService;
	
	@GetMapping("/")
	public String indexPage(Model model) {
		model.addAttribute("search", new SearchRequest());
		init(model);
		return "index";
	}
	
	@PostMapping("/search")
	public String handleSearch(@ModelAttribute ("search") SearchRequest request, Model model) {
		List<CitizenPlan> plans = reportService.search(request);
		model.addAttribute("plans", plans);
		init(model);
		return "index";
	}
	

	private void init(Model model) {
		model.addAttribute("names", reportService.getPlanName());
		model.addAttribute("status", reportService.getPlanStatus());
	}
	
	@GetMapping("/excel")
	public void excelExport(HttpServletResponse response) throws Exception{
		
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition", "attachment;filename=plans.xls");
		reportService.exportExcel(response);
		
	}
	
	@GetMapping("/pdf")
	public void pdfExport(HttpServletResponse response) throws Exception{
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "attachment;filename=plans.pdf");
		reportService.exportPdf(response);
	}
	
}





























