import java.util.Scanner;

public class Exemplo3 {
    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);
        int idade = ler.nextInt();
        if (idade >= 18) {
            System.out.println("Adulto");
        } else if (idade < 18) {
            System.out.println("Adolescente");
        }else {
            System.out.println("Infantil");
        }
        ler.close();
    }
}
