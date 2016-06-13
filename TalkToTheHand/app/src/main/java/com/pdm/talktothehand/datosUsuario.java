package com.pdm.talktothehand;


public class datosUsuario {
    private static String nombre_usuario;
    private static String dni;


    public static String getNombre_usuario() {
        return nombre_usuario;
    }

    public static void setNombre_usuario(String nombre_usuario) {
        datosUsuario.nombre_usuario = nombre_usuario;
    }
    public static String getDni() {
        return dni;
    }

    public static void setDni(String dni) {
        datosUsuario.dni = dni;
    }
}
