package observer.punto5.model;

import observer.punto5.model.productos.ItemMenu;
import observer.punto5.model.tarjetas.Tarjeta;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Pedido {
    private Map<ItemMenu, Integer> items;
    private boolean cerrado;

    public Pedido() {
        this.items = new HashMap<>();
        this.cerrado = false;
    }

    public void agregarItem(ItemMenu item, int cantidad) {
        if (cerrado) {
            throw new IllegalStateException("No se pueden agregar items a un pedido confirmado");
        }
        if (cantidad <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser mayor a cero");
        }
        items.put(item, items.getOrDefault(item, 0) + cantidad);
    }

    public void confirmar() {
        if (items.isEmpty()) {
            throw new IllegalStateException("No se puede confirmar un pedido vacío");
        }
        this.cerrado = true;
    }

    public double calcularTotalOriginal() {
        return items.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().precio() * entry.getValue())
                .sum();
    }

    public double calcularSubtotalConDescuento(Tarjeta tarjeta) {
        return items.entrySet().stream()
                .mapToDouble(entry -> entry.getKey().calcularPrecioConDescuento(tarjeta) * entry.getValue())
                .sum();
    }

    public Map<ItemMenu, Integer> obtenerItems() {
        return Collections.unmodifiableMap(items);
    }

}
