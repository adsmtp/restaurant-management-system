package com.parra.gestion_restaurante.controller;

import com.parra.gestion_restaurante.model.Pago;
import com.parra.gestion_restaurante.service.PagoService;
import com.parra.gestion_restaurante.service.PedidoService;
import com.parra.gestion_restaurante.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pagos")
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("pagos", pagoService.listarTodos());
        return "pagos/lista";
    }

    @GetMapping("/nuevo/{idPedido}")
    public String formularioNuevo(@PathVariable Integer idPedido, Model model) {
        Pago pago = new Pago();
        pedidoService.buscarPorId(idPedido).ifPresent(pago::setPedido);
        model.addAttribute("pago", pago);
        model.addAttribute("cajeros", usuarioService.listarTodos());
        return "pagos/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Pago pago) {
        pagoService.registrarPago(pago);
        return "redirect:/pagos";
    }
}