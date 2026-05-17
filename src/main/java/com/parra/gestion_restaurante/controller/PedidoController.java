package com.parra.gestion_restaurante.controller;

import com.parra.gestion_restaurante.model.Pedido;
import com.parra.gestion_restaurante.service.PedidoService;
import com.parra.gestion_restaurante.service.ProductoService;
import com.parra.gestion_restaurante.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("pedidos", pedidoService.listarTodos());
        return "pedidos/lista";
    }

    @GetMapping("/nuevo")
    public String formularioNuevo(Model model) {
        model.addAttribute("pedido", new Pedido());
        model.addAttribute("productos", productoService.listarTodos());
        model.addAttribute("meseros", usuarioService.listarTodos());
        return "pedidos/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Pedido pedido) {
        pedidoService.crearPedido(pedido);
        return "redirect:/pedidos";
    }

    @GetMapping("/estado/{id}/{estado}")
    public String cambiarEstado(@PathVariable Integer id,
                                 @PathVariable Pedido.Estado estado) {
        pedidoService.cambiarEstado(id, estado);
        return "redirect:/pedidos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Integer id) {
        pedidoService.eliminar(id);
        return "redirect:/pedidos";
    }
}
