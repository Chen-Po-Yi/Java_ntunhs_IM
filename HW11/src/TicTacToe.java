import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TicTacToe extends JFrame {
    private char[][] board;
    private JButton[][] buttons;
    private char currentPlayer; // Added to keep track of current player
    private JLabel statusLabel; // Added to display current player status

    public TicTacToe() {
        super("Tic Tac Toe");
        setSize(300, 350); // Increased height to accommodate status label
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Initialize the board with initial values
        board = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }

        // Set initial player
        currentPlayer = 'X'; // Assuming player X starts first

        // Create buttons
        buttons = new JButton[3][3];
        JPanel panel = new JPanel(new GridLayout(3, 3));
        ActionListener buttonListener = new ButtonListener();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new JButton();
                buttons[i][j].setFont(new Font("Arial", Font.BOLD, 48));
                buttons[i][j].addActionListener(buttonListener);
                panel.add(buttons[i][j]);
            }
        }

        // Create status label
        statusLabel = new JLabel("Current player: " + currentPlayer);
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // Add components to the frame
        add(panel, BorderLayout.CENTER);
        add(statusLabel, BorderLayout.SOUTH);

        setVisible(true);
    }

    // ActionListener for buttons
    private class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton clickedButton = (JButton) e.getSource();
            int row = -1;
            int col = -1;
            // Find the clicked button's position
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (buttons[i][j] == clickedButton) {
                        row = i;
                        col = j;
                        break;
                    }
                }
            }
            // Update the game board and GUI
            if (row != -1 && col != -1 && board[row][col] == ' ') {
                board[row][col] = currentPlayer;
                clickedButton.setText(String.valueOf(currentPlayer));
                if (checkWinner(currentPlayer)) {
                    JOptionPane.showMessageDialog(null, "Player " + currentPlayer + " wins!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                    resetGame();
                } else if (isBoardFull()) {
                    JOptionPane.showMessageDialog(null, "It's a tie!", "Game Over", JOptionPane.INFORMATION_MESSAGE);
                    resetGame();
                } else {
                    currentPlayer = (currentPlayer == 'X') ? 'O' : 'X'; // Switch players
                    statusLabel.setText("Current player: " + currentPlayer); // Update status label
                }
            }
        }
    }

    // Check for a winner
    private boolean checkWinner(char player) {
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player)
                return true; // Row check
            if (board[0][i] == player && board[1][i] == player && board[2][i] == player)
                return true; // Column check
        }

        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player)
            return true; // Diagonal 1
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player)
            return true; // Diagonal 2

        return false;
    }

    // Check if the board is full
    private boolean isBoardFull() {
        // Check if any cell is empty
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ')
                    return false;
            }
        }
        return true; // Board is full
    }

    // Reset the game
    private void resetGame() {
        // Clear the board and reset the current player
        currentPlayer = 'X';
        statusLabel.setText("Current player: " + currentPlayer); // Reset status label
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
                buttons[i][j].setText("");
            }
        }
    }

    // Main method to start the game
    public static void main(String[] args) {
        new TicTacToe();
    }
}
