package cenaflix;

public class Cenaflix {
    public static void main(String[] args) {
        //cadastroFilme tela1 = new cadastroFilme();
        //tela1.setVisible(true);
        conexaoJDBC c = new conexaoJDBC();
        c.conectar();
        MenuFilmes menu = new MenuFilmes();
        menu.setVisible(true);
    
    
    }
}
