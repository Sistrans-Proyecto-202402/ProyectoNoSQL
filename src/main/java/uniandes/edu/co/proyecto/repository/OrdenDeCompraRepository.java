package uniandes.edu.co.proyecto.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import uniandes.edu.co.proyecto.modelo.OrdenDeCompra;

public interface OrdenDeCompraRepository extends MongoRepository<OrdenDeCompra, Integer> {

    @Query(value="{}")
    List<OrdenDeCompra> buscarTodasLasCategorias();

    @Query("{ $insert: {_id: ?0, fecha_creacion: ?1, fecha_estimada: ?2, fecha_entrega: null, estado: ?3, detalle_producto: {cantidad: ?4, precio: ?5, fecha_vencimiento: ?6, producto: ?7}, bodega: ?8, sucursal: ?9, proveedor: ?10 } }")
    void insertarOrdenDeCompra(int id, Date fecha_creacion, Date fecha_estimada, String estado, int cantidad, double precio, Date fecha_vencimiento, String producto, String bodega, int sucursal, int proveedor);

    @Query("{_id: ?0}")
    OrdenDeCompra buscarPorId(int id);

    

}
