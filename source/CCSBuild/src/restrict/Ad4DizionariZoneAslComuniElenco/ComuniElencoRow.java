//ComuniElencoRow: import @8-37C168A1
package restrict.Ad4DizionariZoneAslComuniElenco;

import java.util.*;
import com.codecharge.db.*;
//End ComuniElencoRow: import

//ComuniElencoRow: class head @8-D377F9E5
public class ComuniElencoRow {
//End ComuniElencoRow: class head

//ComuniElencoRow: declare fiels @8-2066EBE2
    private TextField COMUNE_DESC = new TextField("COMUNE_DESC", "COMUNE_DESC");
    private TextField AFCNavigator = new TextField("AFCNavigator", "AFCNavigator");
    private TextField COMUNE = new TextField("COMUNE", "COMUNE");
    private TextField PROVINCIA = new TextField("PROVINCIA", "PROVINCIA");
    private TextField ID_ZONA_ASL = new TextField("ID_ZONA_ASL", "ID_ZONA_ASL");
//End ComuniElencoRow: declare fiels

//ComuniElencoRow: constructor @8-EEFA7C7A
    public ComuniElencoRow() {
    }
//End ComuniElencoRow: constructor

//ComuniElencoRow: method(s) of COMUNE_DESC @13-E6E222F6
    public TextField getCOMUNE_DESCField() {
        return COMUNE_DESC;
    }

    public String getCOMUNE_DESC() {
        return COMUNE_DESC.getValue();
    }

    public void setCOMUNE_DESC(String value) {
        this.COMUNE_DESC.setValue(value);
    }
//End ComuniElencoRow: method(s) of COMUNE_DESC

//ComuniElencoRow: method(s) of AFCNavigator @28-B6FE7CCE
    public TextField getAFCNavigatorField() {
        return AFCNavigator;
    }

    public String getAFCNavigator() {
        return AFCNavigator.getValue();
    }

    public void setAFCNavigator(String value) {
        this.AFCNavigator.setValue(value);
    }
//End ComuniElencoRow: method(s) of AFCNavigator

//ComuniElencoRow: method(s) of COMUNE @15-B41017A6
    public TextField getCOMUNEField() {
        return COMUNE;
    }

    public String getCOMUNE() {
        return COMUNE.getValue();
    }

    public void setCOMUNE(String value) {
        this.COMUNE.setValue(value);
    }
//End ComuniElencoRow: method(s) of COMUNE

//ComuniElencoRow: method(s) of PROVINCIA @30-0A4A498E
    public TextField getPROVINCIAField() {
        return PROVINCIA;
    }

    public String getPROVINCIA() {
        return PROVINCIA.getValue();
    }

    public void setPROVINCIA(String value) {
        this.PROVINCIA.setValue(value);
    }
//End ComuniElencoRow: method(s) of PROVINCIA

//ComuniElencoRow: method(s) of ID_ZONA_ASL @38-1A368EE5
    public TextField getID_ZONA_ASLField() {
        return ID_ZONA_ASL;
    }

    public String getID_ZONA_ASL() {
        return ID_ZONA_ASL.getValue();
    }

    public void setID_ZONA_ASL(String value) {
        this.ID_ZONA_ASL.setValue(value);
    }
//End ComuniElencoRow: method(s) of ID_ZONA_ASL

//ComuniElencoRow: class tail @8-FCB6E20C
}
//End ComuniElencoRow: class tail

