package examen.actividades.repository;
import java.util.List;

public interface Repositorio<T>{

    List<T> cargarTodos();
    void guardarTodos(List<T> elementos);

}
