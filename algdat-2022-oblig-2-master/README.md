# Obligatorisk oppgave 2 i Algoritmer og Datastrukturer

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:
* Navn Navnesen, S123456, s123456@oslomet.no
* ...

# Arbeidsfordeling

I oppgaven har vi hatt følgende arbeidsfordeling:
* Per har hatt hovedansvar for oppgave 1, 3, og 5. 
* Else har hatt hovedansvar for oppgave 2, 4, og 6. 
* Fatima har hatt hovedansvar for oppgave 7 og 8. 
* Vi har i fellesskap løst oppgave 10. 

# Oppgavebeskrivelse

I oppgave 1 så gikk jeg frem ved å først lage metodene antall og boolean tom. Den første returnerer antall i listen, mens den
andre returnerer true om lista er tom, og false om lista ikke er tom. Etter det så lagde jeg konstruktøren
til en dobbelt lenket liste a. Om listen er tom så kaster den feilmelding. Ellers så sjekker vi at hode pekeren ligger foran i listen og
peker neste, og halen ligger bakerst og peker siste noden. Her tar vi tilfellene om lista inneholder flere null verdier, om lista er tom, eller om den ikke er det.

I oppgave 2 så brukte jeg en metode til å lage en tegnstreng med listens verider ved hjelp av
StringBuilder. Etter det så lagde jeg en lik metode bare at denne skulle returnere veridene i omvendt rekkefølge. Istedenfor å starte
fra hodet, så startet jeg fra halen.
Jeg lagde en boolean metode for å legge inn verdier til lista. Først sjekker jeg om det er null verdier som skal legges til,
og da bruker jeg en reqquiereNonNull slik at dette ikke skjer. Om hodet og halen er begge null så er listen tom og da legger vi
den nye verdien mellom hodet og halen. Ellers om de begge ikke er null så vil det si at de "holder" en node så man trenger ikke å initialisere
variabelen. Ellers så blir det lagt til mellom to noder om lista ikke er tom. I alle disse tilfellene så får man returnert true.

I oppgave 3 lagde jeg finnNode(int indeks) metoden. Den skal finne Noden med indeksen i lista. Jeg tok en if setning som sjekker om indeks er mindre enn antall delt på 2, da leter den fra venstre side, altså fra hodet,
og peker til neste til den finner indeksen man ser etter. Hvis den er større enn antall/2 så skal letingen
starte fra halen (høyre side) og gå til neste med forrige peker. Så returnerer den noden etter den blir funnet.
Så lagde jeg oppdater metoden ved å lage en verdi kalt gammelVerdi for å lagre gammel noden før den blir erstattet med T nyverdi. Dette skjer om
indeks er mindre enn antall og større enn 0. Listen blir endret derfor tar vi endringer++.

I oppgave 4  lagde jeg metoden int indeksTil(T verdi), som returnerer indeksen hvor verdien er lik verdien som man ser etter. Om verdien er null eller om hodet.neste er null så skal det bli returnert -1.
Ellers så går den gjennom en for løkke slik at den går fra hodet til neste node til man finner indeksen og den blir returnert. Mens på boolean inneholder(T verdi) så skal true eller false bli returnert
om verdien finnes i listen eller ikke. Da bruker vi indeksTil for å finne indeksen til verdien, om den ikke returnerer -1 som vi satt istad, så skal 
den returnere true siden da finnes indeksen. 

I oppgave 5 lagde jeg metoden void leggInn(int indeks, T verdi). Om indeks er mindre enn 0,
større enn antall eller verdien er lik null så kastes det en feilmelding. Ellers hvis antall er 0 så legges det til verdien mellom
hodet og halen. Om indeksen er 0 så legges verdien fra hodet. Om indeksen er lik antall i lista så legges det til fra halen. Hvis det ikke er noen av de tilfellene 
da vil det si at det blir lagt til mellom to noder og da lagde jeg to variabler left og right slik at jeg kan lagre den nye noden mellom de. Antall og endringer blir økt.

I oppgave 6 lagde jeg to fjern metoder. T fjern(int indeks) fjerner (og returnere) verdien på posisjon indeks, mens den andre skal fjerne verdi fra listen og returnere true.
Siden koden på begge var like så valgte jeg å lage en private metode som kunne tilpasset seg til begge metodene.
istedenfor å ha indeks eller verdi i parameteren så hadde jeg <T> slik at man kan bruke det til integer, string, osv.
Hvis antall er en da peker både hode og hale til null. Hvis Node<T> p equals hode.neste fjerner vi da fra hodet, om den er lik halen.forrige så fjerner vi fra halen. Om det er ingen av tilfellene
så skal det bli fjernet fra mellom to noder.
Antall er mindre siden vi holder på å fjerne, mens endringer øker siden lista blir endret.
Da kan vi kalle denne private metoden både på fjern indeksen og fjern verdien.

I oppgave 8 lagde jeg flere metoder. Det er en denne peker som peker til en node og next metoden 
flytter den til neste node dersom den finnes. Det returnerer også verdien til forrige noden. Next metoden sjekker først om iteratorendringer ikke er null endring
og hasNext ikke finnes da kaster det en feilmelding . Hvis denne.verdi er lik null da peker den til denne.neste, dette gjorde jeg
for å unngå en feil jeg fikk før jeg implementerte den if setningen. Da lager jeg en førDenne Node som holder denne slik at jeg henter verdien senere.
Og da peker denne til denne.neste. Metoden skal da returnere verdien til førDenne. Jeg måtte låne oppgave 7 midlertidig fra en kamerat for å sjekke om oppgave 8 kjørte riktig, siden oppgave 7 ikke var en del av oppgavene
jeg skulle gjøre.