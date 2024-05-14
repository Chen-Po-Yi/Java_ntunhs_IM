import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class hw11 extends JFrame {
    private JButton[][] buttons;
    private JLabel turnLabel;

    private char currentPlayer = 'X';

    public hw11() {
        setTitle("Tic Tac Toe");
        setSize(320, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a 3x3 grid layout
        setLayout(new BorderLayout());

        JPanel boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));

        // Initialize the buttons
        buttons = new JButton[3][3];
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col] = new JButton("");
                buttons[row][col].setFont(new Font("Arial", Font.PLAIN, 80));
                int finalRow = row;
                int finalCol = col;
                buttons[row][col].addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        buttonClicked(finalRow, finalCol);
                    }
                });
                boardPanel.add(buttons[row][col]);
            }
        }

        add(boardPanel, BorderLayout.CENTER);

        // Label to show current player's turn
        turnLabel = new JLabel("Current turn: " + currentPlayer);
        add(turnLabel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void buttonClicked(int row, int col) {
        JButton clickedButton = buttons[row][col];
        if (clickedButton.getText().equals("")) {
            clickedButton.setText(Character.toString(currentPlayer));
            if (checkWin()) {
                JOptionPane.showMessageDialog(this, "Player " + currentPlayer + " wins!");
                resetBoard();
            } else if (checkDraw()) {
                JOptionPane.showMessageDialog(this, "It's a draw!");
                resetBoard();
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
                turnLabel.setText("Current turn: " + currentPlayer);
            }
        }
    }

    private boolean checkWin() {
        // Implement your win condition checking logic here
        return false;
    }

    private boolean checkDraw() {
        // Implement your draw condition checking logic here
        return false;
    }

    private void resetBoard() {
        // Reset button texts and current player
        currentPlayer = 'X';
        turnLabel.setText("Current turn: " + currentPlayer);
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                buttons[row][col].setText("");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new hw11();
            }
        });
    }
}

