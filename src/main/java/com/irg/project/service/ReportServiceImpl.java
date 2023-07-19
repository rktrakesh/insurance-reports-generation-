package com.irg.project.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.irg.project.dto.SearchRequest;
import com.irg.project.entity.CitizenPlan;
import com.irg.project.repository.CitizenRepository;
import com.irg.project.util.ExcelGenerator;
import com.irg.project.util.PdfGenerator;

@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private CitizenRepository repository;
	
	@Autowired
	private ExcelGenerator excelGenerator;
	
	@Autowired
	private PdfGenerator pdfGenerator;

	@Override
	public List<String> getPlanName() {
		return repository.getPlanName();
	}

	@Override
	public List<String> getPlanStatus() {
		return repository.getPlanStatus();
	}

	@Override
	public List<CitizenPlan> search(SearchRequest request) {
		CitizenPlan plan = new CitizenPlan();
		//BeanUtils.copyProperties(request, plan);
		
		if(null!=request.getPlanName() && !"".equals(request.getPlanName())) {
			plan.setPlanName(request.getPlanName());
		}
		
		if(null!=request.getPlanStatus() && !"".equals(request.getPlanStatus())) {
			plan.setPlanStatus(request.getPlanStatus());
		}
		
		if(null!=request.getGender() && !"".equals(request.getGender())) {
			plan.setGender(request.getGender());
		}
		
		if (null != request.getPlanStartDate() && !"".equals(request.getPlanStartDate())) {
		    String startDate = request.getPlanStartDate();
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    LocalDate localDate = LocalDate.parse(startDate, formatter);
		    plan.setPlanStartDate(localDate);
		}
		
		if(null!=request.getPlanEndDate() && !"".equals(request.getPlanEndDate())) {
			String endDate = request.getPlanEndDate();
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate localDate = LocalDate.parse(endDate, formatter);
			plan.setPlanEndDate(localDate);
		}

		
		return repository.findAll(Example.of(plan));
	}

	@Override
	public boolean exportExcel(HttpServletResponse response) throws Exception{
		
		List<CitizenPlan> records = repository.findAll();
		excelGenerator.generator(response, records);
		
		return true;
	}

	@Override
	public boolean exportPdf(HttpServletResponse response) throws Exception {
		
		List<CitizenPlan> records = repository.findAll();
		pdfGenerator.generate(response, records);
		
		return true;
	}
}




























