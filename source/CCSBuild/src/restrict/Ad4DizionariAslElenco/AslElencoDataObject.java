//AslElenco DataSource @8-125CACDD
package restrict.Ad4DizionariAslElenco;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End AslElenco DataSource

//class DataObject Header @8-4B7AF972
public class AslElencoDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @8-E3C9D7CE
    

    TextField urlS_REGIONE = new TextField(null, null);
    
    TextField urlS_ASL = new TextField(null, null);
    
    TextField urlS_ATTIVA = new TextField(null, null);
    

    private AslElencoRow[] rows = new AslElencoRow[1000];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @8-AB6E6E1C

    public void  setUrlS_REGIONE( String param ) {
        this.urlS_REGIONE.setValue( param );
    }

    public void  setUrlS_REGIONE( Object param ) {
        this.urlS_REGIONE.setValue( param );
    }

    public void  setUrlS_REGIONE( Object param, Format ignore ) {
        this.urlS_REGIONE.setValue( param );
    }

    public void  setUrlS_ASL( String param ) {
        this.urlS_ASL.setValue( param );
    }

    public void  setUrlS_ASL( Object param ) {
        this.urlS_ASL.setValue( param );
    }

    public void  setUrlS_ASL( Object param, Format ignore ) {
        this.urlS_ASL.setValue( param );
    }

    public void  setUrlS_ATTIVA( String param ) {
        this.urlS_ATTIVA.setValue( param );
    }

    public void  setUrlS_ATTIVA( Object param ) {
        this.urlS_ATTIVA.setValue( param );
    }

    public void  setUrlS_ATTIVA( Object param, Format ignore ) {
        this.urlS_ATTIVA.setValue( param );
    }

    public AslElencoRow[] getRows() {
        return rows;
    }

    public void setRows(AslElencoRow[] rows) {
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

//constructor @8-8EA8466A
    public AslElencoDataObject(Page page) {
        super(page);
    }
//End constructor

//load @8-636B603D
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "select reg.denominazione                                                  regione_denominazione "
                    + "      ,regione_asl "
                    + "      ,codice_asl "
                    + "      ,descrizione "
                    + "      ,asl.utente_aggiornamento "
                    + "      ,asl.attiva "
                    + "      ,ad4_ccs.format_data_trunc(asl.data_aggiornamento)                      data_aggiornamento "
                    + "  from ad4_asl       asl "
                    + "      ,ad4_regioni   reg "
                    + " where asl.regione_asl = reg.regione "
                    + "   and (   descrizione like upper('%'||'{s_ASL}')||'%' "
                    + "        or codice_asl like '{s_ASL}'||'%' "
                    + "       ) "
                    + "   and (   regione = '{s_REGIONE}' "
                    + "        or '{s_REGIONE}' is null "
                    + "       ) "
                    + "   and (attiva like'{s_ATTIVA}' or '{s_ATTIVA}' is null) "
                    + " " );
        if ( StringUtils.isEmpty( (String) urlS_REGIONE.getObjectValue() ) ) urlS_REGIONE.setValue( "" );
        command.addParameter( "s_REGIONE", urlS_REGIONE, null );
        if ( StringUtils.isEmpty( (String) urlS_ASL.getObjectValue() ) ) urlS_ASL.setValue( "" );
        command.addParameter( "s_ASL", urlS_ASL, null );
        if ( StringUtils.isEmpty( (String) urlS_ATTIVA.getObjectValue() ) ) urlS_ATTIVA.setValue( "%" );
        command.addParameter( "s_ATTIVA", urlS_ATTIVA, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( select reg.denominazione regione_denominazione ,regione_asl ,codice_asl ,descrizione ,asl.utente_aggiornamento ,asl.attiva ,ad4_ccs.format_data_trunc(asl.data_aggiornamento) data_aggiornamento from ad4_asl asl ,ad4_regioni reg where asl.regione_asl = reg.regione and ( descrizione like upper('%'||'{s_ASL}')||'%' or codice_asl like '{s_ASL}'||'%' ) and ( regione = '{s_REGIONE}' or '{s_REGIONE}' is null ) and (attiva like'{s_ATTIVA}' or '{s_ATTIVA}' is null)  ) cnt " );
        if ( ! StringUtils.isEmpty( orderBy ) ) {
            command.setOrder( orderBy );
        } else {
            command.setOrder( "descrizione" );
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

//loadDataBind @8-3CD7294F
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                AslElencoRow row = new AslElencoRow();
                DbRow record = (DbRow) records.nextElement();
                row.setREGIONE_ASL(Utils.convertToString(ds.parse(record.get("REGIONE_ASL"), row.getREGIONE_ASLField())));
                row.setCODICE_ASL(Utils.convertToString(ds.parse(record.get("CODICE_ASL"), row.getCODICE_ASLField())));
                row.setDESCRIZIONE(Utils.convertToString(ds.parse(record.get("DESCRIZIONE"), row.getDESCRIZIONEField())));
                row.setREGIONE_DENOMINAZIONE(Utils.convertToString(ds.parse(record.get("REGIONE_DENOMINAZIONE"), row.getREGIONE_DENOMINAZIONEField())));
                row.setATTIVA(Utils.convertToString(ds.parse(record.get("ATTIVA"), row.getATTIVAField())));
                row.setUTENTE_AGGIORNAMENTO(Utils.convertToString(ds.parse(record.get("UTENTE_AGGIORNAMENTO"), row.getUTENTE_AGGIORNAMENTOField())));
                row.setDATA_AGGIORNAMENTO(Utils.convertToString(ds.parse(record.get("DATA_AGGIORNAMENTO"), row.getDATA_AGGIORNAMENTOField())));
                rows[counter++] = row;
            }
        }
//End loadDataBind

//End of load @8-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//getParameterByName @8-B504C1A1
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "s_REGIONE".equals(name) && "url".equals(prefix) ) {
                param = urlS_REGIONE;
            } else if ( "s_REGIONE".equals(name) && prefix == null ) {
                param = urlS_REGIONE;
            }
            if ( "s_ASL".equals(name) && "url".equals(prefix) ) {
                param = urlS_ASL;
            } else if ( "s_ASL".equals(name) && prefix == null ) {
                param = urlS_ASL;
            }
            if ( "s_ATTIVA".equals(name) && "url".equals(prefix) ) {
                param = urlS_ATTIVA;
            } else if ( "s_ATTIVA".equals(name) && prefix == null ) {
                param = urlS_ATTIVA;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addGridDataObjectListener @8-B1E4C7C7
    public synchronized void addDataObjectListener( DataObjectListener l ) {
        listeners.addElement(l);
    }
//End addGridDataObjectListener

//removeGridDataObjectListener @8-9F30CEFB
    public synchronized void removeDataObjectListener( DataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeGridDataObjectListener

//fireBeforeBuildSelectEvent @8-238A81BB
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

//fireBeforeExecuteSelectEvent @8-9DA7B025
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

//fireAfterExecuteSelectEvent @8-F7E8A616
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

//class DataObject Tail @8-ED3F53A4
} // End of class DS
//End class DataObject Tail

