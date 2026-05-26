package punto3.model.concreteDecorator;

import punto3.model.component.Exportable;
import punto3.model.decorator.ReportDecorator;

import java.io.File;

public class ConSobreescritura extends ReportDecorator {

    public ConSobreescritura(Exportable component) {
        super(component);
    }

    @Override
    public void export(File file) {
        // delega el componente que ya realiza la escritura/sobrescritura por defecto.
        // decorador explícito para el requerimiento  pqueermitir sobrescribir.
        super.export(file);
    }
    /**
     * PROXIMOS PASOS
     * 2. Lo dejo como un decorador que simplemente deja explicito la intención,
     * aunque la delegación es directa.
     */
}
