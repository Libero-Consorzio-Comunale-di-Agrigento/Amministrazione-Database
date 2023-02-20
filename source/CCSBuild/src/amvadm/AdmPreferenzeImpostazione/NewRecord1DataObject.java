//NewRecord1 DataSource @45-454E111D
package amvadm.AdmPreferenzeImpostazione;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End NewRecord1 DataSource

//class DataObject Header @45-1B0DE35C
public class NewRecord1DataObject extends DS {
//End class DataObject Header

//attributes of DataObject @45-6B83E679
    

    TextField postSTRINGA_ALIAS = new TextField(null, null);
    
    TextField postVALORE = new TextField(null, null);
    
    TextField postMODULO = new TextField(null, null);
    
    TextField exprKey64 = new TextField(null, null);
    
    TextField postP_STRINGA = new TextField(null, null);
    
    TextField exprKey62 = new TextField(null, null);
    
    TextField urlP_UTENTE = new TextField(null, null);
    
    TextField urlSTRINGA = new TextField(null, null);
    
    TextField sesModulo = new TextField(null, null);
    
    TextField urlMVAV = new TextField(null, null);
    

    private NewRecord1Row row = new NewRecord1Row();

//End attributes of DataObject

//properties of DataObject @45-4927D134

    public void  setPostSTRINGA_ALIAS( String param ) {
        this.postSTRINGA_ALIAS.setValue( param );
    }

    public void  setPostSTRINGA_ALIAS( Object param ) {
        this.postSTRINGA_ALIAS.setValue( param );
    }

    public void  setPostSTRINGA_ALIAS( Object param, Format ignore ) {
        this.postSTRINGA_ALIAS.setValue( param );
    }

    public void  setPostVALORE( String param ) {
        this.postVALORE.setValue( param );
    }

    public void  setPostVALORE( Object param ) {
        this.postVALORE.setValue( param );
    }

    public void  setPostVALORE( Object param, Format ignore ) {
        this.postVALORE.setValue( param );
    }

    public void  setPostMODULO( String param ) {
        this.postMODULO.setValue( param );
    }

    public void  setPostMODULO( Object param ) {
        this.postMODULO.setValue( param );
    }

    public void  setPostMODULO( Object param, Format ignore ) {
        this.postMODULO.setValue( param );
    }

    public void  setExprKey64( String param ) {
        this.exprKey64.setValue( param );
    }

    public void  setExprKey64( Object param ) {
        this.exprKey64.setValue( param );
    }

    public void  setExprKey64( Object param, Format ignore ) {
        this.exprKey64.setValue( param );
    }

    public void  setPostP_STRINGA( String param ) {
        this.postP_STRINGA.setValue( param );
    }

    public void  setPostP_STRINGA( Object param ) {
        this.postP_STRINGA.setValue( param );
    }

    public void  setPostP_STRINGA( Object param, Format ignore ) {
        this.postP_STRINGA.setValue( param );
    }

    public void  setExprKey62( String param ) {
        this.exprKey62.setValue( param );
    }

    public void  setExprKey62( Object param ) {
        this.exprKey62.setValue( param );
    }

    public void  setExprKey62( Object param, Format ignore ) {
        this.exprKey62.setValue( param );
    }

    public void  setUrlP_UTENTE( String param ) {
        this.urlP_UTENTE.setValue( param );
    }

    public void  setUrlP_UTENTE( Object param ) {
        this.urlP_UTENTE.setValue( param );
    }

    public void  setUrlP_UTENTE( Object param, Format ignore ) {
        this.urlP_UTENTE.setValue( param );
    }

    public void  setUrlSTRINGA( String param ) {
        this.urlSTRINGA.setValue( param );
    }

    public void  setUrlSTRINGA( Object param ) {
        this.urlSTRINGA.setValue( param );
    }

    public void  setUrlSTRINGA( Object param, Format ignore ) {
        this.urlSTRINGA.setValue( param );
    }

    public void  setSesModulo( String param ) {
        this.sesModulo.setValue( param );
    }

    public void  setSesModulo( Object param ) {
        this.sesModulo.setValue( param );
    }

    public void  setSesModulo( Object param, Format ignore ) {
        this.sesModulo.setValue( param );
    }

    public void  setUrlMVAV( String param ) {
        this.urlMVAV.setValue( param );
    }

    public void  setUrlMVAV( Object param ) {
        this.urlMVAV.setValue( param );
    }

    public void  setUrlMVAV( Object param, Format ignore ) {
        this.urlMVAV.setValue( param );
    }

    public NewRecord1Row getRow() {
        return row;
    }

    public void setRow( NewRecord1Row row ) {
        this.row = row;
    }

//End properties of DataObject

//constructor @45-FD1C8415
    public NewRecord1DataObject(Page page) {
        super(page);
    }
//End constructor

//load @45-A7563398
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT stringa stringa_alias "
                    + ",  "
                    + "AMVWEB.GET_PREFERENZA(stringa,0,decode('{MVAV}',1,'{Modulo}','')) valore "
                    + ", decode('{MVAV}',1,'{Modulo}','') modulo "
                    + ",  "
                    + "max(commento) commento "
                    + "from REGISTRO  "
                    + "where stringa = '{STRINGA}' "
                    + "group by stringa" );
        if ( StringUtils.isEmpty( (String) urlSTRINGA.getObjectValue() ) ) urlSTRINGA.setValue( "" );
        command.addParameter( "STRINGA", urlSTRINGA, null );
        if ( StringUtils.isEmpty( (String) sesModulo.getObjectValue() ) ) sesModulo.setValue( "" );
        command.addParameter( "Modulo", sesModulo, null );
        if ( StringUtils.isEmpty( (String) urlMVAV.getObjectValue() ) ) urlMVAV.setValue( "" );
        command.addParameter( "MVAV", urlMVAV, null );
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

//loadDataBind @45-8D02F056
        if ( record == null || record.isEmpty() ) {
            empty = true;
        } else {
            row.setCOMMENTO(Utils.convertToString(ds.parse(record.get("COMMENTO"), row.getCOMMENTOField())));
            row.setSTRINGA_ALIAS(Utils.convertToString(ds.parse(record.get("STRINGA_ALIAS"), row.getSTRINGA_ALIASField())));
            row.setMODULO(Utils.convertToString(ds.parse(record.get("MODULO"), row.getMODULOField())));
            row.setVALORE(Utils.convertToString(ds.parse(record.get("VALORE"), row.getVALOREField())));
        }
//End loadDataBind

//End of load @45-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//update @45-E6341198
        boolean update() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call AMVWEB.SET_PREFERENZA(?,?,?,?)}" );
            if ( StringUtils.isEmpty( (String) postSTRINGA_ALIAS.getObjectValue() ) ) postSTRINGA_ALIAS.setValue( "" );
            command.addParameter( "P_STRINGA", postSTRINGA_ALIAS, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postVALORE.getObjectValue() ) ) postVALORE.setValue( "" );
            command.addParameter( "P_VALORE", postVALORE, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postMODULO.getObjectValue() ) ) postMODULO.setValue( "" );
            command.addParameter( "P_MODULO", postMODULO, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) exprKey64.getObjectValue() ) ) exprKey64.setValue( "" );
            command.addParameter( "P_UTENTE", exprKey64, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );

            fireBeforeBuildUpdateEvent( new DataObjectEvent(command) );


//End update

//updateDataBound @45-0130DCE2
            fireBeforeExecuteUpdateEvent( new DataObjectEvent(command) );

            if ( ! ds.hasErrors() ) {
                command.executeUpdate();
            }

            CCLogger.getInstance().debug(command.toString());

            fireAfterExecuteUpdateEvent( new DataObjectEvent(command) );

//End updateDataBound

//End of update @45-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of update

//delete @45-2B876810
        boolean delete() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{call AMVWEB.SET_PREFERENZA(?,?,?,?)}" );
            if ( StringUtils.isEmpty( (String) postP_STRINGA.getObjectValue() ) ) postP_STRINGA.setValue( "" );
            command.addParameter( "P_STRINGA", postP_STRINGA, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) exprKey62.getObjectValue() ) ) exprKey62.setValue( "" );
            command.addParameter( "P_VALORE", exprKey62, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postMODULO.getObjectValue() ) ) postMODULO.setValue( "" );
            command.addParameter( "P_MODULO", postMODULO, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) urlP_UTENTE.getObjectValue() ) ) urlP_UTENTE.setValue( "" );
            command.addParameter( "P_UTENTE", urlP_UTENTE, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );

            fireBeforeBuildDeleteEvent( new DataObjectEvent(command) );


//End delete

//deleteDataBound @45-67425D5E
            fireBeforeExecuteDeleteEvent( new DataObjectEvent(command) );

            if ( ! ds.hasErrors() ) {
                command.executeUpdate();
            }

            CCLogger.getInstance().debug(command.toString());

            fireAfterExecuteDeleteEvent( new DataObjectEvent(command) );

//End deleteDataBound

//End of delete @45-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of delete

//getParameterByName @45-43556604
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "STRINGA_ALIAS".equals(name) && "post".equals(prefix) ) {
                param = postSTRINGA_ALIAS;
            } else if ( "STRINGA_ALIAS".equals(name) && prefix == null ) {
                param = postSTRINGA_ALIAS;
            }
            if ( "VALORE".equals(name) && "post".equals(prefix) ) {
                param = postVALORE;
            } else if ( "VALORE".equals(name) && prefix == null ) {
                param = postVALORE;
            }
            if ( "MODULO".equals(name) && "post".equals(prefix) ) {
                param = postMODULO;
            } else if ( "MODULO".equals(name) && prefix == null ) {
                param = postMODULO;
            }
            if ( "exprKey64".equals(name) && "expr".equals(prefix) ) {
                param = exprKey64;
            } else if ( "exprKey64".equals(name) && prefix == null ) {
                param = exprKey64;
            }
            if ( "P_STRINGA".equals(name) && "post".equals(prefix) ) {
                param = postP_STRINGA;
            } else if ( "P_STRINGA".equals(name) && prefix == null ) {
                param = postP_STRINGA;
            }
            if ( "exprKey62".equals(name) && "expr".equals(prefix) ) {
                param = exprKey62;
            } else if ( "exprKey62".equals(name) && prefix == null ) {
                param = exprKey62;
            }
            if ( "P_UTENTE".equals(name) && "url".equals(prefix) ) {
                param = urlP_UTENTE;
            } else if ( "P_UTENTE".equals(name) && prefix == null ) {
                param = urlP_UTENTE;
            }
            if ( "STRINGA".equals(name) && "url".equals(prefix) ) {
                param = urlSTRINGA;
            } else if ( "STRINGA".equals(name) && prefix == null ) {
                param = urlSTRINGA;
            }
            if ( "Modulo".equals(name) && "ses".equals(prefix) ) {
                param = sesModulo;
            } else if ( "Modulo".equals(name) && prefix == null ) {
                param = sesModulo;
            }
            if ( "MVAV".equals(name) && "url".equals(prefix) ) {
                param = urlMVAV;
            } else if ( "MVAV".equals(name) && prefix == null ) {
                param = urlMVAV;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addRecordDataObjectListener @45-47141785
    public synchronized void addRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.addElement(l);
    }
//End addRecordDataObjectListener

//removeRecordDataObjectListener @45-A1ABC1F4
    public synchronized void removeRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeRecordDataObjectListener

//fireBeforeBuildSelectEvent @45-305A023C
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

//fireBeforeExecuteSelectEvent @45-D00ACF95
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

//fireAfterExecuteSelectEvent @45-3BAD39CE
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

//fireBeforeBuildInsertEvent @45-FBA08B71
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

//fireBeforeExecuteInsertEvent @45-47AFA6A5
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

//fireAfterExecuteInsertEvent @45-E9CE95AE
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

//fireBeforeBuildSelectEvent @45-2405BE8B
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

//fireBeforeExecuteSelectEvent @45-E9DFF86B
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

//fireAfterExecuteSelectEvent @45-580A2987
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

//fireBeforeBuildSelectEvent @45-D021D0EA
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

//fireBeforeExecuteDeleteEvent @45-DD540FBB
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

//fireAfterExecuteDeleteEvent @45-2A6E2049
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

//class DataObject Tail @45-ED3F53A4
} // End of class DS
//End class DataObject Tail

