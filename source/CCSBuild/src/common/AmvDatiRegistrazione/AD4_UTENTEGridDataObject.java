//AD4_UTENTEGrid DataSource @2-2C236626
package common.AmvDatiRegistrazione;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End AD4_UTENTEGrid DataSource

//class DataObject Header @2-C8A5C6AD
public class AD4_UTENTEGridDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @2-E24254E2
    

    TextField sesUtente = new TextField(null, null);
    
    TextField sesMVUTE = new TextField(null, null);
    
    TextField sesModulo = new TextField(null, null);
    
    TextField urlMODULO = new TextField(null, null);
    
    TextField sesIstanza = new TextField(null, null);
    
    TextField urlISTANZA = new TextField(null, null);
    
    TextField sesMVPWD = new TextField(null, null);
    

    private AD4_UTENTEGridRow[] rows = new AD4_UTENTEGridRow[20];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @2-4DC4A8F4

    public void  setSesUtente( String param ) {
        this.sesUtente.setValue( param );
    }

    public void  setSesUtente( Object param ) {
        this.sesUtente.setValue( param );
    }

    public void  setSesUtente( Object param, Format ignore ) {
        this.sesUtente.setValue( param );
    }

    public void  setSesMVUTE( String param ) {
        this.sesMVUTE.setValue( param );
    }

    public void  setSesMVUTE( Object param ) {
        this.sesMVUTE.setValue( param );
    }

    public void  setSesMVUTE( Object param, Format ignore ) {
        this.sesMVUTE.setValue( param );
    }

    public void  setSesModulo( String param ) {
        this.sesModulo.setValue( param );
    }

    public void  setSesModulo( Object param ) {
        this.sesModulo.setValue( param );
    }

    public void  setSesModulo( Object param, Format ignore ) {
        this.sesModulo.setValue( param );
    }

    public void  setUrlMODULO( String param ) {
        this.urlMODULO.setValue( param );
    }

    public void  setUrlMODULO( Object param ) {
        this.urlMODULO.setValue( param );
    }

    public void  setUrlMODULO( Object param, Format ignore ) {
        this.urlMODULO.setValue( param );
    }

    public void  setSesIstanza( String param ) {
        this.sesIstanza.setValue( param );
    }

    public void  setSesIstanza( Object param ) {
        this.sesIstanza.setValue( param );
    }

    public void  setSesIstanza( Object param, Format ignore ) {
        this.sesIstanza.setValue( param );
    }

    public void  setUrlISTANZA( String param ) {
        this.urlISTANZA.setValue( param );
    }

    public void  setUrlISTANZA( Object param ) {
        this.urlISTANZA.setValue( param );
    }

    public void  setUrlISTANZA( Object param, Format ignore ) {
        this.urlISTANZA.setValue( param );
    }

    public void  setSesMVPWD( String param ) {
        this.sesMVPWD.setValue( param );
    }

    public void  setSesMVPWD( Object param ) {
        this.sesMVPWD.setValue( param );
    }

    public void  setSesMVPWD( Object param, Format ignore ) {
        this.sesMVPWD.setValue( param );
    }

    public AD4_UTENTEGridRow[] getRows() {
        return rows;
    }

    public void setRows(AD4_UTENTEGridRow[] rows) {
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

//constructor @2-62C00618
    public AD4_UTENTEGridDataObject(Page page) {
        super(page);
    }
//End constructor

//load @2-F561CEF6
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT   AD4_SOGGETTO.GET_NOME(s.SOGGETTO,'Y') NOME_SOGGETTO "
                    + ",  "
                    + "AD4_SOGGETTO.GET_DATA_NASCITA(s.SOGGETTO,'N') DATA_NASCITA "
                    + ",  "
                    + "AD4_SOGGETTO.GET_DES_COMUNE_NAS(s.SOGGETTO,'N') COMUNE_NASCITA "
                    + ",  "
                    + "AD4_SOGGETTO.GET_PROVINCIA_NAS_SIGLA(s.SOGGETTO,'N') PROVINCIA_NASCITA "
                    + ",  "
                    + "AD4_SOGGETTO.GET_CODICE_FISCALE(s.SOGGETTO,'N') CODICE_FISCALE "
                    + ", AD4_SOGGETTO.GET_INDIRIZZO_COMPLETO(s.SOGGETTO,'N') INDIRIZZO_COMPLETO "
                    + ",  "
                    + "AD4_SOGGETTO.GET_INDIRIZZO_WEB(s.SOGGETTO) INDIRIZZO_WEB "
                    + ",  "
                    + "AD4_SOGGETTO.GET_TELEFONO(s.SOGGETTO) TELEFONO "
                    + ", AD4_SOGGETTO.GET_FAX(s.SOGGETTO) FAX "
                    + ",  "
                    + "m.DESCRIZIONE||' - '||i.DESCRIZIONE SERVIZIO "
                    + ", u.NOMINATIVO NOMINATIVO "
                    + ", '{MVPWD}' PASSWORD "
                    + ",  "
                    + "decode (v.TIPO_NOTIFICA,'MAIL','Posta elettronica','POSTA',  "
                    + "'Posta Ordinaria','SMS', 'SMS (Short Message System)') TIPO_NOTIFICA "
                    + ",  "
                    + "decode (v.TIPO_NOTIFICA "
                    + "         ,'MAIL',AD4_SOGGETTO.GET_INDIRIZZO_WEB(s.SOGGETTO) "
                    + "         ,'POSTA',AD4_SOGGETTO.GET_INDIRIZZO_COMPLETO(s.SOGGETTO,'N')  "
                    + "         ,'SMS',AD4_SOGGETTO.GET_TELEFONO(s.SOGGETTO)  "
                    + "          ) INDIRIZZO_NOTIFICA "
                    + ", nvl('{MODULOURL}','{Modulo}') MODULO "
                    + ", nvl('{ISTANZAURL}','{Istanza}')ISTANZA "
                    + "FROM AD4_UTENTI u, AD4_UTENTI_SOGGETTI s, AD4_MODULI m, AD4_ISTANZE i, AD4_SERVIZI v "
                    + "WHERE u.UTENTE = nvl('{MVUTE}','{Utente}') "
                    + "AND s.utente = u.utente "
                    + "AND m.modulo = nvl('{MODULOURL}','{Modulo}') "
                    + "AND i.istanza = nvl('{ISTANZAURL}','{Istanza}') "
                    + "AND v.modulo = m.modulo "
                    + "AND v.istanza = i.istanza " );
        if ( StringUtils.isEmpty( (String) sesUtente.getObjectValue() ) ) sesUtente.setValue( "" );
        command.addParameter( "Utente", sesUtente, null );
        if ( StringUtils.isEmpty( (String) sesMVUTE.getObjectValue() ) ) sesMVUTE.setValue( "" );
        command.addParameter( "MVUTE", sesMVUTE, null );
        if ( StringUtils.isEmpty( (String) sesModulo.getObjectValue() ) ) sesModulo.setValue( "" );
        command.addParameter( "Modulo", sesModulo, null );
        if ( StringUtils.isEmpty( (String) urlMODULO.getObjectValue() ) ) urlMODULO.setValue( "" );
        command.addParameter( "MODULOURL", urlMODULO, null );
        if ( StringUtils.isEmpty( (String) sesIstanza.getObjectValue() ) ) sesIstanza.setValue( "" );
        command.addParameter( "Istanza", sesIstanza, null );
        if ( StringUtils.isEmpty( (String) urlISTANZA.getObjectValue() ) ) urlISTANZA.setValue( "" );
        command.addParameter( "ISTANZAURL", urlISTANZA, null );
        if ( StringUtils.isEmpty( (String) sesMVPWD.getObjectValue() ) ) sesMVPWD.setValue( "" );
        command.addParameter( "MVPWD", sesMVPWD, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( SELECT AD4_SOGGETTO.GET_NOME(s.SOGGETTO,'Y') NOME_SOGGETTO ,  "
                                                         + "            AD4_SOGGETTO.GET_DATA_NASCITA(s.SOGGETTO,'N') DATA_NASCITA ,  "
                                                         + "            AD4_SOGGETTO.GET_DES_COMUNE_NAS(s.SOGGETTO,'N') COMUNE_NASCITA ,  "
                                                         + "            AD4_SOGGETTO.GET_PROVINCIA_NAS_SIGLA(s.SOGGETTO,'N') PROVINCIA_NASCITA ,  "
                                                         + "            AD4_SOGGETTO.GET_CODICE_FISCALE(s.SOGGETTO,'N') CODICE_FISCALE ,  "
                                                         + "            AD4_SOGGETTO.GET_INDIRIZZO_COMPLETO(s.SOGGETTO,'N') INDIRIZZO_COMPLETO ,  "
                                                         + "            AD4_SOGGETTO.GET_INDIRIZZO_WEB(s.SOGGETTO) INDIRIZZO_WEB ,  "
                                                         + "            AD4_SOGGETTO.GET_TELEFONO(s.SOGGETTO) TELEFONO , AD4_SOGGETTO.GET_FAX(s.SOGGETTO) FAX ,  "
                                                         + "            m.DESCRIZIONE||' - '||i.DESCRIZIONE SERVIZIO , u.NOMINATIVO NOMINATIVO ,  "
                                                         + "            '{MVPWD}' PASSWORD ,  "
                                                         + "            decode (v.TIPO_NOTIFICA,'MAIL','Posta elettronica','POSTA', 'Posta Ordinaria','SMS',  "
                                                         + "            'SMS (Short Message System)') TIPO_NOTIFICA ,  "
                                                         + "            decode (v.TIPO_NOTIFICA ,'MAIL',AD4_SOGGETTO.GET_INDIRIZZO_WEB(s.SOGGETTO) ,'POSTA',AD4_SOGGETTO.GET_INDIRIZZO_COMPLETO(s.SOGGETTO,'N') ,'SMS',AD4_SOGGETTO.GET_TELEFONO(s.SOGGETTO) ) INDIRIZZO_NOTIFICA , nvl('{MODULOURL}','{Modulo}') MODULO , nvl('{ISTANZAURL}','{Istanza}')ISTANZA FROM AD4_UTENTI u, AD4_UTENTI_SOGGETTI s, AD4_MODULI m, AD4_ISTANZE i, AD4_SERVIZI v WHERE u.UTENTE = nvl('{MVUTE}','{Utente}') AND s.utente = u.utente AND m.modulo = nvl('{MODULOURL}','{Modulo}') AND i.istanza = nvl('{ISTANZAURL}','{Istanza}') AND v.modulo = m.modulo AND v.istanza = i.istanza  ) cnt " );
        if ( ! StringUtils.isEmpty( orderBy ) ) {
            command.setOrder( orderBy );
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

//loadDataBind @2-F02E1894
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                AD4_UTENTEGridRow row = new AD4_UTENTEGridRow();
                DbRow record = (DbRow) records.nextElement();
                row.setNOME_SOGGETTO(Utils.convertToString(ds.parse(record.get("NOME_SOGGETTO"), row.getNOME_SOGGETTOField())));
                row.setDATA_NASCITA(Utils.convertToString(ds.parse(record.get("DATA_NASCITA"), row.getDATA_NASCITAField())));
                row.setCOMUNE_NASCITA(Utils.convertToString(ds.parse(record.get("COMUNE_NASCITA"), row.getCOMUNE_NASCITAField())));
                row.setPROVINCIA_NASCITA(Utils.convertToString(ds.parse(record.get("PROVINCIA_NASCITA"), row.getPROVINCIA_NASCITAField())));
                row.setCODICE_FISCALE(Utils.convertToString(ds.parse(record.get("CODICE_FISCALE"), row.getCODICE_FISCALEField())));
                row.setINDIRIZZO_COMPLETO(Utils.convertToString(ds.parse(record.get("INDIRIZZO_COMPLETO"), row.getINDIRIZZO_COMPLETOField())));
                row.setINDIRIZZO_WEB(Utils.convertToString(ds.parse(record.get("INDIRIZZO_WEB"), row.getINDIRIZZO_WEBField())));
                row.setTELEFONO(Utils.convertToString(ds.parse(record.get("TELEFONO"), row.getTELEFONOField())));
                row.setFAX(Utils.convertToString(ds.parse(record.get("FAX"), row.getFAXField())));
                row.setNOMINATIVO(Utils.convertToString(ds.parse(record.get("NOMINATIVO"), row.getNOMINATIVOField())));
                row.setPASSWORD(Utils.convertToString(ds.parse(record.get("PASSWORD"), row.getPASSWORDField())));
                row.setTIPO_NOTIFICA(Utils.convertToString(ds.parse(record.get("TIPO_NOTIFICA"), row.getTIPO_NOTIFICAField())));
                row.setINDIRIZZO_NOTIFICA(Utils.convertToString(ds.parse(record.get("INDIRIZZO_NOTIFICA"), row.getINDIRIZZO_NOTIFICAField())));
                rows[counter++] = row;
            }
        }
//End loadDataBind

//End of load @2-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//getParameterByName @2-3DC97B2E
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "Utente".equals(name) && "ses".equals(prefix) ) {
                param = sesUtente;
            } else if ( "Utente".equals(name) && prefix == null ) {
                param = sesUtente;
            }
            if ( "MVUTE".equals(name) && "ses".equals(prefix) ) {
                param = sesMVUTE;
            } else if ( "MVUTE".equals(name) && prefix == null ) {
                param = sesMVUTE;
            }
            if ( "Modulo".equals(name) && "ses".equals(prefix) ) {
                param = sesModulo;
            } else if ( "Modulo".equals(name) && prefix == null ) {
                param = sesModulo;
            }
            if ( "MODULO".equals(name) && "url".equals(prefix) ) {
                param = urlMODULO;
            } else if ( "MODULO".equals(name) && prefix == null ) {
                param = urlMODULO;
            }
            if ( "Istanza".equals(name) && "ses".equals(prefix) ) {
                param = sesIstanza;
            } else if ( "Istanza".equals(name) && prefix == null ) {
                param = sesIstanza;
            }
            if ( "ISTANZA".equals(name) && "url".equals(prefix) ) {
                param = urlISTANZA;
            } else if ( "ISTANZA".equals(name) && prefix == null ) {
                param = urlISTANZA;
            }
            if ( "MVPWD".equals(name) && "ses".equals(prefix) ) {
                param = sesMVPWD;
            } else if ( "MVPWD".equals(name) && prefix == null ) {
                param = sesMVPWD;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addGridDataObjectListener @2-B1E4C7C7
    public synchronized void addDataObjectListener( DataObjectListener l ) {
        listeners.addElement(l);
    }
//End addGridDataObjectListener

//removeGridDataObjectListener @2-9F30CEFB
    public synchronized void removeDataObjectListener( DataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeGridDataObjectListener

//fireBeforeBuildSelectEvent @2-238A81BB
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

//fireBeforeExecuteSelectEvent @2-9DA7B025
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

//fireAfterExecuteSelectEvent @2-F7E8A616
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

//class DataObject Tail @2-ED3F53A4
} // End of class DS
//End class DataObject Tail

