package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uniandes.edu.co.proyecto.repository.ProveedorRepository;
import uniandes.edu.co.proyecto.modelo.Proveedor;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/proveedores")
public class ProveedoresController {

    @Autowired
    private ProveedorRepository proveedorRepository;

    // Obtener todos los proveedores
    @GetMapping
    public ResponseEntity<List<Proveedor>> obtenerTodosLosProveedores() {
        try {
            List<Proveedor> proveedores = proveedorRepository.buscarTodosLosProveedores();
            return ResponseEntity.ok(proveedores);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Crear un nuevo proveedor
    @PostMapping("/new")
    public ResponseEntity<String> crearProveedor(@RequestBody Proveedor proveedor) {
        try {
            proveedorRepository.save(proveedor);
            return ResponseEntity.status(HttpStatus.CREATED).body("Proveedor creado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al crear el proveedor: " + e.getMessage());
        }
    }

    // Obtener un proveedor por su ID
    @GetMapping("/{id}")
    public ResponseEntity<Proveedor> obtenerProveedorPorId(@PathVariable int id) {
        try {
            Optional<Proveedor> proveedor = proveedorRepository.findById(id);
            if (proveedor.isPresent()) {
                return ResponseEntity.ok(proveedor.get());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // Editar un proveedor existente
    @PutMapping("/edit/{id}")
    public ResponseEntity<String> actualizarProveedor(@PathVariable int id, @RequestBody Proveedor proveedor) {
        try {
            proveedorRepository.actualizarProveedor(id, proveedor.getNombre(), proveedor.getDireccion(), proveedor.getNombreContacto(), proveedor.getTelefonoContacto());
            return ResponseEntity.ok("Proveedor actualizado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar el proveedor: " + e.getMessage());
        }
    }

    // Eliminar un proveedor por su ID
    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> eliminarProveedor(@PathVariable int id) {
        try {
            proveedorRepository.deleteById(id);
            return ResponseEntity.ok("Proveedor eliminado con éxito");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar el proveedor: " + e.getMessage());
        }
    }
}