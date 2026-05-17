package com.parra.gestion_restaurante.service;

import com.parra.gestion_restaurante.model.DetallePedido;
import com.parra.gestion_restaurante.model.Pedido;
import com.parra.gestion_restaurante.model.Producto;
import com.parra.gestion_restaurante.repository.PedidoRepository;
import com.parra.gestion_restaurante.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {
    
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProductoService productoService;

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscarPorId(Integer id) {
        return pedidoRepository.findById(id);
    }

    public List<Pedido> listarPorEstado(Pedido.Estado estado) {
        return pedidoRepository.findByEstado(estado);
    }

    public Pedido crearPedido(Pedido pedido) {
    pedido.setFecha(LocalDate.now());
    pedido.setEstado(Pedido.Estado.pendiente);

    BigDecimal total = BigDecimal.ZERO;
    for (DetallePedido detalle : pedido.getDetalles()) {
        detalle.setPedido(pedido);

        // Buscar el producto completo desde la BD
        Producto productoCompleto = productoRepository.findById(
                detalle.getProducto().getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        detalle.setProducto(productoCompleto);
        detalle.setPrecioUnitario(productoCompleto.getPrecio());

        total = total.add(productoCompleto.getPrecio()
                .multiply(BigDecimal.valueOf(detalle.getCantidad())));

        productoService.descontarStock(productoCompleto, detalle.getCantidad());
    }
    pedido.setTotal(total);
    return pedidoRepository.save(pedido);
}

    public Pedido cambiarEstado(Integer id, Pedido.Estado nuevoEstado) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido no encontrado"));
        pedido.setEstado(nuevoEstado);
        return pedidoRepository.save(pedido);
    }

    public void eliminar(Integer id) {
        pedidoRepository.deleteById(id);
    }
}