package com.irg.project.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.irg.project.dto.SearchRequest;
import com.irg.project.entity.CitizenPlan;

public interface ReportService {
	
	public List<String> getPlanName();
	
	public List<String> getPlanStatus();
	
	public List<CitizenPlan> search(SearchRequest request);
	
	public boolean exportPdf(HttpServletResponse response) throws Exception;
	
	public boolean exportExcel(HttpServletResponse response) throws Exception;

}
