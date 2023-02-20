//AD4_CARATTERISTICHE_ACCESSOHandler Head @38-69C7252A
package ad4web.AD4CopiaUtenti;
import java.util.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
public class AD4_CARATTERISTICHE_ACCESSORecordHandler implements RecordListener {
//End AD4_CARATTERISTICHE_ACCESSOHandler Head

//BeforeShow Head @38-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//Set values @38-0EED6F6E
        if (! e.getRecord().isEditMode()) {
            e.getRecord().getControl("ACCESSO_SE").setDefaultValue("NO");
            e.getRecord().getControl("ACCESSO").setDefaultValue("L");
            e.getRecord().getControl("TRACCIA").setDefaultValue("M");
            e.getRecord().getControl("GIORNI_TRACCIA").setDefaultValue(60);
            e.getRecord().getControl("SPOSTA_FILE_ARCH").setDefaultValue(0);
            e.getRecord().getControl("ARCHIVIAZIONE_TRACCIA").setDefaultValue("NO");
            e.getRecord().getControl("VALIDITA_PASSWORD").setDefaultValue(60);
            e.getRecord().getControl("MOD_PWD_PRIMO_ACCESSO").setDefaultValue("NO");
            e.getRecord().getControl("AMMESSI_CAR_SPECIALI_PWD").setDefaultValue("SI");
            e.getRecord().getControl("NUMERI_OBB_PWD").setDefaultValue("NO");
            e.getRecord().getControl("LDAP").setDefaultValue("NO");
            e.getRecord().getControl("SINGLE_SIGN_ON").setDefaultValue("NO");
        }
//End Set values

//BeforeShow Tail @38-FCB6E20C
    }
//End BeforeShow Tail

//OnValidate Head @38-5F430F8E
    public void onValidate(Event e) {
//End OnValidate Head

//OnValidate Tail @38-FCB6E20C
    }
//End OnValidate Tail

//BeforeSelect Head @38-E5EC9AD3
    public void beforeSelect(Event e) {
//End BeforeSelect Head

//BeforeSelect Tail @38-FCB6E20C
    }
//End BeforeSelect Tail

//BeforeInsert Head @38-75B62B83
    public void beforeInsert(Event e) {
//End BeforeInsert Head

//BeforeInsert Tail @38-FCB6E20C
    }
//End BeforeInsert Tail

//AfterInsert Head @38-767A9165
    public void afterInsert(Event e) {
//End AfterInsert Head

//AfterInsert Tail @38-FCB6E20C
    }
//End AfterInsert Tail

//BeforeUpdate Head @38-33A3CFAC
    public void beforeUpdate(Event e) {
//End BeforeUpdate Head

//BeforeUpdate Tail @38-FCB6E20C
    }
//End BeforeUpdate Tail

//AfterUpdate Head @38-306F754A
    public void afterUpdate(Event e) {
//End AfterUpdate Head

//AfterUpdate Tail @38-FCB6E20C
    }
//End AfterUpdate Tail

//BeforeDelete Head @38-752E3118
    public void beforeDelete(Event e) {
//End BeforeDelete Head

//BeforeDelete Tail @38-FCB6E20C
    }
//End BeforeDelete Tail

//AfterDelete Head @38-76E28BFE
    public void afterDelete(Event e) {
//End AfterDelete Head

//AfterDelete Tail @38-FCB6E20C
    }
//End AfterDelete Tail

//AD4_CARATTERISTICHE_ACCESSOHandler Tail @38-FCB6E20C
}
//End AD4_CARATTERISTICHE_ACCESSOHandler Tail

