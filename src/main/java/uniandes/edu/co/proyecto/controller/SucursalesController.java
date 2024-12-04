package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.proyecto.modelo.Bodega;
import uniandes.edu.co.proyecto.modelo.Sucursal;
import uniandes.edu.co.proyecto.repository.SucursalRepository;
import uniandes.edu.co.proyecto.repository.SucursalRepositoryCustom;

import org.bson.Document;
import org.springframework.http.HttpStatus;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/sucursales")
public class SucursalesController {

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private SucursalRepositoryCustom sucursalRepositoryCustom;

    // Obtener todas las sucursales
    @GetMapping
    public ResponseEntity<List<Sucursal>> getSucursales() {
        try {
            List<Sucursal> sucursales = sucursalRepository.buscarTodasLasSucursales();
            return ResponseEntity.ok(sucursales);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Obtener bodegas sin almacenamiento
    @GetMapping("/bodegas")
    public ResponseEntity<List<Document>> getBodegas() {
        try {
            List<Document> bodegasDocs = sucursalRepositoryCustom.obtenerBodegas();
            return ResponseEntity.ok(bodegasDocs);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(Collections.singletonList(new Document("error", e.getMessage())));
        }
    }


    // Guardar una nueva sucursal
    @PostMapping("/new/save")
    public ResponseEntity<String> guardarSucursal(@RequestBody Sucursal sucursal) {
        try {
            sucursal.setBodegas(Collections.emptyList());
            sucursalRepository.save(sucursal);
            return ResponseEntity.ok("Sucursal creada con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear la sucursal: " + e.getMessage());
        }
    }

    // Eliminar una bodega de una sucursal
    @DeleteMapping("/bodegas/delete")
    public ResponseEntity<String> eliminarBodega(@RequestParam("nombre") String nombreBodega) {
        try {
            Integer sucursalId = sucursalRepositoryCustom.obtenerIdSucursalPorBodega(nombreBodega);
            if (sucursalId == null) {
                throw new IllegalArgumentException("No se encontró una sucursal con esa bodega");
            }
            sucursalRepository.eliminarBodega(sucursalId, nombreBodega);
            return ResponseEntity.ok("Bodega eliminada con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar la bodega: " + e.getMessage());
        }
    }

    // Obtener inventario de una sucursal
    @GetMapping("/inventario")
    public ResponseEntity<List<Document>> getInventario(@RequestParam("idSucursal") int idSucursal) {
        try {
            List<Document> inventario = sucursalRepositoryCustom.obtenerInventarioProductosPorSucursal(idSucursal);
            return ResponseEntity.ok(inventario);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Crear una nueva bodega
    @PostMapping("/bodegas/new/save")
    public ResponseEntity<String> crearBodega(@RequestBody Bodega bodega, @RequestParam("id") int id) {
        try {
            sucursalRepository.insertarBodega(id, bodega.getNombre(), bodega.getTamanio(), bodega.getVolumen(), bodega.getVolumenOcupado());
            return ResponseEntity.ok("Bodega creada con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear la bodega: " + e.getMessage());
        }
    }
}