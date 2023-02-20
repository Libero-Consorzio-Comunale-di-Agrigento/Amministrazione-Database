<html>
<head>
<title>Main</title>
<link rel="stylesheet" type="text/css" href="../Themes/AFC/Style.css">
</head>
<h3 align="center">Pagina di test del Portale</h3>
<body class="AFCPageBODY">
<table class="AFCFormTable" width="800px" align="center">
<tr>
<td class="AFCColumnTD" colspan="2">AD4</td>
</tr>
<tr><td class="AFCDataTD" width="200px" valign="top">Test di connessione AD4 (dataSource jdbc/ad4)</td>
<td class="AFCDataTD" width="600px" valign="top">
<%
  // Connessione ad AD4 mediante JNDI
  java.sql.Connection conn = it.finmatica.jfc.naming.ConnectionFactory.getConnection("jdbc/ad4");

  // select di test
  java.sql.PreparedStatement stmt = conn.prepareStatement("select id_utente from utenti");
  stmt.executeQuery();
  stmt.close();
  out.println("OK");
%>
</td></tr>
<tr><td class="AFCDataTD" valign="top">Versione AD4 installata</td><td class="AFCDataTD" valign="top">
<%
  // Estraggo la versione di AD4 installata
  java.sql.PreparedStatement stmt2 = conn.prepareStatement("select versione from istanze where progetto='AD4'");
  java.sql.ResultSet rs = stmt2.executeQuery();
  rs.next();
  String versione = rs.getString(1);
  rs.close();
  stmt2.close();
  conn.close();
  out.println(versione);

%>
</td></tr>
<tr>
<td class="AFCColumnTD" colspan="2">Variabili di sessione (CodeChargeFilter)</td>
</tr><tr>
<td class="AFCDataTD" valign="top">Progetto</td>
<td class="AFCDataTD" valign="top">
<% 
   out.println(request.getSession(false).getAttribute("Progetto"));
%>
<% 
   out.println(request.getServerName()+ request.getServerPort()); 
%>

</td>
</tr>

<tr>
<td class="AFCDataTD" valign="top">Modulo</td>
<td class="AFCDataTD" valign="top">
<% 
   out.println(request.getSession(false).getAttribute("Modulo"));
%>
</td>
</tr>

<tr>
<td class="AFCDataTD" valign="top">Istanza</td>
<td class="AFCDataTD" valign="top">
<% 
   out.println(request.getSession(false).getAttribute("Istanza"));
%>
</td>
</tr>
<tr>
<td class="AFCDataTD" valign="top">Utente</td>
<td class="AFCDataTD" valign="top">
<% 
   out.println(request.getSession(false).getAttribute("Utente"));
%>
</td>
</tr>

<tr>
<td class="AFCDataTD" valign="top">Ruolo</td>
<td class="AFCDataTD" valign="top">
<% 
   out.println(request.getSession(false).getAttribute("Ruolo"));
%>
</td>
</tr>

<tr>
<td class="AFCColumnTD" colspan="2">Variabili di sessione (AmvControl)</td>
</tr><tr>
<td class="AFCDataTD" valign="top">MVCONTEXT</td>
<td class="AFCDataTD" valign="top">
<% 
   out.println(request.getSession(false).getAttribute("MVCONTEXT"));
%>
</td>
</tr>

<tr>
<td class="AFCDataTD" valign="top">MVPATH</td>
<td class="AFCDataTD" valign="top">
<% 
   out.println(request.getSession(false).getAttribute("MVPATH"));
%>
</td>
</tr>

<tr>
<td class="AFCDataTD" valign="top">MVURL</td>
<td class="AFCDataTD" valign="top">
<% 
   out.println(request.getSession(false).getAttribute("MVURL"));
%>
</td>
</tr>
<tr>
<td class="AFCDataTD" valign="top">MVRP</td>
<td class="AFCDataTD" valign="top">
<% 
   out.println(request.getSession(false).getAttribute("MVRP"));
%>
</td>
</tr>

<tr>
<td class="AFCDataTD" valign="top">MVABILITAZIONI</td>
<td class="AFCDataTD" valign="top"><textarea cols="60" rows="20">
<% 
   out.println(request.getSession(false).getAttribute("MVABILITAZIONI"));
%>
</textarea>
</td>
</tr>

<tr>
<td class="AFCDataTD" valign="top">MVDIRUPLOAD</td>
<td class="AFCDataTD" valign="top">
<% 
   out.println(request.getSession(false).getAttribute("MVDIRUPLOAD"));
%>
</td>
</tr>

<tr>
<td class="AFCDataTD" valign="top">MVTEST</td>
<td class="AFCDataTD" valign="top">
<% 
   out.println(request.getSession(false).getAttribute("MVTEST"));
%>
</td>
</tr>

<tr>
<td class="AFCDataTD" valign="top">MVVC</td>
<td class="AFCDataTD" valign="top">
<% 
   out.println(request.getSession(false).getAttribute("MVVC"));
%>
</td>
</tr>
<tr>
<td class="AFCDataTD" valign="top">MVID</td>
<td class="AFCDataTD" valign="top">
<% 
   out.println(request.getSession(false).getAttribute("MVID"));
%>
</td>
</tr>
<tr>
<td class="AFCDataTD" valign="top">MVPC</td>
<td class="AFCDataTD" valign="top">
<% 
   out.println(request.getSession(false).getAttribute("MVPC"));
%>
</td>
</tr>

<tr>
<td class="AFCDataTD" valign="top">MVPP</td>
<td class="AFCDataTD" valign="top">
<% 
   out.println(request.getSession(false).getAttribute("MVPP"));
%>
</td>
</tr>
<tr>
<td class="AFCDataTD" valign="top">Utente selezionato da Admin (MVUTE)</td>
<td class="AFCDataTD" valign="top">
<% 
   out.println(request.getSession(false).getAttribute("MVUTE"));
%>
</td>
</tr>
<tr>
<td class="AFCColumnTD" colspan="2">Richieste modulistica</td>
</tr>
<tr>
<td class="AFCDataTD" valign="top">MVIDRIC Sessione</td>
<td class="AFCDataTD" valign="top">
<% 
   out.println(request.getSession(false).getAttribute("MVIDRIC"));
%>
</td>
</tr>
<tr>
<td class="AFCDataTD" valign="top">MVREVRIC Sessione</td>
<td class="AFCDataTD" valign="top">
<% 
   out.println(request.getSession(false).getAttribute("MVREVRIC"));
%>
</td>
</tr>

<tr>
<td class="AFCDataTD" valign="top">Inoltri (attributo Request)</td>
<td class="AFCDataTD" valign="top">
<% 
   out.println(request.getAttribute("INOLTRI"));
%>
</td>
</tr>

<tr>
<td class="AFCDataTD" valign="top">Inoltro modello (attributo Request)</td>
<td class="AFCDataTD" valign="top">
<% 
   out.println(request.getAttribute("INOLTRO_MODELLO"));
%>
</td>
</tr>
<tr>
<td class="AFCDataTD" valign="top">Errori Controlli (attributo Request)</td>
<td class="AFCDataTD" valign="top">
<% 
   out.println(request.getAttribute("ERRORI_CONTROLLI"));
%>
</td>
</tr>
<tr>
<td class="AFCDataTD" valign="top">Errori Controlli (password)</td>
<td class="AFCDataTD" valign="top">
<% 
   out.println(request.getAttribute("MVPWDVALIDA"));
%>
</td>
</tr>

</table>
</body>
</html>