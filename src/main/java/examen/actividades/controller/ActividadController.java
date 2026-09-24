package examen.actividades.controller;

import examen.actividades.model.Actividad;
import examen.actividades.model.TipoActividad;
import examen.actividades.repository.RepositorioActividadTxt;
import examen.actividades.service.ActividadService;
import examen.actividades.view.VentanaActividades;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
/** Conexión inicial proporcionada; las acciones del examen están pendientes. */
public class ActividadController {
    private ActividadService service;
    private final VentanaActividades vista;

    public ActividadController(VentanaActividades vista) {
        this.vista = vista;


        RepositorioActividadTxt repositorio = new RepositorioActividadTxt(Actividades.txt);

        service = new ActividadService(repositorio);
        conectarEventos();
    }

    public void iniciar(){
        DefaultComboBoxModel<TipoActividad> modelo = new DefaultComboBoxModel<>();
        modelo.addElement(TipoActividad.PRESENCIAL);
        modelo.addElement(TipoActividad.VIRTUAL);

        vista.cbmTipo.setModel(modelo);

        try{
            service.cargarDatos();
            mostrarTodas();
        }catch(exeption e) {

        }




    }

    // TODO: crear el servicio y programar iniciar() y las seis acciones.
    // Los listeners deben delegar en los métodos del controlador.
    // La lógica de archivos pertenece al repositorio, no a esta clase.
}
