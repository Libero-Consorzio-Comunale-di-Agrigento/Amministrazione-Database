//ComuniElenco DataSource @8-6BBCCF02
package ad4web.Ad4DizionariComuniElenco;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End ComuniElenco DataSource

//class DataObject Header @8-F404B0BA
public class ComuniElencoDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @8-C81C93F8
    

    TextField urlS_COMUNE = new TextField(null, null);
    
    TextField urlS_PROVINCIA = new TextField(null, null);
    

    private ComuniElencoRow[] rows = new ComuniElencoRow[1000];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @8-932C094F

    public void  setUrlS_COMUNE( String param ) {
        this.urlS_COMUNE.setValue( param );
    }

    public void  setUrlS_COMUNE( Object param ) {
        this.urlS_COMUNE.setValue( param );
    }

    public void  setUrlS_COMUNE( Object param, Format ignore ) {
        this.urlS_COMUNE.setValue( param );
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

    public ComuniElencoRow[] getRows() {
        return rows;
    }

    public void setRows(ComuniElencoRow[] rows) {
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

//constructor @8-326755EE
    public ComuniElencoDataObject(Page page) {
        super(page);
    }
//End constructor

//load @8-4A6551FD
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "select comune "
                    + "      ,provincia_stato "
                    + "      ,denominazione "
                    + "      ,ad4_ccs.get_provincia_denominazione(provincia_stato)                provincia_desc "
                    + "      ,capoluogo_provincia "
                    + "      ,cap "
                    + "      ,utente_aggiornamento "
                    + "      ,ad4_ccs.format_data_trunc(data_aggiornamento)                           data_aggiornamento "
                    + "      ,ad4_ccs.get_comune_denominazione(provincia_fusione,comune_fusione)  fusione_desc "
                    + "      ,ad4_ccs.format_data_trunc(data_soppressione)                            soppressioned_data "
                    + "  from ad4_comuni "
                    + " where (   denominazione like upper('%'||'{s_COMUNE}')||'%' "
                    + "        or comune like '{s_COMUNE}'||'%' "
                    + "       ) "
                    + "   and (   provincia_stato = '{s_PROVINCIA}' "
                    + "        or '{s_PROVINCIA}' is null "
                    + "       ) "
                    + " " );
        if ( StringUtils.isEmpty( (String) urlS_COMUNE.getObjectValue() ) ) urlS_COMUNE.setValue( "" );
        command.addParameter( "s_COMUNE", urlS_COMUNE, null );
        if ( StringUtils.isEmpty( (String) urlS_PROVINCIA.getObjectValue() ) ) urlS_PROVINCIA.setValue( "" );
        command.addParameter( "s_PROVINCIA", urlS_PROVINCIA, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( select comune ,provincia_stato ,denominazione ,ad4_ccs.get_provincia_denominazione(provincia_stato) provincia_desc ,capoluogo_provincia ,cap ,utente_aggiornamento ,ad4_ccs.format_data_trunc(data_aggiornamento) data_aggiornamento ,ad4_ccs.get_comune_denominazione(provincia_fusione,comune_fusione) fusione_desc ,ad4_ccs.format_data_trunc(data_soppressione) soppressioned_data from ad4_comuni where ( denominazione like upper('%'||'{s_COMUNE}')||'%' or comune like '{s_COMUNE}'||'%' ) and ( provincia_stato = '{s_PROVINCIA}' or '{s_PROVINCIA}' is null )  ) cnt " );
        if ( ! StringUtils.isEmpty( orderBy ) ) {
            command.setOrder( orderBy );
        } else {
            command.setOrder( "denominazione" );
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

//loadDataBind @8-B866BB2A
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                ComuniElencoRow row = new ComuniElencoRow();
                DbRow record = (DbRow) records.nextElement();
                row.setPROVINCIA_STATO(Utils.convertToString(ds.parse(record.get("PROVINCIA_STATO"), row.getPROVINCIA_STATOField())));
                row.setCOMUNE(Utils.convertToString(ds.parse(record.get("COMUNE"), row.getCOMUNEField())));
                row.setDENOMINAZIONE(Utils.convertToString(ds.parse(record.get("DENOMINAZIONE"), row.getDENOMINAZIONEField())));
                row.setCAP(Utils.convertToString(ds.parse(record.get("CAP"), row.getCAPField())));
                row.setPROVINCIA_DESC(Utils.convertToString(ds.parse(record.get("PROVINCIA_DESC"), row.getPROVINCIA_DESCField())));
                row.setSOPPRESSIONED_DATA(Utils.convertToString(ds.parse(record.get("SOPPRESSIONED_DATA"), row.getSOPPRESSIONED_DATAField())));
                row.setFUSIONE_DESC(Utils.convertToString(ds.parse(record.get("FUSIONE_DESC"), row.getFUSIONE_DESCField())));
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

//getParameterByName @8-53ADDA06
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "s_COMUNE".equals(name) && "url".equals(prefix) ) {
                param = urlS_COMUNE;
            } else if ( "s_COMUNE".equals(name) && prefix == null ) {
                param = urlS_COMUNE;
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

