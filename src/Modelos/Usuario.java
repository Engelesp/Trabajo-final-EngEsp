package Modelos;

// atributos
public class Usuario {
    private int idUser;
    private String UserName;
    private String Nombre;
    private String Apellido;
    private String Telefono;
    private String Email;
    private String Password;

// constructor
    public Usuario(String nombreUsuario, String nombre, String apellido, String telefono, String correo, String contraseña) {
        this.UserName = nombreUsuario;
        this.Nombre = nombre;
        this.Apellido = apellido;
        this.Telefono = telefono;
        this.Email = correo;
        this.Password = contraseña;
    }

// getters y setters
    public int getId() { return idUser; }
    public void setId(int id) { this.idUser = id; }

    public String getNombreUsuario() { return UserName; }
    public void setNombreUsuario(String nombreUsuario) { this.UserName = nombreUsuario; }

    public String getNombre() { return Nombre; }
    public void setNombre(String nombre) { this.Nombre = nombre; }

    public String getApellido() { return Apellido; }
    public void setApellido(String apellido) { this.Apellido = apellido; }

    public String getTelefono() { return Telefono; }
    public void setTelefono(String telefono) { this.Telefono = telefono; }

    public String getCorreo() { return Email; }
    public void setCorreo(String correo) { this.Email = correo; }

    public String getContraseña() { return Password; }
    public void setContraseña(String contraseña) { this.Password = contraseña; }
}