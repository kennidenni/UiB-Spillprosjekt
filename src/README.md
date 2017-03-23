## Hvordan generere Javadocs?
Uavhengig av metoden man bruker skal mappen `build/docs/` bli generert med
dokumentasjonen som innhold.

### Med Gradle ###
Naviger til prosjektmappen i kommandolinjen. Skriv inn følgende:

    ./gradlew javadoc

### Med Eclipse ###
Åpne *Gradle Tasks*-panelet hvis det ikke er åpent:

    Window->Show View->Other...->Gradle->Gradle Tasks

Høyreklikk og kjør følgende i *Gradle Tasks*-panelet:

    inf112api/documentation/javadoc
