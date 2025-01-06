package prova1.lpr.debi;
/**
 *
 * @author 0040838
 */
public class Prova1LPRDebi {

    public static void main(String[] args) {

        Controle controle = new Controle();
        Menu menu = new Menu(controle);
        menu.exibirMenu();
    }
}
