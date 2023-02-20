//ldap_gruppo DataSource @136-3988B4B4
package ad4web.AD4LdapGruppo;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End ldap_gruppo DataSource

//class DataObject Header @136-FF740E58
public class ldap_gruppoDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @136-F7B75A8C
    

    TextField urlSTRINGA = new TextField(null, null);
    
    TextField postCHIAVE = new TextField(null, null);
    
    TextField postOLD_STRINGA = new TextField(null, null);
    

    private ldap_gruppoRow row = new ldap_gruppoRow();

//End attributes of DataObject

//properties of DataObject @136-5495C293

    public void  setUrlSTRINGA( String param ) {
        this.urlSTRINGA.setValue( param );
    }

    public void  setUrlSTRINGA( Object param ) {
        this.urlSTRINGA.setValue( param );
    }

    public void  setUrlSTRINGA( Object param, Format ignore ) {
        this.urlSTRINGA.setValue( param );
    }

    public void  setPostCHIAVE( String param ) {
        this.postCHIAVE.setValue( param );
    }

    public void  setPostCHIAVE( Object param ) {
        this.postCHIAVE.setValue( param );
    }

    public void  setPostCHIAVE( Object param, Format ignore ) {
        this.postCHIAVE.setValue( param );
    }

    public void  setPostOLD_STRINGA( String param ) {
        this.postOLD_STRINGA.setValue( param );
    }

    public void  setPostOLD_STRINGA( Object param ) {
        this.postOLD_STRINGA.setValue( param );
    }

    public void  setPostOLD_STRINGA( Object param, Format ignore ) {
        this.postOLD_STRINGA.setValue( param );
    }

    public ldap_gruppoRow getRow() {
        return row;
    }

    public void setRow( ldap_gruppoRow row ) {
        this.row = row;
    }

//End properties of DataObject

//constructor @136-C90CC7DA
    public ldap_gruppoDataObject(Page page) {
        super(page);
    }
//End constructor

//load @136-38D17348
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT \"REGISTRO\".\"CHIAVE\",    "
                    + "         \"REGISTRO\".\"STRINGA\",  "
                    + "  "
                    + "         \"REGISTRO\".\"STRINGA\" old_stringa,  "
                    + "  "
                    + "         \"REGISTRO\".\"VALORE\" "
                    + "    FROM \"REGISTRO\"     "
                    + "   WHERE \"REGISTRO\".\"CHIAVE\" = 'PRODUCTS/LDAPCONFIG/GRUPPI' AND   "
                    + "         \"REGISTRO\".\"STRINGA\" <> '(Predefinito)' and "
                    + "         \"REGISTRO\".\"STRINGA\" = '{stringa}'" );
        if ( StringUtils.isEmpty( (String) urlSTRINGA.getObjectValue() ) ) urlSTRINGA.setValue( "" );
        command.addParameter( "stringa", urlSTRINGA, null );
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

//loadDataBind @136-4323E9FC
        if ( record == null || record.isEmpty() ) {
            empty = true;
        } else {
            row.setSTRINGA(Utils.convertToString(ds.parse(record.get("STRINGA"), row.getSTRINGAField())));
            row.setCHIAVE(Utils.convertToString(ds.parse(record.get("CHIAVE"), row.getCHIAVEField())));
            row.setOLD_STRINGA(Utils.convertToString(ds.parse(record.get("OLD_STRINGA"), row.getOLD_STRINGAField())));
            row.setVALORE(Utils.convertToString(ds.parse(record.get("VALORE"), row.getVALOREField())));
        }
//End loadDataBind

//End of load @136-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//insert @136-EF47B52B
        boolean insert() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            command.setNeedQuotes(true);
            command.setSql("INSERT INTO REGISTRO ( STRINGA, VALORE, CHIAVE ) VALUES ( {STRINGA}, {VALORE}, {CHIAVE} )");
            command.addParameter("STRINGA", row.getSTRINGAField());
            command.addParameter("VALORE", row.getVALOREField());
            if (row.getCHIAVE() == null || StringUtils.isEmpty(row.getCHIAVE())) {row.setCHIAVE("PRODUCTS/LDAPCONFIG/GRUPPI");}
            command.addParameter("CHIAVE", row.getCHIAVEField());

            fireBeforeBuildInsertEvent( new DataObjectEvent(command) );

//End insert

//insertDataBound @136-BC781F8A
            fireBeforeExecuteInsertEvent( new DataObjectEvent(command) );

            if ( ! ds.hasErrors() ) {
                command.executeUpdate();
            }

            CCLogger.getInstance().debug(command.toString());

            fireAfterExecuteInsertEvent( new DataObjectEvent(command) );

//End insertDataBound

//End of insert @136-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of insert

//update @136-41264BF4
        boolean update() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            command.setNeedQuotes(true);
            command.setSql("UPDATE REGISTRO SET STRINGA = {STRINGA}, CHIAVE = {CHIAVE}, VALORE = {VALORE}");
            command.addParameter("STRINGA", row.getSTRINGAField());
            command.addParameter("CHIAVE", row.getCHIAVEField());
            command.addParameter("VALORE", row.getVALOREField());

            fireBeforeBuildUpdateEvent( new DataObjectEvent(command) );

            String where1;
            if ( StringUtils.isEmpty( (String) postCHIAVE.getObjectValue() ) ) {
                where1 = WhereParams.rawOperation( "CHIAVE", FieldOperation.EQUAL.getNullRelation(), new TextField("null", "null", "null") , null, ds );
            } else {
                where1 = WhereParams.rawOperation( "CHIAVE", FieldOperation.EQUAL, postCHIAVE, null, ds );
            }
            String where2;
            if ( StringUtils.isEmpty( (String) postOLD_STRINGA.getObjectValue() ) ) {
                where2 = WhereParams.rawOperation( "STRINGA", FieldOperation.EQUAL.getNullRelation(), new TextField("null", "null", "null") , null, ds );
            } else {
                where2 = WhereParams.rawOperation( "STRINGA", FieldOperation.EQUAL, postOLD_STRINGA, null, ds );
            }
            String whereParams = WhereParams.and(false, where1, where2);

            if ( where1 == null || where2 == null ) {
                addError(getResourceBundle().getString("CustomOperationError_MissingParameters"));
            }
            if ( ! StringUtils.isEmpty(whereParams) ) {
                if ( ! StringUtils.isEmpty(command.getWhere()) ) {
                    command.setWhere( command.getWhere() + " AND (" + whereParams + ")" );
                } else {
                    command.setWhere( whereParams );
                }
            }

//End update

//updateDataBound @136-35E17193
            fireBeforeExecuteUpdateEvent( new DataObjectEvent(command) );
            if (!command.isCmdExecution()) {
                ds.closeConnection();
                return false;
            }

            if ( ! ds.hasErrors() ) {
                command.executeUpdate();
            }

            CCLogger.getInstance().debug(command.toString());

            fireAfterExecuteUpdateEvent( new DataObjectEvent(command) );

//End updateDataBound

//End of update @136-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of update

//delete @136-E4D60465
        boolean delete() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            command.setNeedQuotes(true);
            command.setSql("DELETE FROM REGISTRO");

            fireBeforeBuildDeleteEvent( new DataObjectEvent(command) );

            String where1;
            if ( StringUtils.isEmpty( (String) postCHIAVE.getObjectValue() ) ) {
                where1 = WhereParams.rawOperation( "CHIAVE", FieldOperation.EQUAL.getNullRelation(), new TextField("null", "null", "null") , null, ds );
            } else {
                where1 = WhereParams.rawOperation( "CHIAVE", FieldOperation.EQUAL, postCHIAVE, null, ds );
            }
            String where2;
            if ( StringUtils.isEmpty( (String) postOLD_STRINGA.getObjectValue() ) ) {
                where2 = WhereParams.rawOperation( "STRINGA", FieldOperation.EQUAL.getNullRelation(), new TextField("null", "null", "null") , null, ds );
            } else {
                where2 = WhereParams.rawOperation( "STRINGA", FieldOperation.EQUAL, postOLD_STRINGA, null, ds );
            }
            String whereParams = WhereParams.and(false, where1, where2);

            if ( where1 == null || where2 == null ) {
                addError(getResourceBundle().getString("CustomOperationError_MissingParameters"));
            }
            if ( ! StringUtils.isEmpty(whereParams) ) {
                if ( ! StringUtils.isEmpty(command.getWhere()) ) {
                    command.setWhere( command.getWhere() + " AND (" + whereParams + ")" );
                } else {
                    command.setWhere( whereParams );
                }
            }

//End delete

//deleteDataBound @136-5B959F17
            fireBeforeExecuteDeleteEvent( new DataObjectEvent(command) );
            if (!command.isCmdExecution()) {
                ds.closeConnection();
                return false;
            }

            if ( ! ds.hasErrors() ) {
                command.executeUpdate();
            }

            CCLogger.getInstance().debug(command.toString());

            fireAfterExecuteDeleteEvent( new DataObjectEvent(command) );

//End deleteDataBound

//End of delete @136-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of delete

//getParameterByName @136-2FDEE4CE
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "STRINGA".equals(name) && "url".equals(prefix) ) {
                param = urlSTRINGA;
            } else if ( "STRINGA".equals(name) && prefix == null ) {
                param = urlSTRINGA;
            }
            if ( "CHIAVE".equals(name) && "post".equals(prefix) ) {
                param = postCHIAVE;
            } else if ( "CHIAVE".equals(name) && prefix == null ) {
                param = postCHIAVE;
            }
            if ( "OLD_STRINGA".equals(name) && "post".equals(prefix) ) {
                param = postOLD_STRINGA;
            } else if ( "OLD_STRINGA".equals(name) && prefix == null ) {
                param = postOLD_STRINGA;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addRecordDataObjectListener @136-47141785
    public synchronized void addRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.addElement(l);
    }
//End addRecordDataObjectListener

//removeRecordDataObjectListener @136-A1ABC1F4
    public synchronized void removeRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeRecordDataObjectListener

//fireBeforeBuildSelectEvent @136-305A023C
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

//fireBeforeExecuteSelectEvent @136-D00ACF95
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

//fireAfterExecuteSelectEvent @136-3BAD39CE
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

//fireBeforeBuildInsertEvent @136-FBA08B71
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

//fireBeforeExecuteInsertEvent @136-47AFA6A5
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

//fireAfterExecuteInsertEvent @136-E9CE95AE
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

//fireBeforeBuildSelectEvent @136-2405BE8B
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

//fireBeforeExecuteSelectEvent @136-E9DFF86B
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

//fireAfterExecuteSelectEvent @136-580A2987
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

//fireBeforeBuildSelectEvent @136-D021D0EA
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

//fireBeforeExecuteDeleteEvent @136-DD540FBB
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

//fireAfterExecuteDeleteEvent @136-2A6E2049
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

//class DataObject Tail @136-ED3F53A4
} // End of class DS
//End class DataObject Tail

