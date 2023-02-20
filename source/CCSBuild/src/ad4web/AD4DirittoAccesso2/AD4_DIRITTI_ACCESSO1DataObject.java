//AD4_DIRITTI_ACCESSO1 DataSource @38-67F7D9F4
package ad4web.AD4DirittoAccesso2;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End AD4_DIRITTI_ACCESSO1 DataSource

//class DataObject Header @38-FE5E26E8
public class AD4_DIRITTI_ACCESSO1DataObject extends DS {
//End class DataObject Header

//attributes of DataObject @38-AFF072EE
    

    DoubleField postSEQUENZA = new DoubleField(null, null);
    
    TextField ctrlUTENTE = new TextField(null, null);
    
    TextField postMODULO = new TextField(null, null);
    
    TextField ctrlMODULO_ORIG = new TextField(null, null);
    
    TextField postISTANZA = new TextField(null, null);
    
    TextField ctrlISTANZA_ORIG = new TextField(null, null);
    
    TextField postRUOLO = new TextField(null, null);
    
    TextField postNOTE = new TextField(null, null);
    
    TextField sesAD4UTENTE = new TextField(null, null);
    
    TextField urlMODULO = new TextField(null, null);
    
    TextField urlISTANZA = new TextField(null, null);
    
    TextField urlISLISTBOX = new TextField(null, null);
    
    TextField urlMVAV = new TextField(null, null);
    
    TextField sesAD4GRUPPO = new TextField(null, null);
    
    TextField sesAD4REFRESHSLAVES = new TextField(null, null);
    

    private AD4_DIRITTI_ACCESSO1Row row = new AD4_DIRITTI_ACCESSO1Row();

//End attributes of DataObject

//properties of DataObject @38-D17BC43A

    public void  setPostSEQUENZA( double param ) {
        this.postSEQUENZA.setValue( param );
    }

    public void  setPostSEQUENZA( double param, Format ignore ) throws java.text.ParseException {
        this.postSEQUENZA.setValue( param );
    }

    public void  setPostSEQUENZA( Object param, Format format ) throws java.text.ParseException {
        this.postSEQUENZA.setValue( param, format );
    }

    public void  setPostSEQUENZA( Double param ) {
        this.postSEQUENZA.setValue( param );
    }

    public void  setCtrlUTENTE( String param ) {
        this.ctrlUTENTE.setValue( param );
    }

    public void  setCtrlUTENTE( Object param ) {
        this.ctrlUTENTE.setValue( param );
    }

    public void  setCtrlUTENTE( Object param, Format ignore ) {
        this.ctrlUTENTE.setValue( param );
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

    public void  setCtrlMODULO_ORIG( String param ) {
        this.ctrlMODULO_ORIG.setValue( param );
    }

    public void  setCtrlMODULO_ORIG( Object param ) {
        this.ctrlMODULO_ORIG.setValue( param );
    }

    public void  setCtrlMODULO_ORIG( Object param, Format ignore ) {
        this.ctrlMODULO_ORIG.setValue( param );
    }

    public void  setPostISTANZA( String param ) {
        this.postISTANZA.setValue( param );
    }

    public void  setPostISTANZA( Object param ) {
        this.postISTANZA.setValue( param );
    }

    public void  setPostISTANZA( Object param, Format ignore ) {
        this.postISTANZA.setValue( param );
    }

    public void  setCtrlISTANZA_ORIG( String param ) {
        this.ctrlISTANZA_ORIG.setValue( param );
    }

    public void  setCtrlISTANZA_ORIG( Object param ) {
        this.ctrlISTANZA_ORIG.setValue( param );
    }

    public void  setCtrlISTANZA_ORIG( Object param, Format ignore ) {
        this.ctrlISTANZA_ORIG.setValue( param );
    }

    public void  setPostRUOLO( String param ) {
        this.postRUOLO.setValue( param );
    }

    public void  setPostRUOLO( Object param ) {
        this.postRUOLO.setValue( param );
    }

    public void  setPostRUOLO( Object param, Format ignore ) {
        this.postRUOLO.setValue( param );
    }

    public void  setPostNOTE( String param ) {
        this.postNOTE.setValue( param );
    }

    public void  setPostNOTE( Object param ) {
        this.postNOTE.setValue( param );
    }

    public void  setPostNOTE( Object param, Format ignore ) {
        this.postNOTE.setValue( param );
    }

    public void  setSesAD4UTENTE( String param ) {
        this.sesAD4UTENTE.setValue( param );
    }

    public void  setSesAD4UTENTE( Object param ) {
        this.sesAD4UTENTE.setValue( param );
    }

    public void  setSesAD4UTENTE( Object param, Format ignore ) {
        this.sesAD4UTENTE.setValue( param );
    }

    public void  setUrlMODULO( String param ) {
        this.urlMODULO.setValue( param );
    }

    public void  setUrlMODULO( Object param ) {
        this.urlMODULO.setValue( param );
    }

    public void  setUrlMODULO( Object param, Format ignore ) {
        this.urlMODULO.setValue( param );
    }

    public void  setUrlISTANZA( String param ) {
        this.urlISTANZA.setValue( param );
    }

    public void  setUrlISTANZA( Object param ) {
        this.urlISTANZA.setValue( param );
    }

    public void  setUrlISTANZA( Object param, Format ignore ) {
        this.urlISTANZA.setValue( param );
    }

    public void  setUrlISLISTBOX( String param ) {
        this.urlISLISTBOX.setValue( param );
    }

    public void  setUrlISLISTBOX( Object param ) {
        this.urlISLISTBOX.setValue( param );
    }

    public void  setUrlISLISTBOX( Object param, Format ignore ) {
        this.urlISLISTBOX.setValue( param );
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

    public void  setSesAD4GRUPPO( String param ) {
        this.sesAD4GRUPPO.setValue( param );
    }

    public void  setSesAD4GRUPPO( Object param ) {
        this.sesAD4GRUPPO.setValue( param );
    }

    public void  setSesAD4GRUPPO( Object param, Format ignore ) {
        this.sesAD4GRUPPO.setValue( param );
    }

    public void  setSesAD4REFRESHSLAVES( String param ) {
        this.sesAD4REFRESHSLAVES.setValue( param );
    }

    public void  setSesAD4REFRESHSLAVES( Object param ) {
        this.sesAD4REFRESHSLAVES.setValue( param );
    }

    public void  setSesAD4REFRESHSLAVES( Object param, Format ignore ) {
        this.sesAD4REFRESHSLAVES.setValue( param );
    }

    public AD4_DIRITTI_ACCESSO1Row getRow() {
        return row;
    }

    public void setRow( AD4_DIRITTI_ACCESSO1Row row ) {
        this.row = row;
    }

//End properties of DataObject

//constructor @38-32C7D3C2
    public AD4_DIRITTI_ACCESSO1DataObject(Page page) {
        super(page);
        addRecordDataObjectListener( new AD4_DIRITTI_ACCESSO1DataObjectHandler() );
    }
//End constructor

//load @38-FA4463B6
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT DIAC.UTENTE UTENTE, "
                    + "       'del'||decode(UTE2.TIPO_UTENTE,  "
                    + "'U','l''utente ',' gruppo ')||UTE2.NOMINATIVO NOMINATIVO, "
                    + "	   decode(UTE2.TIPO_UTENTE,  "
                    + "'U','D','G','G') TIPO_ACCESSO, "
                    + "       DECODE('{ISLISTBOX}','Y','{MODULO}',DIAC.MODULO) MODULO, "
                    + "       DIAC.MODULO MODULO_ORIG, "
                    + "       DECODE('{ISLISTBOX}','Y','{ISTANZA}',DIAC.ISTANZA) ISTANZA, "
                    + "       DIAC.ISTANZA ISTANZA_ORIG, "
                    + "	   ISTA.PROGETTO PROGETTO, "
                    + "       NVL('{RUOLO}',DIAC.RUOLO) RUOLO,  "
                    + "       DIAC.SEQUENZA SEQUENZA,  "
                    + "       DIAC.ULTIMO_ACCESSO ULTIMO_ACCESSO,  "
                    + "       DIAC.NUMERO_ACCESSI NUMERO_ACCESSI,  "
                    + "       UTEN.NOMINATIVO GRUPPO,  "
                    + "       NVL('{NOTE}',DIAC.NOTE) NOTE, "
                    + "       '{AD4REFRESHSLAVES}' REFRESH_SLAVES "
                    + "  FROM DIRITTI_ACCESSO DIAC, UTENTI UTEN,  "
                    + "       MODULI MODU, UTENTI UTE2, ISTANZE ISTA "
                    + " WHERE DIAC.UTENTE         = decode('{TIPO_UTENTE}','U','{UTENTE}','G','{GRUPPO}','{UTENTE}') "
                    + "   AND UTEN.UTENTE(+)      = DIAC.GRUPPO "
                    + "   AND UTE2.UTENTE         = DIAC.UTENTE "
                    + "   AND DIAC.MODULO         = '{MODULO}' "
                    + "   AND DIAC.ISTANZA        = '{ISTANZA}' "
                    + "   AND DIAC.MODULO         = MODU.MODULO "
                    + "   AND ISTA.ISTANZA        = DIAC.ISTANZA "
                    + "   AND '{MODULO}' IS NOT NULL "
                    + "   AND '{ISTANZA}' IS NOT NULL "
                    + "UNION "
                    + "SELECT decode(UTEN.TIPO_UTENTE, 'U','{UTENTE}','{GRUPPO}') UTENTE, "
                    + "       UTEN.NOMINATIVO NOMINATIVO, "
                    + "	   decode(UTEN.TIPO_UTENTE, 'U','D','G') TIPO_ACCESSO,	    "
                    + "       '{MODULO}' MODULO, "
                    + "       '' MODULO_ORIG, "
                    + "       '{ISTANZA}' ISTANZA,  "
                    + "       '' ISTANZA_ORIG, "
                    + "	   '' PROGETTO,	    "
                    + "       '{RUOLO}' RUOLO,  "
                    + "       TO_NUMBER(NULL) SEQUENZA,  "
                    + "       TO_DATE(NULL) ULTIMO_ACCESSO,  "
                    + "       TO_NUMBER(NULL) NUMERO_ACCESSI,  "
                    + "       '' GRUPPO,  "
                    + "       '{NOTE}' NOTE, "
                    + "       '{AD4REFRESHSLAVES}' REFRESH_SLAVES  "
                    + "  FROM UTENTI UTEN "
                    + " WHERE UTEN.UTENTE = decode('{TIPO_UTENTE}','U','{UTENTE}','G','{GRUPPO}','{UTENTE}') "
                    + "   AND not exists (select 1 "
                    + "                     from diritti_accesso diac "
                    + "					where diac.utente = UTEN.UTENTE "
                    + "					  and diac.modulo = '{MODULO}' "
                    + "					  and diac.istanza = '{ISTANZA}' "
                    + "                  )" );
        if ( StringUtils.isEmpty( (String) sesAD4UTENTE.getObjectValue() ) ) sesAD4UTENTE.setValue( "" );
        command.addParameter( "UTENTE", sesAD4UTENTE, null );
        if ( StringUtils.isEmpty( (String) urlMODULO.getObjectValue() ) ) urlMODULO.setValue( "" );
        command.addParameter( "MODULO", urlMODULO, null );
        if ( StringUtils.isEmpty( (String) urlISTANZA.getObjectValue() ) ) urlISTANZA.setValue( "" );
        command.addParameter( "ISTANZA", urlISTANZA, null );
        if ( StringUtils.isEmpty( (String) urlISLISTBOX.getObjectValue() ) ) urlISLISTBOX.setValue( "N" );
        command.addParameter( "ISLISTBOX", urlISLISTBOX, null );
        if ( StringUtils.isEmpty( (String) postRUOLO.getObjectValue() ) ) postRUOLO.setValue( "" );
        command.addParameter( "RUOLO", postRUOLO, null );
        if ( StringUtils.isEmpty( (String) postNOTE.getObjectValue() ) ) postNOTE.setValue( "" );
        command.addParameter( "NOTE", postNOTE, null );
        if ( StringUtils.isEmpty( (String) urlMVAV.getObjectValue() ) ) urlMVAV.setValue( "" );
        command.addParameter( "TIPO_UTENTE", urlMVAV, null );
        if ( StringUtils.isEmpty( (String) sesAD4GRUPPO.getObjectValue() ) ) sesAD4GRUPPO.setValue( "" );
        command.addParameter( "GRUPPO", sesAD4GRUPPO, null );
        if ( StringUtils.isEmpty( (String) sesAD4REFRESHSLAVES.getObjectValue() ) ) sesAD4REFRESHSLAVES.setValue( "" );
        command.addParameter( "AD4REFRESHSLAVES", sesAD4REFRESHSLAVES, null );
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

//loadDataBind @38-A08D6229
        if ( record == null || record.isEmpty() ) {
            empty = true;
        } else {
            row.setNOMINATIVO(Utils.convertToString(ds.parse(record.get("NOMINATIVO"), row.getNOMINATIVOField())));
            row.setSEQUENZA(Utils.convertToLong(ds.parse(record.get("SEQUENZA"), row.getSEQUENZAField())));
            row.setUTENTE(Utils.convertToString(ds.parse(record.get("UTENTE"), row.getUTENTEField())));
            row.setMODULO(Utils.convertToString(ds.parse(record.get("MODULO"), row.getMODULOField())));
            row.setMODULO_ORIG(Utils.convertToString(ds.parse(record.get("MODULO_ORIG"), row.getMODULO_ORIGField())));
            row.setISTANZA(Utils.convertToString(ds.parse(record.get("ISTANZA"), row.getISTANZAField())));
            row.setISTANZA_ORIG(Utils.convertToString(ds.parse(record.get("ISTANZA_ORIG"), row.getISTANZA_ORIGField())));
            row.setRUOLO(Utils.convertToString(ds.parse(record.get("RUOLO"), row.getRUOLOField())));
            row.setNOTE(Utils.convertToString(ds.parse(record.get("NOTE"), row.getNOTEField())));
            try {
                row.setULTIMO_ACCESSO(Utils.convertToDate(ds.parse(record.get("ULTIMO_ACCESSO"), row.getULTIMO_ACCESSOField())));
            } catch ( java.text.ParseException pe ) {
                model.addError( "Invalid data" );
            }
            row.setNUMERO_ACCESSI(Utils.convertToLong(ds.parse(record.get("NUMERO_ACCESSI"), row.getNUMERO_ACCESSIField())));
            row.setGRUPPO(Utils.convertToString(ds.parse(record.get("GRUPPO"), row.getGRUPPOField())));
            row.setTIPO_ACCESSO(Utils.convertToString(ds.parse(record.get("TIPO_ACCESSO"), row.getTIPO_ACCESSOField())));
            row.setPROGETTO(Utils.convertToString(ds.parse(record.get("PROGETTO"), row.getPROGETTOField())));
        }
//End loadDataBind

//End of load @38-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//update @38-DBD6B648
        boolean update() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{? = call AD4WEB.DIRITTI_ACCESSO_PM(?,?,?,?,?,?,?,?)}" );
            command.addParameter( "RETURN_VALUE", null, java.sql.Types.DOUBLE, 0, SPParameter.OUTPUT_PARAMETER );
            command.addParameter( "P_SEQUENZA", postSEQUENZA, java.sql.Types.DOUBLE, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getUTENTE()) ) row.setUTENTE( "" );
            command.addParameter( "P_UTENTE", row.getUTENTEField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postMODULO.getObjectValue() ) ) postMODULO.setValue( "" );
            command.addParameter( "P_MODULO", postMODULO, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getMODULO_ORIG()) ) row.setMODULO_ORIG( "" );
            command.addParameter( "P_MODULO_OLD", row.getMODULO_ORIGField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postISTANZA.getObjectValue() ) ) postISTANZA.setValue( "" );
            command.addParameter( "P_ISTANZA", postISTANZA, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getISTANZA_ORIG()) ) row.setISTANZA_ORIG( "" );
            command.addParameter( "P_ISTANZA_OLD", row.getISTANZA_ORIGField(), java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postRUOLO.getObjectValue() ) ) postRUOLO.setValue( "" );
            command.addParameter( "P_RUOLO", postRUOLO, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postNOTE.getObjectValue() ) ) postNOTE.setValue( "" );
            command.addParameter( "P_NOTE", postNOTE, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );

            fireBeforeBuildUpdateEvent( new DataObjectEvent(command) );


//End update

//updateDataBound @38-0130DCE2
            fireBeforeExecuteUpdateEvent( new DataObjectEvent(command) );

            if ( ! ds.hasErrors() ) {
                command.executeUpdate();
            }

            CCLogger.getInstance().debug(command.toString());

            fireAfterExecuteUpdateEvent( new DataObjectEvent(command) );

//End updateDataBound

//End of update @38-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of update

//delete @38-A20C57B5
        boolean delete() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            command.setSql( "delete DIRITTI_ACCESSO "
                        + " where utente = decode('{TIPO_UTENTE}',  "
                        + "'U','{UTENTE}','G','{GRUPPO}','{UTENTE}') "
                        + "   AND MODULO = '{MODULO}' "
                        + "   AND ISTANZA = '{ISTANZA}'" );
            if ( StringUtils.isEmpty( (String) sesAD4UTENTE.getObjectValue() ) ) sesAD4UTENTE.setValue( "" );
            command.addParameter( "UTENTE", sesAD4UTENTE, null );
            if ( StringUtils.isEmpty( (String) sesAD4GRUPPO.getObjectValue() ) ) sesAD4GRUPPO.setValue( "" );
            command.addParameter( "GRUPPO", sesAD4GRUPPO, null );
            if ( StringUtils.isEmpty( (String) postISTANZA.getObjectValue() ) ) postISTANZA.setValue( "" );
            command.addParameter( "ISTANZA", postISTANZA, null );
            if ( StringUtils.isEmpty( (String) postMODULO.getObjectValue() ) ) postMODULO.setValue( "" );
            command.addParameter( "MODULO", postMODULO, null );
            if ( StringUtils.isEmpty( (String) urlMVAV.getObjectValue() ) ) urlMVAV.setValue( "" );
            command.addParameter( "TIPO_UTENTE", urlMVAV, null );

            fireBeforeBuildDeleteEvent( new DataObjectEvent(command) );


//End delete

//deleteDataBound @38-67425D5E
            fireBeforeExecuteDeleteEvent( new DataObjectEvent(command) );

            if ( ! ds.hasErrors() ) {
                command.executeUpdate();
            }

            CCLogger.getInstance().debug(command.toString());

            fireAfterExecuteDeleteEvent( new DataObjectEvent(command) );

//End deleteDataBound

//End of delete @38-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of delete

//getParameterByName @38-9B3D1F53
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "SEQUENZA".equals(name) && "post".equals(prefix) ) {
                param = postSEQUENZA;
            } else if ( "SEQUENZA".equals(name) && prefix == null ) {
                param = postSEQUENZA;
            }
            if ( "UTENTE".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlUTENTE;
            } else if ( "UTENTE".equals(name) && prefix == null ) {
                param = ctrlUTENTE;
            }
            if ( "MODULO".equals(name) && "post".equals(prefix) ) {
                param = postMODULO;
            } else if ( "MODULO".equals(name) && prefix == null ) {
                param = postMODULO;
            }
            if ( "MODULO_ORIG".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlMODULO_ORIG;
            } else if ( "MODULO_ORIG".equals(name) && prefix == null ) {
                param = ctrlMODULO_ORIG;
            }
            if ( "ISTANZA".equals(name) && "post".equals(prefix) ) {
                param = postISTANZA;
            } else if ( "ISTANZA".equals(name) && prefix == null ) {
                param = postISTANZA;
            }
            if ( "ISTANZA_ORIG".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlISTANZA_ORIG;
            } else if ( "ISTANZA_ORIG".equals(name) && prefix == null ) {
                param = ctrlISTANZA_ORIG;
            }
            if ( "RUOLO".equals(name) && "post".equals(prefix) ) {
                param = postRUOLO;
            } else if ( "RUOLO".equals(name) && prefix == null ) {
                param = postRUOLO;
            }
            if ( "NOTE".equals(name) && "post".equals(prefix) ) {
                param = postNOTE;
            } else if ( "NOTE".equals(name) && prefix == null ) {
                param = postNOTE;
            }
            if ( "AD4UTENTE".equals(name) && "ses".equals(prefix) ) {
                param = sesAD4UTENTE;
            } else if ( "AD4UTENTE".equals(name) && prefix == null ) {
                param = sesAD4UTENTE;
            }
            if ( "MODULO".equals(name) && "url".equals(prefix) ) {
                param = urlMODULO;
            } else if ( "MODULO".equals(name) && prefix == null ) {
                param = urlMODULO;
            }
            if ( "ISTANZA".equals(name) && "url".equals(prefix) ) {
                param = urlISTANZA;
            } else if ( "ISTANZA".equals(name) && prefix == null ) {
                param = urlISTANZA;
            }
            if ( "ISLISTBOX".equals(name) && "url".equals(prefix) ) {
                param = urlISLISTBOX;
            } else if ( "ISLISTBOX".equals(name) && prefix == null ) {
                param = urlISLISTBOX;
            }
            if ( "MVAV".equals(name) && "url".equals(prefix) ) {
                param = urlMVAV;
            } else if ( "MVAV".equals(name) && prefix == null ) {
                param = urlMVAV;
            }
            if ( "AD4GRUPPO".equals(name) && "ses".equals(prefix) ) {
                param = sesAD4GRUPPO;
            } else if ( "AD4GRUPPO".equals(name) && prefix == null ) {
                param = sesAD4GRUPPO;
            }
            if ( "AD4REFRESHSLAVES".equals(name) && "ses".equals(prefix) ) {
                param = sesAD4REFRESHSLAVES;
            } else if ( "AD4REFRESHSLAVES".equals(name) && prefix == null ) {
                param = sesAD4REFRESHSLAVES;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addRecordDataObjectListener @38-47141785
    public synchronized void addRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.addElement(l);
    }
//End addRecordDataObjectListener

//removeRecordDataObjectListener @38-A1ABC1F4
    public synchronized void removeRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeRecordDataObjectListener

//fireBeforeBuildSelectEvent @38-305A023C
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

//fireBeforeExecuteSelectEvent @38-D00ACF95
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

//fireAfterExecuteSelectEvent @38-3BAD39CE
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

//fireBeforeBuildInsertEvent @38-FBA08B71
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

//fireBeforeExecuteInsertEvent @38-47AFA6A5
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

//fireAfterExecuteInsertEvent @38-E9CE95AE
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

//fireBeforeBuildSelectEvent @38-2405BE8B
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

//fireBeforeExecuteSelectEvent @38-E9DFF86B
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

//fireAfterExecuteSelectEvent @38-580A2987
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

//fireBeforeBuildSelectEvent @38-D021D0EA
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

//fireBeforeExecuteDeleteEvent @38-DD540FBB
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

//fireAfterExecuteDeleteEvent @38-2A6E2049
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

//class DataObject Tail @38-ED3F53A4
} // End of class DS
//End class DataObject Tail

