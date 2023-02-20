<html>
<style type="text/css">
body { font-family: arial; font-size: 14px;} 
</style>
<body>

	
	<TABLE width="100%" cellspacing="2" cellpadding="5">
	<TR bgcolor=#0079D6>
	<TD><font style="font-size: 200%; color: white;"><strong>:(</strong></font>
	<BR><font style="font-size: 120%; color: white;">
	<%@ page isErrorPage="true" %>
	<% 
      Throwable throwable = (Throwable)request.getAttribute("javax.servlet.error.exception");
      Integer statusCode = (Integer)request.getAttribute("javax.servlet.error.status_code");
      String servletName = (String)request.getAttribute("javax.servlet.error.servlet_name");
	  String errore = it.finmatica.sa4.Sanitizer.sanitize(request.getParameter("e"));
	  if(errore.equals("autenticazione fallita")){
		  response.sendRedirect("AmvLoginSecure.do?MVERR=S");
	  }
      if (servletName == null) {
         servletName = "Sconosciuto";
      }
      String requestUri = (String)request.getAttribute("javax.servlet.error.request_uri");
      if (requestUri == null) {
         requestUri = "Sconosciuto";
      }
	  out.println("HTTP "+statusCode+ "<br/><br/>");
		out.println("Servlet: " + servletName + "</br>");
        out.println("Risorsa: " + requestUri + "<br>");
		if(errore!=null){
        out.println("Errore: " + errore+ "</br>");
		}
		if(throwable!=null){
        out.println("Tipo eccezione: " + throwable.getClass( ).getName( ) + "</br>");
        out.println("Messaggio: " + throwable.getMessage( ));
		}
	
	%>
	
	</font></TD></TR></TABLE>
	
</body>
</html>
