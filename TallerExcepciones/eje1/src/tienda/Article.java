package tienda;
public class Article {
    public String code;
    public String name;
    public int stock;

    public String getCode() {
        return code;
    }

    public Article(String code, String name, int stock) {
        this.code = code;
        this.name = name;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public int getStock() {
        return stock;
    }
}  
