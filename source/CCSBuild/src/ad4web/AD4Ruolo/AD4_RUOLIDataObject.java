//AD4_RUOLI DataSource @23-F478E6ED
package ad4web.AD4Ruolo;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End AD4_RUOLI DataSource

//class DataObject Header @23-34C5C805
public class AD4_RUOLIDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @23-2A74BE75
    

    TextField postRUOLO = new TextField(null, null);
    
    TextField postRUOLO_ORIG = new TextField(null, null);
    
    TextField postDESCRIZIONE = new TextField(null, null);
    
    TextField postPROGETTO = new TextField(null, null);
    
    TextField postMODULO = new TextField(null, null);
    
    DoubleField postPROFILO = new DoubleField(null, null);
    
    TextField ctrlSTATO = new TextField(null, null);
    
    TextField ctrlGRUPPO_LAVORO = new TextField(null, null);
    
    TextField ctrlGRUPPO_SO = new TextField(null, null);
    
    TextField urlRUOLO = new TextField(null, null);
    
    TextField urlSE_NUOVO = new TextField(null, null);
    
    TextField urlPROGETTO = new TextField(null, null);
    
    TextField urlMODULO = new TextField(null, null);
    
    TextField urlDESCRIZIONE = new TextField(null, null);
    
    TextField urlPROFILO = new TextField(null, null);
    
    TextField urlISLISTBOX = new TextField(null, null);
    
    TextField urlSTATO = new TextField(null, null);
    
    TextField urlGRUPPO_LAVORO = new TextField(null, null);
    
    TextField urlGRUPPO_SO = new TextField(null, null);
    

    private AD4_RUOLIRow row = new AD4_RUOLIRow();

//End attributes of DataObject

//properties of DataObject @23-CE6652D9

    public void  setPostRUOLO( String param ) {
        this.postRUOLO.setValue( param );
    }

    public void  setPostRUOLO( Object param ) {
        this.postRUOLO.setValue( param );
    }

    public void  setPostRUOLO( Object param, Format ignore ) {
        this.postRUOLO.setValue( param );
    }

    public void  setPostRUOLO_ORIG( String param ) {
        this.postRUOLO_ORIG.setValue( param );
    }

    public void  setPostRUOLO_ORIG( Object param ) {
        this.postRUOLO_ORIG.setValue( param );
    }

    public void  setPostRUOLO_ORIG( Object param, Format ignore ) {
        this.postRUOLO_ORIG.setValue( param );
    }

    public void  setPostDESCRIZIONE( String param ) {
        this.postDESCRIZIONE.setValue( param );
    }

    public void  setPostDESCRIZIONE( Object param ) {
        this.postDESCRIZIONE.setValue( param );
    }

    public void  setPostDESCRIZIONE( Object param, Format ignore ) {
        this.postDESCRIZIONE.setValue( param );
    }

    public void  setPostPROGETTO( String param ) {
        this.postPROGETTO.setValue( param );
    }

    public void  setPostPROGETTO( Object param ) {
        this.postPROGETTO.setValue( param );
    }

    public void  setPostPROGETTO( Object param, Format ignore ) {
        this.postPROGETTO.setValue( param );
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

    public void  setPostPROFILO( double param ) {
        this.postPROFILO.setValue( param );
    }

    public void  setPostPROFILO( double param, Format ignore ) throws java.text.ParseException {
        this.postPROFILO.setValue( param );
    }

    public void  setPostPROFILO( Object param, Format format ) throws java.text.ParseException {
        this.postPROFILO.setValue( param, format );
    }

    public void  setPostPROFILO( Double param ) {
        this.postPROFILO.setValue( param );
    }

    public void  setCtrlSTATO( String param ) {
        this.ctrlSTATO.setValue( param );
    }

    public void  setCtrlSTATO( Object param ) {
        this.ctrlSTATO.setValue( param );
    }

    public void  setCtrlSTATO( Object param, Format ignore ) {
        this.ctrlSTATO.setValue( param );
    }

    public void  setCtrlGRUPPO_LAVORO( String param ) {
        this.ctrlGRUPPO_LAVORO.setValue( param );
    }

    public void  setCtrlGRUPPO_LAVORO( Object param ) {
        this.ctrlGRUPPO_LAVORO.setValue( param );
    }

    public void  setCtrlGRUPPO_LAVORO( Object param, Format ignore ) {
        this.ctrlGRUPPO_LAVORO.setValue( param );
    }

    public void  setCtrlGRUPPO_SO( String param ) {
        this.ctrlGRUPPO_SO.setValue( param );
    }

    public void  setCtrlGRUPPO_SO( Object param ) {
        this.ctrlGRUPPO_SO.setValue( param );
    }

    public void  setCtrlGRUPPO_SO( Object param, Format ignore ) {
        this.ctrlGRUPPO_SO.setValue( param );
    }

    public void  setUrlRUOLO( String param ) {
        this.urlRUOLO.setValue( param );
    }

    public void  setUrlRUOLO( Object param ) {
        this.urlRUOLO.setValue( param );
    }

    public void  setUrlRUOLO( Object param, Format ignore ) {
        this.urlRUOLO.setValue( param );
    }

    public void  setUrlSE_NUOVO( String param ) {
        this.urlSE_NUOVO.setValue( param );
    }

    public void  setUrlSE_NUOVO( Object param ) {
        this.urlSE_NUOVO.setValue( param );
    }

    public void  setUrlSE_NUOVO( Object param, Format ignore ) {
        this.urlSE_NUOVO.setValue( param );
    }

    public void  setUrlPROGETTO( String param ) {
        this.urlPROGETTO.setValue( param );
    }

    public void  setUrlPROGETTO( Object param ) {
        this.urlPROGETTO.setValue( param );
    }

    public void  setUrlPROGETTO( Object param, Format ignore ) {
        this.urlPROGETTO.setValue( param );
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

    public void  setUrlDESCRIZIONE( String param ) {
        this.urlDESCRIZIONE.setValue( param );
    }

    public void  setUrlDESCRIZIONE( Object param ) {
        this.urlDESCRIZIONE.setValue( param );
    }

    public void  setUrlDESCRIZIONE( Object param, Format ignore ) {
        this.urlDESCRIZIONE.setValue( param );
    }

    public void  setUrlPROFILO( String param ) {
        this.urlPROFILO.setValue( param );
    }

    public void  setUrlPROFILO( Object param ) {
        this.urlPROFILO.setValue( param );
    }

    public void  setUrlPROFILO( Object param, Format ignore ) {
        this.urlPROFILO.setValue( param );
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

    public void  setUrlSTATO( String param ) {
        this.urlSTATO.setValue( param );
    }

    public void  setUrlSTATO( Object param ) {
        this.urlSTATO.setValue( param );
    }

    public void  setUrlSTATO( Object param, Format ignore ) {
        this.urlSTATO.setValue( param );
    }

    public void  setUrlGRUPPO_LAVORO( String param ) {
        this.urlGRUPPO_LAVORO.setValue( param );
    }

    public void  setUrlGRUPPO_LAVORO( Object param ) {
        this.urlGRUPPO_LAVORO.setValue( param );
    }

    public void  setUrlGRUPPO_LAVORO( Object param, Format ignore ) {
        this.urlGRUPPO_LAVORO.setValue( param );
    }

    public void  setUrlGRUPPO_SO( String param ) {
        this.urlGRUPPO_SO.setValue( param );
    }

    public void  setUrlGRUPPO_SO( Object param ) {
        this.urlGRUPPO_SO.setValue( param );
    }

    public void  setUrlGRUPPO_SO( Object param, Format ignore ) {
        this.urlGRUPPO_SO.setValue( param );
    }

    public AD4_RUOLIRow getRow() {
        return row;
    }

    public void setRow( AD4_RUOLIRow row ) {
        this.row = row;
    }

//End properties of DataObject

//constructor @23-50D8FF8C
    public AD4_RUOLIDataObject(Page page) {
        super(page);
        addRecordDataObjectListener( new AD4_RUOLIDataObjectHandler() );
    }
//End constructor

//load @23-3A4D557B
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "SELECT '<input class=\"AFCInputDisplay\" maxlength=\"8\" size=\"8\" value=\"'||RUOLO||'\" name=\"RUOLO\" readonly ></input>' RUOLO, "
                    + "       RUOLO RUOLO_ORIG, "
                    + "       nvl('{DESCRIZIONE}',DESCRIZIONE) descrizione,  "
                    + " "
                    + "       DECODE('{ISLISTBOX}','Y','{PROGETTO}',PROGETTO) PROGETTO, "
                    + "       DECODE('{ISLISTBOX}','Y','{MODULO}',MODULO) MODULO, "
                    + "DECODE('{ISLISTBOX}','Y',TO_NUMBER('{PROFILO}'),PROFILO) PROFILO, "
                    + "       '{SE_NUOVO}' SE_NUOVO, "
                    + "       '{RUOLO}' P_RUOLO, "
                    + "DECODE('{ISLISTBOX}','Y',DECODE('{STATO}', '1','U','C'),STATO) STATO, "
                    + "DECODE('{ISLISTBOX}','Y',DECODE('{GRUPPO_LAVORO}', '1','S','N'),GRUPPO_LAVORO) GRUPPO_LAVORO, "
                    + "DECODE('{ISLISTBOX}','Y',DECODE('{GRUPPO_SO}', '1','S','N'),GRUPPO_SO) GRUPPO_SO "
                    + "  FROM RUOLI "
                    + " WHERE RUOLO = '{RUOLO}' "
                    + "   AND '{SE_NUOVO}' = 'N' "
                    + "   and ruolo not in ('*','def') "
                    + "UNION "
                    + "SELECT '<input class=\"AFCInput\" style=\"TEXT-TRANSFORM: uppercase\" maxlength=\"8\" size=\"8\" value=\"'||'{RUOLO}'||'\" name=\"RUOLO\"></input>' RUOLO,  "
                    + "       '' RUOLO_ORIG, "
                    + "       '{DESCRIZIONE}',  "
                    + "       '{PROGETTO}' PROGETTO, "
                    + "       '{MODULO}' MODULO, "
                    + "       TO_NUMBER('{PROFILO}') PROFILO, "
                    + "       '{SE_NUOVO}' SE_NUOVO, "
                    + "       '{RUOLO}' P_RUOLO, "
                    + "       '{STATO}' STATO, "
                    + "       '{GRUPPO_LAVORO}' GRUPPO_LAVORO, "
                    + "       '{GRUPPO_SO}' GRUPPO_SO "
                    + "  FROM DUAL "
                    + " WHERE '{SE_NUOVO}' = 'Y' "
                    + "    OR '{RUOLO}' is null" );
        if ( StringUtils.isEmpty( (String) urlRUOLO.getObjectValue() ) ) urlRUOLO.setValue( "" );
        command.addParameter( "RUOLO", urlRUOLO, null );
        if ( StringUtils.isEmpty( (String) urlSE_NUOVO.getObjectValue() ) ) urlSE_NUOVO.setValue( "N" );
        command.addParameter( "SE_NUOVO", urlSE_NUOVO, null );
        if ( StringUtils.isEmpty( (String) urlPROGETTO.getObjectValue() ) ) urlPROGETTO.setValue( "" );
        command.addParameter( "PROGETTO", urlPROGETTO, null );
        if ( StringUtils.isEmpty( (String) urlMODULO.getObjectValue() ) ) urlMODULO.setValue( "" );
        command.addParameter( "MODULO", urlMODULO, null );
        if ( StringUtils.isEmpty( (String) urlDESCRIZIONE.getObjectValue() ) ) urlDESCRIZIONE.setValue( "" );
        command.addParameter( "DESCRIZIONE", urlDESCRIZIONE, null );
        if ( StringUtils.isEmpty( (String) urlPROFILO.getObjectValue() ) ) urlPROFILO.setValue( "" );
        command.addParameter( "PROFILO", urlPROFILO, null );
        if ( StringUtils.isEmpty( (String) urlISLISTBOX.getObjectValue() ) ) urlISLISTBOX.setValue( "N" );
        command.addParameter( "ISLISTBOX", urlISLISTBOX, null );
        if ( StringUtils.isEmpty( (String) urlSTATO.getObjectValue() ) ) urlSTATO.setValue( "" );
        command.addParameter( "STATO", urlSTATO, null );
        if ( StringUtils.isEmpty( (String) urlGRUPPO_LAVORO.getObjectValue() ) ) urlGRUPPO_LAVORO.setValue( "" );
        command.addParameter( "GRUPPO_LAVORO", urlGRUPPO_LAVORO, null );
        if ( StringUtils.isEmpty( (String) urlGRUPPO_SO.getObjectValue() ) ) urlGRUPPO_SO.setValue( "" );
        command.addParameter( "GRUPPO_SO", urlGRUPPO_SO, null );
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

//loadDataBind @23-277B099C
        if ( record == null || record.isEmpty() ) {
            empty = true;
        } else {
            row.setRUOLO(Utils.convertToString(ds.parse(record.get("RUOLO"), row.getRUOLOField())));
            row.setRUOLO_ORIG(Utils.convertToString(ds.parse(record.get("RUOLO_ORIG"), row.getRUOLO_ORIGField())));
            row.setDESCRIZIONE(Utils.convertToString(ds.parse(record.get("DESCRIZIONE"), row.getDESCRIZIONEField())));
            row.setPROGETTO(Utils.convertToString(ds.parse(record.get("PROGETTO"), row.getPROGETTOField())));
            row.setMODULO(Utils.convertToString(ds.parse(record.get("MODULO"), row.getMODULOField())));
            row.setPROFILO(Utils.convertToLong(ds.parse(record.get("PROFILO"), row.getPROFILOField())));
            row.setSTATO(Utils.convertToString(ds.parse(record.get("STATO"), row.getSTATOField())));
            row.setGRUPPO_LAVORO(Utils.convertToString(ds.parse(record.get("GRUPPO_LAVORO"), row.getGRUPPO_LAVOROField())));
            row.setGRUPPO_SO(Utils.convertToString(ds.parse(record.get("GRUPPO_SO"), row.getGRUPPO_SOField())));
        }
//End loadDataBind

//End of load @23-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//update @23-ADD091CE
        boolean update() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            SPCommand command = SPCommandFactory.getSPCommand( "cn" );
            command.setJdbcConnection( ds );

            command.setSql( "{? = call AD4WEB.RUOLI_PM(?,?,?,?,?,?,?,?,?)}" );
            command.addParameter( "RETURN_VALUE", null, java.sql.Types.CHAR, 0, SPParameter.OUTPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postRUOLO.getObjectValue() ) ) postRUOLO.setValue( "" );
            command.addParameter( "P_RUOLO", postRUOLO, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postRUOLO_ORIG.getObjectValue() ) ) postRUOLO_ORIG.setValue( "" );
            command.addParameter( "P_RUOLO_OLD", postRUOLO_ORIG, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postDESCRIZIONE.getObjectValue() ) ) postDESCRIZIONE.setValue( "" );
            command.addParameter( "P_DESCRIZIONE", postDESCRIZIONE, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postPROGETTO.getObjectValue() ) ) postPROGETTO.setValue( "" );
            command.addParameter( "P_PROGETTO", postPROGETTO, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) postMODULO.getObjectValue() ) ) postMODULO.setValue( "" );
            command.addParameter( "P_MODULO", postMODULO, java.sql.Types.CHAR, 0, SPParameter.INPUT_PARAMETER );
            command.addParameter( "P_PROFILO", postPROFILO, java.sql.Types.DOUBLE, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getSTATO()) ) row.setSTATO( "" );
            command.addParameter( "P_STATO", row.getSTATOField(), java.sql.Types.VARCHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getGRUPPO_LAVORO()) ) row.setGRUPPO_LAVORO( "" );
            command.addParameter( "P_GRUPPO_LAVORO", row.getGRUPPO_LAVOROField(), java.sql.Types.VARCHAR, 0, SPParameter.INPUT_PARAMETER );
            if ( StringUtils.isEmpty( (String) row.getGRUPPO_SO()) ) row.setGRUPPO_SO( "" );
            command.addParameter( "P_GRUPPO_SO", row.getGRUPPO_SOField(), java.sql.Types.VARCHAR, 0, SPParameter.INPUT_PARAMETER );

            fireBeforeBuildUpdateEvent( new DataObjectEvent(command) );


//End update

//updateDataBound @23-0130DCE2
            fireBeforeExecuteUpdateEvent( new DataObjectEvent(command) );

            if ( ! ds.hasErrors() ) {
                command.executeUpdate();
            }

            CCLogger.getInstance().debug(command.toString());

            fireAfterExecuteUpdateEvent( new DataObjectEvent(command) );

//End updateDataBound

//End of update @23-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of update

//delete @23-DE5F2BA2
        boolean delete() {
            boolean isErrors = false;
            JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
            ds.setLocale(page.getCCSLocale().getLocale());
            RawCommand command = new RawCommand( ds );

            command.setNeedQuotes(true);
            command.setSql("DELETE FROM AD4_RUOLI");

            fireBeforeBuildDeleteEvent( new DataObjectEvent(command) );

            String where1 = WhereParams.rawOperation( "RUOLO", FieldOperation.EQUAL, postRUOLO, null, ds );
            if (StringUtils.isEmpty(where1)) command.setCmdExecution(false);
            String whereParams = where1;

            if ( where1 == null ) {
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

//deleteDataBound @23-5B959F17
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

//End of delete @23-CDC5319F
            ds.closeConnection();
            isErrors = ds.hasErrors();
            if ( isErrors ) addErrors( ds.getErrors() );
            return ( ! isErrors );
        }
//End End of delete

//getParameterByName @23-87E097D8
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "RUOLO".equals(name) && "post".equals(prefix) ) {
                param = postRUOLO;
            } else if ( "RUOLO".equals(name) && prefix == null ) {
                param = postRUOLO;
            }
            if ( "RUOLO_ORIG".equals(name) && "post".equals(prefix) ) {
                param = postRUOLO_ORIG;
            } else if ( "RUOLO_ORIG".equals(name) && prefix == null ) {
                param = postRUOLO_ORIG;
            }
            if ( "DESCRIZIONE".equals(name) && "post".equals(prefix) ) {
                param = postDESCRIZIONE;
            } else if ( "DESCRIZIONE".equals(name) && prefix == null ) {
                param = postDESCRIZIONE;
            }
            if ( "PROGETTO".equals(name) && "post".equals(prefix) ) {
                param = postPROGETTO;
            } else if ( "PROGETTO".equals(name) && prefix == null ) {
                param = postPROGETTO;
            }
            if ( "MODULO".equals(name) && "post".equals(prefix) ) {
                param = postMODULO;
            } else if ( "MODULO".equals(name) && prefix == null ) {
                param = postMODULO;
            }
            if ( "PROFILO".equals(name) && "post".equals(prefix) ) {
                param = postPROFILO;
            } else if ( "PROFILO".equals(name) && prefix == null ) {
                param = postPROFILO;
            }
            if ( "STATO".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlSTATO;
            } else if ( "STATO".equals(name) && prefix == null ) {
                param = ctrlSTATO;
            }
            if ( "GRUPPO_LAVORO".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlGRUPPO_LAVORO;
            } else if ( "GRUPPO_LAVORO".equals(name) && prefix == null ) {
                param = ctrlGRUPPO_LAVORO;
            }
            if ( "GRUPPO_SO".equals(name) && "ctrl".equals(prefix) ) {
                param = ctrlGRUPPO_SO;
            } else if ( "GRUPPO_SO".equals(name) && prefix == null ) {
                param = ctrlGRUPPO_SO;
            }
            if ( "RUOLO".equals(name) && "url".equals(prefix) ) {
                param = urlRUOLO;
            } else if ( "RUOLO".equals(name) && prefix == null ) {
                param = urlRUOLO;
            }
            if ( "SE_NUOVO".equals(name) && "url".equals(prefix) ) {
                param = urlSE_NUOVO;
            } else if ( "SE_NUOVO".equals(name) && prefix == null ) {
                param = urlSE_NUOVO;
            }
            if ( "PROGETTO".equals(name) && "url".equals(prefix) ) {
                param = urlPROGETTO;
            } else if ( "PROGETTO".equals(name) && prefix == null ) {
                param = urlPROGETTO;
            }
            if ( "MODULO".equals(name) && "url".equals(prefix) ) {
                param = urlMODULO;
            } else if ( "MODULO".equals(name) && prefix == null ) {
                param = urlMODULO;
            }
            if ( "DESCRIZIONE".equals(name) && "url".equals(prefix) ) {
                param = urlDESCRIZIONE;
            } else if ( "DESCRIZIONE".equals(name) && prefix == null ) {
                param = urlDESCRIZIONE;
            }
            if ( "PROFILO".equals(name) && "url".equals(prefix) ) {
                param = urlPROFILO;
            } else if ( "PROFILO".equals(name) && prefix == null ) {
                param = urlPROFILO;
            }
            if ( "ISLISTBOX".equals(name) && "url".equals(prefix) ) {
                param = urlISLISTBOX;
            } else if ( "ISLISTBOX".equals(name) && prefix == null ) {
                param = urlISLISTBOX;
            }
            if ( "STATO".equals(name) && "url".equals(prefix) ) {
                param = urlSTATO;
            } else if ( "STATO".equals(name) && prefix == null ) {
                param = urlSTATO;
            }
            if ( "GRUPPO_LAVORO".equals(name) && "url".equals(prefix) ) {
                param = urlGRUPPO_LAVORO;
            } else if ( "GRUPPO_LAVORO".equals(name) && prefix == null ) {
                param = urlGRUPPO_LAVORO;
            }
            if ( "GRUPPO_SO".equals(name) && "url".equals(prefix) ) {
                param = urlGRUPPO_SO;
            } else if ( "GRUPPO_SO".equals(name) && prefix == null ) {
                param = urlGRUPPO_SO;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addRecordDataObjectListener @23-47141785
    public synchronized void addRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.addElement(l);
    }
//End addRecordDataObjectListener

//removeRecordDataObjectListener @23-A1ABC1F4
    public synchronized void removeRecordDataObjectListener( RecordDataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeRecordDataObjectListener

//fireBeforeBuildSelectEvent @23-305A023C
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

//fireBeforeExecuteSelectEvent @23-D00ACF95
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

//fireAfterExecuteSelectEvent @23-3BAD39CE
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

//fireBeforeBuildInsertEvent @23-FBA08B71
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

//fireBeforeExecuteInsertEvent @23-47AFA6A5
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

//fireAfterExecuteInsertEvent @23-E9CE95AE
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

//fireBeforeBuildSelectEvent @23-2405BE8B
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

//fireBeforeExecuteSelectEvent @23-E9DFF86B
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

//fireAfterExecuteSelectEvent @23-580A2987
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

//fireBeforeBuildSelectEvent @23-D021D0EA
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

//fireBeforeExecuteDeleteEvent @23-DD540FBB
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

//fireAfterExecuteDeleteEvent @23-2A6E2049
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

//class DataObject Tail @23-ED3F53A4
} // End of class DS
//End class DataObject Tail

