package com.parra.gestion_restaurante.service;

import com.parra.gestion_restaurante.model.Producto;
import com.parra.gestion_restaurante.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    public Optional<Producto> buscarPorId(Integer id) {
        return productoRepository.findById(id);
    }

    public List<Producto> listarConStockBajo() {
        return productoRepository.findAll().stream()
                .filter(p -> p.getStock() <= p.getStockMinimo())
                .toList();
    }

    public Producto guardar(Producto producto) {
        return productoRepository.save(producto);
    }

    public void eliminar(Integer id) {
        productoRepository.deleteById(id);
    }

    public void descontarStock(Producto producto, int cantidad) {
        producto.setStock(producto.getStock() - cantidad);
        productoRepository.save(producto);
    }
}