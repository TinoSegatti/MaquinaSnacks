package maquina_sanks_archivos.servicio;

import maquina_sanks_archivos.dominio.Snack;

import java.util.List;

/**
 * Interfaz para los servicios de gestion de snacks
 * Esta interfaz permite desacoplar la capa de presentacion de la forma en
 * que se almacenan los datos
 */
public interface IServicioSnacks {

    void agregarSnack(Snack snack);

    void mostrarSnacks();

    List<Snack> getSnacks();

}
