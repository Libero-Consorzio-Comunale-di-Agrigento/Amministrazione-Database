//AD4SoggettiV DataSource @142-E0EBEB45
package ad4web.AD4SoggRicercaInclusa_orig;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End AD4SoggettiV DataSource

//class DataObject Header @142-0B95F557
public class AD4SoggettiVDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @142-D4C7DDF6
    

    TextField urlS_NOME = new TextField(null, null);
    
    TextField urlS_UTENTE = new TextField(null, null);
    
    TextField urlS_ENTE = new TextField(null, null);
    
    TextField urlS_RICERCA = new TextField(null, null);
    
    TextField sesAD4PaginaSoggetto = new TextField(null, null);
    

    private AD4SoggettiVRow[] rows = new AD4SoggettiVRow[100];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @142-F0618C34

    public void  setUrlS_NOME( String param ) {
        this.urlS_NOME.setValue( param );
    }

    public void  setUrlS_NOME( Object param ) {
        this.urlS_NOME.setValue( param );
    }

    public void  setUrlS_NOME( Object param, Format ignore ) {
        this.urlS_NOME.setValue( param );
    }

    public void  setUrlS_UTENTE( String param ) {
        this.urlS_UTENTE.setValue( param );
    }

    public void  setUrlS_UTENTE( Object param ) {
        this.urlS_UTENTE.setValue( param );
    }

    public void  setUrlS_UTENTE( Object param, Format ignore ) {
        this.urlS_UTENTE.setValue( param );
    }

    public void  setUrlS_ENTE( String param ) {
        this.urlS_ENTE.setValue( param );
    }

    public void  setUrlS_ENTE( Object param ) {
        this.urlS_ENTE.setValue( param );
    }

    public void  setUrlS_ENTE( Object param, Format ignore ) {
        this.urlS_ENTE.setValue( param );
    }

    public void  setUrlS_RICERCA( String param ) {
        this.urlS_RICERCA.setValue( param );
    }

    public void  setUrlS_RICERCA( Object param ) {
        this.urlS_RICERCA.setValue( param );
    }

    public void  setUrlS_RICERCA( Object param, Format ignore ) {
        this.urlS_RICERCA.setValue( param );
    }

    public void  setSesAD4PaginaSoggetto( String param ) {
        this.sesAD4PaginaSoggetto.setValue( param );
    }

    public void  setSesAD4PaginaSoggetto( Object param ) {
        this.sesAD4PaginaSoggetto.setValue( param );
    }

    public void  setSesAD4PaginaSoggetto( Object param, Format ignore ) {
        this.sesAD4PaginaSoggetto.setValue( param );
    }

    public AD4SoggettiVRow[] getRows() {
        return rows;
    }

    public void setRows(AD4SoggettiVRow[] rows) {
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

//constructor @142-C7D03A1F
    public AD4SoggettiVDataObject(Page page) {
        super(page);
    }
//End constructor

//load @142-628D76DD
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT SOGGETTI.SOGGETTO, "
                    + "       TO_CHAR(SOGGETTI.SOGGETTO) soggetto_vis,	   "
                    + "       AD4WEB.GET_DATI_SOGGETTO(SOGGETTI.SOGGETTO)|| "
                    + "       DECODE(AD4WEB.GET_UTENTI_SOGGETTO(SOGGETTI.SOGGETTO), NULL, TO_CHAR(NULL),  "
                    + "'<strong>Associato agli utenti:</strong><br>'||AD4WEB.GET_UTENTI_SOGGETTO(SOGGETTI.SOGGETTO)) dati, "
                    + "       DECODE('{s_UTENTE}', NULL, TO_CHAR(NULL) "
                    + "                                , '<img title=\"Associa il soggetto all''utente '||UTENTE.GET_NOMINATIVO('{s_UTENTE}','Y',0)||'\" height=\"18\" src=\"../common/images/AD4/sogg_associa.gif\" width=\"18\" border=\"0\">') RIPORTA_UTENTE,	     "
                    + "       DECODE('{s_ENTE}', NULL, TO_CHAR(NULL) "
                    + "                              , '<img title=\"Associa il soggetto all''ente '''||'{s_ENTE}'||'''\" height=\"18\" src=\"../common/images/AD4/sogg_associa.gif\" width=\"18\" border=\"0\">') RIPORTA_ENTE, "
                    + "       '<a class=\"AFCDataLink\" href=\"'||nvl('{AD4PaginaSoggetto}','AD4Soggetto.do')||'?SOGGETTO='||SOGGETTO||'\">'||SOGGETTO||'</a>' pagina "
                    + "   FROM SOGGETTI "
                    + "  where SOGGETTI.NOME > ' ' AND "
                    + "        (upper(SOGGETTI.NOME) like upper('%{s_NOME}%') "
                    + "         OR SOGGETTI.Soggetto = TO_NUMBER(DECODE(Afc.is_number('{s_NOME}'),0,-1,'{s_NOME}')))    AND   "
                    + "        '{s_RICERCA}' = 'Y' AND "
                    + "        '{s_NOME}' is not null        " );
        if ( StringUtils.isEmpty( (String) urlS_NOME.getObjectValue() ) ) urlS_NOME.setValue( "" );
        command.addParameter( "s_NOME", urlS_NOME, null );
        if ( StringUtils.isEmpty( (String) urlS_UTENTE.getObjectValue() ) ) urlS_UTENTE.setValue( "" );
        command.addParameter( "s_UTENTE", urlS_UTENTE, null );
        if ( StringUtils.isEmpty( (String) urlS_ENTE.getObjectValue() ) ) urlS_ENTE.setValue( "" );
        command.addParameter( "s_ENTE", urlS_ENTE, null );
        if ( StringUtils.isEmpty( (String) urlS_RICERCA.getObjectValue() ) ) urlS_RICERCA.setValue( "" );
        command.addParameter( "s_RICERCA", urlS_RICERCA, null );
        if ( StringUtils.isEmpty( (String) sesAD4PaginaSoggetto.getObjectValue() ) ) sesAD4PaginaSoggetto.setValue( "" );
        command.addParameter( "AD4PaginaSoggetto", sesAD4PaginaSoggetto, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( SELECT SOGGETTI.SOGGETTO,  "
                                                         + "            TO_CHAR(SOGGETTI.SOGGETTO) soggetto_vis,	 AD4WEB.GET_DATI_SOGGETTO(SOGGETTI.SOGGETTO)|| DECODE(AD4WEB.GET_UTENTI_SOGGETTO(SOGGETTI.SOGGETTO), NULL, TO_CHAR(NULL), '<strong>Associato agli utenti:</strong><br>'||AD4WEB.GET_UTENTI_SOGGETTO(SOGGETTI.SOGGETTO)) dati, DECODE('{s_UTENTE}', NULL, TO_CHAR(NULL) , '<img title=\"Associa il soggetto all''utente '||UTENTE.GET_NOMINATIVO('{s_UTENTE}','Y',0)||'\" height=\"18\" src=\"../common/images/AD4/sogg_associa.gif\" width=\"18\" border=\"0\">') RIPORTA_UTENTE,	 DECODE('{s_ENTE}', NULL, TO_CHAR(NULL) , '<img title=\"Associa il soggetto all''ente '''||'{s_ENTE}'||'''\" height=\"18\" src=\"../common/images/AD4/sogg_associa.gif\" width=\"18\" border=\"0\">') RIPORTA_ENTE, '<a class=\"AFCDataLink\" href=\"'||nvl('{AD4PaginaSoggetto}','AD4Soggetto.do')||'?SOGGETTO='||SOGGETTO||'\">'||SOGGETTO||'</a>' pagina FROM SOGGETTI where SOGGETTI.NOME > ' ' AND (upper(SOGGETTI.NOME) like upper('%{s_NOME}%') OR SOGGETTI.Soggetto = TO_NUMBER(DECODE(Afc.is_number('{s_NOME}'),0,-1,'{s_NOME}'))) AND '{s_RICERCA}' = 'Y' AND '{s_NOME}' is not null  ) cnt " );
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

//loadDataBind @142-F343C293
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                AD4SoggettiVRow row = new AD4SoggettiVRow();
                DbRow record = (DbRow) records.nextElement();
                row.setSOGGETTO(Utils.convertToString(ds.parse(record.get("SOGGETTO"), row.getSOGGETTOField())));
                row.setSOGGETTO_VIS(Utils.convertToString(ds.parse(record.get("PAGINA"), row.getSOGGETTO_VISField())));
                row.setDATI(Utils.convertToString(ds.parse(record.get("DATI"), row.getDATIField())));
                row.setRIPORTA_UTENTE(Utils.convertToString(ds.parse(record.get("RIPORTA_UTENTE"), row.getRIPORTA_UTENTEField())));
                row.setRIPORTA_ENTE(Utils.convertToString(ds.parse(record.get("RIPORTA_ENTE"), row.getRIPORTA_ENTEField())));
                row.setPAGINA(Utils.convertToString(ds.parse(record.get("PAGINA"), row.getPAGINAField())));
                rows[counter++] = row;
            }
        }
//End loadDataBind

//End of load @142-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//getParameterByName @142-47F5E9C2
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "s_NOME".equals(name) && "url".equals(prefix) ) {
                param = urlS_NOME;
            } else if ( "s_NOME".equals(name) && prefix == null ) {
                param = urlS_NOME;
            }
            if ( "s_UTENTE".equals(name) && "url".equals(prefix) ) {
                param = urlS_UTENTE;
            } else if ( "s_UTENTE".equals(name) && prefix == null ) {
                param = urlS_UTENTE;
            }
            if ( "s_ENTE".equals(name) && "url".equals(prefix) ) {
                param = urlS_ENTE;
            } else if ( "s_ENTE".equals(name) && prefix == null ) {
                param = urlS_ENTE;
            }
            if ( "s_RICERCA".equals(name) && "url".equals(prefix) ) {
                param = urlS_RICERCA;
            } else if ( "s_RICERCA".equals(name) && prefix == null ) {
                param = urlS_RICERCA;
            }
            if ( "AD4PaginaSoggetto".equals(name) && "ses".equals(prefix) ) {
                param = sesAD4PaginaSoggetto;
            } else if ( "AD4PaginaSoggetto".equals(name) && prefix == null ) {
                param = sesAD4PaginaSoggetto;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addGridDataObjectListener @142-B1E4C7C7
    public synchronized void addDataObjectListener( DataObjectListener l ) {
        listeners.addElement(l);
    }
//End addGridDataObjectListener

//removeGridDataObjectListener @142-9F30CEFB
    public synchronized void removeDataObjectListener( DataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeGridDataObjectListener

//fireBeforeBuildSelectEvent @142-238A81BB
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

//fireBeforeExecuteSelectEvent @142-9DA7B025
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

//fireAfterExecuteSelectEvent @142-F7E8A616
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

//class DataObject Tail @142-ED3F53A4
} // End of class DS
//End class DataObject Tail

