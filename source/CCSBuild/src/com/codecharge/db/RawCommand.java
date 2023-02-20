/******************************************************************************
 REVISIONI:
 Rev. Data       Autore Descrizione
 ---- ---------- ------ --------------------------------------------------------
 1    01/06/2006 AO     Gestione registrazione accessi
******************************************************************************/
//RawCommand class @0-E770B82B
package com.codecharge.db;

import java.text.*;
import java.util.*;
import java.sql.*;
import com.codecharge.util.*;
import com.codecharge.db.*;
import com.codecharge.components.*;

public class RawCommand extends Command {
                                                              
    private final static String LIMIT_TOP_SYNTAX_PARAMETER = "{SqlParam_endRecord}";
    private final static int LIMIT_TOP_SYNTAX_PARAMETER_LENGTH = LIMIT_TOP_SYNTAX_PARAMETER.length();

    private String sqlString;

    private Format defaultBooleanFormat = new BooleanFormat( "true", "false", "NULL" );
    private Format defaultDateFormat = new SimpleDateFormat( "yyyy-MM-dd hh:mm" );
    
    /**
     * Indicate that sql query should be optimized (for MySql or PostgreSql) 
     * or was optimized (for MsAccess or MsSqlServer).
     * This property works if database type is one of 'MySQL', 'PostgreSQL', 
     * 'MSAccess' or 'MSSQLServer'.
     * Property optimizeSQL can be used to disallow sql optimization for some sql
     * queries if sql optimization is allowed for connection. If sql optimization
     * for connection is disabled this property can't be used to allow it.
     */
    protected boolean optimizeSQL;

    private String utente;	//Rev.1
    private String modulo;	//Rev.1
    private String istanza;	//Rev.1
    private String progetto;	//Rev.1
    private String ente;	//Rev.1

    public RawCommand(){}

    public RawCommand( JDBCConnection conn ) {
        super( conn );
        optimizeSQL = conn.isOptimizeSQL();
    }

    public void addParameter( String paramName, Parameter param, Format format ) {
        if ( param != null && ! StringUtils.isEmpty( paramName ) ) {
            parameters.add( param );
            formats.add ( format );
            names.add ( paramName );
        }
    }

    public void addParameter( String paramName, Parameter param ) {
      if (param != null) {
        Format fmt = null;
        if (! StringUtils.isEmpty(param.getDbFormatPattern())) {
          if (param instanceof DateField) {
          fmt = conn.getCCSLocale().getDateFormat(param.getDbFormatPattern());
          } else if (param instanceof BooleanField) {
            fmt = conn.getCCSLocale().getBooleanFormat(param.getDbFormatPattern());
        } else if (param instanceof Control) {
          fmt = conn.getCCSLocale().getFormat(((Control) param).getType(), param.getDbFormatPattern());
        } else if (param instanceof SqlParameter) {
          fmt = conn.getCCSLocale().getFormat(((SqlParameter) param).getType(), param.getDbFormatPattern());
          }
      }
      addParameter( paramName, param, fmt );
      }
    }

    public void addParameter( Parameter param ) {
        return;
    }

    public Parameter getParameter( String paramName ) {
        Parameter param = null;
        if ( ! StringUtils.isEmpty( paramName ) ) {
            for ( int i = 0; i < names.size(); i++ ) {
                if ( paramName.equals((String) names.get(i)) ) {
                    param = (Parameter) parameters.get(i);
                    break;
                }
            }
        }
        return param;
    }

    public void addParameter( SqlParameter param ) {
        if ( param != null ) {
            FieldOperation oper = param.getOperation();
            ControlType type = param.getType();
            if ( (type == ControlType.TEXT || type == ControlType.MEMO) && oper != null ) {
                try {
                    param.setValue( oper.getPrefix() + ((String) param.getValue()) + oper.getSuffix() );
                } catch ( java.text.ParseException ignore ){
                }
            }
      Format fmt = null; 
      if (! StringUtils.isEmpty(param.getDbFormatPattern())) {
        fmt = conn.getCCSLocale().getFormat(param.getType(), param.getDbFormatPattern());
      }
            parameters.add( param );
            formats.add ( fmt );
            names.add( param.getName() );
        }
    }

    public void addRawParameter( SqlParameter param ) {
        if ( param != null ) {
            FieldOperation oper = param.getOperation();
            ControlType type = param.getType();
            param.setApplyToSql( true );
            if ( (type == ControlType.TEXT || type == ControlType.MEMO) && oper != null ) {
                try {
                    param.setValue( oper.getPrefix() + ((String) param.getValue()) + oper.getSuffix() );
                } catch ( java.text.ParseException ignore ){
                }
            }
      Format fmt = null; 
      if (! StringUtils.isEmpty(param.getDbFormatPattern())) {
        fmt = conn.getCCSLocale().getFormat(param.getType(), param.getDbFormatPattern());
      }
      parameters.add( param );
      formats.add ( fmt );
            names.add( param.getName() );
        }
    }

    public Enumeration getRows( int start, int rows ) {
        Enumeration result = null;
        PreparedStatement stmt = prepareCommand();
        ResultSet rs = null;
        if ( stmt == null )
            return result;
        try {
            rs = stmt.executeQuery();
            result = conn.getRows( rs, start, rows );
            next = conn.isNext();
        } catch ( SQLException sqle ) {
            catchException( sqle );
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException sqle) { catchException( sqle ); }
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException sqle) { catchException( sqle ); }
        }
    return result;
    }
    
    public boolean isOptimizeSQL() {
        return optimizeSQL;
    }
    
    public void setOptimizeSQL(boolean flag) {
        optimizeSQL = flag;
    }

    public Enumeration getRows() {
        Enumeration result = null;
        PreparedStatement stmt = prepareCommand();
        ResultSet rs = null;
        if ( stmt == null )
            return result;
        try {
            rs = stmt.executeQuery();
            if ( fetchSize > 0 ) {
                result = conn.getRows( rs, startPos, fetchSize );
            } else {
                result = conn.getRows( rs );
            }
            next = conn.isNext();
        } catch ( SQLException sqle ) {
            catchException( sqle );
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException sqle) { catchException( sqle ); }
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException sqle) { catchException( sqle ); }
        }
    return result;
    }

    public DbRow getOneRow() {
        DbRow result = null;
        int fSize = fetchSize; 
        fetchSize = 1;
        PreparedStatement stmt = prepareCommand();
        fetchSize = fSize;
        ResultSet rs = null;
        if ( stmt == null )
            return result;
        try {
            rs = stmt.executeQuery();
            result = conn.getOneRow( rs );
        } catch ( SQLException sqle ) {
            catchException( sqle );
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException sqle) { catchException( sqle ); }
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException sqle) { catchException( sqle ); }
        }
    return result;
    }

    public int count() {
        int result = 0;
        int mode = RawCommand.COUNT_SQL;
        if ( countSql == null ) {
            mode = RawCommand.SQL;
        }
        PreparedStatement stmt = prepareCommand(mode, false);
        ResultSet rs = null;
        if ( stmt == null )
            return result;
        try {
            rs = stmt.executeQuery();
            if ( rs.next() )
                result = rs.getInt(1);
        } catch ( SQLException sqle ) {
            catchException( sqle );
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException sqle) { catchException( sqle ); }
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException sqle) { catchException( sqle ); }
        }
    return result;
    }

    /**
      Check if query returns something. Number of records.
      @return 0 if query returns no records and 1 otherwise.
    **/
    public int nrecords() {
        int result = 0;
        int mode = RawCommand.COUNT_SQL;
        PreparedStatement stmt = prepareCommand(mode, false);
        ResultSet rs = null;
        if (stmt == null) return result;
        try {
            rs = stmt.executeQuery();
            if (rs.next()) result = 1;
        } catch (SQLException sqle) {
            catchException(sqle);
        } finally {
            try {
                if (rs != null) rs.close();
            } catch (SQLException sqle) { catchException( sqle ); }
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException sqle) { catchException( sqle ); }
        }
    return result;
    }

    public int executeUpdate() {
        int result = 0;
        boolean optSql = optimizeSQL;
        optimizeSQL = false;
        PreparedStatement stmt = prepareCommand(true);
        optimizeSQL = optSql;
        if ( stmt == null )
            return result;
        try {
            registraAccesso();	//Rev.1
            result = stmt.executeUpdate();
        } catch ( SQLException sqle ) {
            catchException( sqle );
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException sqle) { catchException( sqle ); }
        }
    return result;
    }

    /**
     * Returns true if collection of parameters doesn't contain null parameters
     */
    public boolean isSetAllParams() {
        boolean result = true;
        if ( ! (parameters == null || parameters.isEmpty()) ) {
            Iterator params = parameters.iterator();
            while ( params.hasNext() ) {
                Parameter param = (Parameter) params.next();
                if ( param == null ) {
                    result = false;
                }
            }
        }
        return result;
    }

    private PreparedStatement prepareCommand() {
        return prepareCommand( RawCommand.SQL, false );
    }

    private PreparedStatement prepareCommand(boolean forUpdate) {
        return prepareCommand( RawCommand.SQL, forUpdate );
    }

    private PreparedStatement prepareCommand( int mode, boolean forUpdate ) {
      PreparedStatement pstmt = null;
      if (!isCmdExecution()) return pstmt;
      ArrayList stmtParams = new ArrayList();
        if ( mode == RawCommand.COUNT_SQL ) {
            sqlString = countSql;
            sqlString += StringUtils.isEmpty(where) ? "" : " WHERE "+where;
        } else {
            sqlString = sql;
            if (StringUtils.isEmpty(where) == false) sqlString += " WHERE "+where;
            if (StringUtils.isEmpty(order) == false) sqlString += " ORDER BY "+order;
            if (optimizeSQL) {
                if (conn.getLimitSyntax()==JDBCConnection.LIMIT_TOP_SYNTAX) {
                  int ndx = sqlString.indexOf(RawCommand.LIMIT_TOP_SYNTAX_PARAMETER);
                  if (ndx >= 0) sqlString = sqlString.substring(0, ndx) + conn.getLimitClause(startPos, fetchSize) + sqlString.substring(ndx+RawCommand.LIMIT_TOP_SYNTAX_PARAMETER_LENGTH);
                } else if (conn.getLimitSyntax()==JDBCConnection.LIMIT_LIMIT_SYNTAX) {
                  sqlString += conn.getLimitClause(startPos, fetchSize);
                  startPos = 0; fetchSize = 0;
                }
            }
        }
        if ( conn == null ) {
        } else if ( StringUtils.isEmpty( sqlString ) ) {
        } else {
      if ( ! (parameters == null || parameters.isEmpty()) ) {
        for ( int i = 0; i < names.size(); i++ ) {
          Parameter param = (Parameter) parameters.get( i );
          if (param instanceof LongTextField || 
              (param instanceof SqlParameter && ((SqlParameter) param).getType()==ControlType.MEMO) ||
              (param instanceof Control && ((Control) param).getType()==ControlType.MEMO)) {
            String name = (String) names.get( i );
            String value = (String) param.getObjectValue();
            String pattern = null;
            if (needQuotes) {
              pattern = "{"+name+"}";
            } else {
              pattern = "'{"+name+"}'";
            }
            int patternLength = pattern.length();
            char[] creplace = new char[patternLength];
            Arrays.fill(creplace,' ');
            creplace[0] = '?';
            String sreplace = new String(creplace);
            int[] positions = StringUtils.getAllEntriesPositions(sqlString,pattern);
            if (positions != null) {
              for (int j = 0; j < positions.length; j++ ) {
                stmtParams.add(new BParameter(name,value,java.sql.Types.LONGVARCHAR,positions[j]));
              }
              sqlString = StringUtils.replace( sqlString, pattern, sreplace );
            }
          }
        }
        Collections.sort(stmtParams);
      }
            if ( ! (parameters == null || parameters.isEmpty()) ) {
                Format dateFormat = conn.getDateFormat();
                Format booleanFormat = conn.getBooleanFormat();
                boolean applyFormat = false;
                ControlType type = null;
                for ( int i = 0; i < names.size(); i++ ) {
                    applyFormat = false;
                    type = null;
                    Parameter param = (Parameter) parameters.get( i );
                    Format format = (Format) formats.get( i );
                    String name = (String) names.get( i );

                    if ( format == null ) {
                        format = param.getDbFormat();
                    }
                    Object value = null;
                    if ( param != null ) {

                        value = param.getObjectValue();

                        // Get type for Control & SqlParameter
                        if ( param instanceof Control ) {
                            type = ((Control) param).getType();
                        } else if ( param instanceof SqlParameter ) {
                            type = ((SqlParameter) param).getType();
                        }

                        //set concrete format for Date & Boolean
                        if ( param instanceof DateField
                                || ( type != null && type == ControlType.DATE ) ) {
                            if ( format != null || dateFormat != null ) {
                                applyFormat = true;
                                if ( format == null ) {
                                    format = dateFormat;
                                }
                                if ( format == null ) {
                                    format = this.defaultDateFormat;
                                }

                            }
                        } else if ( param instanceof BooleanField
                                || ( type != null && type == ControlType.BOOLEAN ) ) {
                            if ( format != null || booleanFormat != null ) {
                                applyFormat = true;
                                if ( format == null ) {
                                    format = booleanFormat;
                                }
                                if ( format == null ) {
                                    format = this.defaultBooleanFormat;
                                }
                            }
                        }

                        if ( value != null && applyFormat ) {
                            value = format.format( value );
                        }

                        if ( value != null ) {
                            if ( param instanceof LongField ||
                                    type == ControlType.INTEGER ) {
                                sqlString = StringUtils.replace( sqlString, "{" + name + "}", value.toString() );
                            } else if ( param instanceof BooleanField ||
                                    type == ControlType.BOOLEAN ) {
                                sqlString = StringUtils.replace( sqlString, "{" + name + "}", conn.toSql( (String) value, JDBCConnection.BOOLEAN ) );
                            } else if ( param instanceof DoubleField ||
                                    type == ControlType.FLOAT ) {
                                sqlString = StringUtils.replace( sqlString, "{" + name + "}", value.toString().replace( ',', '.' ) );
                            //} else if ( type == ControlType.DATE ) {
                            //    sqlString = StringUtils.replace( sqlString, "{" + name + "}", conn.toSql( value.toString(), JDBCConnection.DATE) );
                            } else if ( param instanceof DateField || type == ControlType.DATE) {
                                if ( needQuotes ) {
                                    sqlString = StringUtils.replace( sqlString, "{" + name + "}", conn.toSql( value.toString(), JDBCConnection.DATE) );
                                } else {
                                    sqlString = StringUtils.replace( sqlString, "{" + name + "}", value.toString() );
                                }
                            } else if ( param instanceof TextField || param instanceof LongTextField ) {
                                if ( needQuotes ) {
                                    sqlString = StringUtils.replace( sqlString, "{" + name + "}", conn.toSql( ((String) value), JDBCConnection.TEXT ) );
                                } else {
                                    String vvv = (String) value;
                                    if ( "MySQL".equals(conn.getDbType()) ) {
                                       vvv = StringUtils.replace( vvv, "\\", "\\\\" );
                                    }
                                    sqlString = StringUtils.replace( sqlString, "{" + name + "}", StringUtils.replace( vvv, "'", "''" ) );
                                }
              } else if ( param instanceof LongTextField ) {
                //skip this type there
                            } else if ( type == ControlType.TEXT || type == ControlType.MEMO ) {
                                String val = value.toString();
                                if (value instanceof Double || value instanceof Float || value instanceof java.math.BigDecimal) {
                                    val = val.replace(',', '.');
                                }
                                if ( needQuotes ) {
                                    sqlString = StringUtils.replace( sqlString, "{" + name + "}", conn.toSql( val, JDBCConnection.TEXT ) );
                                } else {
                                    String vvv = (String) value;
                                    if ( "MySQL".equals(conn.getDbType()) ) {
                                       vvv = StringUtils.replace( vvv, "\\", "\\\\" );
                                    }
                                    sqlString = StringUtils.replace( sqlString, "{" + name + "}", StringUtils.replace( vvv, "'", "''" ) );
                                }
                            } else {
                                sqlString = StringUtils.replace( sqlString, "{" + name + "}", StringUtils.replace( value.toString(), "'", "''" ) );
                            }
                        } else {
                            sqlString = StringUtils.replace(sqlString, "{"+name+"}", "NULL");
                        }

                    }
                }
            }
        }
        if (forUpdate) {
            if ((pstmt = conn.createPreparedStatementForUpdate(sqlString)) == null) {
                return null;
            }
        } else {
            if ((pstmt = conn.createPreparedStatement(sqlString)) == null) {
                return null;
            }
        }
    for (int i = 0; i < stmtParams.size(); i++) {
      BParameter param = (BParameter) stmtParams.get(i);
      try {
        if (param.getValue()==null) {
          pstmt.setNull(i+1,param.getType());
        } else {
            if ("Oracle".equals(conn.getDbType())) {
                java.io.InputStream is = new java.io.ByteArrayInputStream(param.getValue().getBytes());
                pstmt.setAsciiStream(i+1, is, param.getValue().length());
            } else {
                pstmt.setObject(i+1,param.getValue(),java.sql.Types.LONGVARCHAR);
            }
        }
      } catch (SQLException sqle) {
        catchException(sqle);
      }
    }
        return pstmt;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("RawCommand sql='"+sql+"'\n");
        sb.append("         where='"+where+"'\n");
        sb.append("         order='"+order+"'\n");
        sb.append("      countSql='"+countSql+"'\n");
        sb.append("     sqlString='"+sqlString+"'\n");
        sb.append("      startPos='"+startPos+"'\n");
        sb.append("     fetchSize='"+fetchSize+"'\n");
        sb.append("    connection='"+conn.getPoolName()+"'\n");
        sb.append("Parameters:");
        if ( parameters == null || parameters.isEmpty() ) {
            sb.append("none\n");
        } else {
            sb.append("\n");
            Iterator params = parameters.iterator();
            Iterator pnames = names.iterator();
            int countParam = 1;
            while ( params.hasNext() ) {
                sb.append( "param" );
                sb.append( countParam++ );
                sb.append( " paramName: " );
                sb.append( (String) pnames.next() );
                Parameter param = (Parameter) params.next();
                if ( param == null ) {
                  sb.append( " : parameter is null\n" );
                } else {
                  Object value = param.getObjectValue();
                  if ( value == null ) {
                    sb.append( " : value is null\n" );
                  } else {
                    sb.append( " : " + value.toString() + " type: " + value.getClass().getName() + "\n" );
                  }
                }
            }
        }
        return sb.toString();
    }

    /**
     * Returns last executed query for this command.
     * @return String - last executed query for this command.
     */
    public String getSqlString() {
        return sqlString;
    }

  private class BParameter implements Comparable {
    int bindNumber;
    int position;
    String value;
    String name;
    int type;
    
    public BParameter(String name, String value, int type, int position) {
      this.name = name;     
      this.value = value;     
      this.position = position;     
      this.type = type;
    }
    
    public String getName() {return name;}
    public String getValue() {return value;}
    public int getPosition() {return position;}
    public int getType() {return type;}
    
    public int compareTo(Object obj) {
      int result = 0;
      if (obj instanceof BParameter) {
        if (((BParameter) obj).getPosition() < position ) {
          result = 1;
        } else if (((BParameter) obj).getPosition() > position ) {
          result = -1;
        }
      }
      return result;
    }
  }

  //Rev.1 Inizio
  public String getUtente() {
      return utente; 
  }

  public void setUtente(String utente) {
      this.utente = utente; 
  }

  public String getModulo() {
      return modulo; 
  }

  public void setModulo(String modulo) {
      this.modulo = modulo; 
  }

  public String getIstanza() {
      return istanza; 
  }

  public void setIstanza(String istanza) {
      this.istanza = istanza; 
  }
      
  public String getProgetto() {
      return progetto; 
  }

  public void setProgetto(String progetto) {
      this.progetto = progetto; 
  }
      
  public String getEnte() {
      return ente; 
  }

  public void setEnte(String ente) {
      this.ente = ente; 
  }
      
  private void registraAccesso() throws SQLException {
    CallableStatement cstmtAccesso = null;
	String sqlAccesso = sql;
	cstmtAccesso = conn.createCallableStatement("{call AMVWEB.REGISTRA_ACCESSO(?, ?, ?, ?)}");
    String nullString = new String("");
	cstmtAccesso.setString(1, getIstanza());
	cstmtAccesso.setString(2, getModulo());
	cstmtAccesso.setString(3, getUtente());
	cstmtAccesso.setString(4, sqlString);
	cstmtAccesso.execute();
	cstmtAccesso.close();
  }
  //Rev.1 Fine
}
//End RawCommand class

