//ZonaAslComuneImpostaRow: import @5-042DF5B7
package ad4web.Ad4DizionariZonaAslComuneImposta;

import java.util.*;
import com.codecharge.db.*;
//End ZonaAslComuneImpostaRow: import

//ZonaAslComuneImpostaRow: class head @5-F1158150
public class ZonaAslComuneImpostaRow {
//End ZonaAslComuneImpostaRow: class head

//ZonaAslComuneImpostaRow: declare fiels @5-76F91741
    private TextField TITOLO = new TextField("TITOLO", "TITOLO");
    private LongField ID_ZONA_ASL = new LongField("ID_ZONA_ASL", "ID_ZONA_ASL");
    private TextField COMUNE_DESC = new TextField("COMUNE_DESC", "COMUNE_DESC");
    private TextField COMUNE_LOV = new TextField("COMUNE_LOV", "COMUNE_LOV");
    private LongField PROVINCIA = new LongField("PROVINCIA", "PROVINCIA");
    private LongField COMUNE = new LongField("COMUNE", "COMUNE");
//End ZonaAslComuneImpostaRow: declare fiels

//ZonaAslComuneImpostaRow: constructor @5-F4E9C478
    public ZonaAslComuneImpostaRow() {
    }
//End ZonaAslComuneImpostaRow: constructor

//ZonaAslComuneImpostaRow: method(s) of TITOLO @6-FB48796E
    public TextField getTITOLOField() {
        return TITOLO;
    }

    public String getTITOLO() {
        return TITOLO.getValue();
    }

    public void setTITOLO(String value) {
        this.TITOLO.setValue(value);
    }
//End ZonaAslComuneImpostaRow: method(s) of TITOLO

//ZonaAslComuneImpostaRow: method(s) of ID_ZONA_ASL @52-E418A8CB
    public LongField getID_ZONA_ASLField() {
        return ID_ZONA_ASL;
    }

    public Long getID_ZONA_ASL() {
        return ID_ZONA_ASL.getValue();
    }

    public void setID_ZONA_ASL(Long value) {
        this.ID_ZONA_ASL.setValue(value);
    }
//End ZonaAslComuneImpostaRow: method(s) of ID_ZONA_ASL

//ZonaAslComuneImpostaRow: method(s) of COMUNE_DESC @10-E6E222F6
    public TextField getCOMUNE_DESCField() {
        return COMUNE_DESC;
    }

    public String getCOMUNE_DESC() {
        return COMUNE_DESC.getValue();
    }

    public void setCOMUNE_DESC(String value) {
        this.COMUNE_DESC.setValue(value);
    }
//End ZonaAslComuneImpostaRow: method(s) of COMUNE_DESC

//ZonaAslComuneImpostaRow: method(s) of COMUNE_LOV @53-ECEAD2C3
    public TextField getCOMUNE_LOVField() {
        return COMUNE_LOV;
    }

    public String getCOMUNE_LOV() {
        return COMUNE_LOV.getValue();
    }

    public void setCOMUNE_LOV(String value) {
        this.COMUNE_LOV.setValue(value);
    }
//End ZonaAslComuneImpostaRow: method(s) of COMUNE_LOV

//ZonaAslComuneImpostaRow: method(s) of PROVINCIA @49-A3592314
    public LongField getPROVINCIAField() {
        return PROVINCIA;
    }

    public Long getPROVINCIA() {
        return PROVINCIA.getValue();
    }

    public void setPROVINCIA(Long value) {
        this.PROVINCIA.setValue(value);
    }
//End ZonaAslComuneImpostaRow: method(s) of PROVINCIA

//ZonaAslComuneImpostaRow: method(s) of COMUNE @50-532A21D6
    public LongField getCOMUNEField() {
        return COMUNE;
    }

    public Long getCOMUNE() {
        return COMUNE.getValue();
    }

    public void setCOMUNE(Long value) {
        this.COMUNE.setValue(value);
    }
//End ZonaAslComuneImpostaRow: method(s) of COMUNE

//ZonaAslComuneImpostaRow: class tail @5-FCB6E20C
}
//End ZonaAslComuneImpostaRow: class tail

