package org.example.servlet;


import static org.mockito.Mockito.*;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Ignore;
import org.junit.Test;


import static org.junit.Assert.*;




public class AssessmentServletTest {

	@Ignore
	public void testAssesmentServlet() throws IOException, ServletException {
		
		HttpServletRequest request = mock(HttpServletRequest.class);       
        HttpServletResponse response = mock(HttpServletResponse.class); 
        StringWriter stringWriter = new StringWriter();
        PrintWriter writer = new PrintWriter(stringWriter);
        
        when(response.getWriter()).thenReturn(writer);
        when(request.getParameter("searchText")).thenReturn("title");
        
		new AssessmentServlet().doGet(request,response);	
	
		 writer.flush(); 
		 
		 assertTrue(stringWriter.toString().contains("iframe Properties"));
		 

	}

}
