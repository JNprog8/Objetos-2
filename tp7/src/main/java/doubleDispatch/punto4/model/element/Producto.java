package doubleDispatch.punto4.model.element;

import doubleDispatch.punto4.model.visitor.Cliente;

public interface Producto {

    float precio();

    float costoEnvio(Cliente cliente);

    float impuesto(Cliente cliente);
}
