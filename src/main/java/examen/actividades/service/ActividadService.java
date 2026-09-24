package examen.actividades.service;

import examen.actividades.model.ActividadPresencial;
import examen.actividades.model.ActividadVirtual;
import examen.actividades.model.Actividad;
import examen.actividades.model.TipoActividad;
import examen.actividades.repository.Repositorio;

import java.util.ArrayList;
import java.util.List;

public class ActividadService {

    private List<Actividad> actividades;
    private Repositorio<Actividad> repositorio;

    public ActividadService(Repositorio<Actividad> repositorio){
        this.repositorio = repositorio;
        this.actividades= new ArrayList<>();
    }
    public void registrarActividad(String codigo, String nombre, TipoActividad tipo, double tarifaBase, int cupoTotal) {
        if (tipo == null) {
            throw new IllegalArgumentException("Debe seleccionar un tipo de actividad");
        }
        String codigoNormalizado = codigo == null ? "" : codigo.trim();

        if(codigoNormalizado.isEmpty()){
            throw new IllegalArgumentException("El codigo no puede estar vacio");
        }

        if(buscarPorCodigo(codigoNormalizado) != null){
            throw new IllegalArgumentException("Ya existe una actividad con este codigo");
        }

        Actividad nueva;

        if(TipoActividad.PRESENCIAL){
            nueva = new ActividadPresencial(codigoNormalizado, nombre, tarifaBase, cupoTotal,0);
        }else{
            nueva = new ActividadVirtual(codigoNormalizado, nombre, tarifaBase, cupoTotal, 0);
        }

        actividades.add(nueva);
    }

    public Actividad buscarPorCodigo(String codigo) {
        if (codigo == null || codigo.trim().isEmpty()) {
            throw new IllegalArgumentException("El codigo no puede estar vacio");
        }
        String codigoBuscado = codigo.trim();

        for(Actividad actividad : actividades){
            if(actividad.getCodigo().trim().equalsIgnoreCase(codigoBuscado)){
                return actividad;
            }
        }
        return null;
    }

    public List<Actividad> listarActividades(){
        return new ArrayList<>(actividades);
    }

    public void inscribir(String codigo){
        Actividad actividad = buscarPorCodigo(codigo);

        if(actividad == null){
            throw new IllegalArgumentException("No existe una actividad con ese codigo");
        }
        actividad.inscribir();
    }

    public void cargarDatos(){
        List<Actividad> datos = repositorio.cargarTodos();
        actividades = new ArrayList<>(datos);
    }

    public void guardarDatos(){
        repositorio.guardarTodos(actividades);
    }

}
