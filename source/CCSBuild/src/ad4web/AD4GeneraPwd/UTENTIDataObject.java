//UTENTI DataSource @6-9E475F64
package ad4web.AD4GeneraPwd;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End UTENTI DataSource

//class DataObject Header @6-847567AB
public class UTENTIDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @6-CAF603CA
    

    TextField urlUTENTE = new TextField(null, null);
    
    DoubleField postMIN_PWD_LENGTH = new DoubleField(null, null);
    
    TextField sesAd4PwdGenerata = new TextField(null, null);
    

    private UTENTIRow row = new UTENTIRow();

//End attributes of DataObject

//properties of DataObject @6-553758DB

    public void  setUrlUTENTE( String param ) {
        this.urlUTENTE.setValue( param );
    }

    public void  setUrlUTENTE( Object param ) {
        this.urlUTENTE.setValue( param );
    }

    public void  setUrlUTENTE( Object param, Format ignore ) {
        this.urlUTENTE.setValue( param );
    }

    public void  setPostMIN_PWD_LENGTH( double param ) {
        this.postMIN_PWD_LENGTH.setValue( param );
    }

    public void  setPostMIN_PWD_LENGTH( double param, Format ignore ) throws java.text.ParseException {
        this.postMIN_PWD_LENGTH.setValue( param );
    }

    public void  setPostMIN_PWD_LENGTH( Object param, Format format ) throws java.text.ParseException {
        this.postMIN_PWD_LENGTH.setValue( param, format );
    }

    public void  setPostMIN_PWD_LENGTH( Double param ) {
        this.postMIN_PWD_LENGTH.setValue( param );
    }

    public void  setSesAd4PwdGenerata( String param ) {
        this.sesAd4PwdGenerata.setValue( param );
    }

    public void  setSesAd4PwdGenerata( Object param ) {
        this.sesAd4PwdGenerata.setValue( param );
    }

    public void  setSesAd4PwdGenerata( Object param, Format ignore ) {
        this.sesAd4PwdGenerata.setValue( param );
    }

    public UTENTIRow getRow() {
        return row;
    }

    public void setRow( UTENTIRow row ) {
        this.row = row;
    }

//End properties of DataObject

//constructor @6-8DC60F42
    public UTENTIDataObject(Page page) {
        super(page);
    }
//End constructor

//load @6-83779A81
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT Utente "
                    + "     , nominativo "
                    + "     ,  "
                    + "Utente.GET_ATTRIBUTI_PASSWORD(Utente) descr "
                    + "     ,  "
                    + "DECODE(NVL(Caratteristica_Accesso.GET_MINPWDLENGTH(Utente),0),0,8,Caratteristica_Accesso.GET_MINPWDLENGTH(Utente)) MIN_PWD_LENGTH "
                    + "     ,  "
                    + "DECODE('{PWD}','','','La nuova password dell''utente e'':<br><br>'|| "
                    + "       '<font color=\"#0000FF\" style=\"FONT-SIZE:12pt;FONT-FAMILY:Lucida Console\"><strong>'||'{PWD}'||'</strong></font><br><br>'|| "
                    + "       'Assicurarsi di conservarla: stamparla, salvarla oppure trascriverla.<br><br>'|| "
                    + "       'Assicurarsi, inoltre, di avere identificato correttamente le lettere ed i numeri presenti confrontandoli con l''alfabeto di seguito riportato.<br><br>'|| "
                    + "       '<font style=\"FONT-SIZE:12pt;FONT-FAMILY:Lucida Console\"><strong>ABCDEFGHIJKLMNOPQRSTUVWXYZ<br><br>abcdefghijklmnopqrstuvwxyz<br><br>0123456789</strong></font><br>') PWD "
                    + "  FROM UTENTI "
                    + " WHERE Utente = '{UTENTE}'" );
        if ( StringUtils.isEmpty( (String) urlUTENTE.getObjectValue() ) ) urlUTENTE.setValue( "" );
        command.addParameter( "UTENTE", urlUTENTE, null );
        if ( StringUtils.isEmpty( (String) sesAd4PwdGenerata.getObjectValue() ) ) sesAd4PwdGenerata.setValue( "" );
        command.addParameter( "PWD", sesAd4PwdGenerata, null );
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

//loadDataBind @6-F7D69CFC
        if ( record == null || record.isEmpty() ) {
            empty = true;
        } else {
            row.setNOMINATIVO(Utils.convertToString(ds.parse(record.get("NOMINATIVO"), row.getNOMINATIVOField())));
            row.setDESCR(Utils.convertToString(ds.parse(record.get("DESCR"), row.getDESCRField())));
            row.setUTENTE(Utils.convertToString(ds.parse(record.get("UTENTE"), row.getUTENTEField())));
            row.setMIN_PWD_LENGTH(Utils.convertToLong(ds.parse(record.get("MIN_PWD_LENGTH"), row.getMIN_PWD_LENGTHField())));
            row.setPWD(Utils.convertToString(ds.parse(record.get("PWD"), row.getPWDField())));
        }
//End loadDataBind

//End of load @6-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//update @6-13D73F0D
        boolean update() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{ ? = call UTENTE.GENERA_PASSWORD ( ?, ? ) }" );
            command.addParameter( "RETURN_VALUE", null, java.sql.Types.CHAR, 0, SPParameter.OUTPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) urlUTENTE.getObjectValue() ) ) urlUTENTE.setValue( "" );
            command.addParameter( "P_UTENTE", urlUTENTE, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "P_LENGHT", postMIN_PWD_LENGTH, java.sql.Types.DOUBLE, 0, SPParameter.INPUT_PARAMETER );

            fireBeforeBuildUpdateEvent( new DataObjectEvent(command) );


//End update

//updateDataBound @6-0130DCE2
            fireBeforeExecuteUpdateEvent( new DataObjectEvent(command) );

//            if ( ! ds.hasErrors() ) {
//                command.executeUpdate();
//            }
			Collection cParam=null;            
            if ( ! ds.hasErrors() ) {
                command.executeUpdate();
        		cParam = command.getParameters();
			  	Object params[] = cParam.toArray();
				if ( params[0] != null ) {
                     SessionStorage.getInstance(page.getRequest()).setAttribute("Ad4PwdGenerata", ((Parameter)params[0]).getObjectValue());
		        }
				else {
					SessionStorage.getInstance(page.getRequest()).setAttribute("Ad4PwdGenerata", "");
		        }
            }

            CCLogger.getInstance().debug(command.toString());

            fireAfterExecuteUpdateEvent( new DataObjectEvent(command) );

//End updateDataBound

//End of update @6-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of update

//getParameterByName @6-31114CA4
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "UTENTE".equals(name) && "url".equals(prefix) ) {
                param = urlUTENTE;
            } else if ( "UTENTE".equals(name) && prefix == null ) {
                param = urlUTENTE;
            }
            if ( "MIN_PWD_LENGTH".equals(name) && "post".equals(prefix) ) {
                param = postMIN_PWD_LENGTH;
            } else if ( "MIN_PWD_LENGTH".equals(name) && prefix == null ) {
                param = postMIN_PWD_LENGTH;
            }
            if ( "Ad4PwdGenerata".equals(name) && "ses".equals(prefix) ) {
                param = sesAd4PwdGenerata;
            } else if ( "Ad4PwdGenerata".equals(name) && prefix == null ) {
                param = sesAd4PwdGenerata;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addRecordDataObjectListener @6-47141785
    public synchronized void addRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.addElement(l);
    }
//End addRecordDataObjectListener

//removeRecordDataObjectListener @6-A1ABC1F4
    public synchronized void removeRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeRecordDataObjectListener

//fireBeforeBuildSelectEvent @6-305A023C
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

//fireBeforeExecuteSelectEvent @6-D00ACF95
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

//fireAfterExecuteSelectEvent @6-3BAD39CE
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

//fireBeforeBuildInsertEvent @6-FBA08B71
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

//fireBeforeExecuteInsertEvent @6-47AFA6A5
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

//fireAfterExecuteInsertEvent @6-E9CE95AE
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

//fireBeforeBuildSelectEvent @6-2405BE8B
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

//fireBeforeExecuteSelectEvent @6-E9DFF86B
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

//fireAfterExecuteSelectEvent @6-580A2987
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

//fireBeforeBuildSelectEvent @6-D021D0EA
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

//fireBeforeExecuteDeleteEvent @6-DD540FBB
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

//fireAfterExecuteDeleteEvent @6-2A6E2049
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

//class DataObject Tail @6-ED3F53A4
} // End of class DS
//End class DataObject Tail

