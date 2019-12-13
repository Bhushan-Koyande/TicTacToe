package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.muddzdev.styleabletoastlibrary.StyleableToast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button[][] buttons=new Button[3][3];
    private boolean player1Turn=true;
    private int roundCount;
    private int player1Points;
    private int player2Points;
    private TextView points1;
    private TextView points2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        points1=findViewById(R.id.playerOneScore);
        points2=findViewById(R.id.playerTwoScore);
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                String buttonID="button"+i+j;
                int resID=getResources().getIdentifier(buttonID,"id",getPackageName());
                buttons[i][j]=findViewById(resID);
                buttons[i][j].setOnClickListener(this);
            }
        }
        Button resetButton=findViewById(R.id.reset);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetGame();
            }
        });
    }

    @Override
    public void onClick(View view) {
        if(!((Button)view).getText().toString().equals("")){
            return;
        }
        if(player1Turn){
            ((Button)view).setText("X");
        }else{
            ((Button)view).setText("O");
        }
        roundCount++;
        if(getWinner()) {
            if (player1Turn) {
                player1Win();
            } else {
                player2Win();
            }
        }else if(roundCount==9){
            drawGame();
        }else{
            player1Turn=!player1Turn;
        }
    }

    private boolean getWinner(){
        String[][] field=new String[3][3];
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                field[i][j]=buttons[i][j].getText().toString();
            }
        }
        for (int i=0;i<3;i++){
            if(field[i][0].equals(field[i][1])
                && field[i][0].equals(field[i][2])
                && !field[i][0].equals("")){
                return true;
            }
        }
        for (int i=0;i<3;i++){
            if(field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")){
                return true;
            }
        }
        if(field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")){
            return true;
        }
        if(field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")){
            return true;
        }
        return false;
    }

    private void player1Win(){
        player1Points++;
        //Toast.makeText(this,"Player 1 wins",Toast.LENGTH_SHORT).show();
        showToast("Player 1 wins");
        updatePoints();
        clearGrid();
    }

    private void player2Win(){
        player2Points++;
        //Toast.makeText(this,"Player 2 wins",Toast.LENGTH_SHORT).show();
        showToast("Player 2 wins");
        updatePoints();
        clearGrid();
    }

    private void drawGame(){
        //Toast.makeText(this,"It's a draw",Toast.LENGTH_SHORT).show();
        showToast("It's a draw");
        clearGrid();
    }

    private void updatePoints(){
        String str1=Integer.toString(player1Points);
        String str2=Integer.toString(player2Points);
        points1.setText(str1);
        points2.setText(str2);
    }

    private void resetGame(){
        player1Points=0;
        player2Points=0;
        updatePoints();
        clearGrid();
    }

    private void clearGrid(){
        for (int i=0;i<3;i++){
            for (int j=0;j<3;j++){
                buttons[i][j].setText("");
            }
        }
        roundCount=0;
        player1Turn=true;
    }

    public void showToast(String str){
        StyleableToast.makeText(this,str,R.style.MaterialAlertDialog_MaterialComponents_Title_Icon);
    }
}