package it.unibo.databaseplatform.data;

public class Piatto {

    private final String codicePiatto;
    private final String nomePiatto;
    private final float prezzoPorzione;
    private final int apportoCaloricoPorzione;
    private final String codicePortata;

    public Piatto(String codicePiatto, String nomePiatto, float prezzoPorzione, int apportoCaloricoPorzione, String codicePortata){
        this.codicePiatto = codicePiatto;
        this.nomePiatto = nomePiatto;
        this.prezzoPorzione = prezzoPorzione;
        this.apportoCaloricoPorzione = apportoCaloricoPorzione;
        this.codicePortata = codicePortata;
    }
}
