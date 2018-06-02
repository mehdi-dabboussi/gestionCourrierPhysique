<%@ page contentType="application/pdf"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ page import="net.sf.jasperreports.engine.*"%>
<%@ page import="net.sf.jasperreports.engine.data.*"%>
<%@ page import="java.io.*"%>
<%@ page import="java.util.*"%>
<%
	try{
		List<Map<String, ?>> dataSource = (List<Map<String, ?>>) request
				.getAttribute("listTransferts");
		JRDataSource jrDataSource = new JRBeanCollectionDataSource(
				dataSource);
		
		Map<String, Object> parameters = (Map<String, Object>) request
				.getAttribute("parameters");
		String jrxmlFile = session.getServletContext().getRealPath("/report/printBordereau.jrxml");
		InputStream input = new FileInputStream(new File(jrxmlFile));
		JasperReport jasperReport = JasperCompileManager
				.compileReport(input);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, 
				parameters, jrDataSource);
		
		JasperExportManager.exportReportToPdfStream(jasperPrint,
				response.getOutputStream());
		response.getOutputStream().flush();
		response.getOutputStream().close();
	} catch(Exception e){
		e.printStackTrace();
	}
%>
