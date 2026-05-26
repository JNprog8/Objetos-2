package punto3.model.decorator;

import punto3.model.component.Exportable;

import java.io.File;

public abstract class ReportDecorator implements Exportable {
    private Exportable component;

    protected ReportDecorator(Exportable component) {
        this.component = component;
    }

    @Override
    public void export(File file) {
        component.export(file);
    }
}
