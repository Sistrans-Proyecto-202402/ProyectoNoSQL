package uniandes.edu.co.proyecto.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import uniandes.edu.co.proyecto.repository.ProveedorRepository;
import uniandes.edu.co.proyecto.modelo.Proveedor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;




@Controller
public class ProveedoresController {

    @Autowired
    private ProveedorRepository proveedorRepository;

    @GetMapping("/proveedores")
    public String obtenerTodosLosProveedores(Model model) {
        model.addAttribute("proveedores", proveedorRepository.buscarTodosLosProveedores());
        return "proveedores";
    }
    @GetMapping("/proveedores/new")
    public String proveedorForm(Model model) {
        model.addAttribute("proveedor", new Proveedor());
        return "proveedorNuevo";
    }
    

    @PostMapping("proveedores/new/save")
    public String crearProveedor(@ModelAttribute Proveedor proveedor, Model model) {
        try {
            proveedorRepository.save(proveedor);
            return "redirect:/proveedores";
        } catch (Exception e) {
             model.addAttribute("error", "Error al crear el proovedor: " + e.getMessage());
            return "error"; 
        }
    }

    @GetMapping("/proveedores/{id}/edit")
    public String proveedorEditarForm(@PathVariable("id") int id, Model model) {
        Proveedor proveedor = proveedorRepository.findById(id).orElse(null);
        if (proveedor != null) {
            model.addAttribute("proveedor", proveedor);
            return "proveedorEditar";
        } else {
            return "redirect:/proveedores";
        }
    }

    @PostMapping("proveedores/{id}/edit/save")
    public String proveedorEditarGuardar(@PathVariable("id") int id, @ModelAttribute Proveedor proveedor) {
            proveedorRepository.actualizarProveedor(id, proveedor.getNombre(), proveedor.getDireccion(), proveedor.getNombreContacto(), proveedor.getTelefonoContacto());
            return "redirect:/proveedores";
    }

    
}
