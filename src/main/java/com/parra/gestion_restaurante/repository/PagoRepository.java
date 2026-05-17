
package com.parra.gestion_restaurante.repository;

import com.parra.gestion_restaurante.model.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface PagoRepository extends JpaRepository<Pago, Integer> {
    Optional<Pago> findByPedidoId(Integer idPedido);
}

