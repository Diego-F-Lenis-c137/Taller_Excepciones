import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import utils.Booking;
import utils.Flight;
import utils.exeptions.FlightNotFound;
import utils.exeptions.FlightOutOfSeats;

public class AirLine {
    public static Scanner input;
    public static ArrayList<Flight> flights;
    public static ArrayList<Booking> bookings;

    public static void main(String[] args) {
        init();
        menu();
    }

    public static void showBookings() {
        System.out.println("ingrese cualquier caracter para continuar");
        for (int i =0; i < bookings.size() ; i++) {
            System.out.println(i+1 + " - Nombre: " + bookings.get(i).getPassengerName()+ " | " 
                                          + " Codigo de vuelo: " + bookings.get(i).getFligthCode()+ " | ");
        }
        input.next();
    } 

    public static void showFlights() {
        System.out.println("ingrese cualquier caracter para continuar");
        for (int i =0; i < flights.size() ; i++) {
            System.out.println(i+1 + " - Codigo: " + flights.get(i).getCode() + " | " 
                                          + " Origen: " + flights.get(i).getOrigin()+ " | "
                                          + " Destino: " + flights.get(i).getDestiny()+ " | " 
                                          + " Capacidad: " + flights.get(i).getCapacity()+ " | "
                                          + " Puestos disponibles: " + flights.get(i).getCapacity()+ " | ");
        }
        input.next();
    } 

    public static void reserveFlight() {
        String code, name;
        Flight Sflight = null;
        System.out.println("\033c");
        showFlights();

        System.out.println("ingrese El codigo del vuelo que desea reservar\nIngrese 0 si desea cancelar");
        while (true) {
            try {
                code = input.next();
                if (code.equals("0")) break;
                for(Flight f : flights) {
                    if (f.getCode().equals(code)) Sflight = f;
                }
                if (Sflight == null) throw new FlightNotFound("Vuelo no encontrado asegurece de que el codigo este bien escrito");
                if (Sflight.getCapacity() == Sflight.getSeatsOcupied()) throw new FlightOutOfSeats("No quedan puestos disponibles en este vuelo");

                System.out.println("Vuelo encontrado");
                System.out.println("Igrese su nombre para reservar el vuelo");
                name = input.next();

                bookings.add(new Booking(Sflight.getCode(), name));
                flights.get(flights.indexOf(Sflight)).setCapacity(Sflight.getSeatsOcupied()+1);

                break;
            } catch (FlightNotFound e) {
                System.out.println(e.getMessage());
            } catch (FlightOutOfSeats e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public static void menu() {
        input = new Scanner(System.in);
        int opt;
        String msg = "";
        boolean shouldExit = false;

        while (!shouldExit) {
            System.out.println("\033c");
            System.out.println("      Bienvenido al Sitema de Inventario de la tienda");
            System.out.println("1 : para reservar vuelos");
            System.out.println("2 : para mostrar todos los vuelos");
            System.out.println("3 : para mostrar las reservas");
            System.out.println("0 : para salir");
            if (msg != "") {
                System.out.println("Error: " + msg);
                msg = "";
            }
            
            try {
                opt = input.nextInt();
            } catch (InputMismatchException e) {
                input.next();
                msg = "Por favor ingrese un numero valido";
                opt = -1;
            }
            
            switch (opt) {
                case 1:
                    reserveFlight();
                    break;
                case 2:
                    showFlights();
                    break;
                case 3:
                    showBookings();
                    break;
                case 0:
                    shouldExit = true;
                    break;
            }
        }
        input.close();
    }

    public static void init() {
        flights = new ArrayList<Flight>();
        bookings = new ArrayList<Booking>();
        flights.add(new Flight("123", "Colombia", "Mexico", 100, 32));
        flights.add(new Flight("111", "Colombia", "Ecuador", 100, 32));
        flights.add(new Flight("222", "Colombia", "Estados unidos", 100, 32));
    }
}
