import java.util.Scanner;
import java.util.Arrays;

public class JogoDaVelha {

    private static char[][] tabuleiro;
    private static char jogadorAtual;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        tabuleiro = new char[3][3];
        jogadorAtual = 'X';

        // Inicializa o tabuleiro com espaços vazios
        for (char[] linha : tabuleiro) {
            Arrays.fill(linha, ' ');
        }

        System.out.println("Bem-vindo ao Jogo da Velha!");
        boolean jogoContinua = true;

        while (jogoContinua) {
            exibirTabuleiro();
            System.out.println("É a vez do jogador " + jogadorAtual + ".");

            System.out.print("Digite a linha (1-3) e a coluna (1-3) separadas por um espaço: ");
            int linha = scanner.nextInt() - 1;
            int coluna = scanner.nextInt() - 1;

            if (fazerJogada(linha, coluna)) {
                if (verificarVitoria()) {
                    exibirTabuleiro();
                    System.out.println("Parabéns, o jogador " + jogadorAtual + " venceu!");
                    jogoContinua = false;
                } else if (tabuleiroCheio()) {
                    exibirTabuleiro();
                    System.out.println("O jogo terminou em empate!");
                    jogoContinua = false;
                } else {
                    trocarJogador();
                }
            } else {
                System.out.println("Jogada inválida. A posição já está ocupada ou é inválida. Tente novamente.");
            }
        }
        scanner.close();
    }

    private static void exibirTabuleiro() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(tabuleiro[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    private static boolean fazerJogada(int linha, int coluna) {
        if (linha >= 0 && linha < 3 && coluna >= 0 && coluna < 3 && tabuleiro[linha][coluna] == ' ') {
            tabuleiro[linha][coluna] = jogadorAtual;
            return true;
        }
        return false;
    }

    private static boolean verificarVitoria() {
        // Verifica linhas
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] == jogadorAtual && tabuleiro[i][1] == jogadorAtual && tabuleiro[i][2] == jogadorAtual) {
                return true;
            }
        }
        // Verifica colunas
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[0][i] == jogadorAtual && tabuleiro[1][i] == jogadorAtual && tabuleiro[2][i] == jogadorAtual) {
                return true;
            }
        }
        // Verifica diagonais
        if (tabuleiro[0][0] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][2] == jogadorAtual) {
            return true;
        }
        if (tabuleiro[0][2] == jogadorAtual && tabuleiro[1][1] == jogadorAtual && tabuleiro[2][0] == jogadorAtual) {
            return true;
        }
        return false;
    }

    private static boolean tabuleiroCheio() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (tabuleiro[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private static void trocarJogador() {
        if (jogadorAtual == 'X') {
            jogadorAtual = 'O';
        } else {
            jogadorAtual = 'X';
        }
    }
}