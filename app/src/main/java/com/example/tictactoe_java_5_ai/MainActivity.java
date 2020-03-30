package com.example.tictactoe_java_5_ai;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button_tic);
        Button button_ai = findViewById(R.id.button_tic_ai);
        Button button_ai_win = findViewById(R.id.button_tic_ai_win);


        button.setOnClickListener(this);
        button_ai.setOnClickListener(this);
        button_ai_win.setOnClickListener(this);
    }




    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.button_tic) {
            startActivity(new Intent(this, TicTacToe_Game.class));
        } else if (v.getId() == R.id.button_tic_ai) {
            startActivity((new Intent(this, TicTacToe_AI.class)));
            // block of code to be executed if the condition1 is false and condition2 is true
        } else if (v.getId() == R.id.button_tic_ai_win) {
            startActivity((new Intent(this, TicTacToeAiWin.class)));
            // block of code to be executed if the condition1 is false and condition2 is true
        } else {
            System.out.println("Error - should not happen !" );
        }
    }

}


