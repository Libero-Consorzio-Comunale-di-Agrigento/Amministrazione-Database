//RegioniElenco DataSource @8-A66046EE
package restrict.Ad4DizionariRegioniElenco;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End RegioniElenco DataSource

//class DataObject Header @8-7D669AEE
public class RegioniElencoDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @8-5FF4C5EB
    

    TextField urlS_REGIONE = new TextField(null, null);
    

    private RegioniElencoRow[] rows = new RegioniElencoRow[1000];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @8-94D5630A

    public void  setUrlS_REGIONE( String param ) {
        this.urlS_REGIONE.setValue( param );
    }

    public void  setUrlS_REGIONE( Object param ) {
        this.urlS_REGIONE.setValue( param );
    }

    public void  setUrlS_REGIONE( Object param, Format ignore ) {
        this.urlS_REGIONE.setValue( param );
    }

    public RegioniElencoRow[] getRows() {
        return rows;
    }

    public void setRows(RegioniElencoRow[] rows) {
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

//constructor @8-E00C7884
    public RegioniElencoDataObject(Page page) {
        super(page);
    }
//End constructor

//load @8-3C6B24F3
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "select regione "
                    + "      ,denominazione "
                    + "      ,id_regione "
                    + "      ,utente_aggiornamento "
                    + "      ,ad4_ccs.format_data_trunc(data_aggiornamento)                      data_aggiornamento "
                    + "      ,  "
                    + " '<a class=\"AFCDataLink\"' "
                    + "       ||' href=\"' "
                    + "       ||'Ad4DizionariComuni.do' "
                    + "       ||'?'||'MVPG=Ad4DizionariProvinceElenco' "
                    + "       ||'&'||'s_REGIONE='||regione "
                    + "       ||'\"' "
                    + "       ||'>' "
                    + "       ||'<img border=0 src=\"../images/freccia_destra.gif\" title=\"vai a province\">' "
                    + "       ||'</a>' "
                    + "       ||'&'||'nbsp;'                                                 link_province "
                    + "  from ad4_regioni "
                    + " where (   denominazione like upper('%'||'{s_REGIONE}')||'%' "
                    + "        or regione like upper('{s_REGIONE}')||'%' "
                    + "       ) "
                    + " " );
        if ( StringUtils.isEmpty( (String) urlS_REGIONE.getObjectValue() ) ) urlS_REGIONE.setValue( "" );
        command.addParameter( "s_REGIONE", urlS_REGIONE, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( select regione ,denominazione ,id_regione ,utente_aggiornamento ,ad4_ccs.format_data_trunc(data_aggiornamento) data_aggiornamento ,  "
                                                         + "            '<a class=\"AFCDataLink\"' ||' href=\"' ||'Ad4DizionariComuni.do' ||'?'||'MVPG=Ad4DizionariProvinceElenco' ||'&'||'s_REGIONE='||regione ||'\"' ||'>' ||'<img border=0 src=\"../images/freccia_destra.gif\" title=\"vai a province\">' ||'</a>' ||'&'||'nbsp;' link_province from ad4_regioni where ( denominazione like upper('%'||'{s_REGIONE}')||'%' or regione like upper('{s_REGIONE}')||'%' )  ) cnt " );
        if ( ! StringUtils.isEmpty( orderBy ) ) {
            command.setOrder( orderBy );
        } else {
            command.setOrder( "regione" );
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

//loadDataBind @8-CEF6C75F
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                RegioniElencoRow row = new RegioniElencoRow();
                DbRow record = (DbRow) records.nextElement();
                row.setREGIONE(Utils.convertToString(ds.parse(record.get("REGIONE"), row.getREGIONEField())));
                row.setLINK_PROVINCE(Utils.convertToString(ds.parse(record.get("LINK_PROVINCE"), row.getLINK_PROVINCEField())));
                row.setDENOMINAZIONE(Utils.convertToString(ds.parse(record.get("DENOMINAZIONE"), row.getDENOMINAZIONEField())));
                row.setID_REGIONE(Utils.convertToString(ds.parse(record.get("ID_REGIONE"), row.getID_REGIONEField())));
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

//getParameterByName @8-CC837AB3
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "s_REGIONE".equals(name) && "url".equals(prefix) ) {
                param = urlS_REGIONE;
            } else if ( "s_REGIONE".equals(name) && prefix == null ) {
                param = urlS_REGIONE;
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

