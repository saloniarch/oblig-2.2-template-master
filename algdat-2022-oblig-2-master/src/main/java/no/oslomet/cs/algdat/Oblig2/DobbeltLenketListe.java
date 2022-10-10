package no.oslomet.cs.algdat.Oblig2;


////////////////// class DobbeltLenketListe //////////////////////////////


import java.sql.SQLOutput;
import java.util.Comparator;
import java.util.Iterator;
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
        throw new UnsupportedOperationException();
    }

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
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean inneholder(T verdi) {
        throw new UnsupportedOperationException();
    }

    public Node<T> finnNode(int indeks){
        int i = 0;
        if(indeks < antall/2){
            Node nesteverdi = hode.neste;
           while(i < indeks){
               nesteverdi = nesteverdi.neste;
               i ++;
           }
        } else{
            Node forrigeverdi = hale.forrige;
            while(i<indeks){
                forrigeverdi = forrigeverdi.forrige;
                i--;

            }
        }
        return finnNode(indeks);
    }

    @Override
    public T hent(int indeks) {

    }

    @Override
    public int indeksTil(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T oppdater(int indeks, T nyverdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean fjern(T verdi) {
        throw new UnsupportedOperationException();
    }

    @Override
    public T fjern(int indeks) {
        throw new UnsupportedOperationException();
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


