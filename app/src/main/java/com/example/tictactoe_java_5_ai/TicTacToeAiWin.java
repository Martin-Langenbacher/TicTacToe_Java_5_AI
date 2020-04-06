package com.example.tictactoe_java_5_ai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;



public class TicTacToeAiWin extends AppCompatActivity implements View.OnClickListener {

    TextView f11, f12, f13, f21, f22, f23, f31, f32, f33;
    String currentPlayer = "X";
    int[][] gameStorage;
    private TextView statusText;
    String gameState = "playing";
    String winnerString = "";
    Board b = new Board();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_toe__game);

        // idHeadline ist der Head-Line-String !
        statusText = findViewById(R.id.idHeadline);

        f11 = findViewById(R.id.f0);
        f12 = findViewById(R.id.f1);
        f13 = findViewById(R.id.f2);
        f21 = findViewById(R.id.f3);
        f22 = findViewById(R.id.f4);
        f23 = findViewById(R.id.f5);
        f31 = findViewById(R.id.f6);
        f32 = findViewById(R.id.f7);
        f33 = findViewById(R.id.f8);

        f11.setOnClickListener(this);
        f12.setOnClickListener(this);
        f13.setOnClickListener(this);
        f21.setOnClickListener(this);
        f22.setOnClickListener(this);
        f23.setOnClickListener(this);
        f31.setOnClickListener(this);
        f32.setOnClickListener(this);
        f33.setOnClickListener(this);

        gameStorage = new int[3][3];
    }



    @SuppressLint("ResourceType")
    @Override
    public void onClick(View v) {
        // Start bei Click...
        if (!gameState.equals("playing")) {
            finishGame();
        }

        if (gameState.equals("playing") && "".equals(((TextView) v).getText())) {
            boolean moveOk = true;
            // wir tun nur etwas, wenn 1) das Feld leer ist UND das Spiel auf "playing" steht!
            switch (v.getId()) {
                case R.id.f0:
                    f11.setText(currentPlayer);
                    moveOk = b.placeAMove(new Point(0,0), Board.PLAYER_X);
                    handleInput(1, 1);
                    break;

                case R.id.f1:
                    f12.setText(currentPlayer);
                    b.placeAMove(new Point(0,1), Board.PLAYER_X);
                    //handleInput(1, 2);
                    break;

                case R.id.f2:
                    f13.setText(currentPlayer);
                    b.placeAMove(new Point(0,2), Board.PLAYER_X);
                    //handleInput(1, 3);
                    break;

                case R.id.f3:
                    f21.setText(currentPlayer);
                    b.placeAMove(new Point(1,0), Board.PLAYER_X);
                    //handleInput(2, 1);
                    break;

                case R.id.f4:
                    f22.setText(currentPlayer);
                    b.placeAMove(new Point(1,1), Board.PLAYER_X);
                    //handleInput(2, 2);
                    break;

                case R.id.f5:
                    f23.setText(currentPlayer);
                    b.placeAMove(new Point(1,2), Board.PLAYER_X);
                    //handleInput(2, 3);
                    break;

                case R.id.f6:
                    f31.setText(currentPlayer);
                    b.placeAMove(new Point(2, 0), Board.PLAYER_X);
                    //handleInput(3, 1);
                    break;

                case R.id.f7:
                    f32.setText(currentPlayer);
                    b.placeAMove(new Point(2,1), Board.PLAYER_X);
                    //handleInput(3, 2);
                    break;

                case R.id.f8:
                    f33.setText(currentPlayer);
                    b.placeAMove(new Point(2  ,2), Board.PLAYER_X);
                    //handleInput(3, 3);
                    break;
            }
            //inCaseOfWinnerDo();
            inCaseOfWinnerDo(Board.PLAYER_X);

            if (gameState.equals("playing")){
                statusText.setText("Vor MiniMax - aiMove!");
                currentPlayer = "O";
                //=========================================================>> Computer-Move:
                //aiMove();
                // 1) gehe in minimax: Werte: a) Level=0 und b) PLAYER_O = 2
                b.minimax4(0, Board.PLAYER_O);
                int a = b.minimax4(0, Board.PLAYER_O);

                // 2) placeAMove: Werte: a) Punkt und b) PLAYER_O = 2
                b.placeAMove(b.computerMove, Board.PLAYER_O);
                printPositionOnBoard(b.computerMove);

                // 3) displayBoard)
                //printPositionOnBoard(b.computerMove);
                // ==================> Show in Display !!!! ===================================================<<<
                //inCaseOfWinnerDo();
                inCaseOfWinnerDo(Board.PLAYER_O);
                currentPlayer = "X";
                statusText.setText("Spieler " +currentPlayer + " ist am Zug.");
                //statusText.setText("Nach aiMove: " + currentPlayer);

            }
        }
    }





    /*






//$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ E N D E ............
*/

//    =============================================handle Input===================

    private void handleInput(int x, int y) {

        // Wert eintragen
        if (gameStorage[x - 1][y - 1] == 0 && (!allFieldsFull())) {
            if (currentPlayer.equals("X")) {
                gameStorage[x - 1][y - 1] = 1;
            } else {
                gameStorage[x - 1][y - 1] = -1;
            }
        } else {
            statusText.setText("Fehler: handle Input!");
        }
    }



    private void inCaseOfWinnerDo() {
        if (isThereAWinner()){
            statusText.setText("Spieler " +currentPlayer + " hat gewonnen");
            gameState = "won";
            winnerString = currentPlayer;
            finishGame();
        } else if (allFieldsFull()){
            statusText.setText("Unentschieden!");
            gameState = "tie";
            winnerString = "tie";
            finishGame();
        } else {
            statusText.setText("Fehler: handle Input!");
        }
    }




        private void inCaseOfWinnerDo(int player) {
            if (b.hasPlayerWon(player)){
                statusText.setText("Spieler " +currentPlayer + " hat gewonnen");
                gameState = "won";
                winnerString = currentPlayer;
                finishGame();

            // allFieldsFull...
            } else if (b.getAvailableCells().isEmpty()){
                statusText.setText("Unentschieden!");
                gameState = "tie";
                winnerString = "tie";
                finishGame();
            } else {
                statusText.setText("Fehler: handle Input!");
            }
        }







//    =============================================Matrix: End of Game / Felder voll===================

    private boolean allFieldsFull() {
        return    (Math.abs(gameStorage[0][0]) + Math.abs(gameStorage[0][1]) +
                Math.abs(gameStorage[0][2]) + Math.abs(gameStorage[1][0]) +
                Math.abs(gameStorage[1][1]) + Math.abs(gameStorage[1][2]) +
                Math.abs(gameStorage[2][0]) + Math.abs(gameStorage[2][1]) +
                Math.abs(gameStorage[2][2]) == 9);
    }



    private boolean isThereAWinner() {
        return    (Math.abs(gameStorage[0][0] + gameStorage[0][1] +gameStorage[0][2]) == 3
                || Math.abs(gameStorage[1][0] + gameStorage[1][1] +gameStorage[1][2]) == 3
                || Math.abs(gameStorage[2][0] + gameStorage[2][1] +gameStorage[2][2]) == 3
                || Math.abs(gameStorage[0][0] + gameStorage[1][0] +gameStorage[2][0]) == 3
                || Math.abs(gameStorage[0][1] + gameStorage[1][1] +gameStorage[2][1]) == 3
                || Math.abs(gameStorage[0][2] + gameStorage[1][2] +gameStorage[2][2]) == 3
                || Math.abs(gameStorage[0][0] + gameStorage[1][1] +gameStorage[2][2]) == 3
                || Math.abs(gameStorage[0][2] + gameStorage[1][1] +gameStorage[2][0]) == 3);
    }



//    =============================================AI=======================================
/*
    private void aiMove() {
        //currentPlayer = "O";
        int bestScore = 99999;
        int x = 3;
        int y = 1;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                // is the field available?

                if (gameStorage[i][j] == 0) {
                    gameStorage[i][j] = -1;//board[i][j] = ai;  ==> What is ai ? --> ai = "O"
                    //int score = miniMax2(gameStorage); // --> O an nächster Stelle eingesetzt...
                    int score = miniMax(gameStorage, 0, false, "O"); // miniMax
                    gameStorage[i][j] = 0;//board[i][j] = 0;

                    if (score < bestScore){
                        bestScore = score;
                        x = i;
                        y = j;
                        //bestScore = Math.min(score, bestScore);
                    }
                }
            }

        }
        printPositionOnBoard(x+1, y+1);

        statusText.setText("AI fertig: X-Turn !");
    }






    // miniMax: ============================================================================
    private int miniMax(int[][] gameStorage, int depth, boolean isMaximizing, String playerForMiniMax) {
        // if (gameState != "playing") {
        if (isThereAWinner()) {
            return getScore(playerForMiniMax);
        }
        if (isMaximizing) {
            int bestScore = -99999;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // is the field available
                    if (gameStorage[i][j] == 0) {
                        gameStorage[i][j] = 1;
                        int score = miniMax(gameStorage, depth + 1, false, "O");
                        gameStorage[i][j] = 0;
                        bestScore = Math.max(score, bestScore);
                    }
                }
            }
            return bestScore;
        } else {
            int bestScore = 99999;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    // is the field available
                    if (gameStorage[i][j] == 0){
                        gameStorage[i][j] = -1;
                        int score = miniMax(gameStorage, depth +1, true, "X");
                        gameStorage[i][j] = 0;
                        bestScore = Math.min(score, bestScore);
                    }
                }
            }
            return bestScore;
        }
    }




    private int miniMax2(int[][] gameStorage) {
        return 1;
    }


*/

//    =============================================AI Positioning===================

    //private void printPositionOnBoard(int x, int y) {
    private void printPositionOnBoard(Point point) {

        //statusText.setText("x: " +x + "y= " + y);

        int x = point.x;
        int y = point.y;

        if (x == 1 && y == 1) {
            f11.setText(currentPlayer);
            b.placeAMove(new Point(0  ,0), Board.PLAYER_O);
            //handleInput(1, 1);
        } else if (x == 1 && y == 2) {
            f12.setText(currentPlayer);
            b.placeAMove(new Point(0  ,1), Board.PLAYER_O);
            //handleInput(1, 2);

        } else if (x == 1 && y == 3) {
            f13.setText(currentPlayer);
            b.placeAMove(new Point(0  ,2), Board.PLAYER_O);
            //handleInput(1, 3);

        } else if (x == 2 && y == 1) {
            f21.setText(currentPlayer);
            b.placeAMove(new Point(1  ,0), Board.PLAYER_O);
            //handleInput(2, 1);

        } else if (x == 2 && y == 2) {
            f22.setText(currentPlayer);
            b.placeAMove(new Point(1  ,1), Board.PLAYER_O);
            //handleInput(2, 2);

        } else if (x == 2 && y == 3) {
            f23.setText(currentPlayer);
            b.placeAMove(new Point(1  ,2), Board.PLAYER_O);
            //handleInput(2, 3);

        } else if (x == 3 && y == 1) {
            f31.setText(currentPlayer);
            b.placeAMove(new Point(2  ,0), Board.PLAYER_O);
            //handleInput(3, 1);

        } else if (x == 3 && y == 2) {
            f32.setText(currentPlayer);
            b.placeAMove(new Point(2  ,1), Board.PLAYER_O);
            //handleInput(3, 2);

        } else if (x == 3 && y == 3) {
            f33.setText(currentPlayer);
            b.placeAMove(new Point(2  ,2), Board.PLAYER_O);
            //handleInput(3, 3);

        } else {
            f33.setText("F");
        }
    }













    // get score...
    private int getScore(String winnerStringMiniMax) {
        int scoreForMinMax;

        if (winnerStringMiniMax.equals("X")) {
            scoreForMinMax = 1;
        } else if (winnerStringMiniMax.equals("O")) {
            scoreForMinMax = -1;
        } else {
            scoreForMinMax = 0;
        }
        return scoreForMinMax;
    }



// Game is finished !

    private void finishGame() {
        if (currentPlayer.equals("O")){
            Toast.makeText(getApplicationContext(), "O hat gewonnen: Neues Spiel?", Toast.LENGTH_LONG).show();
        } else if (gameState.equals("won")) {
            Toast.makeText(getApplicationContext(), "X hat gewonnen! Neues Spiel?", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Unentschieden! Neues Spiel?", Toast.LENGTH_LONG).show();
        }


        // slow down... >>> =============================================slow down at the END===================
        // mit anonymer Klasse...
        final Handler handler = new Handler();

        final TicTacToeAiWin ticTacToeAiWin = this;

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                Intent intent = new Intent (ticTacToeAiWin, MainActivity.class);
                startActivity(intent);
                ticTacToeAiWin.finish();

            }
        }, 3000);
    }

}





























//=========================================================================================================================





/*

        // get score...
        private int getScore(String winnerStringMiniMax) {
            int scoreForMinMax;

            if (winnerStringMiniMax.equals("X")) {
                scoreForMinMax = 1;
            } else if (winnerStringMiniMax.equals("O")) {
                scoreForMinMax = -1;
            } else {
                scoreForMinMax = 0;
            }
            return scoreForMinMax;
        }

*/






/*

    public boolean isGameOver() {
        return hasPlayerWon(PLAYER_X) || hasPlayerWon(PLAYER_O) || getAvailableCells().isEmpty();
    }





    // player can be either 1(X) or 2(O)...
    public boolean hasPlayerWon(int player) {
        if ((board[0][0] == board[1][1] && board[0][0] == board[2][2] && board[0][0] == player)
                ||
                (board[0][2] == board[1][1] && board[0][2] == board[2][0] && board[0][2] == player)
        ) {
            return true;
        }

        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == board[i][1] && board[i][0] == board[i][2] && board[i][0] == player)
                    ||
                    (board[0][i] == board[1][i] && board[0][i] == board[2][i] && board[0][i] == player)
            ) {
                return true;
            }
        }
        return false;
    }

*/
