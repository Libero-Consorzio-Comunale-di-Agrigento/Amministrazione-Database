//BancheElenco DataSource @8-9579225C
package restrict.Ad4DizionariBancheElenco;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End BancheElenco DataSource

//class DataObject Header @8-A1C64A7B
public class BancheElencoDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @8-FC0E4828
    

    TextField urlS_BANCA = new TextField(null, null);
    

    private BancheElencoRow[] rows = new BancheElencoRow[1000];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @8-F8E75FD4

    public void  setUrlS_BANCA( String param ) {
        this.urlS_BANCA.setValue( param );
    }

    public void  setUrlS_BANCA( Object param ) {
        this.urlS_BANCA.setValue( param );
    }

    public void  setUrlS_BANCA( Object param, Format ignore ) {
        this.urlS_BANCA.setValue( param );
    }

    public BancheElencoRow[] getRows() {
        return rows;
    }

    public void setRows(BancheElencoRow[] rows) {
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

//constructor @8-F815A4D7
    public BancheElencoDataObject(Page page) {
        super(page);
    }
//End constructor

//load @8-69876CCB
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "select abi "
                    + "      ,denominazione "
                    + "      ,cin_abi "
                    + "      ,  "
                    + " '<a class=\"AFCDataLink\"' "
                    + "       ||' href=\"' "
                    + "       ||'Ad4DizionariBanche.do' "
                    + "       ||'?'||'MVPG=Ad4DizionariSportelliElenco' "
                    + "       ||'&'||'s_BANCA='||abi "
                    + "       ||'\"' "
                    + "       ||'>' "
                    + "       ||'<img border=0 src=\"../images/freccia_destra.gif\" title=\"vai a sportelli\">' "
                    + "       ||'</a>' "
                    + "       ||'&'||'nbsp;'                                                 link_sportelli "
                    + "  from ad4_banche where (   denominazione like upper('%'||'{s_BANCA}')||'%' "
                    + "        or ABI like upper('{s_BANCA}')||'%' "
                    + "       ) "
                    + " " );
        if ( StringUtils.isEmpty( (String) urlS_BANCA.getObjectValue() ) ) urlS_BANCA.setValue( "" );
        command.addParameter( "s_BANCA", urlS_BANCA, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( select abi ,denominazione ,cin_abi ,  "
                                                         + "            '<a class=\"AFCDataLink\"' ||' href=\"' ||'Ad4DizionariBanche.do' ||'?'||'MVPG=Ad4DizionariSportelliElenco' ||'&'||'s_BANCA='||abi ||'\"' ||'>' ||'<img border=0 src=\"../images/freccia_destra.gif\" title=\"vai a sportelli\">' ||'</a>' ||'&'||'nbsp;' link_sportelli from ad4_banche where ( denominazione like upper('%'||'{s_BANCA}')||'%' or ABI like upper('{s_BANCA}')||'%' )  ) cnt " );
        if ( ! StringUtils.isEmpty( orderBy ) ) {
            command.setOrder( orderBy );
        } else {
            command.setOrder( "abi" );
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

//loadDataBind @8-C0FBFA20
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                BancheElencoRow row = new BancheElencoRow();
                DbRow record = (DbRow) records.nextElement();
                row.setABI(Utils.convertToString(ds.parse(record.get("ABI"), row.getABIField())));
                row.setLINK_SPORTELLI(Utils.convertToString(ds.parse(record.get("LINK_SPORTELLI"), row.getLINK_SPORTELLIField())));
                row.setDENOMINAZIONE(Utils.convertToString(ds.parse(record.get("DENOMINAZIONE"), row.getDENOMINAZIONEField())));
                row.setCIN_ABI(Utils.convertToString(ds.parse(record.get("CIN_ABI"), row.getCIN_ABIField())));
                row.setBANCA(Utils.convertToString(ds.parse(record.get("ABI"), row.getBANCAField())));
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

//getParameterByName @8-1548DEC6
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "s_BANCA".equals(name) && "url".equals(prefix) ) {
                param = urlS_BANCA;
            } else if ( "s_BANCA".equals(name) && prefix == null ) {
                param = urlS_BANCA;
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

