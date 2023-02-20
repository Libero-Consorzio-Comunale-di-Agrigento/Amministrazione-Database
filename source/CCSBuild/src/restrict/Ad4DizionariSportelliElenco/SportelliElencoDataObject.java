//SportelliElenco DataSource @8-5C1560F3
package restrict.Ad4DizionariSportelliElenco;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End SportelliElenco DataSource

//class DataObject Header @8-E116EC01
public class SportelliElencoDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @8-F5B54134
    

    TextField urlS_BANCA = new TextField(null, null);
    
    TextField urlS_SPORTELLO = new TextField(null, null);
    

    private SportelliElencoRow[] rows = new SportelliElencoRow[1000];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @8-3B8F4E3E

    public void  setUrlS_BANCA( String param ) {
        this.urlS_BANCA.setValue( param );
    }

    public void  setUrlS_BANCA( Object param ) {
        this.urlS_BANCA.setValue( param );
    }

    public void  setUrlS_BANCA( Object param, Format ignore ) {
        this.urlS_BANCA.setValue( param );
    }

    public void  setUrlS_SPORTELLO( String param ) {
        this.urlS_SPORTELLO.setValue( param );
    }

    public void  setUrlS_SPORTELLO( Object param ) {
        this.urlS_SPORTELLO.setValue( param );
    }

    public void  setUrlS_SPORTELLO( Object param, Format ignore ) {
        this.urlS_SPORTELLO.setValue( param );
    }

    public SportelliElencoRow[] getRows() {
        return rows;
    }

    public void setRows(SportelliElencoRow[] rows) {
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

//constructor @8-1281B9B9
    public SportelliElencoDataObject(Page page) {
        super(page);
    }
//End constructor

//load @8-BB792B43
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "select cab "
                    + "      ,  "
                    + "abi "
                    + "      ,ad4_ccs.get_BANCA_denominazione(abi) banca "
                    + "      ,descrizione "
                    + "      ,INDIRIZZO "
                    + "      ,LOCALITA "
                    + "      ,COMUNE "
                    + "      ,PROVINCIA "
                    + "      ,CAP "
                    + "      ,DIPENDENZA "
                    + "      ,BIC "
                    + "      , CIN_CAB "
                    + "  from ad4_sportelli "
                    + " where (   descrizione like upper('%'||'{s_SPORTELLO}')||'%' "
                    + "        or cab like '{s_SPORTELLO}'||'%' "
                    + "       ) "
                    + "   and (   ABI = '{s_BANCA}' "
                    + "        or '{s_BANCA}' is null "
                    + "       ) "
                    + " " );
        if ( StringUtils.isEmpty( (String) urlS_BANCA.getObjectValue() ) ) urlS_BANCA.setValue( "" );
        command.addParameter( "s_BANCA", urlS_BANCA, null );
        if ( StringUtils.isEmpty( (String) urlS_SPORTELLO.getObjectValue() ) ) urlS_SPORTELLO.setValue( "" );
        command.addParameter( "s_SPORTELLO", urlS_SPORTELLO, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( select cab ,  "
                                                         + "            abi ,ad4_ccs.get_BANCA_denominazione(abi) banca ,descrizione ,INDIRIZZO ,LOCALITA ,COMUNE ,PROVINCIA ,CAP ,DIPENDENZA ,BIC ,  "
                                                         + "            CIN_CAB from ad4_sportelli where ( descrizione like upper('%'||'{s_SPORTELLO}')||'%' or cab like '{s_SPORTELLO}'||'%' ) and ( ABI = '{s_BANCA}' or '{s_BANCA}' is null )  ) cnt " );
        if ( ! StringUtils.isEmpty( orderBy ) ) {
            command.setOrder( orderBy );
        } else {
            command.setOrder( "CAB" );
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

//loadDataBind @8-73A29A54
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                SportelliElencoRow row = new SportelliElencoRow();
                DbRow record = (DbRow) records.nextElement();
                row.setCAB(Utils.convertToString(ds.parse(record.get("CAB"), row.getCABField())));
                row.setABI(Utils.convertToString(ds.parse(record.get("ABI"), row.getABIField())));
                row.setCIN_CAB(Utils.convertToString(ds.parse(record.get("CIN_CAB"), row.getCIN_CABField())));
                row.setDESCRIZIONE(Utils.convertToString(ds.parse(record.get("DESCRIZIONE"), row.getDESCRIZIONEField())));
                row.setINDIRIZZO(Utils.convertToString(ds.parse(record.get("INDIRIZZO"), row.getINDIRIZZOField())));
                row.setLOCALITA(Utils.convertToString(ds.parse(record.get("LOCALITA"), row.getLOCALITAField())));
                row.setCOMUNE(Utils.convertToString(ds.parse(record.get("COMUNE"), row.getCOMUNEField())));
                row.setPROVINCIA(Utils.convertToString(ds.parse(record.get("PROVINCIA"), row.getPROVINCIAField())));
                row.setCAP(Utils.convertToString(ds.parse(record.get("CAP"), row.getCAPField())));
                row.setDIPENDENZA(Utils.convertToString(ds.parse(record.get("DIPENDENZA"), row.getDIPENDENZAField())));
                row.setBIC(Utils.convertToString(ds.parse(record.get("BIC"), row.getBICField())));
                row.setBANCA(Utils.convertToString(ds.parse(record.get("BANCA"), row.getBANCAField())));
                row.setSPORTELLO(Utils.convertToString(ds.parse(record.get("CAB"), row.getSPORTELLOField())));
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

//getParameterByName @8-A6225CAE
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "s_BANCA".equals(name) && "url".equals(prefix) ) {
                param = urlS_BANCA;
            } else if ( "s_BANCA".equals(name) && prefix == null ) {
                param = urlS_BANCA;
            }
            if ( "s_SPORTELLO".equals(name) && "url".equals(prefix) ) {
                param = urlS_SPORTELLO;
            } else if ( "s_SPORTELLO".equals(name) && prefix == null ) {
                param = urlS_SPORTELLO;
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

