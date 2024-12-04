package uniandes.edu.co.proyecto.controller;

import java.util.Collections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;


import uniandes.edu.co.proyecto.modelo.Bodega;
import uniandes.edu.co.proyecto.modelo.Sucursal;
import uniandes.edu.co.proyecto.repository.CiudadRepository;
import uniandes.edu.co.proyecto.repository.SucursalRepository;
import org.springframework.web.bind.annotation.PostMapping;
import uniandes.edu.co.proyecto.repository.SucursalRepositoryCustom;

@Controller
public class SucursalesController {

    @Autowired
    private SucursalRepository sucursalRepository;

    @Autowired
    private SucursalRepositoryCustom sucursalRepositoryCustom;

    
    @Autowired
    private CiudadRepository ciudadRepository;  

    @GetMapping("/sucursales")
    public String getSucursales(Model model) {
        model.addAttribute("sucursales", sucursalRepository.buscarTodasLasSucursales());
        return "sucursales";
    }

    @GetMapping("/sucursales/bodegas")
    public String getBodegas(Model model) {
        model.addAttribute("bodegas", sucursalRepositoryCustom.obtenerBodegasSinAlmacenamientos());
        return "bodegas";
    }


    @GetMapping("/sucursales/new")
    public String sucursalForm(Model model) {
        model.addAttribute("sucursal", new Sucursal());
        model.addAttribute("ciudades", ciudadRepository.findAll());

        return "sucursalNuevo";
    }

    @PostMapping("/sucursales/new/save")
    public String sucursalGuardar(@ModelAttribute Sucursal sucursal, Model model) {
        try {
            sucursal.setBodegas(Collections.emptyList());
            sucursalRepository.save(sucursal);
            return "redirect:/sucursales";
        } catch (Exception e) {
            model.addAttribute("error", "Error al crear la sucursal: " + e.getMessage());
            return "error"; // Nombre de la vista de error
        }
    }

    @PostMapping("/sucursales/bodegas/delete")
    public String eliminarBodega(@RequestParam("nombre") String nombreBodega) {
        try {
            Integer sucursalId = sucursalRepositoryCustom.obtenerIdSucursalPorBodega(nombreBodega);
            if (sucursalId == null) {
                throw new IllegalArgumentException("No se encontró una sucursal con esa bodega");
            }

            sucursalRepository.eliminarBodega(sucursalId, nombreBodega);
            return "redirect:/sucursales/bodegas";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }




    @GetMapping("/inventario")
    public String getInventario(Model model, @RequestParam("idSucursal") int idSucursal) {
        model.addAttribute("inventario", sucursalRepositoryCustom.obtenerInventarioProductosPorSucursal(idSucursal));
        return "inventario";
    }

    @GetMapping("/sucursales/bodegas/new")
    public String bodegaForm(Model model) {
        model.addAttribute("bodega", new Bodega());
        model.addAttribute("sucursales", sucursalRepository.findAll());
        return "bodegaNueva";
    } 

    @PostMapping("/sucursales/bodegas/new/save")
    public String crearBodega(@ModelAttribute Bodega bodega, @RequestParam("id") int id) {
        
        sucursalRepository.insertarBodega(id, bodega.getNombre(), bodega.getTamanio(), bodega.getVolumen(), bodega.getVolumenOcupado());
        return "redirect:/sucursales/bodegas";
        
    }
    


  

    

    
    
}
