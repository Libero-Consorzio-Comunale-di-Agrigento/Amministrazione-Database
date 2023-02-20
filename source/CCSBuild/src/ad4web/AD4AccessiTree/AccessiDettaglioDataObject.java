//AccessiDettaglio DataSource @22-9698737B
package ad4web.AD4AccessiTree;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End AccessiDettaglio DataSource

//class DataObject Header @22-0F756870
public class AccessiDettaglioDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @22-0FE140D2
    

    TextField urlID = new TextField(null, null);
    
    TextField sesProgetto = new TextField(null, null);
    
    TextField urlUTENTE = new TextField(null, null);
    

    private AccessiDettaglioRow[] rows = new AccessiDettaglioRow[20];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @22-B12D8CDE

    public void  setUrlID( String param ) {
        this.urlID.setValue( param );
    }

    public void  setUrlID( Object param ) {
        this.urlID.setValue( param );
    }

    public void  setUrlID( Object param, Format ignore ) {
        this.urlID.setValue( param );
    }

    public void  setSesProgetto( String param ) {
        this.sesProgetto.setValue( param );
    }

    public void  setSesProgetto( Object param ) {
        this.sesProgetto.setValue( param );
    }

    public void  setSesProgetto( Object param, Format ignore ) {
        this.sesProgetto.setValue( param );
    }

    public void  setUrlUTENTE( String param ) {
        this.urlUTENTE.setValue( param );
    }

    public void  setUrlUTENTE( Object param ) {
        this.urlUTENTE.setValue( param );
    }

    public void  setUrlUTENTE( Object param, Format ignore ) {
        this.urlUTENTE.setValue( param );
    }

    public AccessiDettaglioRow[] getRows() {
        return rows;
    }

    public void setRows(AccessiDettaglioRow[] rows) {
        this.rows = rows;
    }

    public void setPageNum( int pageNum ) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize( int pageSize ) {
        this.pageSize = pageSize;
    }

//End properties of DataObject

//constructor @22-8DB9C08E
    public AccessiDettaglioDataObject(Page page) {
        super(page);
    }
//End constructor

//load @22-C322773B
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT 'n.' || acce.ACCE_ID DES_ACCESSO "
                    + "     , TO_CHAR (acce.DATA,  "
                    + "'HH24:mi.ss') DES_ORA "
                    + "     ,    DECODE (LOG "
                    + "                , 'WPW',  "
                    + "'Autenticazione fallita' "
                    + "                , 'ON', 'Login al servizio' "
                    + "                , 'OFF',  "
                    + "'Login al servizio' "
                    + "                , 'OUT', 'Logout dal servizio' "
                    + "                , 'ABT',  "
                    + "'Interruzione per errore applicazione' "
                    + "                ,  "
                    + "'Accesso a risorsa web' "
                    + "                 ) "
                    + "       || ' [' "
                    + "       || 'Sessione ' "
                    + "       || acce.SESSION_ID "
                    + "       || ']' "
                    + "       || DECODE (acce.NOTE "
                    + "                , NULL, '' "
                    + "                , DECODE(Amv_Menu.get_titolo_pagina ('{Progetto}', acce.NOTE),'', '',' (' "
                    + "                  ||  Amv_Menu.get_titolo_pagina ('{Progetto}', acce.NOTE) "
                    + "                  || ')') "
                    + "                 ) DSP_SESSIONE "
                    + "     , acce.NOTE "
                    + "  FROM ACCESSI ACCE, AD4_MODULI modu, AD4_ISTANZE ista "
                    + " WHERE '{UTENTE}' is not null "
                    + "   and TO_CHAR(999999 - (TO_NUMBER(TO_CHAR(TRUNC(acce.DATA),'yyyyMM'))))||'['||TO_CHAR(99999999 - (TO_NUMBER(TO_CHAR(TRUNC(acce.DATA),'yyyyMMDD'))))||'['||acce.modulo||'-'||acce.Istanza||'['||acce.Utente = '{ID}' "
                    + "   AND modu.modulo(+) = acce.modulo "
                    + "   AND ista.Istanza(+) = acce.Istanza "
                    + "" );
        if ( StringUtils.isEmpty( (String) urlID.getObjectValue() ) ) urlID.setValue( "" );
        command.addParameter( "ID", urlID, null );
        if ( StringUtils.isEmpty( (String) sesProgetto.getObjectValue() ) ) sesProgetto.setValue( "" );
        command.addParameter( "Progetto", sesProgetto, null );
        if ( StringUtils.isEmpty( (String) urlUTENTE.getObjectValue() ) ) urlUTENTE.setValue( "" );
        command.addParameter( "UTENTE", urlUTENTE, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( SELECT 'n.' || acce.ACCE_ID DES_ACCESSO , TO_CHAR (acce.DATA,  "
                                                         + "            'HH24:mi.ss') DES_ORA , DECODE (LOG , 'WPW', 'Autenticazione fallita' , 'ON',  "
                                                         + "            'Login al servizio' , 'OFF', 'Login al servizio' , 'OUT',  "
                                                         + "            'Logout dal servizio' , 'ABT', 'Interruzione per errore applicazione' ,  "
                                                         + "            'Accesso a risorsa web' ) || ' [' || 'Sessione ' || acce.SESSION_ID || ']' || DECODE (acce.NOTE , NULL, '' , DECODE(Amv_Menu.get_titolo_pagina ('{Progetto}', acce.NOTE),'', '',' (' || Amv_Menu.get_titolo_pagina ('{Progetto}', acce.NOTE) || ')') ) DSP_SESSIONE , acce.NOTE FROM ACCESSI ACCE, AD4_MODULI modu, AD4_ISTANZE ista WHERE '{UTENTE}' is not null and TO_CHAR(999999 - (TO_NUMBER(TO_CHAR(TRUNC(acce.DATA),'yyyyMM'))))||'['||TO_CHAR(99999999 - (TO_NUMBER(TO_CHAR(TRUNC(acce.DATA),'yyyyMMDD'))))||'['||acce.modulo||'-'||acce.Istanza||'['||acce.Utente = '{ID}' AND modu.modulo(+) = acce.modulo AND ista.Istanza(+) = acce.Istanza  ) cnt " );
        if ( ! StringUtils.isEmpty( orderBy ) ) {
            command.setOrder( orderBy );
        } else {
            command.setOrder( "acce_id DESC" );
        }
        command.setStartPos( ( pageNum - 1 ) * pageSize + 1 );
        command.setFetchSize( pageSize );

        fireBeforeBuildSelectEvent( new DataObjectEvent(command) );


        fireBeforeExecuteSelectEvent( new DataObjectEvent(command) );

        if ( ! StringUtils.isEmpty( command.getCountSql() ) ) {
            if ( ! ds.hasErrors() ) {
                amountOfRows = command.count();
                CCLogger.getInstance().debug(command.toString());
            }
        }
        Enumeration records = null;
        if ( ! ds.hasErrors() ) {
            records = command.getRows();
        }

        CCLogger.getInstance().debug(command.toString());

        fireAfterExecuteSelectEvent( new DataObjectEvent(command) );

        ds.closeConnection();
//End load

//loadDataBind @22-A682685F
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                AccessiDettaglioRow row = new AccessiDettaglioRow();
                DbRow record = (DbRow) records.nextElement();
                row.setDES_ACCESSO(Utils.convertToString(ds.parse(record.get("DES_ACCESSO"), row.getDES_ACCESSOField())));
                row.setDES_ORA(Utils.convertToString(ds.parse(record.get("DES_ORA"), row.getDES_ORAField())));
                row.setNOTE(Utils.convertToString(ds.parse(record.get("NOTE"), row.getNOTEField())));
                row.setDSP_SESSIONE(Utils.convertToString(ds.parse(record.get("DSP_SESSIONE"), row.getDSP_SESSIONEField())));
                rows[counter++] = row;
            }
        }
//End loadDataBind

//End of load @22-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//getParameterByName @22-A7363F04
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "ID".equals(name) && "url".equals(prefix) ) {
                param = urlID;
            } else if ( "ID".equals(name) && prefix == null ) {
                param = urlID;
            }
            if ( "Progetto".equals(name) && "ses".equals(prefix) ) {
                param = sesProgetto;
            } else if ( "Progetto".equals(name) && prefix == null ) {
                param = sesProgetto;
            }
            if ( "UTENTE".equals(name) && "url".equals(prefix) ) {
                param = urlUTENTE;
            } else if ( "UTENTE".equals(name) && prefix == null ) {
                param = urlUTENTE;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addGridDataObjectListener @22-B1E4C7C7
    public synchronized void addDataObjectListener( DataObjectListener l ) {
        listeners.addElement(l);
    }
//End addGridDataObjectListener

//removeGridDataObjectListener @22-9F30CEFB
    public synchronized void removeDataObjectListener( DataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeGridDataObjectListener

//fireBeforeBuildSelectEvent @22-238A81BB
    public void fireBeforeBuildSelectEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource(this);
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i=0; i < v.size(); i++) {
            ((DataObjectListener)v.elementAt(i)).beforeBuildSelect(e);
        }
    }
//End fireBeforeBuildSelectEvent

//fireBeforeExecuteSelectEvent @22-9DA7B025
    public void fireBeforeExecuteSelectEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i=0; i < v.size(); i++) {
            ((DataObjectListener)v.elementAt(i)).beforeExecuteSelect(e);
        }
    }
//End fireBeforeExecuteSelectEvent

//fireAfterExecuteSelectEvent @22-F7E8A616
    public void fireAfterExecuteSelectEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i=0; i < v.size(); i++) {
            ((DataObjectListener)v.elementAt(i)).afterExecuteSelect(e);
        }
    }
//End fireAfterExecuteSelectEvent

//class DataObject Tail @22-ED3F53A4
} // End of class DS
//End class DataObject Tail

