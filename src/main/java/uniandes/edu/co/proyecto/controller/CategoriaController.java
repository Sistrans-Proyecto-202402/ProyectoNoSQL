package uniandes.edu.co.proyecto.controller;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.proyecto.modelo.Categoria;
import uniandes.edu.co.proyecto.modelo.Producto;
import uniandes.edu.co.proyecto.repository.CategoriaRepository;
import uniandes.edu.co.proyecto.repository.CategoriaRepositoryCustom;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private CategoriaRepositoryCustom categoriaRepositoryCustom;

    // Crear una nueva categoría
    @PostMapping("/new")
    public ResponseEntity<String> guardarCategoria(@RequestBody Categoria categoria) {
        try {
            categoria.setProductos(Collections.emptyList());
            categoriaRepository.save(categoria);
            return ResponseEntity.ok("Categoría creada con éxito");
        } 
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear la categoría: " + e.getMessage());
        }
    }

    // Agregar un producto a una categoría existente
    @PostMapping("/{id}/productos")
    public ResponseEntity<String> agregarProductoACategoria(@PathVariable int id, @RequestBody Producto producto) {
        try {
            categoriaRepository.insertarProducto(
                    id, 
                    producto.getNombre(),
                    producto.getPrecio_unitario(),
                    producto.getPresentacion(),
                    producto.getCantidad_en_presentacion(),
                    producto.getUnidad_medida(),
                    producto.getVolumen_empaque(),
                    producto.getPeso_empaque(),
                    producto.getCodigo_barras()
            );
            return ResponseEntity.ok("Producto agregado correctamente a la categoría");
        } 
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al agregar el producto: " + e.getMessage());
        }
    }

    // Obtener todas las categorías
    @GetMapping
    public ResponseEntity<List<Categoria>> obtenerTodasLasCategorias() {
        try {
            List<Categoria> categorias = categoriaRepository.buscarTodasLasCategorias();
            return ResponseEntity.ok(categorias);
        } 
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener todos los productos de todas las categorías
    @GetMapping("/productos")
    public ResponseEntity<List<Document>> obtenerTodosLosProductos() {
        try {
            List<Document> productos = categoriaRepositoryCustom.obtenerProductosDeTodasLasCategorias();
            return ResponseEntity.ok(productos);
        } 
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Clasificar productos con filtros
    @GetMapping("/clasificar")
    public ResponseEntity<List<Document>> clasificarProductos(
            @RequestParam int idSucursal,
            @RequestParam int idCategoria,
            @RequestParam double rangoPrecioMin,
            @RequestParam double rangoPrecioMax,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaVencimiento) {
        try {
            List<Document> documentos = categoriaRepositoryCustom.clasificarProductos(
                    idSucursal, idCategoria, rangoPrecioMin, rangoPrecioMax, fechaVencimiento);
            return ResponseEntity.ok(documentos);
        } 
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Collections.singletonList(new Document("error", e.getMessage())));
        }
    }
}
