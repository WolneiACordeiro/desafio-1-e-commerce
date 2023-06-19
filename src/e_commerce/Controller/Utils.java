package e_commerce.Controller;

import java.util.Scanner;

import static java.lang.System.in;
import static java.lang.System.out;

public class Utils {
    Scanner scanner = new Scanner(in);
    public void confirmAction(int option, Runnable method) {
        do {
            boolean validOption = false;
            while (!validOption) {
                out.println("SELECIONE UMA OPÇÃO: (1 - SIM) e OPÇÃO (2 - NÃO)");
                try {
                    option = Integer.parseInt(scanner.nextLine());
                    if ((option != 1) && (option != 2)) {
                        out.println("Escolha apenas entre a OPÇÃO (1 - SIM) e OPÇÃO (2 - NÃO)");
                    } else {
                        validOption = true;
                    }
                } catch (NumberFormatException e) {
                    out.println("A únicas opções são os valores numéricos inteiros: OPÇÃO (1 - SIM) e OPÇÃO (2 - NÃO)");
                }
            }
            switch (option) {
                case 1:
                    method.run();
                    break;
                case 2:
                    out.println("RETORNAR");
                    out.println("#############################");
                    break;
                default:
                    out.println("Opção inválida");
                    break;
            }
        } while ((option != 2) && (option != 1));
    }

}
