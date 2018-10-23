package com.vpr.pokemon.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

public class Util {
	
	//esta informacion genera documentacion. Escribiendo /**
	/**
	 * Copia una imagen desde una ruta de origen a una carpeta de iconos que se encuentra en el
	 * proyecto de la aplicación con el nombre que queramos
	 * @param rutaOrigen Ruta de la imagen
	 * @param nombre Nombre asignado a la copia de la imagen
	 * @throws IOException 
	 */
	public static void copiarImagen(String rutaOrigen, String nombreDestino) throws IOException {
		Path origen = FileSystems.getDefault().getPath(rutaOrigen);
		FileOutputStream destino = new FileOutputStream(
				new File(System.getProperty("user.dir") + File.separator + "icons" + File.separator+ nombreDestino)
				);
		
		Files.copy(origen, destino);
	}
}
