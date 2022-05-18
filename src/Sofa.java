public class Sofa {
    public String name;
    public String country;
    public int price;

    public Sofa(String name, String country, int price){
        this.name = name;
        this.country = country;
        this.price = price;
    }

    @Override
    public String toString(){
        return "Название: " + name + ", Изготовитель: " + country + ", Цена: " + price;
    }
}
