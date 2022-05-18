import java.util.ArrayList;
import java.util.Objects;
import java.util.stream.Collectors;

public class SofaList {
    ArrayList<Sofa> lst = new ArrayList<Sofa>();

    SofaList() {

    }

    SofaList(ArrayList<Sofa> sofas) {
        lst = sofas;
    }

    public void insert(String name, String country, int price) {
        lst.add(new Sofa(name, country, price));
    }

    public void insert(String name, String country, String price) {
        lst.add(new Sofa(name, country, Integer.parseInt(price)));
    }

    public void insert(Sofa sofa) {
        lst.add(sofa);
    }

    public ArrayList<Sofa> getLst() {
        return lst;
    }

    public SofaList sortList(int col, String r) {
        boolean reverse = r.equals(">");
        ArrayList<Sofa> newlst = lst;
        switch (col) {
            case (0):
                newlst = (ArrayList<Sofa>) lst.stream().sorted((o1, o2) -> {
                    if (reverse) return (o1.name.compareTo(o2.name)) ;
                    else return (o2.name.compareTo(o1.name));
                }).collect(Collectors.toList());
                System.out.println(newlst.toString());
                break;
            case (1):
                newlst = (ArrayList<Sofa>) lst.stream().sorted((o1, o2) -> {
                    if (reverse) return (o1.name.compareTo(o2.name));
                    else return (o2.name.compareTo(o1.name));
                }).collect(Collectors.toList());
                break;
            case (2):
                newlst = (ArrayList<Sofa>) lst.stream().sorted((o1, o2) -> {
                    if (reverse) return o1.price - o2.price;
                    else return o2.price - o1.price;
                }).collect(Collectors.toList());
                break;
            default:
                break;
        }
        return new SofaList(newlst);
    }

    public SofaList filterList(int col, String sim, String s) {
        ArrayList<Sofa> newlst = lst;
        switch (col) {
            case 0:
                newlst = (ArrayList<Sofa>) lst.stream().filter(sofa -> Objects.equals(
                        sofa.name, s)).collect(Collectors.toList());

                break;
            case 1:
                newlst = (ArrayList<Sofa>) lst.stream().filter(sofa -> Objects.equals(
                        sofa.country, s)).collect(Collectors.toList());
                break;
            case 2:
                switch (sim) {
                    case "=":
                        newlst = (ArrayList<Sofa>) lst.stream().filter(sofa -> Objects.equals(
                                sofa.price, Integer.parseInt(s))).collect(Collectors.toList());
                        break;
                    case "<":
                        newlst = (ArrayList<Sofa>) lst.stream().filter(sofa -> (
                                sofa.price < Integer.parseInt(s))).collect(Collectors.toList());
                        break;
                    case ">":
                        newlst = (ArrayList<Sofa>) lst.stream().filter(sofa -> (
                                sofa.price > Integer.parseInt(s))).collect(Collectors.toList());
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
        return new SofaList(newlst);
    }

    public void remove(int id) {
        lst.remove(id);
    }

    public void change(int id, String name, String country, String price) {
        lst.set(id, new Sofa(name, country, Integer.parseInt(price)));
    }
}
