//MessagesBundle class @0-B07A16B5
import java.util.ListResourceBundle;

/** Message Recource Bundle. */
public class MessagesBundle extends ListResourceBundle {
  public Object[][] getContents() {
    return contents;
  }
  static final Object[][] contents = {
        {"IncorrectFormat", "Il valore nel campo {0} non � corretto, usa il seguente formato: {1}"},
        {"IncorrectValue", "Il valore nel campo {0} non � corretto."},
        {"RequiredField", "Il valore nel campo {0} � richiesto."},
        {"UniqueValue", "Il valore nel campo {0} � gi� presente nel database."},
        {"MaskValidation", "Validazione errata per il campo {0}."},
        {"IncorrectEmailFormat", "Errato formato email nel campo {0}."},
        {"MaximumLength", "La lunghezza del campo  {0} non pu� essere pi� grande di {1} symbols."},
        {"MaximumValue", "Il valore nel campo {0} non pu� essere pi� grande di {1}."},
        {"MinimumLength", "La lunghezza del campo {0} non pu� essere pi� piccola di {1} symbols."},
        {"MinimumValue", "Il valore nel campo {0} non pu� essere minore di {1}."},
        {"IncorrectPhoneFormat", "Invalid phone number format in field {0}."},
        {"IncorrectZipFormat", "Invalid zip code format in field {0}."},
        {"LoginError", "Login or Password errati."},
        {"RequiredFieldUpload", "Il file attachment nel campo {0} � obbligatorio."},
        {"LargeFile", "La dimensione del file nel campo {0} � troppo grande."},
        {"WrongType", "Il tipo di file specificato nel campo {0} non � permesso."},
        {"UploadingError", "Errore nel caricamento del file specificato in {0}. Descrizione errore: {1}."},
        {"FileNotFound", "Il file {0} specificato in {1} non esiste."},
        {"InsufficientPermissions", "Privilegi insufficienti sul filesystem per caricare il file specificato in {0}."},
        {"FilesFolderNotFound", "Impossibile caricare il file specificato in {0} - la cartella di upload non esiste."},
        {"TempInsufficientPermissions", "Privilegi insufficienti sul filesystem per caricare il file specificato in {0} nella cartella temporanea."},
        {"TempFolderNotFound", "Impossibile caricare il file specificato in {0} - la cartella temporanea di upload non esiste."},
        {"FirstWeekDay", "Dom"},
        {"ShortWeekdays", "Dom, Lun, Mar, Mer, Gio, Ven, Sab"},
        {"Weekdays", "Domenica, Luned�, Marted�, Mercoled�, Gioved�, Venerd�, Sabato"},
        {"ShortMonths", "Gen, Feb, Mar, Apr, Mag, Giu, Lug, Ago, Set, Ott, Nov, Dic"},
        {"Months", "Gennaio, Febbraio, Marzo, Aprile, Maggio, Giugno, Luglio, AGosto, Settembre, Ottobre, Novembre, Dicembre"},
        {"InsertLink", "Nuovo"},
        {"Insert", "Inserisci"},
        {"Update", "Aggiorna"},
        {"Delete", "Elimina"},
        {"Cancel", "Annulla"},
        {"Search", "Cerca"},
        {"NoRecords", "Nessuna registrazione"},
        {"First", "Primo"},
        {"Previous", "Prec."},
        {"Next", "Pross."},
        {"Last", "Ultimo"},
        {"LoginBtn", "Login"},
        {"LogoutBtn", "Logout"},
        {"Login", "Login"},
        {"Password", "Password"},
        {"SortBy", "Ordina per"},
        {"SortDir", "Tipo ordinamento"},
        {"RecPerPage", "Registrazioni per pagina"},
        {"CustomLinkField", "Dettaglio"},
        {"SelectField", "Seleziona campo"},
        {"SelectOrder", "Seleziona ordinamento"},
        {"SelectValue", " - -"},
        {"Filter", "Parola chiave"},
        {"SearchFormPrefix", "Cerca"},
        {"GridFormPrefix", "Elenco"},
        {"RecordFormPrefix", "Aggiornamento"},
        {"RecordFormPrefix2", "Visualizza"},
        {"SearchFormPostfix", ""},
        {"GridFormPostfix", ""},
        {"RecordFormPostfix", ""},
        {"Of", "di"},
        {"ASC", "Ascendente"},
        {"DESC", "Discendente"},
        {"RememberLogin", "Memorizza Username e Password"},
        {"DeleteConfirmation", "Eliminare la registrazione?"},
        {"SubmitConfirmation", "Inviare le registrazioni?"},
        {"Clear", "Clear"},
        {"NoCategories", "Nessuna categories trovata"},
        {"Main", "Main"},
        {"More", "More..."},
        {"Today", "Today"},
        {"TotalRecords", "Totale Registrazioni:"},
        {"DatePickerNav61", "Il componente Date Picker non � compatible con Netscape 6.1"},
        {"Bytes", "bytes"},
        {"UploadComponentNotFound", "il componente di upload %s  \"\"%s\"\" non � stato trovato. Selezionarne un altro o installare il componente."},
        {"UploadComponentError", "Errore in fase di inizializzazione del componente di upload."},
        {"FieldValue", "The value in field "},
        {"FileField", "The file in field "},
        {"FileType", "The file type specified in field "},
        {"NotAllowed", " is not allowed."},
        {"Required", " is required."},
        {"Unique", " is already in database."},
        {"Large", " is too large."},
        {"MaskMatch", "Mask validation failed for field "},
        {"SSLError", "SSL connection error. This page can be accessed only via secured connection."}
    };
}
//End MessagesBundle class

