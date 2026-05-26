package punto3.model.concreteDecorator;

import punto3.model.component.Exportable;
import punto3.model.decorator.ReportDecorator;

import java.io.File;

public class SinSobreescritura extends ReportDecorator {

    private static final String ERROR_FILE_EXISTENTE = "El archivo ya existe y no se permite sobrescribir.";

    public SinSobreescritura(Exportable component) {
        super(component);
    }

    private void validarExistencia(File file) {
        if (file.exists()) {
            throw new IllegalArgumentException(ERROR_FILE_EXISTENTE);
        }
    }

    @Override
    public void export(File file) {
        validarExistencia(file);
        super.export(file);
    }
}
