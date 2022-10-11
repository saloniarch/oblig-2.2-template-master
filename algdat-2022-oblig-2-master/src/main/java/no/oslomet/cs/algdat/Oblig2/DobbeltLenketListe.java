package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.sql.SQLOutput;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;


public class DobbeltLenketListe<T> implements Liste<T> {

    /**
     * Node class
     *
     * @param <T>
     */
    private static final class Node<T> {
        private T verdi;                   // nodens verdi
        private Node<T> forrige, neste;    // pekere

        private Node(T verdi, Node<T> forrige, Node<T> neste) {
            this.verdi = verdi;
            this.forrige = forrige;
            this.neste = neste;
        }

        private Node(T verdi) {
            this(verdi, null, null);
        }
    }

    // instansvariabler
    private Node<T> hode;          // peker til den første i listen
    private Node<T> hale;          // peker til den siste i listen
    private int antall;            // antall noder i listen
    private int endringer;         // antall endringer i listen

    public DobbeltLenketListe() {
        antall = 0;
        endringer = 0;
        hode = hale = null;
    }

    public DobbeltLenketListe(T[] a) {
        if (a == null) {
            throw new NullPointerException("Tabellen a er null");
        }
        hode = new Node<>(null,null,null);
        hale = new Node<>(null,null,null);
        Node current = hode;
        Node forrige = null;
        for (int i =0; i < a.length; i++){
             if(a[i] != null) {
                 current = new Node(a[i], null, null);
                 if (hode.neste == null){
                     hode.neste = current;
                 } else {
                     forrige.neste = current;
                     current.forrige = forrige;
                 }
                 forrige = current;
                 antall++;

        }

        }
        hale.forrige = current;
    }


    public Liste<T> subliste(int fra, int til) {

        fratilKontroll(antall, fra, til);
        DobbeltLenketListe nyListe = new DobbeltLenketListe();
        int i;
        for(i = fra; i < til; i++){
            nyListe.leggInn(hent(i));
        }
        return nyListe;
    }
    //START KOMPENDIET
    private static void fratilKontroll(int antall, int fra, int til) {
        if (fra < 0)                                  // fra er negativ
            throw new IndexOutOfBoundsException("fra(" + fra + ") er negativ!");

        if (til > antall)                          // til er utenfor tabellen
            throw new IndexOutOfBoundsException("til(" + til + ") > antall(" + antall + ")");

        if (fra > til)                                // fra er større enn til
            throw new IllegalArgumentException("fra(" + fra + ") > til(" + til + ") - illegalt intervall!");
    }
    //SLUTT KOMPENDIET

    @Override
    public int antall() {
        return antall;
    }
    @Override
    public boolean tom() {
        if (antall == 0){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean leggInn(T verdi) {
        Objects.requireNonNull(verdi);
        if (hode == null && hale == null) {
            Node nyNode = new Node(verdi, null, null);
            hode = new Node<>(null, null, nyNode);
            hale = new Node<>(null, nyNode, null);
            antall++;
            endringer++;

            return true;

        } else if(hode !=null && hale != null){
            Node<T> nyverdi = new Node<>(verdi, null, null);
            Node forrigeverdi = hale.forrige;
                forrigeverdi.neste = nyverdi;
                hale.forrige = nyverdi;
                nyverdi.forrige = forrigeverdi;
                antall++;
                endringer++;
        }
        return false;
    }



    @Override
    public void leggInn(int indeks, T verdi) {
        if(indeks < 0 || indeks > antall){
            throw new IndexOutOfBoundsException("Indeks kan ikke være mindre enn 0 eller større enn antall");
        } else if( verdi == null){
            throw new NullPointerException("verdien kan ikke være tom");
        } else if (antall == 0){
            hode = new Node<>(null);
            hale = new Node<>(null);
            Node node1 = new Node<>(verdi);
            hale.forrige = node1;
            hode.neste = node1;
        } else if(indeks == 0){
            Node gammelNode = hode.neste;
            Node nyNode = new Node<>(verdi, null, gammelNode);
            gammelNode.forrige = nyNode;
            hode.neste = nyNode;
        } else if(indeks == antall){
            Node gammelNode = hale.forrige;
            Node nyNode = new Node<>(verdi, gammelNode, null);
            gammelNode.neste = nyNode;
            hale.forrige = nyNode;
        } else {
            Node left = finnNode(indeks-1);
            Node right = finnNode(indeks);
            Node nyNode = new Node<>(verdi,left, right);
            left.neste = nyNode;
            nyNode.neste = right;
            right.forrige = nyNode;
        }
        antall++;
        endringer++;
    }

    @Override
    public boolean inneholder(T verdi) {
        if(indeksTil(verdi) != -1){
            return true;
        } else return false;
    }

    public Node<T> finnNode(int indeks){

        if(hode== null && hode.neste == null){
            throw new NoSuchElementException("Hode er null");
        } else {
            Node node1;

            if(indeks < antall/2){
                int i = 0;
                node1 = hode.neste;
                while(i < indeks) {
                    node1 = node1.neste;
                    i++;
                }
            }
            else {
                int i = antall;
                node1 = hale;
                while (i > indeks) {
                    node1 = node1.forrige;
                    i--;
                }
            }
            return node1;
        }
    }

    @Override
    public T hent(int indeks) {
        indeksKontroll(indeks, false);
        return finnNode(indeks).verdi;
    }

    @Override
    public int indeksTil(T verdi) {
        int indeks = -1;
        Node current;
        if (verdi == null) {
            return -1;
        }
        else {
            if (hode != null && hode.neste != null) {
                current = hode;
            } else {
                return -1;
            }
        }
        for (int i = indeks; i < antall; i++){
            if (!(verdi.equals(current.verdi))) {
                current = current.neste;
            } else {
                indeks = i;
                break;
            }
        }
        return indeks;
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        if (nyverdi == null){
            throw new NullPointerException("Verdien er null");
        } else {
            indeksKontroll(indeks, false);

            Node gammelNode = finnNode(indeks);
            T gammelVerdi = (T) gammelNode.verdi;
            gammelNode.verdi = nyverdi;


            endringer++;
            return (T) gammelVerdi ;
        }
    }


    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
    }

    @Override
    public void nullstill() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
      StringBuilder ord = new StringBuilder("[");
      Node current;
      if (hode != null && hode.neste != null) {
           current = hode.neste;
      } else {
          return "[]";
      }
          while (current.neste != null) {
              ord.append(current.verdi).append(", ");
              current = current.neste;
          }

          ord.append(current.verdi + "]");


        return ord.toString();
    }

    public String omvendtString() {
        StringBuilder omvendt = new StringBuilder("[");
        Node current;
        if (hale != null && hale.forrige != null) {
            current = hale.forrige;
        } else {
                return "[]";
            }
            while (current.forrige !=null) {
            omvendt.append(current.verdi).append(", ");
            current = current.forrige;
        }
            omvendt.append(current.verdi).append("]");

        return omvendt.toString();
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException();
    }

    public Iterator<T> iterator(int indeks) {
        throw new UnsupportedOperationException();
    }

    private class DobbeltLenketListeIterator implements Iterator<T> {
        private Node<T> denne;
        private boolean fjernOK;
        private int iteratorendringer;

        private DobbeltLenketListeIterator() {
            denne = hode;     // p starter på den første i listen
            fjernOK = false;  // blir sann når next() kalles
            iteratorendringer = endringer;  // teller endringer
        }

        private DobbeltLenketListeIterator(int indeks) {
            throw new UnsupportedOperationException();
        }

        @Override
        public boolean hasNext() {
            return denne != null;
        }

        @Override
        public T next() {
            throw new UnsupportedOperationException();
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }

    } // class DobbeltLenketListeIterator

    public static <T> void sorter(Liste<T> liste, Comparator<? super T> c) {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        String[] s = {"Ole", null, "Per", "Kari", null};
        Liste<String> liste = new DobbeltLenketListe<>(s);
        System.out.println(liste.antall() + " " + liste.tom());
    }
} // class DobbeltLenketListe


