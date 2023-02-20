//ComuneImposta DataSource @5-CF2B8BC5
package restrict.Ad4DizionariComuneBreveTraduzione;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End ComuneImposta DataSource

//class DataObject Header @5-91960CA4
public class ComuneImpostaDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @5-E9CE83E2
    

    LongField urlPROVINCIA_STATO = new LongField(null, null);
    
    LongField urlCOMUNE = new LongField(null, null);
    
    TextField urlLINGUA = new TextField(null, null);
    
    TextField postDENOMINAZIONE_ALT = new TextField(null, null);
    

    private ComuneImpostaRow row = new ComuneImpostaRow();

//End attributes of DataObject

//properties of DataObject @5-37518033

    public void  setUrlPROVINCIA_STATO( long param ) {
        this.urlPROVINCIA_STATO.setValue( param );
    }

    public void  setUrlPROVINCIA_STATO( long param, Format ignore ) throws java.text.ParseException {
        this.urlPROVINCIA_STATO.setValue( param );
    }

    public void  setUrlPROVINCIA_STATO( Object param, Format format ) throws java.text.ParseException {
        this.urlPROVINCIA_STATO.setValue( param, format );
    }

    public void  setUrlPROVINCIA_STATO( Long param ) {
        this.urlPROVINCIA_STATO.setValue( param );
    }

    public void  setUrlCOMUNE( long param ) {
        this.urlCOMUNE.setValue( param );
    }

    public void  setUrlCOMUNE( long param, Format ignore ) throws java.text.ParseException {
        this.urlCOMUNE.setValue( param );
    }

    public void  setUrlCOMUNE( Object param, Format format ) throws java.text.ParseException {
        this.urlCOMUNE.setValue( param, format );
    }

    public void  setUrlCOMUNE( Long param ) {
        this.urlCOMUNE.setValue( param );
    }

    public void  setUrlLINGUA( String param ) {
        this.urlLINGUA.setValue( param );
    }

    public void  setUrlLINGUA( Object param ) {
        this.urlLINGUA.setValue( param );
    }

    public void  setUrlLINGUA( Object param, Format ignore ) {
        this.urlLINGUA.setValue( param );
    }

    public void  setPostDENOMINAZIONE_ALT( String param ) {
        this.postDENOMINAZIONE_ALT.setValue( param );
    }

    public void  setPostDENOMINAZIONE_ALT( Object param ) {
        this.postDENOMINAZIONE_ALT.setValue( param );
    }

    public void  setPostDENOMINAZIONE_ALT( Object param, Format ignore ) {
        this.postDENOMINAZIONE_ALT.setValue( param );
    }

    public ComuneImpostaRow getRow() {
        return row;
    }

    public void setRow( ComuneImpostaRow row ) {
        this.row = row;
    }

//End properties of DataObject

//constructor @5-AEA8D68A
    public ComuneImpostaDataObject(Page page) {
        super(page);
    }
//End constructor

//load @5-1DA9548E
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "select provincia_stato "
                    + "     , comune "
                    + "      ,denominazione_breve "
                    + "     ,  "
                    + "AD4_CCS.GET_TRADUZIONE('COMUNI','DENOMINAZIONE_BREVE',TO_CHAR(PROVINCIA_STATO)||'|'||TO_CHAR(COMUNE),'{LINGUA}') DENOMINAZIONE_ALT  "
                    + " from comuni     "
                    + "where provincia_stato = {PROVINCIA_STATO} "
                    + "   and comune = {COMUNE}" );
        if ( urlPROVINCIA_STATO.getObjectValue() == null ) urlPROVINCIA_STATO.setValue( -1 );
        command.addParameter( "PROVINCIA_STATO", urlPROVINCIA_STATO, null );
        if ( urlCOMUNE.getObjectValue() == null ) urlCOMUNE.setValue( -1 );
        command.addParameter( "COMUNE", urlCOMUNE, null );
        if ( StringUtils.isEmpty( (String) urlLINGUA.getObjectValue() ) ) urlLINGUA.setValue( "" );
        command.addParameter( "LINGUA", urlLINGUA, null );
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

//loadDataBind @5-815A8396
        if ( record == null || record.isEmpty() ) {
            empty = true;
        } else {
            row.setCOMUNE_LABEL(Utils.convertToString(ds.parse(record.get("COMUNE"), row.getCOMUNE_LABELField())));
            row.setDENOMINAZIONE(Utils.convertToString(ds.parse(record.get("DENOMINAZIONE_BREVE"), row.getDENOMINAZIONEField())));
            row.setPROVINCIA_LABEL(Utils.convertToString(ds.parse(record.get("PROVINCIA_STATO"), row.getPROVINCIA_LABELField())));
            row.setDENOMINAZIONE_ALT(Utils.convertToString(ds.parse(record.get("DENOMINAZIONE_ALT"), row.getDENOMINAZIONE_ALTField())));
        }
//End loadDataBind

//End of load @5-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//insert @5-C9B8E253
        boolean insert() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call AD4_CCS.COMUNE_BREVE_TRADUZIONE_INS(?,?,?,?)}" );
            command.addParameter( "IN_PROVINCIA_STATO", urlPROVINCIA_STATO, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "IN_COMUNE", urlCOMUNE, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) urlLINGUA.getObjectValue() ) ) urlLINGUA.setValue( "" );
            command.addParameter( "IN_LINGUA", urlLINGUA, java.sql.Types.VARCHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postDENOMINAZIONE_ALT.getObjectValue() ) ) postDENOMINAZIONE_ALT.setValue( "" );
            command.addParameter( "IN_DENOMINAZIONE_ALT", postDENOMINAZIONE_ALT, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );

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

//update @5-A6721DC8
        boolean update() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call AD4_CCS.COMUNE_BREVE_TRADUZIONE_INS(?,?,?,?)}" );
            command.addParameter( "IN_PROVINCIA_STATO", urlPROVINCIA_STATO, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "IN_COMUNE", urlCOMUNE, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) urlLINGUA.getObjectValue() ) ) urlLINGUA.setValue( "" );
            command.addParameter( "IN_LINGUA", urlLINGUA, java.sql.Types.VARCHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postDENOMINAZIONE_ALT.getObjectValue() ) ) postDENOMINAZIONE_ALT.setValue( "" );
            command.addParameter( "IN_DENOMINAZIONE_ALT", postDENOMINAZIONE_ALT, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );

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

//delete @5-AA13E6DC
        boolean delete() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call AD4_CCS.COMUNE_BREVE_TRADUZIONE_DEL(?,?,?)}" );
            command.addParameter( "IN_PROVINCIA_STATO", urlPROVINCIA_STATO, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "IN_COMUNE", urlCOMUNE, java.sql.Types.INTEGER, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) urlLINGUA.getObjectValue() ) ) urlLINGUA.setValue( "" );
            command.addParameter( "IN_LINGUA", urlLINGUA, java.sql.Types.VARCHAR, 0, SPParameter.INPUT_PARAMETER );

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

//getParameterByName @5-8E63080C
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "PROVINCIA_STATO".equals(name) && "url".equals(prefix) ) {
                param = urlPROVINCIA_STATO;
            } else if ( "PROVINCIA_STATO".equals(name) && prefix == null ) {
                param = urlPROVINCIA_STATO;
            }
            if ( "COMUNE".equals(name) && "url".equals(prefix) ) {
                param = urlCOMUNE;
            } else if ( "COMUNE".equals(name) && prefix == null ) {
                param = urlCOMUNE;
            }
            if ( "LINGUA".equals(name) && "url".equals(prefix) ) {
                param = urlLINGUA;
            } else if ( "LINGUA".equals(name) && prefix == null ) {
                param = urlLINGUA;
            }
            if ( "DENOMINAZIONE_ALT".equals(name) && "post".equals(prefix) ) {
                param = postDENOMINAZIONE_ALT;
            } else if ( "DENOMINAZIONE_ALT".equals(name) && prefix == null ) {
                param = postDENOMINAZIONE_ALT;
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

