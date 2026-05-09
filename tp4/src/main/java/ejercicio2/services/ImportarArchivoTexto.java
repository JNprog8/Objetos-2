package ejercicio2.services;

import ejercicio2.models.Empleado;
import ejercicio2.models.Importar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ImportarArchivoTexto implements Importar {
    private String path;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    public ImportarArchivoTexto(String path) {
        this.path = path;
    }

    @Override
    public List<Empleado> importarEmpleados() {
        List<Empleado> empleados = new ArrayList<>();
        try (
                BufferedReader br = new BufferedReader(new FileReader(path))
        ) {
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                String apellido = partes[0].trim();
                String nombre = partes[1].trim();
                LocalDate fecha = LocalDate.parse(partes[2].trim(), FORMATTER);
                String mail = partes[3].trim();
                empleados.add(new Empleado(apellido, nombre, fecha, mail));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return empleados;
    }
}
