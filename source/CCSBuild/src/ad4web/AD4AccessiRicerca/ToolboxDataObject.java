//Toolbox DataSource @418-281BB8A3
package ad4web.AD4AccessiRicerca;

import java.util.*;
import java.text.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
import com.codecharge.components.*;
//End Toolbox DataSource

//class DataObject Header @418-396ED4D2
public class ToolboxDataObject extends DS {
//End class DataObject Header

//attributes of DataObject @418-E069C4D2
    

    TextField urlS_TIPO = new TextField(null, null);
    
    TextField urlS_TERMINALE = new TextField(null, null);
    
    TextField urlS_UTENTE = new TextField(null, null);
    
    TextField urlS_ISTANZA = new TextField(null, null);
    
    TextField urlS_MODULO = new TextField(null, null);
    
    TextField urlS_DB_USER = new TextField(null, null);
    
    TextField urlS_DATA_DA = new TextField(null, null);
    
    TextField urlS_DATA_A = new TextField(null, null);
    
    TextField urlS_NOTE = new TextField(null, null);
    
    TextField urlS_AUTENTICAZIONE = new TextField(null, null);
    
    TextField urlS_STATO = new TextField(null, null);
    
    TextField urlS_RICERCA = new TextField(null, null);
    

    private ToolboxRow[] rows = new ToolboxRow[100];

    private int pageSize;
    private int pageNum;

//End attributes of DataObject

//properties of DataObject @418-EA08B5A0

    public void  setUrlS_TIPO( String param ) {
        this.urlS_TIPO.setValue( param );
    }

    public void  setUrlS_TIPO( Object param ) {
        this.urlS_TIPO.setValue( param );
    }

    public void  setUrlS_TIPO( Object param, Format ignore ) {
        this.urlS_TIPO.setValue( param );
    }

    public void  setUrlS_TERMINALE( String param ) {
        this.urlS_TERMINALE.setValue( param );
    }

    public void  setUrlS_TERMINALE( Object param ) {
        this.urlS_TERMINALE.setValue( param );
    }

    public void  setUrlS_TERMINALE( Object param, Format ignore ) {
        this.urlS_TERMINALE.setValue( param );
    }

    public void  setUrlS_UTENTE( String param ) {
        this.urlS_UTENTE.setValue( param );
    }

    public void  setUrlS_UTENTE( Object param ) {
        this.urlS_UTENTE.setValue( param );
    }

    public void  setUrlS_UTENTE( Object param, Format ignore ) {
        this.urlS_UTENTE.setValue( param );
    }

    public void  setUrlS_ISTANZA( String param ) {
        this.urlS_ISTANZA.setValue( param );
    }

    public void  setUrlS_ISTANZA( Object param ) {
        this.urlS_ISTANZA.setValue( param );
    }

    public void  setUrlS_ISTANZA( Object param, Format ignore ) {
        this.urlS_ISTANZA.setValue( param );
    }

    public void  setUrlS_MODULO( String param ) {
        this.urlS_MODULO.setValue( param );
    }

    public void  setUrlS_MODULO( Object param ) {
        this.urlS_MODULO.setValue( param );
    }

    public void  setUrlS_MODULO( Object param, Format ignore ) {
        this.urlS_MODULO.setValue( param );
    }

    public void  setUrlS_DB_USER( String param ) {
        this.urlS_DB_USER.setValue( param );
    }

    public void  setUrlS_DB_USER( Object param ) {
        this.urlS_DB_USER.setValue( param );
    }

    public void  setUrlS_DB_USER( Object param, Format ignore ) {
        this.urlS_DB_USER.setValue( param );
    }

    public void  setUrlS_DATA_DA( String param ) {
        this.urlS_DATA_DA.setValue( param );
    }

    public void  setUrlS_DATA_DA( Object param ) {
        this.urlS_DATA_DA.setValue( param );
    }

    public void  setUrlS_DATA_DA( Object param, Format ignore ) {
        this.urlS_DATA_DA.setValue( param );
    }

    public void  setUrlS_DATA_A( String param ) {
        this.urlS_DATA_A.setValue( param );
    }

    public void  setUrlS_DATA_A( Object param ) {
        this.urlS_DATA_A.setValue( param );
    }

    public void  setUrlS_DATA_A( Object param, Format ignore ) {
        this.urlS_DATA_A.setValue( param );
    }

    public void  setUrlS_NOTE( String param ) {
        this.urlS_NOTE.setValue( param );
    }

    public void  setUrlS_NOTE( Object param ) {
        this.urlS_NOTE.setValue( param );
    }

    public void  setUrlS_NOTE( Object param, Format ignore ) {
        this.urlS_NOTE.setValue( param );
    }

    public void  setUrlS_AUTENTICAZIONE( String param ) {
        this.urlS_AUTENTICAZIONE.setValue( param );
    }

    public void  setUrlS_AUTENTICAZIONE( Object param ) {
        this.urlS_AUTENTICAZIONE.setValue( param );
    }

    public void  setUrlS_AUTENTICAZIONE( Object param, Format ignore ) {
        this.urlS_AUTENTICAZIONE.setValue( param );
    }

    public void  setUrlS_STATO( String param ) {
        this.urlS_STATO.setValue( param );
    }

    public void  setUrlS_STATO( Object param ) {
        this.urlS_STATO.setValue( param );
    }

    public void  setUrlS_STATO( Object param, Format ignore ) {
        this.urlS_STATO.setValue( param );
    }

    public void  setUrlS_RICERCA( String param ) {
        this.urlS_RICERCA.setValue( param );
    }

    public void  setUrlS_RICERCA( Object param ) {
        this.urlS_RICERCA.setValue( param );
    }

    public void  setUrlS_RICERCA( Object param, Format ignore ) {
        this.urlS_RICERCA.setValue( param );
    }

    public ToolboxRow[] getRows() {
        return rows;
    }

    public void setRows(ToolboxRow[] rows) {
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

//constructor @418-E6028CE1
    public ToolboxDataObject(Page page) {
        super(page);
    }
//End constructor

//load @418-6FEB8CCF
    boolean load() {
        boolean isErrors = false;
        JDBCConnection ds = JDBCConnectionFactory.getJDBCConnection( "cn" );
        ds.setLocale(page.getCCSLocale().getLocale());
        RawCommand command = new RawCommand( ds );

        command.setSql( "select '<a class=\"AFCToolBox\" id=\"linkRicerca\" title=\"Impostazione condizioni di ricerca.\" href=\"javascript:show();\">'|| "
                    + "         '<img class=\"AFCToolBoxlink\" height=\"18\"  width=\"18\" border=\"0\"'|| "
                    + "            'title=\"'||decode('{s_TERMINALE}', "
                    + "                               null, "
                    + "                               decode('{s_UTENTE}', "
                    + "                                       null, "
                    + "                                       decode('{s_ISTANZA}', "
                    + "                                               null, "
                    + "                                               decode('{s_MODULO}', "
                    + "                                                       null, "
                    + "                                                       decode('{s_DB_USER}', "
                    + "                                                               null, "
                    + "                                                               decode('{s_DATA_DA}', "
                    + "                                                                       null, "
                    + "                                                                       decode('{s_DATA_A}', "
                    + "                                                                               null, "
                    + "                                                                               decode('{s_NOTE}', "
                    + "                                                                                       null, "
                    + "                                                                                       decode('{s_AUTENTICAZIONE}', "
                    + "                                                                                               null, "
                    + "                                                                                               decode('{s_STATO}', "
                    + "                                                                                                       null, "
                    + "                                                                                                       decode('{s_TIPO}', "
                    + "                                                                                                               null, "
                    + "                                                                                                               'Filtro non attivo', "
                    + "                                                                                                               'Filtro attivo' "
                    + "                                                                                                             ), "
                    + "                                                                                                       'Filtro attivo' "
                    + "                                                                                                     ), "
                    + "                                                                                               'Filtro attivo' "
                    + "                                                                                             ), "
                    + "                                                                                       'Filtro attivo' "
                    + "                                                                                     ), "
                    + "                                                                               'Filtro attivo' "
                    + "                                                                             ), "
                    + "                                                                       'Filtro attivo' "
                    + "                                                                     ), "
                    + "                                                               'Filtro attivo' "
                    + "                                                             ), "
                    + "                                                       'Filtro attivo' "
                    + "                                                     ), "
                    + "                                               'Filtro attivo' "
                    + "                                             ), "
                    + "                                       'Filtro attivo' "
                    + "                                     ), "
                    + "                                    'Filtro attivo' "
                    + "                                  ) "
                    + "          ||'\" alt=\"'||decode('{s_TERMINALE}', "
                    + "                               null, "
                    + "                               decode('{s_UTENTE}', "
                    + "                                       null, "
                    + "                                       decode('{s_ISTANZA}', "
                    + "                                               null, "
                    + "                                               decode('{s_MODULO}', "
                    + "                                                       null, "
                    + "                                                       decode('{s_DB_USER}', "
                    + "                                                               null, "
                    + "                                                               decode('{s_DATA_DA}', "
                    + "                                                                       null, "
                    + "                                                                       decode('{s_DATA_A}', "
                    + "                                                                               null, "
                    + "                                                                               decode('{s_NOTE}', "
                    + "                                                                                       null, "
                    + "                                                                                       decode('{s_AUTENTICAZIONE}', "
                    + "                                                                                               null, "
                    + "                                                                                               decode('{s_STATO}', "
                    + "                                                                                                       null, "
                    + "                                                                                                       decode('{s_TIPO}', "
                    + "                                                                                                               null, "
                    + "                                                                                                               'Filtro non attivo', "
                    + "                                                                                                               'Filtro attivo' "
                    + "                                                                                                             ), "
                    + "                                                                                                       'Filtro attivo' "
                    + "                                                                                                     ), "
                    + "                                                                                               'Filtro attivo' "
                    + "                                                                                             ), "
                    + "                                                                                       'Filtro attivo' "
                    + "                                                                                     ), "
                    + "                                                                               'Filtro attivo' "
                    + "                                                                             ), "
                    + "                                                                       'Filtro attivo' "
                    + "                                                                     ), "
                    + "                                                               'Filtro attivo' "
                    + "                                                             ), "
                    + "                                                       'Filtro attivo' "
                    + "                                                     ), "
                    + "                                               'Filtro attivo' "
                    + "                                             ), "
                    + "                                       'Filtro attivo' "
                    + "                                     ), "
                    + "                               'Filtro attivo' "
                    + "                             ) "
                    + "		       	  ||'\" height=\"18\"' "
                    + "           ||' src=\"'||decode('{s_TERMINALE}', "
                    + "                               null, "
                    + "                               decode('{s_UTENTE}', "
                    + "                                       null, "
                    + "                                       decode('{s_ISTANZA}', "
                    + "                                               null, "
                    + "                                               decode('{s_MODULO}', "
                    + "                                                       null, "
                    + "                                                       decode('{s_DB_USER}', "
                    + "                                                               null, "
                    + "                                                               decode('{s_DATA_DA}', "
                    + "                                                                       null, "
                    + "                                                                       decode('{s_DATA_A}', "
                    + "                                                                               null, "
                    + "                                                                               decode('{s_NOTE}', "
                    + "                                                                                       null, "
                    + "                                                                                       decode('{s_AUTENTICAZIONE}', "
                    + "                                                                                               null, "
                    + "                                                                                               decode('{s_STATO}', "
                    + "                                                                                                       null, "
                    + "                                                                                                       decode('{s_TIPO}', "
                    + "                                                                                                               null, "
                    + "                                                                                                               '../common/images/AMV/lente.gif', "
                    + "                                                                                                               '../common/images/AD4/lentef.gif' "
                    + "                                                                                                             ), "
                    + "                                                                                                       '../common/images/AD4/lentef.gif' "
                    + "                                                                                                     ), "
                    + "                                                                                               '../common/images/AD4/lentef.gif' "
                    + "                                                                                             ), "
                    + "                                                                                       '../common/images/AD4/lentef.gif' "
                    + "                                                                                     ), "
                    + "                                                                               '../common/images/AD4/lentef.gif' "
                    + "                                                                             ), "
                    + "                                                                       '../common/images/AD4/lentef.gif' "
                    + "                                                                     ), "
                    + "                                                               '../common/images/AD4/lentef.gif' "
                    + "                                                             ), "
                    + "                                                       '../common/images/AD4/lentef.gif' "
                    + "                                                     ), "
                    + "                                               '../common/images/AD4/lentef.gif' "
                    + "                                             ), "
                    + "                                       '../common/images/AD4/lentef.gif' "
                    + "                                     ),							    "
                    + "                               '../common/images/AD4/lentef.gif') "
                    + "        ||'\">'|| "
                    + "       'Ricerca</a>' ricerca, "
                    + "       decode(count(1),0,'','<a class=\"AFCToolBox\" id=\"linkEliminaArch\" title=\"Eliminazione accessi archiviati su file.\" href=\"javascript:confermaElimina()\"><img class=\"\" id=\"imgRicerca\" title=\"Eliminazione accessi archiviati su file.\" height=\"18\" alt=\"Eliminazione accessi archiviati su file.\" src=\"../common/images/AD4/elimina.gif\" width=\"18\" border=\"0\">Elimina Archiviati</a>') elimina_archiviati "
                    + "  from accessi "
                    + " where nvl(stato,'U') = 'A'" );
        if ( StringUtils.isEmpty( (String) urlS_TIPO.getObjectValue() ) ) urlS_TIPO.setValue( "" );
        command.addParameter( "s_TIPO", urlS_TIPO, null );
        if ( StringUtils.isEmpty( (String) urlS_TERMINALE.getObjectValue() ) ) urlS_TERMINALE.setValue( "" );
        command.addParameter( "s_TERMINALE", urlS_TERMINALE, null );
        if ( StringUtils.isEmpty( (String) urlS_UTENTE.getObjectValue() ) ) urlS_UTENTE.setValue( "" );
        command.addParameter( "s_UTENTE", urlS_UTENTE, null );
        if ( StringUtils.isEmpty( (String) urlS_ISTANZA.getObjectValue() ) ) urlS_ISTANZA.setValue( "" );
        command.addParameter( "s_ISTANZA", urlS_ISTANZA, null );
        if ( StringUtils.isEmpty( (String) urlS_MODULO.getObjectValue() ) ) urlS_MODULO.setValue( "" );
        command.addParameter( "s_MODULO", urlS_MODULO, null );
        if ( StringUtils.isEmpty( (String) urlS_DB_USER.getObjectValue() ) ) urlS_DB_USER.setValue( "" );
        command.addParameter( "s_DB_USER", urlS_DB_USER, null );
        if ( StringUtils.isEmpty( (String) urlS_DATA_DA.getObjectValue() ) ) urlS_DATA_DA.setValue( "" );
        command.addParameter( "s_DATA_DA", urlS_DATA_DA, null );
        if ( StringUtils.isEmpty( (String) urlS_DATA_A.getObjectValue() ) ) urlS_DATA_A.setValue( "" );
        command.addParameter( "s_DATA_A", urlS_DATA_A, null );
        if ( StringUtils.isEmpty( (String) urlS_NOTE.getObjectValue() ) ) urlS_NOTE.setValue( "" );
        command.addParameter( "s_NOTE", urlS_NOTE, null );
        if ( StringUtils.isEmpty( (String) urlS_AUTENTICAZIONE.getObjectValue() ) ) urlS_AUTENTICAZIONE.setValue( "" );
        command.addParameter( "s_AUTENTICAZIONE", urlS_AUTENTICAZIONE, null );
        if ( StringUtils.isEmpty( (String) urlS_STATO.getObjectValue() ) ) urlS_STATO.setValue( "" );
        command.addParameter( "s_STATO", urlS_STATO, null );
        if ( StringUtils.isEmpty( (String) urlS_RICERCA.getObjectValue() ) ) urlS_RICERCA.setValue( "N" );
        command.addParameter( "s_RICERCA", urlS_RICERCA, null );
        command.setCountSql( "SELECT COUNT(*) FROM ( select '<a class=\"AFCToolBox\" id=\"linkRicerca\" title=\"Impostazione condizioni di ricerca.\" href=\"javascript:show();\">'|| '<img class=\"AFCToolBoxlink\" height=\"18\" width=\"18\" border=\"0\"'|| 'title=\"'||decode('{s_TERMINALE}', null, decode('{s_UTENTE}', null,  "
                                                         + "            decode('{s_ISTANZA}', null, decode('{s_MODULO}', null, decode('{s_DB_USER}', null,  "
                                                         + "            decode('{s_DATA_DA}', null, decode('{s_DATA_A}', null,  "
                                                         + "            decode('{s_NOTE}', null, decode('{s_AUTENTICAZIONE}', null, decode('{s_STATO}',  "
                                                         + "            null, decode('{s_TIPO}', null, 'Filtro non attivo',  "
                                                         + "            'Filtro attivo' ), 'Filtro attivo' ), 'Filtro attivo' ), 'Filtro attivo' ),  "
                                                         + "            'Filtro attivo' ), 'Filtro attivo' ), 'Filtro attivo' ), 'Filtro attivo' ),  "
                                                         + "            'Filtro attivo' ), 'Filtro attivo' ),  "
                                                         + "            'Filtro attivo' ) ||'\" alt=\"'||decode('{s_TERMINALE}', null, decode('{s_UTENTE}', null,  "
                                                         + "            decode('{s_ISTANZA}', null, decode('{s_MODULO}', null, decode('{s_DB_USER}',  "
                                                         + "            null, decode('{s_DATA_DA}', null, decode('{s_DATA_A}', null,  "
                                                         + "            decode('{s_NOTE}', null, decode('{s_AUTENTICAZIONE}', null,  "
                                                         + "            decode('{s_STATO}', null, decode('{s_TIPO}', null, 'Filtro non attivo',  "
                                                         + "            'Filtro attivo' ), 'Filtro attivo' ), 'Filtro attivo' ), 'Filtro attivo' ),  "
                                                         + "            'Filtro attivo' ), 'Filtro attivo' ), 'Filtro attivo' ),  "
                                                         + "            'Filtro attivo' ), 'Filtro attivo' ), 'Filtro attivo' ),  "
                                                         + "            'Filtro attivo' ) 		 	 ||'\" height=\"18\"' ||' src=\"'||decode('{s_TERMINALE}', null,  "
                                                         + "            decode('{s_UTENTE}', null, decode('{s_ISTANZA}', null,  "
                                                         + "            decode('{s_MODULO}', null, decode('{s_DB_USER}', null, decode('{s_DATA_DA}', null,  "
                                                         + "            decode('{s_DATA_A}', null, decode('{s_NOTE}', null,  "
                                                         + "            decode('{s_AUTENTICAZIONE}', null, decode('{s_STATO}', null, decode('{s_TIPO}', null,  "
                                                         + "            '../common/images/AMV/lente.gif',  "
                                                         + "            '../common/images/AD4/lentef.gif' ), '../common/images/AD4/lentef.gif' ),  "
                                                         + "            '../common/images/AD4/lentef.gif' ), '../common/images/AD4/lentef.gif' ),  "
                                                         + "            '../common/images/AD4/lentef.gif' ), '../common/images/AD4/lentef.gif' ),  "
                                                         + "            '../common/images/AD4/lentef.gif' ), '../common/images/AD4/lentef.gif' ),  "
                                                         + "            '../common/images/AD4/lentef.gif' ),  "
                                                         + "            '../common/images/AD4/lentef.gif' ),							 '../common/images/AD4/lentef.gif') ||'\">'|| 'Ricerca</a>' ricerca, decode(count(1),0,'','<a class=\"AFCToolBox\" id=\"linkEliminaArch\" title=\"Eliminazione accessi archiviati su file.\" href=\"javascript:confermaElimina()\"><img class=\"\" id=\"imgRicerca\" title=\"Eliminazione accessi archiviati su file.\" height=\"18\" alt=\"Eliminazione accessi archiviati su file.\" src=\"../common/images/AD4/elimina.gif\" width=\"18\" border=\"0\">Elimina Archiviati</a>') elimina_archiviati from accessi where nvl(stato,'U') = 'A' ) cnt " );
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

//loadDataBind @418-A6134EAF
        if ( records == null || ! records.hasMoreElements() ) {
            empty = true;
        } else {
            int counter = 0;
            while ( counter < rows.length && records.hasMoreElements() ) {
                ToolboxRow row = new ToolboxRow();
                DbRow record = (DbRow) records.nextElement();
                row.setRICERCA(Utils.convertToString(ds.parse(record.get("RICERCA"), row.getRICERCAField())));
                row.setELIMINA_ARCHIVIATI(Utils.convertToString(ds.parse(record.get("ELIMINA_ARCHIVIATI"), row.getELIMINA_ARCHIVIATIField())));
                rows[counter++] = row;
            }
        }
//End loadDataBind

//End of load @418-B1C63002
        isErrors = ds.hasErrors();
        if ( isErrors ) addErrors( ds.getErrors() );
        return ( ! isErrors );
    }
//End End of load

//getParameterByName @418-9A4218CE
        public Parameter getParameterByName(String name, ParameterSource parameterSource) {
            Parameter param = null;
            String prefix = (parameterSource == null ? null : parameterSource.getPrefix());
            if ( "s_TIPO".equals(name) && "url".equals(prefix) ) {
                param = urlS_TIPO;
            } else if ( "s_TIPO".equals(name) && prefix == null ) {
                param = urlS_TIPO;
            }
            if ( "s_TERMINALE".equals(name) && "url".equals(prefix) ) {
                param = urlS_TERMINALE;
            } else if ( "s_TERMINALE".equals(name) && prefix == null ) {
                param = urlS_TERMINALE;
            }
            if ( "s_UTENTE".equals(name) && "url".equals(prefix) ) {
                param = urlS_UTENTE;
            } else if ( "s_UTENTE".equals(name) && prefix == null ) {
                param = urlS_UTENTE;
            }
            if ( "s_ISTANZA".equals(name) && "url".equals(prefix) ) {
                param = urlS_ISTANZA;
            } else if ( "s_ISTANZA".equals(name) && prefix == null ) {
                param = urlS_ISTANZA;
            }
            if ( "s_MODULO".equals(name) && "url".equals(prefix) ) {
                param = urlS_MODULO;
            } else if ( "s_MODULO".equals(name) && prefix == null ) {
                param = urlS_MODULO;
            }
            if ( "s_DB_USER".equals(name) && "url".equals(prefix) ) {
                param = urlS_DB_USER;
            } else if ( "s_DB_USER".equals(name) && prefix == null ) {
                param = urlS_DB_USER;
            }
            if ( "s_DATA_DA".equals(name) && "url".equals(prefix) ) {
                param = urlS_DATA_DA;
            } else if ( "s_DATA_DA".equals(name) && prefix == null ) {
                param = urlS_DATA_DA;
            }
            if ( "s_DATA_A".equals(name) && "url".equals(prefix) ) {
                param = urlS_DATA_A;
            } else if ( "s_DATA_A".equals(name) && prefix == null ) {
                param = urlS_DATA_A;
            }
            if ( "s_NOTE".equals(name) && "url".equals(prefix) ) {
                param = urlS_NOTE;
            } else if ( "s_NOTE".equals(name) && prefix == null ) {
                param = urlS_NOTE;
            }
            if ( "s_AUTENTICAZIONE".equals(name) && "url".equals(prefix) ) {
                param = urlS_AUTENTICAZIONE;
            } else if ( "s_AUTENTICAZIONE".equals(name) && prefix == null ) {
                param = urlS_AUTENTICAZIONE;
            }
            if ( "s_STATO".equals(name) && "url".equals(prefix) ) {
                param = urlS_STATO;
            } else if ( "s_STATO".equals(name) && prefix == null ) {
                param = urlS_STATO;
            }
            if ( "s_RICERCA".equals(name) && "url".equals(prefix) ) {
                param = urlS_RICERCA;
            } else if ( "s_RICERCA".equals(name) && prefix == null ) {
                param = urlS_RICERCA;
            }
            return param;
        }

        public Parameter getParameterByName(String name) {
            return getParameterByName( name, null );
        }
//End getParameterByName

//addGridDataObjectListener @418-B1E4C7C7
    public synchronized void addDataObjectListener( DataObjectListener l ) {
        listeners.addElement(l);
    }
//End addGridDataObjectListener

//removeGridDataObjectListener @418-9F30CEFB
    public synchronized void removeDataObjectListener( DataObjectListener l ) {
        listeners.removeElement(l);
    }
//End removeGridDataObjectListener

//fireBeforeBuildSelectEvent @418-238A81BB
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

//fireBeforeExecuteSelectEvent @418-9DA7B025
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

//fireAfterExecuteSelectEvent @418-F7E8A616
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

//class DataObject Tail @418-ED3F53A4
} // End of class DS
//End class DataObject Tail

