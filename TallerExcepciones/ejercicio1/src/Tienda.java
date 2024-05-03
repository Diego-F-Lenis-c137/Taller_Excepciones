import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import tienda.Article;
import tienda.exeptions.ProductNotFoundException;

public class Tienda {
    private static Scanner input;
    private static ArrayList<Article> articles  = new ArrayList<Article>();
    

    public static void main(String[] args) {
        init();
        menu();        
    }

    public static void showArticles() {
        System.out.println("\033c");
        for (int i = 0; i < articles.size() ; i++) {
            System.out.println((i+1) + " - " + "Articulo: " + articles.get(i).getName() + " Codigo: " + articles.get(i).getCode() + " Cantidad en stock: " + articles.get(i).getStock()); 

        }
        input.next();
    }

    public static void searchArticleByIndex() {
        System.out.println("\033c");
        int index;
        while(true) {
            System.out.println("ingrese el indice del aritculo (lo indices empiezan desde cero)");
            try {
                index  = input.nextInt();
                index--;
                System.out.println(index+1 + " - " + "Articulo: " + articles.get(index).getName() 
                                + " Codigo: " + articles.get(index).getCode() 
                                + " Cantidad en stock: " + articles.get(index).getStock()); 
                break;
            } catch (InputMismatchException e){
                System.out.println("Ingrese un numero valido");                       
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Ingrese un indice dentro del rango de articulos exitentes");
            }
        }
        input.next();
        
    }

    public static void searchArticleByCode() {
        String code;
        Article art = null;
        System.out.println("\033c");
        System.out.println("ingrese el codigo del aritculo");

        code = input.next();

        for (Article s: articles) {
            if (s.getCode().equals(code)) {
                art = s;
            }
        }
        try {
            if (art == null) throw new ProductNotFoundException("Aritculo no encontrado");
            System.out.println("Articulo: " + art.getName() 
                            + " Codigo: " + art.getCode() 
                            + " Cantidad en stock: " + art.getStock()); 
        } catch (ProductNotFoundException e){
            System.out.println(e.getMessage());
        }

        input.next();
        
    }

    public static void addArticle() {
        String name;
        String code;
        int stock;

        System.out.println("\033c");
        System.out.println("      Añadir articulo");
        System.out.println("Ingrese el codigo del aritculo");
        code = input.next();
        System.out.println("Ingrese el nombre del aritculo");
        name = input.next();

        while(true) {
            try { 
                stock = input.nextInt();
                System.out.println("Ingrese la Cantidad de stock del aritculo");
                break; }
            catch (InputMismatchException e) {
                System.out.println("Ingrese un numero");
            }
        }

        articles.add(new Article(code, name, stock));
        System.out.println("articulo añadido correctamente");
        input.next();
    }

    public static void deleteArticle() {
        String code;
        Article art = null;

        System.out.println("\033c");
        System.out.println("      Eliminar articulo");
        System.out.println("ingrese el codigo del aritculo");

        code = input.next();

        for (Article s: articles) {
            if (s.getCode().equals(code)) {
                art = s;
            }
        }
        try {
            if (art == null) throw new ProductNotFoundException("Aritculo no encontrado");
            articles.remove(articles.indexOf(art));
        } catch (ProductNotFoundException e){
            System.out.println(e.getMessage());
        }
        System.out.println("Eliminacion exitosa");

        input.next();
    }

    public static void menu() {
        input = new Scanner(System.in);
        int opt;
        String msg = "";
        boolean shouldExit = false;

        while (!shouldExit) {
            System.out.println("\033c");
            System.out.println("      Bienvenido al Sitema de Inventario de la tienda");
            System.out.println("1 : para buscar articulos por indice");
            System.out.println("2 : para buscar articulos por codigo");
            System.out.println("3 : para mostrar todos los aritculos");
            System.out.println("4 : para añadir articulo");
            System.out.println("5 : para Eliminar articulo");
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
                    searchArticleByIndex();
                    break;
                case 2:
                    searchArticleByCode();
                    break;
                case 3:
                    showArticles();
                    break;
                case 4:
                    addArticle();
                    break;
                case 5:
                    deleteArticle();
                    break;
                case 0:
                    shouldExit = true;
                    break;
            }
        }
        input.close();
    }

    public static void init() {
        articles.add(new Article("111", "Camisa catcher", 32));
        articles.add(new Article("222", "jeans", 16));
        articles.add(new Article("333", "polo", 10));
    }
}
