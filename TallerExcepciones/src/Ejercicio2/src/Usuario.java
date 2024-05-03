public class Usuario {
    
    private String nombre;
    private String contraseña;
    private double saldo;

    //?----------------Constructor------------------------
    public Usuario(String nombre, String contraseña, double saldo)
    {
        this.nombre = nombre;
        this.contraseña = contraseña;
        this.saldo = saldo;
    }
    //!--------------GETTERS & SETTERS---------------
    
    //?----------------nombre-------------------------
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }

    //?-------------contraseña-------------------------
    public String getContraseña(){
        return contraseña;
    }

    public void setContraseña(String contraseña){
        this.contraseña = contraseña;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }
}

