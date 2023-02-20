//AD4_UTENTIHandler Head @6-366B5310
package ad4web.AD4Utente;
import java.util.*;
import com.codecharge.*;
import com.codecharge.db.*;
import com.codecharge.components.*;
import com.codecharge.events.*;
import com.codecharge.util.*;
public class AD4_UTENTIRecordHandler implements RecordListener {
//End AD4_UTENTIHandler Head

//BeforeShow Head @6-46046458
    public void beforeShow(Event e) {
//End BeforeShow Head

//Set values @6-DE1340D4
        e.getRecord().getLink("SVUOTA_REG_ANAGRAFICA").getParameter("SOGGETTO").setValue(( -1 ));
        if (! e.getRecord().isEditMode()) {
            e.getRecord().getControl("PWD_MODIFIED").setDefaultValue("N");
            e.getRecord().getControl("RINNOVO_PASSWORD").setDefaultValue("SI");
            e.getRecord().getControl("STATO").setDefaultValue("U");
            e.getRecord().getControl("LINGUA").setDefaultValue("I");
            e.getRecord().getControl("GRUPPO_LAVORO").setDefaultValue("def");
            e.getRecord().getControl("AMMINISTRATORE").setDefaultValue("N");
        }
//End Set values

//Event BeforeShow Action Save Control Value @159-3430900C
        SessionStorage.getInstance(e.getPage().getRequest()).setAttribute("AD4UTENTE", ((com.codecharge.components.Hidden) ((com.codecharge.components.Record) (e.getPage().getChild( "AD4_UTENTI" ))).getChild( "UTENTE" )).getValue() );
//End Event BeforeShow Action Save Control Value

//BeforeShow Tail @6-FCB6E20C
    }
//End BeforeShow Tail

//OnValidate Head @6-5F430F8E
    public void onValidate(Event e) {
//End OnValidate Head

//OnValidate Tail @6-FCB6E20C
    }
//End OnValidate Tail

//BeforeSelect Head @6-E5EC9AD3
    public void beforeSelect(Event e) {
//End BeforeSelect Head

//BeforeSelect Tail @6-FCB6E20C
    }
//End BeforeSelect Tail

//BeforeInsert Head @6-75B62B83
    public void beforeInsert(Event e) {
//End BeforeInsert Head

//BeforeInsert Tail @6-FCB6E20C
    }
//End BeforeInsert Tail

//AfterInsert Head @6-767A9165
    public void afterInsert(Event e) {
//End AfterInsert Head

//AfterInsert Tail @6-FCB6E20C
    }
//End AfterInsert Tail

//BeforeUpdate Head @6-33A3CFAC
    public void beforeUpdate(Event e) {
//End BeforeUpdate Head

//BeforeUpdate Tail @6-FCB6E20C
    }
//End BeforeUpdate Tail

//AfterUpdate Head @6-306F754A
    public void afterUpdate(Event e) {
//End AfterUpdate Head

//AfterUpdate Tail @6-FCB6E20C
    }
//End AfterUpdate Tail

//BeforeDelete Head @6-752E3118
    public void beforeDelete(Event e) {
//End BeforeDelete Head

//BeforeDelete Tail @6-FCB6E20C
    }
//End BeforeDelete Tail

//AfterDelete Head @6-76E28BFE
    public void afterDelete(Event e) {
//End AfterDelete Head

//AfterDelete Tail @6-FCB6E20C
    }
//End AfterDelete Tail

//AD4_UTENTIHandler Tail @6-FCB6E20C
}
//End AD4_UTENTIHandler Tail

