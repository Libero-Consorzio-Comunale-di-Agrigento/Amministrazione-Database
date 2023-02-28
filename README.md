# Amministrazione-Database
AD4 - Amministrazione del database
 
## Descrizione
Prodotto atto al censimento degli utenti e alla gestione di autenticazione e autorizzazione.

## Struttura del Repository

Il repository è suddiviso nelle seguente cartelle:
- __source__ contiene il codice sorgente e le risorse statiche incluse nella webapp.
- __scriptDB__ contiene gli script PL/SQL per la creazione della struttura dello schema database.
- __physicalDataModel__ contiene i diagrammi Entità-Relazione in formato html.

## Prerequisiti e dipendenze

### Prerequisiti
- Java JDK versione 5 o superiore
- Database Oracle versione 10 o superiore
- Apache Tomcat 7.0 dalla minor 47 alla 109

### Dipendenze
- Apache ANT versione 1.5 o superiore per la compilazione dei sorgenti
- Libreria _finmatica-jfcccs.jar_ di Finmatica che contiene utility varie
- Libreria _sa4filter.jar_ di Finmatica per la manipolazione degli header HTTP
- Libreria _sa4thread.jar_ di Finmatica che sincronizza le sessioni HTTP con le sessioni Oracle
- Libreria _sa4sanitizer.jar_ di Finmatica per la sanificazione degli input
- Log4j di Apache Software Foundation per il loggin
- JSoup libreria opens-source per la manipolazione del DOM 
- JSR305 Annotations for Findbugs di Google (dipendenza di JSoup)
- Guava set di librerie Google per java (dipendenza di JSoup)
- Libreria _ojdbc.jar_ driver oracle per Java di Oracle
## Istruzioni per l’installazione:

- Lanciare gli script della cartella _scriptDB/Schema_ per generare lo schema
- Lanciare gli script della cartella _scriptDB/Data_ per inserire i dati basilari
- Lanciare il comando _ant.bat -buildfile "PATH\CCSBuild\build.xml" -logfile "PATH\CCSBuild\build.log"_  per generare la webapp e copiare i file nel contesto di tomcat.

## Stato del progetto 
Stabile

## Amministrazione committente
Libero Consorzio Comunale di Agrigento

## Incaricati del mantenimento del progetto open source
Finmatica S.p.A. 
Via della Liberazione, 15
40128 Bologna

## Indirizzo e-mail a cui inviare segnalazioni di sicurezza 
sicurezza@ads.it