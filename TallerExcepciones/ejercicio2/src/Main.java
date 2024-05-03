//?Librerias importadas
import java.util.Scanner;

import exceptions.NotEnoughFunds;
import exceptions.UserNotFound;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

public class Main 
{
    static ArrayList<Usuario> usuarios = new ArrayList<Usuario>();
    static Map<String, Double> moneyData = new HashMap<String, Double>() ;
    static Usuario usuarioActual; 
    static Scanner input = new Scanner(System.in);  

    //!Funcion Main -------------------------------------------------------------------------------
    public static void main(String[] args) throws Exception 
    {
        menuPrincipal(); //? Se llama a la funcion menu
    }

    //!Funcion que despliega el MenuPrincipal en consola ---------------------------------------------------
    static void menuPrincipal()
    {
        boolean loop = false;
        while (!loop) {
            int opcion;
            try {
                System.out.println("--------------------GESTOR DE CUENTAS UNIVALLE--------------------");
                System.out.println("Bienvenido al Sistema de Gestion de cuentas univalle");
                System.out.println("Porfavor elije una opcion:");
                System.out.println(" 'Ten en cuenta que para poder ingresar debes tener una cuenta'");
                System.out.println(" 'Si no tienes una cuenta debes registrarte' ");
                System.out.println(" 1. Ingresar ");
                System.out.println(" 2. Registrarse ");
                System.out.println(" 3. Cerrar el programa ");
                
                opcion = input.nextInt();
        
                if (opcion < 1 || opcion > 3) {
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida (1, 2 o 3).");
                    continue; // Continuar con el bucle while para solicitar nuevamente la entrada
                }
        
                switch (opcion) {
                    case 1:
                        ingresar();
                        break;
                    case 2:
                        crearUsuario();
                        break;
                    case 3:
                        loop = true;
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor, ingrese un número entero válido.");
                input.next(); // Limpiar el buffer de entrada
            }
        } 
        input.close(); 
    }

    //!Funcion que permite ingresar en caso de ya estar registrado previamente-------------------------
    static void ingresar()
    {
        while (true) {
            System.out.println("--------------------INGRESAR--------------------");
            System.out.println("Por favor ingrese su nombre de usuario:");
            String nombreUsuario = input.next();
            System.out.println("Por favor ingrese su contraseña:");
            String contraseña = input.next();
    
            // Verificar si el nombre de usuario y la contraseña coinciden con los datos almacenados
            boolean accesoPermitido = false;
            for (Usuario usuario : usuarios) {
                if (usuario.getNombre().equals(nombreUsuario) && usuario.getContraseña().equals(contraseña)) {
                    accesoPermitido = true;
                    usuarioActual = usuario;
                    break;
                }
            }
    
            if (accesoPermitido) {
                System.out.println("¡Bienvenido, " + nombreUsuario + "!");
                homeMenu(); // Acceder al menú principal
                break; // Salir del bucle while si el acceso es permitido
            } else {
                System.out.println("Nombre de usuario o contraseña incorrectos.");
                System.out.println("¿Qué desea hacer?");
                System.out.println("1. Intentar ingresar nuevamente");
                System.out.println("2. Regresar al menú principal");
                try {
                    int opcion = input.nextInt();
                    switch (opcion) {
                        case 1:
                            // Intentar ingresar nuevamente
                            break;
                        case 2:
                            menuPrincipal(); // Regresar al menú principal
                            return; // Salir del método después de regresar al menú principal
                        default:
                            System.out.println("Opción no válida. Regresando al menú principal.");
                            menuPrincipal(); // Opción predeterminada: regresar al menú principal
                            return; // Salir del método después de regresar al menú principal
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error: Por favor, ingrese un número entero válido.");
                    input.next(); // Limpiar el buffer de entrada
                }
            }
        }
    }

    //!Funcion que permite Registrarse en caso de no tener cuenta--------------------------------
    static void crearUsuario() {
        String nombre;
        String contraseña;
        double saldo = 0;
    
        System.out.println("\033[H\033[2J"); // Limpia la pantalla
    
        while (true) {
            System.out.println("-----------Creacion de Usuario------------");
            System.out.println("Ingrese el nombre:");
            input.nextLine(); // Limpia el salto de línea del buffer
            nombre = input.nextLine();
    
            // Verificar si el nombre de usuario ya está en uso
            boolean nombreEnUso = false;
            for (Usuario usuario : usuarios) {
                if (usuario.getNombre().equals(nombre)) {
                    nombreEnUso = true;
                    break;
                }
            }
    
            if (nombreEnUso) {
                System.out.println("El nombre de usuario ya está en uso. Por favor, intente con otro nombre.");
            } else {
                break; // Salir del bucle si el nombre de usuario no está en uso
            }
        }
    
        System.out.println("Ingrese su contraseña:");
        contraseña = input.next();
    
        moneyData.put(contraseña, 0.0);
    
        usuarios.add(new Usuario(nombre, contraseña, saldo));
        if (usuarios.size() == 1) usuarioActual = usuarios.get(0);
    
        System.out.println("\033[H\033[2J"); // Limpia la pantalla
    }
    
    //!Funcion que despliega el HomeMenu en consola ---------------------------------------------------
    static void homeMenu()
    {
        while (true) {
            int opcion;
            try {
                System.out.println("--------------------HOME--------------------");
                System.out.println("Este es tu 'HomeMenu' ");
                System.out.println("Desde aqui puedes gestionar lo que quieres hacer con tu cuenta");
                System.out.println(" Elige lo que quieres hacer");
                System.out.println(" 1. Depositar ");
                System.out.println(" 2. Retirar");
                System.out.println(" 3. Transferir");
                System.out.println(" 4. Imprimir Saldo");
                System.out.println(" 5. Cerrar Sesion");

                
                opcion = input.nextInt();
        
                if (opcion < 1 || opcion > 5) {
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida (1, 2 o 3).");
                    continue; // Continuar con el bucle while para solicitar nuevamente la entrada
                }
        
                switch (opcion) {
                    case 1:
                        depositarDinero();
                        break;
                    case 2:
                        retirarDinero();
                        break;
                    case 3:
                        transferirDinero();
                        break;
                    case 4:
                        imprimirSaldo();
                        break;
                    case 5:
                        System.out.println("\033[H\033[2J"); //?This thing cleans the screen 
                        break;
                }
                if (opcion == 5) break;
                break; // Salir del bucle while si la entrada es válida
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor, ingrese un número entero válido.");
                input.next(); // Limpiar el buffer de entrada
            }
        } 
    }

    //!Funcion que Permite Depositar dinero en la cuenta---------------------------------------------------
    static void depositarDinero() {
        double cantidad;
        while (true) {
            try {
                System.out.println("--------------------DEPOSITAR DINERO--------------------");
                System.out.println("Por favor ingrese la cantidad de dinero que desea depositar:");
                cantidad = input.nextDouble();

                // Verificar si la cantidad ingresada es válida
                if (cantidad <= 0) {
                    System.out.println("Error: Por favor, ingrese una cantidad de dinero válida.");
                    continue; // Volver a solicitar la cantidad de dinero
                }

                // Actualizar el saldo del usuario
                double saldoActual = usuarioActual.getSaldo();
                saldoActual += cantidad;
                usuarioActual.setSaldo(saldoActual);

                // Actualizar el saldo en la matriz de datos de dinero
                String contraseñaUsuario = usuarioActual.getContraseña();
                double saldoUsuario = moneyData.get(contraseñaUsuario);
                saldoUsuario += cantidad;
                moneyData.put(contraseñaUsuario, saldoUsuario);

                System.out.println("¡Depósito de $" + cantidad + " realizado con éxito!");
                homeMenu(); // Volver al menú principal
                break; // Salir del bucle while si el depósito se realizó correctamente
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor, ingrese una cantidad de dinero válida (número decimal).");
                input.next(); // Limpiar el buffer de entrada
            }
        }
    }

    //!Funcion que permite retirar dinero de la cuenta-------------------------------------------------------------
    static void retirarDinero() {
        double cantidad;
        while (true) {
            try {
                System.out.println("--------------------RETIRAR DINERO--------------------");
                System.out.println("Por favor ingrese la cantidad de dinero que desea retirar:");
                cantidad = input.nextDouble();
    
                // Verificar si la cantidad ingresada es válida
                if (cantidad <= 0) {
                    System.out.println("Error: Por favor, ingrese una cantidad de dinero válida.");
                    continue; // Volver a solicitar la cantidad de dinero
                }
    
                // Verificar si hay suficiente saldo para retirar
                if (cantidad > usuarioActual.getSaldo()) {
                    throw new NotEnoughFunds("Error: No hay suficiente saldo en la cuenta.");
                }
    
                // Actualizar el saldo del usuario
                double saldoActual = usuarioActual.getSaldo();
                saldoActual -= cantidad;
                usuarioActual.setSaldo(saldoActual);
    
                // Actualizar el saldo en la matriz de datos de dinero
                String contraseñaUsuario = usuarioActual.getContraseña();
                double saldoUsuario = moneyData.get(contraseñaUsuario);
                saldoUsuario -= cantidad;
                moneyData.put(contraseñaUsuario, saldoUsuario);
    
                System.out.println("¡Retiro de $" + cantidad + " realizado con éxito!");
                homeMenu(); // Volver al menú principal
                break; // Salir del bucle while si el retiro se realizó correctamente
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor, ingrese un tipo de dato valido");
                input.next(); // Limpiar el buffer de entrada
            } catch (NotEnoughFunds e) {
                System.out.println(e.getMessage());
                System.out.println("Saldo actual: $" + usuarioActual.getSaldo());
                System.out.println("Por favor, ingrese una cantidad igual o menor al saldo disponible.");
            }
        }
    }

    //!Funcion que permite transferir dinero--------------------------------------------------
    static void transferirDinero() {
        while (true) {
            try {
                System.out.println("--------------------TRANSFERIR DINERO--------------------");
                System.out.println("Por favor ingrese el nombre de la cuenta a la que desea transferir el dinero:");
                String nombreDestinatario = input.next();
    
                // Verificar si el nombre del destinatario está en la lista de usuarios
                boolean destinatarioValido = false;
                for (Usuario usuario : usuarios) {
                    if (usuario.getNombre().equals(nombreDestinatario)) {
                        destinatarioValido = true;
                        break;
                    }
                }
    
                if (!destinatarioValido) {
                    throw new UserNotFound("Error: El nombre de usuario no es válido.");
                }
    
                System.out.println("Por favor ingrese la cantidad de dinero que desea transferir:");
                double cantidad = input.nextDouble();
    
                // Verificar si la cantidad ingresada es válida
                if (cantidad <= 0 || cantidad > usuarioActual.getSaldo()) {
                    throw new NotEnoughFunds("Error: Por favor, ingrese una cantidad de dinero válida (menor o igual a su saldo actual).");
                }
    
                // Actualizar el saldo del usuario actual
                double saldoActualUsuario = usuarioActual.getSaldo();
                saldoActualUsuario -= cantidad;
                usuarioActual.setSaldo(saldoActualUsuario);
    
                // Actualizar el saldo del destinatario
                for (Usuario usuario : usuarios) {
                    if (usuario.getNombre().equals(nombreDestinatario)) {
                        double saldoDestinatario = usuario.getSaldo();
                        saldoDestinatario += cantidad;
                        usuario.setSaldo(saldoDestinatario);
                        break;
                    }
                }
    
                // Mostrar mensaje de confirmación
                System.out.println("Transferencia de $" + cantidad + " a " + nombreDestinatario + " realizada con éxito.");
                homeMenu(); // Volver al menú principal
                break; // Salir del bucle while si la transferencia se realizó correctamente
            } catch (InputMismatchException e) {
                System.out.println("Error: Por favor, ingrese una cantidad de dinero válida (número decimal).");
                input.next(); // Limpiar el buffer de entrada
            } catch (UserNotFound e) {
                System.out.println(e.getMessage());
            } catch (NotEnoughFunds e) {
                System.out.println(e.getMessage());
            }
        }
    }

    //!Funcion que muestra el saldo actual------------------------------------------------
    static void imprimirSaldo() {
        System.out.println("--------------------SALDO ACTUAL--------------------");
        System.out.println("El saldo actual de la cuenta de " + usuarioActual.getNombre() + " es: $" + usuarioActual.getSaldo());
        homeMenu(); // Volver al menú principal
    } 
}
