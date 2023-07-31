package com.restaurante.reviews.repository;

import com.restaurante.reviews.models.Orden;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public class OrdenRepositoryImple implements IOrdenRepository{

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void registrar(Orden nuevaOrden) {
        String query = "INSERT INTO Orden " +
                "(estado,fecha_hora,precio_total,tiempo_entrega,id_cliente,id_restaurante) " +
                "VALUES ('"+nuevaOrden.getEstado()+"','"+nuevaOrden.getFechaHora()+"','" +
                                nuevaOrden.getPrecioTotal()+"','"+nuevaOrden.getTiempoEntrega()+"','"+
                                nuevaOrden.getCliente()+"','"+nuevaOrden.getRestaurante()+"')";
         entityManager.createQuery(query).executeUpdate();
    }
}
