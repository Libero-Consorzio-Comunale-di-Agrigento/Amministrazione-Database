//Comuni DataSource @5-55752591
package restrict.Ad4DizionariComuniLov;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End Comuni DataSource

//class DataObject Header @5-98858DE5
public class ComuniDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @5-F491D983
    

    TextField urlS_COMUNE = new TextField(null, null);
    
    TextField urlS_PROVINCIA_SIGLA = new TextField(null, null);
    

    private ComuniRow[] rows;

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @5-264F2411

    public void  setUrlS_COMUNE( String param ) {
        this.urlS_COMUNE.setValue( param );
    }

    public void  setUrlS_COMUNE( Object param ) {
        this.urlS_COMUNE.setValue( param );
    }

    public void  setUrlS_COMUNE( Object param, Format ignore ) {
        this.urlS_COMUNE.setValue( param );
    }

    public void  setUrlS_PROVINCIA_SIGLA( String param ) {
        this.urlS_PROVINCIA_SIGLA.setValue( param );
    }

    public void  setUrlS_PROVINCIA_SIGLA( Object param ) {
        this.urlS_PROVINCIA_SIGLA.setValue( param );
    }

    public void  setUrlS_PROVINCIA_SIGLA( Object param, Format ignore ) {
        this.urlS_PROVINCIA_SIGLA.setValue( param );
    }

    public ComuniRow[] getRows() {
        return rows;
    }

    public void setRows(ComuniRow[] rows) {
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

//constructor @5-73438C44
    public ComuniDataObject(Page page) {
        super(page);
    }
//End constructor

//load @5-BED5C721
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "select '<a class=\"AFCDataLink\" href=\"\" ' "
                    + "       ||'onclick=\"javascript:top.opener.returnMultiLOV(''' "
                    + "       ||provincia_stato "
                    + "       ||'#'||comune "
                    + "       ||'#' "
                    + "       ||replace(replace(denominazione,'\"','''') "
                    + "                ,'''','\\''' "
                    + "                ) "
                    + "       ||''')\">' "
                    + "       ||denominazione "
                    + "       ||'</a>' comune "
                    + "      ,ad4_ccs.get_provincia_denominazione(provincia_stato) provincia "
                    + "      ,ad4_ccs.format_data_trunc(data_soppressione) soppressione "
                    + "  from ad4_comuni "
                    + "      ,(select ad4_ccs.get_provincia_id_from_sigla(upper('{s_PROVINCIA_SIGLA}')) provincia "
                    + "          from dual "
                    + "       ) temp  "
                    + " where denominazione  like upper('%'||'{s_COMUNE}'||'%') "
                    + "   and (   provincia_stato = temp.provincia "
                    + "        or '{s_PROVINCIA_SIGLA}' is null "
                    + "       ) "
                    + "   and (   '{s_COMUNE}' is not null "
                    + "        or '{s_PROVINCIA_SIGLA}' is not null "
                    + "       ) "
                    + " " );
        if ( StringUtils.isEmpty( (String) urlS_COMUNE.getObjectValue() ) ) urlS_COMUNE.setValue( "" );
        command.addParameter( "s_COMUNE", urlS_COMUNE, null );
        if ( StringUtils.isEmpty( (String) urlS_PROVINCIA_SIGLA.getObjectValue() ) ) urlS_PROVINCIA_SIGLA.setValue( "" );
        command.addParameter( "s_PROVINCIA_SIGLA", urlS_PROVINCIA_SIGLA, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( select '<a class=\"AFCDataLink\" href=\"\" ' ||'onclick=\"javascript:top.opener.returnMultiLOV(''' ||provincia_stato ||'#'||comune ||'#' ||replace(replace(denominazione,'\"','''') ,'''','\''' ) ||''')\">' ||denominazione ||'</a>' comune ,ad4_ccs.get_provincia_denominazione(provincia_stato) provincia ,ad4_ccs.format_data_trunc(data_soppressione) soppressione from ad4_comuni ,(select ad4_ccs.get_provincia_id_from_sigla(upper('{s_PROVINCIA_SIGLA}')) provincia from dual ) temp where denominazione like upper('%'||'{s_COMUNE}'||'%') and ( provincia_stato = temp.provincia or '{s_PROVINCIA_SIGLA}' is null ) and ( '{s_COMUNE}' is not null or '{s_PROVINCIA_SIGLA}' is not null )  ) cnt " );
        if ( ! StringUtils.isEmpty( orderBy ) ) {
            command.setOrder( orderBy );
        } else {
            command.setOrder( "denominazione" );
        }
        command.setStartPos( ( pageNum - 1 ) * pageSize + 1 );
        command.setFetchSize( pageSize );
        command.setOptimizeSQL(false);

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

//loadDataBind @5-88B9F9F3
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            rows = new ComuniRow[(int) amountOfRows];
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                ComuniRow row = new ComuniRow();
                DbRow record = (DbRow) records.nextElement();
                row.setCOMUNE(Utils.convertToString(ds.parse(record.get("COMUNE"), row.getCOMUNEField())));
                row.setPROVINCIA(Utils.convertToString(ds.parse(record.get("PROVINCIA"), row.getPROVINCIAField())));
                row.setSOPPRESSIONE(Utils.convertToString(ds.parse(record.get("SOPPRESSIONE"), row.getSOPPRESSIONEField())));
                rows[counter++] = row;
            }
        }
//End loadDataBind

//End of load @5-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//getParameterByName @5-0C8EB585
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "s_COMUNE".equals(name) && "url".equals(prefix) ) {
                param = urlS_COMUNE;
            } else if ( "s_COMUNE".equals(name) && prefix == null ) {
                param = urlS_COMUNE;
            }
            if ( "s_PROVINCIA_SIGLA".equals(name) && "url".equals(prefix) ) {
                param = urlS_PROVINCIA_SIGLA;
            } else if ( "s_PROVINCIA_SIGLA".equals(name) && prefix == null ) {
                param = urlS_PROVINCIA_SIGLA;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addGridDataObjectListener @5-B1E4C7C7
    public synchronized void addDataObjectListener( DataObjectListener l ) {
        listeners.addElement(l);
    }
//End addGridDataObjectListener

//removeGridDataObjectListener @5-9F30CEFB
    public synchronized void removeDataObjectListener( DataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeGridDataObjectListener

//fireBeforeBuildSelectEvent @5-238A81BB
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

//fireBeforeExecuteSelectEvent @5-9DA7B025
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

//fireAfterExecuteSelectEvent @5-F7E8A616
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

//class DataObject Tail @5-ED3F53A4
} // End of class DS
//End class DataObject Tail

