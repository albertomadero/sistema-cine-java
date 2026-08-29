package org.example.modelo;

public abstract class Asiento implements Identificable{
    private int idAsiento;
    private String numeroAsiento;
    private int idSala;

    protected Asiento(int idAsiento, String numeroAsiento, int idSala) {
        this.idAsiento = idAsiento;
        this.numeroAsiento = numeroAsiento;
        this.idSala = idSala;
    }

    public int getIdAsiento() {
        return idAsiento;
    }

    public String getNumeroAsiento() {
        return numeroAsiento;
    }

    public int getIdSala() {
        return idSala;
    }

    public abstract double calcularPrecio(double precioBase);

    @Override
    public int getId() {
        return getIdAsiento();
    }
}
