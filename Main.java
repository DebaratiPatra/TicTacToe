class Board {
    private char[][] board;
    private static final int SIZE = 3;

    public Board() {
        board = new char[SIZE][SIZE];
        initializeBoard();
    }

    public void initializeBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = '-';
            }
        }
    }

    public boolean isFull() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == '-') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean placeMove(int row, int col, char symbol) {
        if (row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == '-') {
            board[row][col] = symbol;
            return true;
        }
        return false;
    }

    public void displayBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean checkWin(char symbol) {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < SIZE; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) {
                return true;
            }
            if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) {
            return true;
        }

        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) {
            return true;
        }

        return false;
    }
}

class Player {
    private String name;
    private char symbol;

    public Player(String name, char symbol) {
        this.name = name;
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public char getSymbol() {
        return symbol;
    }
}

class Game {
    private Board board;
    private Player player1;
    private Player player2;
    private Player currentPlayer;

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.board = new Board();
    }

    public void start() {
        boolean gameWon = false;
        boolean isTie = false;

        while (!gameWon && !isTie) {
            board.displayBoard();
            int row, col;
            // Assuming valid input for simplicity
            // In actual implementation, you would need to handle invalid inputs
            System.out.println(currentPlayer.getName() + "'s turn. Enter row and column:");
            row = new java.util.Scanner(System.in).nextInt();
            col = new java.util.Scanner(System.in).nextInt();

            if (board.placeMove(row, col, currentPlayer.getSymbol())) {
                gameWon = board.checkWin(currentPlayer.getSymbol());

                if (gameWon) {
                    board.displayBoard();
                    System.out.println(currentPlayer.getName() + " wins!");
                } else if (board.isFull()) {
                    board.displayBoard();
                    System.out.println("The game is a tie!");
                    isTie = true;
                } else {
                    switchPlayer();
                }
            } else {
                System.out.println("Invalid move. Try again.");
            }
        }
    }

    private void switchPlayer() {
        if (currentPlayer == player1) {
            currentPlayer = player2;
        } else {
            currentPlayer = player1;
        }
    }
}

public class TicTacToe {
    public static void main(String[] args) {
        Player player1 = new Player("Player 1", 'X');
        Player player2 = new Player("Player 2", 'O');

        Game game = new Game(player1, player2);
        game.start();
    }
}
