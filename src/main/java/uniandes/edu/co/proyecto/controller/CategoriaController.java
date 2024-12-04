package uniandes.edu.co.proyecto.controller;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;

import uniandes.edu.co.proyecto.modelo.Categoria;
import uniandes.edu.co.proyecto.modelo.Producto;
import uniandes.edu.co.proyecto.repository.CategoriaRepository;
import uniandes.edu.co.proyecto.repository.CategoriaRepositoryCustom;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;


@Controller
public class CategoriaController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @PostMapping("/categorias/new/save")
    public String categoriaGuardar(@ModelAttribute Categoria categoria, Model model) {
        try {
            categoria.setProductos(Collections.emptyList());
            categoriaRepository.save(categoria);
            return "redirect:/categorias";
        } catch (Exception e) {
            model.addAttribute("error", "Error al crear la categoría: " + e.getMessage());
            return "error"; // Nombre de la vista de error
        }
    }

    @PostMapping("/categorias/{id}/productos")
    public ResponseEntity<String> agregarProductoACategoria(@PathVariable int id, @RequestBody Producto producto) {
        try {
            categoriaRepository.insertarProducto(id, producto.getNombre(), producto.getPrecio_unitario(), producto.getPresentacion(), producto.getCantidad_en_presentacion(), producto.getUnidad_medida(), producto.getVolumen_empaque(), producto.getPeso_empaque(), producto.getCodigo_barras());
            return ResponseEntity.ok("Producto agregado correctamente");
        } catch (Exception e) {
            return new ResponseEntity<>("Error al agregar el producto" + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/productos/new")
    public String bodegaForm(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaRepository.findAll()); 
        return "productoNuevo"; // Nombre de la vista para crear un nuevo producto
    }

    @PostMapping("/productos/new/save")
    public String productoGuardar(
            @ModelAttribute Producto producto, 
            Model model, 
            @RequestParam("idCategoria") int idCategoria) {
        try {
            // Guardar el producto usando el repositorio o servicio
            categoriaRepository.insertarProducto(
                idCategoria, 
                producto.getNombre(), 
                producto.getPrecio_unitario(), 
                producto.getPresentacion(),
                producto.getCantidad_en_presentacion(), 
                producto.getUnidad_medida(), 
                producto.getVolumen_empaque(),
                producto.getPeso_empaque(), 
                producto.getCodigo_barras()
            );
            return "redirect:/productos"; // Redirige a la lista de productos
        } catch (Exception e) {
            model.addAttribute("error", "Error al crear el producto: " + e.getMessage());
            return "error"; // Vista de error
        }
    }

        
        
    @GetMapping("/categorias")
    public String obtenerTodasLasCategorias(Model model) {
        List<Categoria> categorias = categoriaRepository.buscarTodasLasCategorias();
        model.addAttribute("categorias", categorias);
        return "categorias";
    }

    @GetMapping("/categorias/new")
    public String categoriaForm(Model model) {
        model.addAttribute("categoria", new Categoria());
        return "categoriaNueva"; // Nombre de la vista para crear una nueva categoría
    }


    @Autowired
    private CategoriaRepositoryCustom categoriaRepositoryCustom;

    @GetMapping("/productos")
    public String obtenerTodosLosProductos(Model model) {
        List<Document> productos = categoriaRepositoryCustom.obtenerProductosDeTodasLasCategorias();
        
        model.addAttribute("productos", productos);
        return "productos";
    }



    @GetMapping("/clasificarProductos")
public String clasificarProductos(
        @RequestParam("idSucursal") int idSucursal,
        @RequestParam("idCategoria") int idCategoria,
        @RequestParam("rangoPrecioMin") double rangoPrecioMin,
        @RequestParam("rangoPrecioMax") double rangoPrecioMax,
        @RequestParam("fechaVencimiento") @DateTimeFormat(pattern = "yyyy-MM-dd") Date fechaVencimiento,
        Model model) {
    
    List<Document> documentos = categoriaRepositoryCustom.clasificarProductos(idSucursal, idCategoria, rangoPrecioMin, rangoPrecioMax, fechaVencimiento);
    List<Producto> productosClasificados = documentos.stream()
            .map(doc -> {
                Producto producto = new Producto();
                Document productoDoc = (Document) doc.get("producto");
                if (productoDoc != null) { // Validar que exista "producto"
                    producto.setNombre(productoDoc.getString("nombre"));
                    producto.setPrecio_unitario(productoDoc.getDouble("precio_unitario"));
                    producto.setPresentacion(productoDoc.getString("presentacion"));
                    producto.setCantidad_en_presentacion(productoDoc.getDouble("cantidad_en_presentacion"));
                    producto.setUnidad_medida(productoDoc.getString("unidad_medida"));
                    producto.setVolumen_empaque(productoDoc.getDouble("volumen_empaque"));
                    producto.setPeso_empaque(productoDoc.getDouble("peso_empaque"));
                    producto.setCodigo_barras(productoDoc.getString("codigo_barras"));
                }
                return producto;
            })
            .filter(p -> p.getNombre() != null) // Filtrar productos válidos
            .collect(Collectors.toList());
    
    model.addAttribute("productosClasificados", productosClasificados);
    return "productos"; // Nombre del HTML generado
}
    

}
