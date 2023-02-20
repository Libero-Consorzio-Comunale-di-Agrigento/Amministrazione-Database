//AD4_UTENTEGrid DataSource @71-865B3015
package amvadm.AdmRichiestaPrint;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End AD4_UTENTEGrid DataSource

//class DataObject Header @71-C8A5C6AD
public class AD4_UTENTEGridDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @71-DB605EDB
    

    TextField urlID = new TextField(null, null);
    

    private AD4_UTENTEGridRow[] rows = new AD4_UTENTEGridRow[20];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @71-565EE1D1

    public void  setUrlID( String param ) {
        this.urlID.setValue( param );
    }

    public void  setUrlID( Object param ) {
        this.urlID.setValue( param );
    }

    public void  setUrlID( Object param, Format ignore ) {
        this.urlID.setValue( param );
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

//constructor @71-62C00618
    public AD4_UTENTEGridDataObject(Page page) {
        super(page);
    }
//End constructor

//load @71-AD9D43E6
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
                    + ",  "
                    + "'n.'||r.id_richiesta||' del '||to_char(r.data,'dd/mm/yyyy') data "
                    + ", u.NOMINATIVO NOMINATIVO "
                    + ",  "
                    + "decode(r.notificata,'S','', "
                    + "         decode(instr(u.note,'PWD='),1,substr(u.note, 5,  "
                    + "length(u.note)) "
                    + "               ,decode(r.stato,'A','PWDUTENTE='||u.password,'')) "
                    + "        ) PASSWORD "
                    + "FROM ad4_richieste_abilitazione r, ad4_istanze i, ad4_moduli m, ad4_utenti u, ad4_utenti_soggetti s "
                    + "WHERE r.modulo = m.modulo "
                    + "AND r.istanza = i.istanza "
                    + "AND r.utente = u.utente "
                    + "AND r.utente = s.utente "
                    + "AND r.id_richiesta = '{ID}'" );
        if ( StringUtils.isEmpty( (String) urlID.getObjectValue() ) ) urlID.setValue( "" );
        command.addParameter( "ID", urlID, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( SELECT AD4_SOGGETTO.GET_NOME(s.SOGGETTO,'Y') NOME_SOGGETTO ,  "
                                                         + "            AD4_SOGGETTO.GET_DATA_NASCITA(s.SOGGETTO,'N') DATA_NASCITA ,  "
                                                         + "            AD4_SOGGETTO.GET_DES_COMUNE_NAS(s.SOGGETTO,'N') COMUNE_NASCITA ,  "
                                                         + "            AD4_SOGGETTO.GET_PROVINCIA_NAS_SIGLA(s.SOGGETTO,'N') PROVINCIA_NASCITA ,  "
                                                         + "            AD4_SOGGETTO.GET_CODICE_FISCALE(s.SOGGETTO,'N') CODICE_FISCALE ,  "
                                                         + "            AD4_SOGGETTO.GET_INDIRIZZO_COMPLETO(s.SOGGETTO,'N') INDIRIZZO_COMPLETO ,  "
                                                         + "            AD4_SOGGETTO.GET_INDIRIZZO_WEB(s.SOGGETTO) INDIRIZZO_WEB ,  "
                                                         + "            AD4_SOGGETTO.GET_TELEFONO(s.SOGGETTO) TELEFONO , AD4_SOGGETTO.GET_FAX(s.SOGGETTO) FAX ,  "
                                                         + "            m.DESCRIZIONE||' - '||i.DESCRIZIONE SERVIZIO ,  "
                                                         + "            'n.'||r.id_richiesta||' del '||to_char(r.data,'dd/mm/yyyy') data , u.NOMINATIVO NOMINATIVO ,  "
                                                         + "            decode(r.notificata,'S','', decode(instr(u.note,'PWD='),1,substr(u.note, 5,  "
                                                         + "            length(u.note)) ,decode(r.stato,'A','PWDUTENTE='||u.password,'')) ) PASSWORD FROM ad4_richieste_abilitazione r, ad4_istanze i, ad4_moduli m, ad4_utenti u, ad4_utenti_soggetti s WHERE r.modulo = m.modulo AND r.istanza = i.istanza AND r.utente = u.utente AND r.utente = s.utente AND r.id_richiesta = '{ID}' ) cnt " );
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

//loadDataBind @71-9875008C
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                AD4_UTENTEGridRow row = new AD4_UTENTEGridRow();
                DbRow record = (DbRow) records.nextElement();
                row.setDATA_RICHIESTA(Utils.convertToString(ds.parse(record.get("DATA"), row.getDATA_RICHIESTAField())));
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
                rows[counter++] = row;
            }
        }
//End loadDataBind

//End of load @71-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//getParameterByName @71-1BE048C3
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "ID".equals(name) && "url".equals(prefix) ) {
                param = urlID;
            } else if ( "ID".equals(name) && prefix == null ) {
                param = urlID;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addGridDataObjectListener @71-B1E4C7C7
    public synchronized void addDataObjectListener( DataObjectListener l ) {
        listeners.addElement(l);
    }
//End addGridDataObjectListener

//removeGridDataObjectListener @71-9F30CEFB
    public synchronized void removeDataObjectListener( DataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeGridDataObjectListener

//fireBeforeBuildSelectEvent @71-238A81BB
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

//fireBeforeExecuteSelectEvent @71-9DA7B025
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

//fireAfterExecuteSelectEvent @71-F7E8A616
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

//class DataObject Tail @71-ED3F53A4
} // End of class DS
//End class DataObject Tail

