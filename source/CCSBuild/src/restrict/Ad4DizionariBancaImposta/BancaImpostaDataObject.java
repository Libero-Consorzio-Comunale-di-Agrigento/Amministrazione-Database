//BancaImposta DataSource @5-605AA0CF
package restrict.Ad4DizionariBancaImposta;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End BancaImposta DataSource

//class DataObject Header @5-6998D1E9
public class BancaImpostaDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @5-58E322B7
    

    TextField ctrlBANCA = new TextField(null, null);
    
    TextField ctrlDENOMINAZIONE = new TextField(null, null);
    
    TextField ctrlCIN_ABI = new TextField(null, null);
    
    TextField urlBANCA = new TextField(null, null);
    

    private BancaImpostaRow row = new BancaImpostaRow();

//End attributes of DataObject

//properties of DataObject @5-7B4C7926

    public void  setCtrlBANCA( String param ) {
        this.ctrlBANCA.setValue( param );
    }

    public void  setCtrlBANCA( Object param ) {
        this.ctrlBANCA.setValue( param );
    }

    public void  setCtrlBANCA( Object param, Format ignore ) {
        this.ctrlBANCA.setValue( param );
    }

    public void  setCtrlDENOMINAZIONE( String param ) {
        this.ctrlDENOMINAZIONE.setValue( param );
    }

    public void  setCtrlDENOMINAZIONE( Object param ) {
        this.ctrlDENOMINAZIONE.setValue( param );
    }

    public void  setCtrlDENOMINAZIONE( Object param, Format ignore ) {
        this.ctrlDENOMINAZIONE.setValue( param );
    }

    public void  setCtrlCIN_ABI( String param ) {
        this.ctrlCIN_ABI.setValue( param );
    }

    public void  setCtrlCIN_ABI( Object param ) {
        this.ctrlCIN_ABI.setValue( param );
    }

    public void  setCtrlCIN_ABI( Object param, Format ignore ) {
        this.ctrlCIN_ABI.setValue( param );
    }

    public void  setUrlBANCA( String param ) {
        this.urlBANCA.setValue( param );
    }

    public void  setUrlBANCA( Object param ) {
        this.urlBANCA.setValue( param );
    }

    public void  setUrlBANCA( Object param, Format ignore ) {
        this.urlBANCA.setValue( param );
    }

    public BancaImpostaRow getRow() {
        return row;
    }

    public void setRow( BancaImpostaRow row ) {
        this.row = row;
    }

//End properties of DataObject

//constructor @5-C13ECBBD
    public BancaImpostaDataObject(Page page) {
        super(page);
    }
//End constructor

//load @5-61B99535
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "select abi banca "
                    + "      ,denominazione "
                    + ",  "
                    + "ad4_ccs.get_link_traduzione_banca(abi) open_traduzione "
                    + "      ,'<!--' hide_begin "
                    + "      ,'-->' hide_end "
                    + "      ,cin_abi "
                    + "      ,  '</td></tr><tr><td class=\"AFCDataTD\" align=\"right\" colspan=\"2\">' label_upd "
                    + "  from ad4_BANCHE "
                    + " where abi = {BANCA}" );
        if ( StringUtils.isEmpty( (String) urlBANCA.getObjectValue() ) ) urlBANCA.setValue( null );
        command.addParameter( "BANCA", urlBANCA, null );
        if ( ! command.isSetAllParams() ) {
            empty = true;
            ds.closeConnection();
            return true;
        }
        if ( ! StringUtils.isEmpty( orderBy ) ) {
            command.setOrder( orderBy );
        }

        fireBeforeBuildSelectEvent( new DataObjectEvent(command) );


        fireBeforeExecuteSelectEvent( new DataObjectEvent(command) );

        DbRow record = null;
        if ( ! ds.hasErrors() ) {
            record = command.getOneRow();
        }

        CCLogger.getInstance().debug(command.toString());

        fireAfterExecuteSelectEvent( new DataObjectEvent(command) );

        ds.closeConnection();
//End load

//loadDataBind @5-5E629C4C
        if ( record == null || record.isEmpty() ) {
            empty = true;
        } else {
            row.setBANCA_LABEL(Utils.convertToString(ds.parse(record.get("BANCA"), row.getBANCA_LABELField())));
            row.setHIDE_BEGIN(Utils.convertToString(ds.parse(record.get("HIDE_BEGIN"), row.getHIDE_BEGINField())));
            row.setBANCA(Utils.convertToString(ds.parse(record.get("BANCA"), row.getBANCAField())));
            row.setHIDE_END(Utils.convertToString(ds.parse(record.get("HIDE_END"), row.getHIDE_ENDField())));
            row.setDENOMINAZIONE(Utils.convertToString(ds.parse(record.get("DENOMINAZIONE"), row.getDENOMINAZIONEField())));
            row.setTRADUZIONE(Utils.convertToString(ds.parse(record.get("OPEN_TRADUZIONE"), row.getTRADUZIONEField())));
            row.setCIN_ABI(Utils.convertToString(ds.parse(record.get("CIN_ABI"), row.getCIN_ABIField())));
            row.setLABEL_UPD(Utils.convertToString(ds.parse(record.get("LABEL_UPD"), row.getLABEL_UPDField())));
        }
//End loadDataBind

//End of load @5-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//insert @5-97E5A8A1
        boolean insert() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call AD4_CCS.BANCA_INS(?,?,?)}" );
            if ( StringUtils.isEmpty( (String) row.getBANCA()) ) row.setBANCA( "" );
            command.addParameter( "IN_ABI", row.getBANCAField(), java.sql.Types.VARCHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getDENOMINAZIONE()) ) row.setDENOMINAZIONE( "" );
            command.addParameter( "IN_DENOMINAZIONE", row.getDENOMINAZIONEField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getCIN_ABI()) ) row.setCIN_ABI( "" );
            command.addParameter( "IN_CIN_ABI", row.getCIN_ABIField(), java.sql.Types.VARCHAR, 0, SPParameter.INPUT_PARAMETER );

            fireBeforeBuildInsertEvent( new DataObjectEvent(command) );

//End insert

//insertDataBound @5-BC781F8A
            fireBeforeExecuteInsertEvent( new DataObjectEvent(command) );

            if ( ! ds.hasErrors() ) {
                command.executeUpdate();
            }

            CCLogger.getInstance().debug(command.toString());

            fireAfterExecuteInsertEvent( new DataObjectEvent(command) );

//End insertDataBound

//End of insert @5-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of insert

//update @5-F05D993A
        boolean update() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call AD4_CCS.BANCA_UPD(?,?,?)}" );
            if ( StringUtils.isEmpty( (String) urlBANCA.getObjectValue() ) ) urlBANCA.setValue( "" );
            command.addParameter( "IN_ABI", urlBANCA, java.sql.Types.VARCHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getDENOMINAZIONE()) ) row.setDENOMINAZIONE( "" );
            command.addParameter( "IN_DENOMINAZIONE", row.getDENOMINAZIONEField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getCIN_ABI()) ) row.setCIN_ABI( "" );
            command.addParameter( "IN_CIN_ABI", row.getCIN_ABIField(), java.sql.Types.VARCHAR, 0, SPParameter.INPUT_PARAMETER );

            fireBeforeBuildUpdateEvent( new DataObjectEvent(command) );


//End update

//updateDataBound @5-0130DCE2
            fireBeforeExecuteUpdateEvent( new DataObjectEvent(command) );

            if ( ! ds.hasErrors() ) {
                command.executeUpdate();
            }

            CCLogger.getInstance().debug(command.toString());

            fireAfterExecuteUpdateEvent( new DataObjectEvent(command) );

//End updateDataBound

//End of update @5-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of update

//delete @5-968F0AFE
        boolean delete() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call AD4_CCS.BANCA_DEL(?)}" );
            if ( StringUtils.isEmpty( (String) urlBANCA.getObjectValue() ) ) urlBANCA.setValue( "" );
            command.addParameter( "IN_ABI", urlBANCA, java.sql.Types.VARCHAR, 0, SPParameter.INPUT_PARAMETER );

            fireBeforeBuildDeleteEvent( new DataObjectEvent(command) );


//End delete

//deleteDataBound @5-67425D5E
            fireBeforeExecuteDeleteEvent( new DataObjectEvent(command) );

            if ( ! ds.hasErrors() ) {
                command.executeUpdate();
            }

            CCLogger.getInstance().debug(command.toString());

            fireAfterExecuteDeleteEvent( new DataObjectEvent(command) );

//End deleteDataBound

//End of delete @5-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of delete

//getParameterByName @5-8F3E3F3B
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "BANCA".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlBANCA;
            } else if ( "BANCA".equals(name) && prefix == null ) {
                param = ctrlBANCA;
            }
            if ( "DENOMINAZIONE".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlDENOMINAZIONE;
            } else if ( "DENOMINAZIONE".equals(name) && prefix == null ) {
                param = ctrlDENOMINAZIONE;
            }
            if ( "CIN_ABI".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlCIN_ABI;
            } else if ( "CIN_ABI".equals(name) && prefix == null ) {
                param = ctrlCIN_ABI;
            }
            if ( "BANCA".equals(name) && "url".equals(prefix) ) {
                param = urlBANCA;
            } else if ( "BANCA".equals(name) && prefix == null ) {
                param = urlBANCA;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addRecordDataObjectListener @5-47141785
    public synchronized void addRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.addElement(l);
    }
//End addRecordDataObjectListener

//removeRecordDataObjectListener @5-A1ABC1F4
    public synchronized void removeRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeRecordDataObjectListener

//fireBeforeBuildSelectEvent @5-305A023C
    public void fireBeforeBuildSelectEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).beforeBuildSelect(e);
        }
    }
//End fireBeforeBuildSelectEvent

//fireBeforeExecuteSelectEvent @5-D00ACF95
    public void fireBeforeExecuteSelectEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).beforeExecuteSelect(e);
        }
    }
//End fireBeforeExecuteSelectEvent

//fireAfterExecuteSelectEvent @5-3BAD39CE
    public void fireAfterExecuteSelectEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).afterExecuteSelect(e);
        }
    }
//End fireAfterExecuteSelectEvent

//fireBeforeBuildInsertEvent @5-FBA08B71
    public void fireBeforeBuildInsertEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).beforeBuildInsert(e);
        }
    }
//End fireBeforeBuildInsertEvent

//fireBeforeExecuteInsertEvent @5-47AFA6A5
    public void fireBeforeExecuteInsertEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).beforeExecuteInsert(e);
        }
    }
//End fireBeforeExecuteInsertEvent

//fireAfterExecuteInsertEvent @5-E9CE95AE
    public void fireAfterExecuteInsertEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).afterExecuteInsert(e);
        }
    }
//End fireAfterExecuteInsertEvent

//fireBeforeBuildSelectEvent @5-2405BE8B
    public void fireBeforeBuildUpdateEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).beforeBuildUpdate(e);
        }
    }
//End fireBeforeBuildSelectEvent

//fireBeforeExecuteSelectEvent @5-E9DFF86B
    public void fireBeforeExecuteUpdateEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).beforeExecuteUpdate(e);
        }
    }
//End fireBeforeExecuteSelectEvent

//fireAfterExecuteSelectEvent @5-580A2987
    public void fireAfterExecuteUpdateEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).afterExecuteUpdate(e);
        }
    }
//End fireAfterExecuteSelectEvent

//fireBeforeBuildSelectEvent @5-D021D0EA
    public void fireBeforeBuildDeleteEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).beforeBuildDelete(e);
        }
    }
//End fireBeforeBuildSelectEvent

//fireBeforeExecuteDeleteEvent @5-DD540FBB
    public void fireBeforeExecuteDeleteEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).beforeExecuteDelete(e);
        }
    }
//End fireBeforeExecuteDeleteEvent

//fireAfterExecuteDeleteEvent @5-2A6E2049
    public void fireAfterExecuteDeleteEvent( DataObjectEvent e ) {
        Vector v;
        e.setDataSource( this );
        e.setSource(model);
        synchronized(this) {v = (Vector)listeners.clone();}
        for (int i = 0; i < v.size(); i++) {
            ((RecordDataObjectListener)v.elementAt(i)).afterExecuteDelete(e);
        }
    }
//End fireAfterExecuteDeleteEvent

//class DataObject Tail @5-ED3F53A4
} // End of class DS
//End class DataObject Tail

