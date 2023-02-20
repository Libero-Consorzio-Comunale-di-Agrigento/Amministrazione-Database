//ProvinceElenco DataSource @8-82DECAAC
package ad4web.Ad4DizionariProvinceElenco;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End ProvinceElenco DataSource

//class DataObject Header @8-F7530786
public class ProvinceElencoDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @8-60766380
    

    TextField urlS_REGIONE = new TextField(null, null);
    
    TextField urlS_PROVINCIA = new TextField(null, null);
    

    private ProvinceElencoRow[] rows = new ProvinceElencoRow[1000];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @8-080E4DE7

    public void  setUrlS_REGIONE( String param ) {
        this.urlS_REGIONE.setValue( param );
    }

    public void  setUrlS_REGIONE( Object param ) {
        this.urlS_REGIONE.setValue( param );
    }

    public void  setUrlS_REGIONE( Object param, Format ignore ) {
        this.urlS_REGIONE.setValue( param );
    }

    public void  setUrlS_PROVINCIA( String param ) {
        this.urlS_PROVINCIA.setValue( param );
    }

    public void  setUrlS_PROVINCIA( Object param ) {
        this.urlS_PROVINCIA.setValue( param );
    }

    public void  setUrlS_PROVINCIA( Object param, Format ignore ) {
        this.urlS_PROVINCIA.setValue( param );
    }

    public ProvinceElencoRow[] getRows() {
        return rows;
    }

    public void setRows(ProvinceElencoRow[] rows) {
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

//constructor @8-30CFDC3A
    public ProvinceElencoDataObject(Page page) {
        super(page);
    }
//End constructor

//load @8-06E93DA8
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "select provincia "
                    + "      ,ad4_ccs.get_regione_denominazione(regione)                          regione "
                    + "      ,denominazione "
                    + "      ,sigla "
                    + "      ,utente_aggiornamento "
                    + "      ,ad4_ccs.format_data_trunc(data_aggiornamento)                      data_aggiornamento "
                    + "      ,  "
                    + " '<a class=\"AFCDataLink\"' "
                    + "       ||' href=\"' "
                    + "       ||'AmvPage.do' "
                    + "       ||'?'||'MVPG=Ad4DizionariComuniElenco' "
                    + "       ||'&'||'s_PROVINCIA='||provincia "
                    + "       ||'\"' "
                    + "       ||'>' "
                    + "       ||'<img border=0 src=\"../images/freccia_destra.gif\" title=\"vai a comuni\">' "
                    + "       ||'</a>' "
                    + "       ||'&'||'nbsp;'                                                 link_comuni "
                    + "  from ad4_province "
                    + " where (   denominazione like upper('%'||'{s_PROVINCIA}')||'%' "
                    + "        or provincia like '{s_PROVINCIA}'||'%' "
                    + "       ) "
                    + "   and (   regione = '{s_REGIONE}' "
                    + "        or '{s_REGIONE}' is null "
                    + "       ) "
                    + " " );
        if ( StringUtils.isEmpty( (String) urlS_REGIONE.getObjectValue() ) ) urlS_REGIONE.setValue( "" );
        command.addParameter( "s_REGIONE", urlS_REGIONE, null );
        if ( StringUtils.isEmpty( (String) urlS_PROVINCIA.getObjectValue() ) ) urlS_PROVINCIA.setValue( "" );
        command.addParameter( "s_PROVINCIA", urlS_PROVINCIA, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( select provincia ,ad4_ccs.get_regione_denominazione(regione) regione ,denominazione ,sigla ,utente_aggiornamento ,ad4_ccs.format_data_trunc(data_aggiornamento) data_aggiornamento ,  "
                                                         + "            '<a class=\"AFCDataLink\"' ||' href=\"' ||'AmvPage.do' ||'?'||'MVPG=Ad4DizionariComuniElenco' ||'&'||'s_PROVINCIA='||provincia ||'\"' ||'>' ||'<img border=0 src=\"../images/freccia_destra.gif\" title=\"vai a comuni\">' ||'</a>' ||'&'||'nbsp;' link_comuni from ad4_province where ( denominazione like upper('%'||'{s_PROVINCIA}')||'%' or provincia like '{s_PROVINCIA}'||'%' ) and ( regione = '{s_REGIONE}' or '{s_REGIONE}' is null )  ) cnt " );
        if ( ! StringUtils.isEmpty( orderBy ) ) {
            command.setOrder( orderBy );
        } else {
            command.setOrder( "provincia" );
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

//loadDataBind @8-0DE8671C
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                ProvinceElencoRow row = new ProvinceElencoRow();
                DbRow record = (DbRow) records.nextElement();
                row.setPROVINCIA(Utils.convertToString(ds.parse(record.get("PROVINCIA"), row.getPROVINCIAField())));
                row.setLINK_COMUNI(Utils.convertToString(ds.parse(record.get("LINK_COMUNI"), row.getLINK_COMUNIField())));
                row.setDENOMINAZIONE(Utils.convertToString(ds.parse(record.get("DENOMINAZIONE"), row.getDENOMINAZIONEField())));
                row.setSIGLA(Utils.convertToString(ds.parse(record.get("SIGLA"), row.getSIGLAField())));
                row.setREGIONE(Utils.convertToString(ds.parse(record.get("REGIONE"), row.getREGIONEField())));
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

//getParameterByName @8-9BB8AA6B
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "s_REGIONE".equals(name) && "url".equals(prefix) ) {
                param = urlS_REGIONE;
            } else if ( "s_REGIONE".equals(name) && prefix == null ) {
                param = urlS_REGIONE;
            }
            if ( "s_PROVINCIA".equals(name) && "url".equals(prefix) ) {
                param = urlS_PROVINCIA;
            } else if ( "s_PROVINCIA".equals(name) && prefix == null ) {
                param = urlS_PROVINCIA;
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

