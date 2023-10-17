import java.util.Scanner;

public class GeradorCPF {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite os 9 primeiros dígitos do CPF: ");
        String cpfBase = scanner.nextLine();

        if (cpfBase.length() != 9 || !cpfBase.matches("[0-9]+")) {
            System.out.println("CPF inválido. Deve conter 9 dígitos numéricos.");
            return;
        }

        String cpfCompleto = calcularCPFFinal(cpfBase);

        System.out.println("CPF gerado: " + formatarCPF(cpfCompleto));
    }

    public static String calcularCPFFinal(String cpfBase) {
        int soma = 0;
        int peso = 2;

        for (int i = cpfBase.length() - 1; i >= 0; i--) {
            int digito = Character.getNumericValue(cpfBase.charAt(i));
            soma += digito * peso;
            peso++;
        }

        int dezena = (soma % 11) <= 1 ? 0 : 11 - (soma % 11);

        // Agora, vamos calcular o dígito final (10º dígito)
        cpfBase = cpfBase + dezena; // Adicionando a dezena calculada

        soma = 0;
        peso = 2;

        for (int i = cpfBase.length() - 1; i >= 0; i--) {
            int digito = Character.getNumericValue(cpfBase.charAt(i));
            soma += digito * peso;
            peso++;
        }

        int unidade = (soma % 11) <= 1 ? 0 : 11 - (soma % 11);

        return cpfBase + unidade;
    }

    public static String formatarCPF(String cpf) {
        return String.format("%s.%s.%s-%s", cpf.substring(0, 3), cpf.substring(3, 6), cpf.substring(6, 9), cpf.substring(9, 11));
    }
}
