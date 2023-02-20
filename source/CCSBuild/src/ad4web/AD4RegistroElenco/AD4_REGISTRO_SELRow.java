//AD4_REGISTRO_SELRow: import @10-1AC2A0C7
package ad4web.AD4RegistroElenco;

import java.util.*;
import com.codecharge.db.*;
//End AD4_REGISTRO_SELRow: import

//AD4_REGISTRO_SELRow: class head @10-F83CCA21
public class AD4_REGISTRO_SELRow {
//End AD4_REGISTRO_SELRow: class head

//AD4_REGISTRO_SELRow: declare fiels @10-59DADE98
    private TextField NOME_SEZIONE = new TextField("NOME_SEZIONE", "NOME");
//End AD4_REGISTRO_SELRow: declare fiels

//AD4_REGISTRO_SELRow: constructor @10-65726BD8
    public AD4_REGISTRO_SELRow() {
    }
//End AD4_REGISTRO_SELRow: constructor

//AD4_REGISTRO_SELRow: method(s) of NOME_SEZIONE @11-DF48CFA4
    public TextField getNOME_SEZIONEField() {
        return NOME_SEZIONE;
    }

    public String getNOME_SEZIONE() {
        return NOME_SEZIONE.getValue();
    }

    public void setNOME_SEZIONE(String value) {
        this.NOME_SEZIONE.setValue(value);
    }
//End AD4_REGISTRO_SELRow: method(s) of NOME_SEZIONE

//AD4_REGISTRO_SELRow: class tail @10-FCB6E20C
}
//End AD4_REGISTRO_SELRow: class tail

