package com.example.tictactoe_java_5_ai;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class TicTacToe_Game extends AppCompatActivity implements View.OnClickListener {

    TextView f11, f12, f13, f21, f22, f23, f31, f32, f33;
    String currentPlayer = "X";
    int[][] gameStorage;
    private TextView statusText;
    String gameState = "playing";

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
        if (gameState != "playing"){
            finishGame();
        }
        //Wie kann ich herausfinden, ob ein Feld leer ist - oder was darin steht?
        // == >> ((TextView) v).getText()
        //
        // Ausgabe: "Was steht im Feld?
        // statusText.setText("==>" +((TextView) v).getText() + "<==");
        // statusText.setText("==>" + "".equals(((TextView) v).getText()) + "<==");


        if (gameState.equals("playing") && "".equals(((TextView) v).getText())){
            // wir tun nur etwas, wenn 1) das Feld leer ist UND das Spiel auf "playing" steht!
            switch (v.getId()){
                case R.id.f0:
                    f11.setText(currentPlayer);
                    handleInput(1, 1);
                    break;

                case R.id.f1:
                    f12.setText(currentPlayer);
                    handleInput(1, 2);
                        /* old code...
                        if(f12.getText() == ""){
                        }
                         */
                    break;

                case R.id.f2:
                    f13.setText(currentPlayer);
                    handleInput(1, 3);
                    break;

                case R.id.f3:
                    f21.setText(currentPlayer);
                    handleInput(2, 1);
                    break;

                case R.id.f4:
                    f22.setText(currentPlayer);
                    handleInput(2, 2);
                    break;

                case R.id.f5:
                    f23.setText(currentPlayer);
                    handleInput(2, 3);
                    break;

                case R.id.f6:
                    f31.setText(currentPlayer);
                    handleInput(3, 1);
                    break;

                case R.id.f7:
                    f32.setText(currentPlayer);
                    handleInput(3, 2);
                    break;

                case R.id.f8:
                    f33.setText(currentPlayer);
                    handleInput(3, 3);
                    break;
            }
        }
    }


    private void handleInput(int x, int y) {
        if (gameStorage[x-1][y-1] == 0 && (!checkFieldfull())){
            if (currentPlayer.equals("X")){
                gameStorage[x-1][y-1] = 1;
                //currentPlayer = "O";
            } else {
                gameStorage[x-1][y-1] = -1;
                //currentPlayer = "X";
            }
        } else {
            statusText.setText("Unentschieden!");
            gameState = "tie";

        }

        if (checkGameEnd()){
            statusText.setText("Spieler " +currentPlayer + " hat gewonnen");
            gameState = "won";
        } else if (checkFieldfull()) {
            statusText.setText("Unentschieden!");
            gameState = "tie";
        } else {
            if(currentPlayer == "X"){
                currentPlayer = "O";
            } else {
                currentPlayer = "X";
            }
            statusText.setText("Spieler " +currentPlayer + " ist am Zug.");
        }
    }


    private boolean checkFieldfull() {
        return    (Math.abs(gameStorage[0][0]) + Math.abs(gameStorage[0][1]) +
                Math.abs(gameStorage[0][2]) + Math.abs(gameStorage[1][0]) +
                Math.abs(gameStorage[1][1]) + Math.abs(gameStorage[1][2]) +
                Math.abs(gameStorage[2][0]) + Math.abs(gameStorage[2][1]) +
                Math.abs(gameStorage[2][2]) == 9);
    }


    private boolean checkGameEnd() {
        return    (Math.abs(gameStorage[0][0] + gameStorage[0][1] +gameStorage[0][2]) == 3
                || Math.abs(gameStorage[1][0] + gameStorage[1][1] +gameStorage[1][2]) == 3
                || Math.abs(gameStorage[2][0] + gameStorage[2][1] +gameStorage[2][2]) == 3
                || Math.abs(gameStorage[0][0] + gameStorage[1][0] +gameStorage[2][0]) == 3
                || Math.abs(gameStorage[0][1] + gameStorage[1][1] +gameStorage[2][1]) == 3
                || Math.abs(gameStorage[0][2] + gameStorage[1][2] +gameStorage[2][2]) == 3
                || Math.abs(gameStorage[0][0] + gameStorage[1][1] +gameStorage[2][2]) == 3
                || Math.abs(gameStorage[0][2] + gameStorage[1][1] +gameStorage[2][0]) == 3);
    }


    private void finishGame() {
        if (currentPlayer.equals("O")){
            Toast.makeText(getApplicationContext(), "O hat gewonnen: Neues Spiel?", Toast.LENGTH_LONG).show();
        } else if (gameState.equals("won")) {
            Toast.makeText(getApplicationContext(), "X hat gewonnen! Neues Spiel?", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), "Unentschieden! Neues Spiel?", Toast.LENGTH_LONG).show();
        }


        // slow down...
        // mit anonymer Klasse...
        final Handler handler = new Handler();

        final TicTacToe_Game ticTacToe_game = this;
        //final MainActivity mainActivity = this;

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Do something after 5s = 5000ms
                Intent intent = new Intent (ticTacToe_game, MainActivity.class);
                startActivity(intent);
                ticTacToe_game.finish();

            }
        }, 3000);
    }
}








