package com.pdm.talktothehand;

/**
 * Created by hugo on 12/06/16.
 */
public class Sala {
    private String _id;
    private String nombre;
    private String descripcion;
    private String fecha;

    public Sala(String _id, String nombre, String descripcion, String fecha) {
        this._id = _id;
        this.nombre=nombre;
        this.descripcion = descripcion;
        this.fecha=fecha;

    }



    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


}



