package examen.actividades.repository;

import examen.actividades.model.Actividad;
import examen.actividades.model.TipoActividad;
import examen.actividades.model.ActividadPresencial;
import examen.actividades.model.ActividadVirtual;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class RepositorioActividadTxt implements Repositorio<Actividad>{

    private String rutaArchivo;

    public RepositorioActividadTxt(String rutaArchivo){
        this.rutaArchivo = rutaArchivo;
    }

    @Override
    public List<Actividad> cargarTodos(){
        List<Actividad> actividades = new ArrayList<>();

        Path ruta = Paths.get(rutaArchivo);

        if(!Files.exists(ruta)){
            return actividades;
        }

        try{
            List<String> lineas = Files.readAllLines(ruta);
            for(String linea : lineas){
                if(!linea.trim().isEmpty()){
                    actividades.add(convertirDesdeLinea(linea));
                }
            }return actividades;
        }catch(IOException e){
            throw new RuntimeException("Error al leer el archivo" + e.getMessage(), e);
        }
    }

    @Override
    public void guardarTodos(List<Actividad> elementos) {

        List<String> lineas = new ArrayList<>();

        for (Actividad actividad : elementos) {
            lineas.add(convertirALinea(actividad));
        }

        Path ruta = Paths.get(rutaArchivo);

        try{
            Files.write(ruta,lineas);
        }catch(IOException e){
            throw new RuntimeException("Error al escribir el archivo: " + e.getMessage(), e);
        }
    }

    private String convertirALinea(Actividad actividad){
        return actividad.getTipo() +";"+ actividad.getCodigo()
                +";"+ actividad.getNombre() +";"+ actividad.getTarifaBase()
                +";"+ actividad.getCupoTotal() +";"+ actividad.getInscritos();
    }

    private String convertirDesdeLinea(String linea){
        String[] campos = linea.split(";",-1);

        TipoActividad tipo = TipoActividad.valueOf(campos[0]);

        String codigo = campos[1];
        String nombre = campos[2];
        double tarifaBase = Double.parseDouble(campos[3]);
        int cupoTotal = Integer.parseInt(campos[4]);
        int inscritos = Integer.parseInt(campos[5]);

        if(tipo == TipoActividad.PRESENCIAL){
            return new ActividadPresencial(codigo, nombre, tarifaBase, cupoTotal, inscritos);
        }else{
            return new ActividadVirtual(codigo, nombre, tarifaBase, cupoTotal, inscritos);
        }

    }
}
