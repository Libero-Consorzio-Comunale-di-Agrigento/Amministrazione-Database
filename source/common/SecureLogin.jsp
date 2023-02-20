<%@ page contentType="text/html;charset=windows-1252"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<title>
Login
</title>
</head>
<link rel="stylesheet" type="text/css" href="../Themes/AFC/Style.css">

<%
  String utente = it.finmatica.sa4.Sanitizer.sanitize(request.getParameter("u"));
  String password = it.finmatica.sa4.Sanitizer.sanitize(request.getParameter("p")).replaceAll("\"","&quot;");
  String sa4ck = it.finmatica.sa4.Sanitizer.sanitizeCrypt(request.getParameter("sa4ck"));
  String redirectError = "";
  if(sa4ck==null){
	  redirectError = request.getContextPath() + "/common/Sa4Error.jsp?e=Rilevato attacco CSRF null";
  } else if (sa4ck.equals("")) {
	  redirectError = "Rilevato attacco CSRF";
  } else{
	  String sa4ckDecr="";
      try{
		sa4ckDecr = new it.finmatica.sa4.crypto.SanCrypter().decryptB64(sa4ck);
	  }catch(Exception e){
		  redirectError = "Rilevato attacco CSRF ("+sa4ck+") "+e;
	  }
	  request.getSession().setAttribute("sa4ck",sa4ck);
  }
  if(!redirectError.equals("")){
   response.sendRedirect(request.getContextPath() + "/common/Sa4Error.jsp?e="+redirectError);
  }
  out.println("<font class=\"AFCFormHeaderFont\">Autenticazione al portale...</font>");
%>
<body onload="document.loginForm.submit()"> 
	<form action="j_security_check" method="POST" name="loginForm">
		<input type="hidden" name="j_username" value="<%= utente %>"/><br>
		<input type="hidden" name="j_password" value="<%= password %>"/><br>
		<input type="hidden" name="sa4tk" value="<%= sa4ck %>"/><br>
	</form>
</body>

</html>
