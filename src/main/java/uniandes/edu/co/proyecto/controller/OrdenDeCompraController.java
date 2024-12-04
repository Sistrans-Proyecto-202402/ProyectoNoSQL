package uniandes.edu.co.proyecto.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import uniandes.edu.co.proyecto.repository.OrdenDeCompraRepository;
import uniandes.edu.co.proyecto.repository.ProveedorRepository;
import uniandes.edu.co.proyecto.repository.SucursalRepository;
import uniandes.edu.co.proyecto.repository.SucursalRepositoryCustom;
import uniandes.edu.co.proyecto.modelo.DetalleProducto;
import uniandes.edu.co.proyecto.modelo.OrdenDeCompra;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.ui.Model;
import uniandes.edu.co.proyecto.repository.CategoriaRepositoryCustom;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class OrdenDeCompraController {

    @Autowired
    private OrdenDeCompraRepository ordenDeCompraRepository;

    @Autowired
    private CategoriaRepositoryCustom categoriaRepository;

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private SucursalRepositoryCustom sucursalRepositoryCustom;

    @Autowired
    private ProveedorRepository proveedorRepository;




    @GetMapping("/ordenesdecompra/new")
    public String ordenDeCompraForm(Model model) {
        model.addAttribute("orden", new OrdenDeCompra());
        model.addAttribute("productos", categoriaRepository.obtenerProductosDeTodasLasCategorias());
        model.addAttribute("sucursales", sucursalRepository.findAll());
        model.addAttribute("bodegas", sucursalRepositoryCustom.obtenerBodegas());
        model.addAttribute("proveedores", proveedorRepository.findAll());
        return "ordenNueva";
    }
    
    @PostMapping("/ordenesdecompra/new/save")
    public String ordenDeCompraGuardar(@RequestParam("id") Integer id, @RequestParam("fecha_creacion") Date fecha_creacion, @RequestParam("fecha_vencimiento") Date fecha_vencimiento, @RequestParam("fecha_estimada") Date fecha_estimada, @RequestParam("estado") String estado, @RequestParam("cantidad") int cantidad, @RequestParam("precio") double precio,  @RequestParam("producto") String codigoBarras, @RequestParam("bodega") String bodega, @RequestParam("sucursal") int sucursal, @RequestParam("proveedor") int proveedor, Model model) {
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
            orden.setFechaEntrega(null);
            orden.setEstado(estado);
            orden.setDetalleProducto(detalle);
            orden.setBodega(bodega);
            orden.setSucursal(sucursal);
            orden.setProveedor(proveedor);
            ordenDeCompraRepository.save(orden);
            return "redirect:/ordenesdecompra";
        } catch (Exception e) {
            model.addAttribute("error", "Error al crear la orden de compra: " + e.getMessage());
            return "error";
        }
    }

    @GetMapping("/ordenesdecompra/{id}")
    public ResponseEntity<OrdenDeCompra> obtenerOrdenDeCompraPorId(@PathVariable int id) {
        try {
            OrdenDeCompra ordenDeCompra = ordenDeCompraRepository.buscarPorId(id);
            if (ordenDeCompra == null) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(ordenDeCompra);
        } catch (Exception e) {
            e.printStackTrace(); // Log de error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/ordenesdecompra")
public String getOrdenesDeCompra(Model model) {
    List<OrdenDeCompra> ordenes = ordenDeCompraRepository.buscarTodasLasCategorias();
    model.addAttribute("ordenesDeCompra", ordenes);
    return "ordenesdecompra";
}

@GetMapping("/ordenesdecompra/buscar")
public String buscarOrdenDeCompra(@RequestParam("id") Integer id, Model model) {
    OrdenDeCompra orden = ordenDeCompraRepository.buscarPorId(id);
    
    model.addAttribute("orden", orden);
    return "ordenHallada";
    
}


}
