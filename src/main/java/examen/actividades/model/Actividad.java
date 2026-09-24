package examen.actividades.model;

public abstract class Actividad {
     private String codigo;
     private String nombre;
     private double tarifaBase;
     private int cupoTotal = 0;
     private int inscritos = 0;

     public Actividad(String codigo, String nombre, double tarifaBase, int cupoTotal, int inscritos){
         validarTexto(codigo, "El codigo: ");
         validarTexto(nombre, "El nombre: ");

         if(Double.isFinite(tarifaBase) || tarifaBase<=0){
             throw new IllegalArgumentException("La tarifa base debe de ser mayor que cero");
         }

         if(cupoTotal<=0){
             throw new IllegalArgumentException("El cupo tot6al debe ser mayor a cero");
         }

         if(inscritos<=0){
             throw new IllegalArgumentException("El numero de personas inscritas debe de estar entre cero y la cantidad de cupos");
         }

         this.codigo = codigo.trim();
         this.nombre = nombre.trim();
         this.tarifaBase = tarifaBase;
         this.cupoTotal = cupoTotal;
         this.inscritos = inscritos;
     }

     private void validarTexto(String texto, String campo){
         if(texto == null){
             throw new IllegalArgumentException(campo + "no puede estar vacio");
         }

         if(texto.contains(";")){
             throw new IllegalArgumentException(campo + "no puede contener punto y coma");
         }

         if(texto.contains("\n") || texto.contains("\r")){
             throw new IllegalArgumentException(campo + "no puede contener saltos de linea");
         }
     }

    public String getCodigo(){
         return codigo;
    }
    public String getNombre(){
         return nombre;
    }
    public double getTarifaBase(){
         return tarifaBase;
    }
    public int getCupoTotal(){
         return cupoTotal;
    }
    public int getInscritos(){
         return inscritos;
    }


    public int getCuposDisponibles(){
        return cupoTotal- inscritos;
    }

    public void inscribir(){
         if(inscritos >= cupoTotal){
             throw new IllegalArgumentException("No hay cupos disponibles para esta actividad");
         }
         inscritos++;
    }

    public abstract double calcularTarifaFinal();

    public abstract TipoActividad getTipo();


}
