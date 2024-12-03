package Modelos;

// Clase Producto
public class Producto {
    // Atributos
    private int idProducto;
    private String nombreProducto;
    private String marcaProducto;
    private String categoriaProducto;
    private int precioProducto;
    private int stockProducto;

    // Constructor
    public Producto(String nombreProducto, String marcaProducto, String categoriaProducto, int precioProducto, int stockProducto) {
        this.nombreProducto = nombreProducto;
        this.marcaProducto = marcaProducto;
        this.categoriaProducto = categoriaProducto;
        this.precioProducto = precioProducto;
        this.stockProducto = stockProducto;
    }


    public Producto() {}

    // Getters y Setters
    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public String getNombreProducto() { return nombreProducto; }
    public void setNombreProducto(String nombreProducto) { this.nombreProducto = nombreProducto; }

    public String getMarcaProducto() { return marcaProducto; }
    public void setMarcaProducto(String marcaProducto) { this.marcaProducto = marcaProducto; }

    public String getCategoriaProducto() { return categoriaProducto; }
    public void setCategoriaProducto(String categoriaProducto) { this.categoriaProducto = categoriaProducto; }

    public int getPrecioProducto() { return precioProducto; }
    public void setPrecioProducto(int precioProducto) { this.precioProducto = precioProducto; }

    public int getStockProducto() { return stockProducto; }
    public void setStockProducto(int stockProducto) { this.stockProducto = stockProducto; }
}
