package punto1.model.proxy;

import punto1.model.infrastructure.DBConnection;
import punto1.model.realsubject.Telefono;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class TelefonosProxy implements Set<Telefono> {
    private static final String NUMERO = "numero";
    private static final String QUERY = "SELECT numero FROM telefonos WHERE persona_id = ?";
    private int idPersona;
    private Set<Telefono> telefonos;
    private boolean estaCargado;


    public TelefonosProxy(int idPersona) {
        this.idPersona = idPersona;
        this.estaCargado = false;
    }

    private void cargarTelefonos() {
        if (estaCargado) {
            return;
        }

        //System.out.println("Cargando teléfonos desde la BD...");

        telefonos = new HashSet<>();

        var sql = QUERY;

        try (Connection conn = DBConnection.obtenerConexion();
             PreparedStatement statement = conn.prepareStatement(sql)) {

            statement.setInt(1, idPersona);

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                telefonos.add(new Telefono(rs.getString(NUMERO)));
            }

            estaCargado = true;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int size() {
        cargarTelefonos();
        return telefonos.size();
    }

    @Override
    public boolean isEmpty() {
        cargarTelefonos();
        return telefonos.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        cargarTelefonos();
        return telefonos.contains(o);
    }

    @Override
    public Iterator<Telefono> iterator() {
        cargarTelefonos();
        return telefonos.iterator();
    }

    @Override
    public Object[] toArray() {
        cargarTelefonos();
        return telefonos.toArray();

    }

    @Override
    public <T> T[] toArray(T[] ts) {
        cargarTelefonos();
        return telefonos.toArray(ts);
    }

    @Override
    public boolean add(Telefono telefono) {
        cargarTelefonos();
        return telefonos.add(telefono);
    }

    @Override
    public boolean remove(Object o) {
        cargarTelefonos();
        return telefonos.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        cargarTelefonos();
        return telefonos.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends Telefono> c) {
        cargarTelefonos();
        return telefonos.addAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        cargarTelefonos();
        return telefonos.retainAll(c);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        cargarTelefonos();
        return telefonos.removeAll(c);
    }

    @Override
    public void clear() {
        cargarTelefonos();
        telefonos.clear();
    }
}
