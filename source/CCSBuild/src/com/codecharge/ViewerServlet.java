//ViewerServlet class @0-009E2903
package com.codecharge;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import com.codecharge.util.*;

public class ViewerServlet extends HttpServlet {
  private static CCLogger logger; 

  private final static String CLASS_NOT_FOUND_MESSAGE = "Class is not found.";
  private final static String INSTANTIATION_MESSAGE = "Unable to create class object.";
  private final static String ILLEGAL_ACCESS_MESSAGE = "Unable to call 'show' method, access violation";

  public void init() {
    logger = CCLogger.getInstance();
  }

  protected void service(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
  {

    logger.info("-= ViewerServlet : " + request.getServletPath() + " =-");

    String pname = request.getServletPath();
    pname = pname.substring(1, pname.length() - Names.SHOW_SUFFIX.length() );
    String packName = new String(pname);
    int pos = pname.lastIndexOf("/");
    if ( pos > -1 ) {
      packName = pname.replace('.','_').replace('/','.').replace(' ','_');
      pname = pname.substring( pos+1 );
    }
    
    String body = "";
    String className = packName + "." + pname + "View";
    try {
      Class c = Class.forName(className);
      View o = (View)c.newInstance();
      body = o.show(request, response, getServletContext());
      String enc = ((CCSLocale) SessionStorage.getInstance(request).getAttribute(Names.CCS_LOCALE_KEY)).getCharacterEncoding();
      PrintWriter out = new PrintWriter( new OutputStreamWriter( response.getOutputStream(), enc ));
            //Crypt
            if (System.getProperty("java.specification.version").indexOf("1.5") < 0) {
                java.util.Properties siteProps = (java.util.Properties) ContextStorage.getInstance().getAttribute(Names.SITE_PROPERTIES_KEY);
                boolean cryptParams = false;
                if (siteProps != null) {
                    if (siteProps.getProperty("params.crypt") != null) {
                        if (siteProps.getProperty("params.crypt").equals("YES")) {
                            String connName = siteProps.getProperty("cn.url");
                            connName = connName.substring(connName.indexOf("jdbc"));
                            java.sql.Connection con = null;
                            String sql = "select nvl(amvweb.get_preferenza('Crypt'" +
                                    ",'" + request.getSession(true).getAttribute("Modulo") + "'" +
                                    ",'" + request.getSession(true).getAttribute("Utente") + "'),'NO') MSG from dual";
                            try {
                                javax.naming.Context initContext = new javax.naming.InitialContext();
                                javax.naming.Context envContext = (javax.naming.Context) initContext.lookup("java:comp/env/");
                                javax.sql.DataSource ds = (javax.sql.DataSource) envContext.lookup(connName);
                                con = ds.getConnection();
                                java.sql.PreparedStatement statement = con.prepareStatement(sql);
                                java.sql.ResultSet resultSet = statement.executeQuery();
                                while (resultSet.next()) {
                                    if (resultSet.getString(1).equals("SI")) {
                                        cryptParams = true;
                                    }
                                }
                                con.close();
                            } catch (Exception e) {
                                try {
                                    if (con != null) {
                                        if (!con.isClosed()) {
                                            con.close();
                                        }
                                    }
                                } catch (Exception exc) {
                                    logger.error(exc.getLocalizedMessage());
                                }
                                logger.error(e.getLocalizedMessage());
                            }
                        }
                    }
                    //body = StringUtils.cryptAndCSRF(body, request.getSession().getId(), cryptParams);
					body = StringUtils.cryptAndCSRFNoForm(body, request.getSession().getId(), cryptParams);
                }
            }
            //end Crypt
	  out.println( body );
      out.close();
    } catch (ClassNotFoundException cnfe) {
      logger.error( ViewerServlet.CLASS_NOT_FOUND_MESSAGE ); 
    } catch (InstantiationException inste) {
      logger.error( ViewerServlet.INSTANTIATION_MESSAGE );
    } catch (IllegalAccessException iae) {
      logger.error( ViewerServlet.ILLEGAL_ACCESS_MESSAGE );
    } catch (Exception iae) {
      logger.error( iae.getLocalizedMessage());
    }

    logger.info("-= ViewerServlet : " + request.getServletPath() + " finished =-");
  }

}

//End ViewerServlet class

