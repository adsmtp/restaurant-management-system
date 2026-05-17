
package com.parra.gestion_restaurante.repository;

import com.parra.gestion_restaurante.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    List<Producto> findByStockLessThanEqual(Integer stockMinimo);
}
