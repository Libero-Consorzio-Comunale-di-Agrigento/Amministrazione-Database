//Titolo DataSource @43-43C7C9DD
package ad4web.AD4RegistroTree;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End Titolo DataSource

//class DataObject Header @43-99BC3D7C
public class TitoloDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @43-D8B781AC
    

    TextField sesAD4BP = new TextField(null, null);
    
    TextField sesISMENU = new TextField(null, null);
    
    TextField sesUSRORCL = new TextField(null, null);
    
    TextField urlID = new TextField(null, null);
    

    private TitoloRow[] rows = new TitoloRow[20];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @43-E65F4FE1

    public void  setSesAD4BP( String param ) {
        this.sesAD4BP.setValue( param );
    }

    public void  setSesAD4BP( Object param ) {
        this.sesAD4BP.setValue( param );
    }

    public void  setSesAD4BP( Object param, Format ignore ) {
        this.sesAD4BP.setValue( param );
    }

    public void  setSesISMENU( String param ) {
        this.sesISMENU.setValue( param );
    }

    public void  setSesISMENU( Object param ) {
        this.sesISMENU.setValue( param );
    }

    public void  setSesISMENU( Object param, Format ignore ) {
        this.sesISMENU.setValue( param );
    }

    public void  setSesUSRORCL( String param ) {
        this.sesUSRORCL.setValue( param );
    }

    public void  setSesUSRORCL( Object param ) {
        this.sesUSRORCL.setValue( param );
    }

    public void  setSesUSRORCL( Object param, Format ignore ) {
        this.sesUSRORCL.setValue( param );
    }

    public void  setUrlID( String param ) {
        this.urlID.setValue( param );
    }

    public void  setUrlID( Object param ) {
        this.urlID.setValue( param );
    }

    public void  setUrlID( Object param, Format ignore ) {
        this.urlID.setValue( param );
    }

    public TitoloRow[] getRows() {
        return rows;
    }

    public void setRows(TitoloRow[] rows) {
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

//constructor @43-D7C9BBE7
    public TitoloDataObject(Page page) {
        super(page);
    }
//End constructor

//load @43-27F220F0
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT '{AD4BP}' AD4BP, "
                    + "       decode('{MENU}','Y','','<img title=\"Indietro\" height=\"18\" src=\"../common/images/AD4/indietro.gif\" width=\"18\" border=\"0\">Indietro') INDIETRO, "
                    + "       '{USRORCL}' USRORCL, "
                    + "       decode(registro_pac.is_chiave_modificabile('{ID}','{USRORCL}'),0,'','<img title=\"Modifica Chiave\" height=\"18\" src=\"../common/images/AMV/edit.gif\" width=\"18\" border=\"0\">'||'Modifica') Modifica, "
                    + "       '<img title=\"Nuova Chiave\" height=\"18\" src=\"../common/images/AMV/nuovo.gif\" width=\"18\" border=\"0\">'||'Nuova Chiave' Nuova_Chiave, "
                    + "       decode(registro_pac.is_chiave_modificabile('{ID}','{USRORCL}'),0,'','<img title=\"Nuova Stringa\" height=\"18\" src=\"../common/images/AMV/nuovo.gif\" width=\"18\" border=\"0\">'||'Nuova Stringa') Nuova_Stringa "
                    + "  from dual "
                    + " where '{ID}' <> '0'  "
                    + "union "
                    + "SELECT '{AD4BP}' AD4BP, "
                    + "       decode('{MENU}','Y','','<img title=\"Indietro\" height=\"18\" src=\"../common/images/AD4/indietro.gif\" width=\"18\" border=\"0\">Indietro') INDIETRO, "
                    + "       '{USRORCL}' USRORCL, "
                    + "       '' Modifica, "
                    + "       '' Nuova_Chiave, "
                    + "       '' Nuova_Stringa "
                    + "  from dual "
                    + " where NVL('{ID}','0') = '0'" );
        if ( StringUtils.isEmpty( (String) sesAD4BP.getObjectValue() ) ) sesAD4BP.setValue( "" );
        command.addParameter( "AD4BP", sesAD4BP, null );
        if ( StringUtils.isEmpty( (String) sesISMENU.getObjectValue() ) ) sesISMENU.setValue( "" );
        command.addParameter( "MENU", sesISMENU, null );
        if ( StringUtils.isEmpty( (String) sesUSRORCL.getObjectValue() ) ) sesUSRORCL.setValue( "AD4" );
        command.addParameter( "USRORCL", sesUSRORCL, null );
        if ( StringUtils.isEmpty( (String) urlID.getObjectValue() ) ) urlID.setValue( "" );
        command.addParameter( "ID", urlID, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( SELECT '{AD4BP}' AD4BP,  "
                                                         + "            decode('{MENU}','Y','','<img title=\"Indietro\" height=\"18\" src=\"../common/images/AD4/indietro.gif\" width=\"18\" border=\"0\">Indietro') INDIETRO, '{USRORCL}' USRORCL, decode(registro_pac.is_chiave_modificabile('{ID}','{USRORCL}'),0,'','<img title=\"Modifica Chiave\" height=\"18\" src=\"../common/images/AMV/edit.gif\" width=\"18\" border=\"0\">'||'Modifica') Modifica, '<img title=\"Nuova Chiave\" height=\"18\" src=\"../common/images/AMV/nuovo.gif\" width=\"18\" border=\"0\">'||'Nuova Chiave' Nuova_Chiave, decode(registro_pac.is_chiave_modificabile('{ID}','{USRORCL}'),0,'','<img title=\"Nuova Stringa\" height=\"18\" src=\"../common/images/AMV/nuovo.gif\" width=\"18\" border=\"0\">'||'Nuova Stringa') Nuova_Stringa from dual where '{ID}' <> '0' union SELECT '{AD4BP}' AD4BP, decode('{MENU}','Y','','<img title=\"Indietro\" height=\"18\" src=\"../common/images/AD4/indietro.gif\" width=\"18\" border=\"0\">Indietro') INDIETRO, '{USRORCL}' USRORCL, '' Modifica, '' Nuova_Chiave, '' Nuova_Stringa from dual where NVL('{ID}','0') = '0' ) cnt " );
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

//loadDataBind @43-CBD0967A
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                TitoloRow row = new TitoloRow();
                DbRow record = (DbRow) records.nextElement();
                row.setUSRORCL(Utils.convertToString(ds.parse(record.get("USRORCL"), row.getUSRORCLField())));
                row.setModChiave(Utils.convertToString(ds.parse(record.get("MODIFICA"), row.getModChiaveField())));
                row.setNuovaChiave(Utils.convertToString(ds.parse(record.get("NUOVA_CHIAVE"), row.getNuovaChiaveField())));
                row.setNuovaStringa(Utils.convertToString(ds.parse(record.get("NUOVA_STRINGA"), row.getNuovaStringaField())));
                row.setINDIETRO(Utils.convertToString(ds.parse(record.get("INDIETRO"), row.getINDIETROField())));
                row.setAD4BP(Utils.convertToString(ds.parse(record.get("AD4BP"), row.getAD4BPField())));
                rows[counter++] = row;
            }
        }
//End loadDataBind

//End of load @43-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//getParameterByName @43-22328C36
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "AD4BP".equals(name) && "ses".equals(prefix) ) {
                param = sesAD4BP;
            } else if ( "AD4BP".equals(name) && prefix == null ) {
                param = sesAD4BP;
            }
            if ( "ISMENU".equals(name) && "ses".equals(prefix) ) {
                param = sesISMENU;
            } else if ( "ISMENU".equals(name) && prefix == null ) {
                param = sesISMENU;
            }
            if ( "USRORCL".equals(name) && "ses".equals(prefix) ) {
                param = sesUSRORCL;
            } else if ( "USRORCL".equals(name) && prefix == null ) {
                param = sesUSRORCL;
            }
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

//addGridDataObjectListener @43-B1E4C7C7
    public synchronized void addDataObjectListener( DataObjectListener l ) {
        listeners.addElement(l);
    }
//End addGridDataObjectListener

//removeGridDataObjectListener @43-9F30CEFB
    public synchronized void removeDataObjectListener( DataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeGridDataObjectListener

//fireBeforeBuildSelectEvent @43-238A81BB
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

//fireBeforeExecuteSelectEvent @43-9DA7B025
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

//fireAfterExecuteSelectEvent @43-F7E8A616
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

//class DataObject Tail @43-ED3F53A4
} // End of class DS
//End class DataObject Tail

