
package com.parra.gestion_restaurante.repository;

import com.parra.gestion_restaurante.model.DetallePedido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {
    List<DetallePedido> findByPedidoId(Integer idPedido);
}

