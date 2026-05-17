
package com.parra.gestion_restaurante.repository;

import com.parra.gestion_restaurante.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByEstado(Pedido.Estado estado);
    List<Pedido> findByMeseroIdUsuario(Integer idMesero);
}

