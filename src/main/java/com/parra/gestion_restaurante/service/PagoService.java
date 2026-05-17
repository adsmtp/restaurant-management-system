package com.parra.gestion_restaurante.service;

import com.parra.gestion_restaurante.model.Pago;
import com.parra.gestion_restaurante.model.Pedido;
import com.parra.gestion_restaurante.repository.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PagoService {

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    private PedidoService pedidoService;

    public List<Pago> listarTodos() {
        return pagoRepository.findAll();
    }

    public Optional<Pago> buscarPorPedido(Integer idPedido) {
        return pagoRepository.findByPedidoId(idPedido);
    }

    public Pago registrarPago(Pago pago) {
        pago.setFecha(LocalDate.now());
        Pago pagoGuardado = pagoRepository.save(pago);
        // Marcar el pedido como pagado
        pedidoService.cambiarEstado(pago.getPedido().getId(), Pedido.Estado.pagado);
        return pagoGuardado;
    }
}