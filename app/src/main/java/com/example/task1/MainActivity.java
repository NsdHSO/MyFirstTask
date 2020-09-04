package com.example.task1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons = new Button[3][3];
    private TextView scorePlayer1;
    private TextView scorePlayer2;
    private TextView equality;
    private int playerPuncte1;
    private int playerPuncte2;
    private boolean firstRound = true;
    private int equalityNumber;
    private int numberOfLaps;

    /*
        (                           )
        |                           |
        |   |     |      |      |   |
        |   |_00__|_01___|__02__|   |
        |   |     |      |      |   |
        |   |_10__|_11___|__12__|   |
        |   |     |      |      |   |
        |   |_20__|_21___|__22__|   |
        |                           |
        (                           )
        combinet True =    Line
                        00 - 01 - 02
                        10 - 11 - 12
                        20 - 21 -22
                            Col
                        00 - 10 - 20
                        01 - 11 - 21
                        02 - 12 - 22

                            extra
                        00 - 11 - 22
                        20 - 11 - 02
            only eight combinations are possible


     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scorePlayer1 = findViewById(R.id.Player1);
        scorePlayer2 = findViewById(R.id.Player2);
        equality = findViewById(R.id.Equality);
        buttons[0][0] = findViewById(R.id.button_00);
        buttons[0][1] = findViewById(R.id.button_01);
        buttons[0][2] = findViewById(R.id.button_02);
        buttons[1][0] = findViewById(R.id.button_10);
        buttons[1][1] = findViewById(R.id.button_11);
        buttons[1][2] = findViewById(R.id.button_12);
        buttons[2][0] = findViewById(R.id.button_20);
        buttons[2][1] = findViewById(R.id.button_21);
        buttons[2][2] = findViewById(R.id.button_22);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
//                String getButtonsID = "button_" + i + j;
//                int resId;
//                resId = getResources().getIdentifier(getButtonsID, "id", getPackageName());
//                buttons[i][j] = findViewById(resId);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Button reset = findViewById(R.id.Reset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });


    }

    @Override
    public void onClick(View view) {
        Log.i("test","Works Yee");
        if (!((Button) view).getText().toString().equals("")) {
            return;
        }
        if (firstRound) {
            ((Button) view).setText("X");
        } else {
            Random random = new Random();
            int cpuMove = random.nextInt(9);
                ((Button) view).setText("O");

        }
        numberOfLaps++;
        if (checkWhichPlayerWin()) {
            if (firstRound) {
                writePlayer1();
                resetCells();


            } else {
                writePlayer2();
                resetCells();
            }
        } else if (numberOfLaps == 9) {
            equality();
            updateEqality();
            updateScore();
        } else {
            firstRound = !firstRound;
        }
    }

    public boolean checkWhichPlayerWin() {
        String[][] combined = new String[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                combined[i][j] = buttons[i][j].getText().toString();
            }
            // Check Line
        }
        for (int i = 0; i < 3; i++) {
            if (combined[i][0].equals(combined[i][1]) && combined[i][0].equals(combined[i][2]) && !combined[i][0].equals("")) {
                return true;
            }
        }
        // Check Col
        for (int i = 0; i < 3; i++) {
            if (combined[0][i].equals(combined[1][i]) && combined[0][i].equals(combined[2][i]) && !combined[0][i].equals("")) {
                return true;
            }
        }
        // Extra main diagonal
        if (combined[0][0].equals(combined[1][1]) && combined[0][0].equals(combined[2][2]) && !combined[0][0].equals("")) {
            return true;
        }
        // Second diagonal
        if (combined[0][2].equals(combined[1][1]) && combined[0][2].equals(combined[2][0]) && !combined[0][2].equals("")) {
            return true;
        }


        return false;
    }

    public void writePlayer1() {
        Toast.makeText(this, "First Player win", Toast.LENGTH_SHORT).show();
        playerPuncte1++;
        updateScore();
    }

    public void writePlayer2() {
        Toast.makeText(this, "Second Player win", Toast.LENGTH_SHORT).show();
        playerPuncte2++;
        updateScore();
    }

    private void equality() {
        Toast.makeText(this, "Egalitate!", Toast.LENGTH_SHORT).show();
        resetCells();
    }

    private void resetCells() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
            }
        }
        numberOfLaps = 0;
        firstRound = true;
    }

    private void resetGame() {
        playerPuncte1 = 0;
        playerPuncte2 = 0;
        equalityNumber = 0;
        updateScore();
        resetCells();
    }

    private void updateScore() {
        scorePlayer1.setText("Player1 : " + playerPuncte1);
        scorePlayer2.setText("Player2 : " + playerPuncte2);
        equality.setText("Equality: " + equalityNumber);


    }

    private void updateEqality() {
        equalityNumber++;

    }
}

