package maquina_sanks_archivos.dominio;

import java.io.Serializable;
import java.util.Objects;


 // Representa un snack (producto) dentro de la maquina de snacks
 
public class Snack implements Serializable {

    // Contador estatico utilizado para generar IDs unicos por instancia
    private static int contadorSnack = 0;

    // Atributos de la entidad de dominio
    private int idSnack;
    private String nameSnack;
    private double priceSnack;

    // Constructor por defecto.
    // Asigna un identificador unico incremental a cada snack creado.
    public Snack() {
        this.idSnack = ++Snack.contadorSnack;
    }

    // Constructor completo.
    public Snack(String nameSnack, double priceSnack) {
        // Se hace un llamado al constructor vacio para la asignacion del id
        this(); // Siempre debe ser la primera linea
        this.nameSnack = nameSnack;
        this.priceSnack = priceSnack;
    }

    public static int getContadorSnack() {
        return contadorSnack;
    }

    public int getIdSnack() {
        return idSnack;
    }

    public String getNameSnack() {
        return nameSnack;
    }

    public void setNameSnack(String nameSnack) {
        this.nameSnack = nameSnack;
    }

    public double getPriceSnack() {
        return priceSnack;
    }

    public void setPriceSnack(double priceSnack) {
        this.priceSnack = priceSnack;
    }

    // Representacion legible del snack
    // Se utiliza para mostrar el inventario en consola
    @Override
    public String toString() {
        return "Snack{" +
                "idSnack=" + idSnack +
                ", nameSnack='" + nameSnack + '\'' +
                ", priceSnack=" + priceSnack +
                '}';
    }

    
    // Devuelve el snack en formato CSV (id,nombre,precio)
    // Formato que se persiste en el archivo de texto
    
    public String escribirSnack() {
        return idSnack + "," + nameSnack + "," + priceSnack;
    }

    
    // Implementacion de igualdad basada en id, nombre y precio
    // Util para comparar instancias dentro de colecciones
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Snack snack = (Snack) o;
        return idSnack == snack.idSnack
                && Double.compare(priceSnack, snack.priceSnack) == 0
                && Objects.equals(nameSnack, snack.nameSnack);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSnack, nameSnack, priceSnack);
    }
}
