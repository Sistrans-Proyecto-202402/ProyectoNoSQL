package uniandes.edu.co.proyecto.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import uniandes.edu.co.proyecto.repository.OrdenDeCompraRepository;
import uniandes.edu.co.proyecto.modelo.DetalleProducto;
import uniandes.edu.co.proyecto.modelo.OrdenDeCompra;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/ordenesdecompra")
public class OrdenDeCompraController {

    @Autowired
    private OrdenDeCompraRepository ordenDeCompraRepository;

    @PostMapping("/new")
    public ResponseEntity<String> ordenDeCompraGuardar(
            @RequestParam("id") Integer id,
            @RequestParam("fecha_creacion") Date fecha_creacion,
            @RequestParam("fecha_vencimiento") Date fecha_vencimiento,
            @RequestParam("fecha_estimada") Date fecha_estimada,
            @RequestParam("estado") String estado,
            @RequestParam("cantidad") int cantidad,
            @RequestParam("precio") double precio,
            @RequestParam("producto") String codigoBarras,
            @RequestParam("bodega") String bodega,
            @RequestParam("sucursal") int sucursal,
            @RequestParam("proveedor") int proveedor) {
        try {
            DetalleProducto detalle = new DetalleProducto();
            detalle.setCantidad(cantidad);
            detalle.setPrecio(precio);
            detalle.setFechaVencimiento(fecha_vencimiento);
            detalle.setProducto(codigoBarras);

            OrdenDeCompra orden = new OrdenDeCompra();
            orden.setId(id);
            orden.setFechaCreacion(fecha_creacion);
            orden.setFechaEstimada(fecha_estimada);
            orden.setEstado(estado);
            orden.setDetalleProducto(detalle);
            orden.setBodega(bodega);
            orden.setSucursal(sucursal);
            orden.setProveedor(proveedor);

            ordenDeCompraRepository.save(orden);

            return ResponseEntity.status(HttpStatus.CREATED).body("Orden de compra creada con éxito.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear la orden de compra: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdenDeCompra> obtenerOrdenDeCompraPorId(@PathVariable int id) {
        try {
            OrdenDeCompra ordenDeCompra = ordenDeCompraRepository.buscarPorId(id);
            return ResponseEntity.ok(ordenDeCompra);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<OrdenDeCompra>> getOrdenesDeCompra() {
        try {
            List<OrdenDeCompra> ordenes = ordenDeCompraRepository.buscarTodasLasCategorias();
            return ResponseEntity.ok(ordenes);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}