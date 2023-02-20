//toolbox_gridRow: import @120-F2CF7305
package ad4web.AD4LdapConfig;

import java.util.*;
import com.codecharge.db.*;
//End toolbox_gridRow: import

//toolbox_gridRow: class head @120-5E74BD01
public class toolbox_gridRow {
//End toolbox_gridRow: class head

//toolbox_gridRow: declare fiels @120-07E7F757
    private TextField TIPO_SERVER = new TextField("TIPO_SERVER", "TIPO_SERVER");
    private TextField CHIAVE = new TextField("CHIAVE", "CHIAVE");
    private TextField Nuovo = new TextField("Nuovo", "");
    private TextField CREA_ALTERNATIVO = new TextField("CREA_ALTERNATIVO", "CREA_ALTERNATIVO");
    private TextField ELIMINA_ALTERNATIVO = new TextField("ELIMINA_ALTERNATIVO", "ELIMINA_ALTERNATIVO");
    private TextField ID = new TextField("ID", "CHIAVE");
//End toolbox_gridRow: declare fiels

//toolbox_gridRow: constructor @120-4D0DDF30
    public toolbox_gridRow() {
    }
//End toolbox_gridRow: constructor

//toolbox_gridRow: method(s) of TIPO_SERVER @129-B4F09F08
    public TextField getTIPO_SERVERField() {
        return TIPO_SERVER;
    }

    public String getTIPO_SERVER() {
        return TIPO_SERVER.getValue();
    }

    public void setTIPO_SERVER(String value) {
        this.TIPO_SERVER.setValue(value);
    }
//End toolbox_gridRow: method(s) of TIPO_SERVER

//toolbox_gridRow: method(s) of CHIAVE @132-2CA70E12
    public TextField getCHIAVEField() {
        return CHIAVE;
    }

    public String getCHIAVE() {
        return CHIAVE.getValue();
    }

    public void setCHIAVE(String value) {
        this.CHIAVE.setValue(value);
    }
//End toolbox_gridRow: method(s) of CHIAVE

//toolbox_gridRow: method(s) of Nuovo @122-42611BD0
    public TextField getNuovoField() {
        return Nuovo;
    }

    public String getNuovo() {
        return Nuovo.getValue();
    }

    public void setNuovo(String value) {
        this.Nuovo.setValue(value);
    }
//End toolbox_gridRow: method(s) of Nuovo

//toolbox_gridRow: method(s) of CREA_ALTERNATIVO @130-2D224AC6
    public TextField getCREA_ALTERNATIVOField() {
        return CREA_ALTERNATIVO;
    }

    public String getCREA_ALTERNATIVO() {
        return CREA_ALTERNATIVO.getValue();
    }

    public void setCREA_ALTERNATIVO(String value) {
        this.CREA_ALTERNATIVO.setValue(value);
    }
//End toolbox_gridRow: method(s) of CREA_ALTERNATIVO

//toolbox_gridRow: method(s) of ELIMINA_ALTERNATIVO @131-26142ABF
    public TextField getELIMINA_ALTERNATIVOField() {
        return ELIMINA_ALTERNATIVO;
    }

    public String getELIMINA_ALTERNATIVO() {
        return ELIMINA_ALTERNATIVO.getValue();
    }

    public void setELIMINA_ALTERNATIVO(String value) {
        this.ELIMINA_ALTERNATIVO.setValue(value);
    }
//End toolbox_gridRow: method(s) of ELIMINA_ALTERNATIVO

//toolbox_gridRow: method(s) of ID @124-2B895796
    public TextField getIDField() {
        return ID;
    }

    public String getID() {
        return ID.getValue();
    }

    public void setID(String value) {
        this.ID.setValue(value);
    }
//End toolbox_gridRow: method(s) of ID

//toolbox_gridRow: class tail @120-FCB6E20C
}
//End toolbox_gridRow: class tail

