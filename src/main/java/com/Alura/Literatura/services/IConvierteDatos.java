
package com.Alura.Literatura.services;

public interface IConvierteDatos {

    <T> T obtenerDatos(String json, Class<T> clase);
}



