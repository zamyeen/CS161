/**
 * Author: Zareef Amyeen
 * Date: 
 */

import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageButton[] buttons = new ImageButton[6];
    int[] buttonState = new int[buttons.length];
    int[] dieImages = new int[buttons.length];
    int[] dieValue = new int[buttons.length];
    final int HOT_DIE = 0;
    final int SCORE_DIE = 1;
    final int LOCKED_DIE = 2;
    Button roll, score, stop;
    TextView currentScoreTxt, totalScoreTxt, currentRoundTxt;
    int currentScore, totalScore, currentRound = 1;

    /*
     * Creating the interface
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttons[0] = (ImageButton) this.findViewById(R.id.imageButton1);
        buttons[1] = (ImageButton) this.findViewById(R.id.imageButton2);
        buttons[2] = (ImageButton) this.findViewById(R.id.imageButton3);
        buttons[3] = (ImageButton) this.findViewById(R.id.imageButton4);
        buttons[4] = (ImageButton) this.findViewById(R.id.imageButton5);
        buttons[5] = (ImageButton) this.findViewById(R.id.imageButton6);
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].setOnClickListener(this);
            buttons[i].setEnabled(false);
            buttons[i].setBackgroundColor(Color.LTGRAY);
        }
        roll = (Button) this.findViewById(R.id.button1);
        roll.setOnClickListener(this);
        score = (Button) this.findViewById(R.id.button2);
        score.setOnClickListener(this);
        score.setEnabled(false);
        stop = (Button) this.findViewById(R.id.button3);
        stop.setOnClickListener(this);
        stop.setEnabled(false);
        currentScoreTxt = (TextView) this.findViewById(R.id.textView1);
        totalScoreTxt = (TextView) this.findViewById(R.id.textView2);
        currentRoundTxt = (TextView) this.findViewById(R.id.textView3);
        dieImages[0] = R.drawable.one;
        dieImages[1] = R.drawable.two;
        dieImages[2] = R.drawable.three;
        dieImages[3] = R.drawable.four;
        dieImages[4] = R.drawable.five;
        dieImages[5] = R.drawable.six;
    }

    /*
     * When anything is clicked run this method
     */
    public void onClick(View v) {
        /*
         * if the user clicks "roll", randomly assign
         * a value to all hot dice. Disable all buttons
         * except score, and the dice themselves.
         */
        if (v.equals(roll)) {
            for (int i = 0; i < buttons.length; i++) {
                if (buttonState[i] == HOT_DIE) {
                    int rand = (int)(Math.random() * 6); // note rand is a value from 0-5
                    dieValue[i] = rand;
                    buttons[i].setImageResource(dieImages[rand]);
                    buttons[i].setEnabled(true);
                    score.setEnabled(true);
                    roll.setEnabled(false);
                    stop.setEnabled(false);
                }
            }
        }
        /*
         * if the user clicks score (which is only available
         * after rolling), check to see if the selected dice
         * are valid. If they are, assign the score. If no
         * dice are selected, forfeit points. If invalid dice
         * are selected, alert the user.
         */
        else if (v.equals(score)) {
            int[] valueCount = new int[6];
            boolean diceSelected = false;
            for (int i = 0; i < buttonState.length; i++) if (buttonState[i] == SCORE_DIE) valueCount[dieValue[i]]++;
            for (int i = 0; i < valueCount.length; i++) diceSelected = diceSelected || valueCount[i] > 0;
            if ((valueCount[1] > 0 && valueCount[1] < 3) || (valueCount[2] > 0 && valueCount[2] < 3) || (valueCount[3] > 0 && valueCount[3] < 3) || (valueCount[5] > 0 && valueCount[5] < 3)) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("Invalid die selected!");
                alertDialogBuilder.setMessage("You can only select scoring dice.").setCancelable(false)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.dismiss();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            } else if (!diceSelected) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("No dice selected!");
                alertDialogBuilder.setMessage("Forfeit score and go to next round?").setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                currentScore = 0;
                                currentScoreTxt.setText("Current Score: " + currentScore);
                                currentRound++;
                                currentRoundTxt.setText("Round: " + currentRound);
                                for (int i = 0; i < buttons.length; i++) {
                                    buttons[i].setEnabled(false);
                                    buttonState[i] = HOT_DIE;
                                    buttons[i].setBackgroundColor(Color.LTGRAY);
                                } roll.setEnabled(true);
                                score.setEnabled(false);
                                stop.setEnabled(false);
                                dialog.dismiss();
                            }
                        }).setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            } else {
                if (valueCount[0] < 3) currentScore += valueCount[0] * 100;
                else currentScore += (valueCount[0] - 2) * 1000;
                if (valueCount[4] < 3) currentScore += valueCount[4] * 50;
                for (int i = 1; i < valueCount.length; i++) if (valueCount[i] >= 3) currentScore += (i + 1) * 100 * (valueCount[i] - 2);
                currentScoreTxt.setText("Current Score: " + currentScore);
                for (int i = 0; i < buttonState.length; i++) {
                    if (buttonState[i] == SCORE_DIE) {
                        buttonState[i] = LOCKED_DIE;
                        buttons[i].setBackgroundColor(Color.BLUE);
                        buttons[i].setEnabled(false);
                    }
                } int lockedCount = 0;
                for (int b: buttonState) lockedCount = b == LOCKED_DIE ? lockedCount + 1 : lockedCount;
                if (lockedCount == 6) {
                    for (int i = 0; i < buttonState.length; i++) {
                        buttonState[i] = HOT_DIE;
                        buttons[i].setBackgroundColor(Color.LTGRAY);
                    }
                } roll.setEnabled(true);
                stop.setEnabled(true);
                score.setEnabled(false);
            }
        }
        /*
         * If the user chooses to stop the round,
         * add all existing points to total score
         * and reset all the dice.
         */
        else if (v.equals(stop)) {
            totalScore += currentScore;
            currentScore = 0;
            currentScoreTxt.setText("Current Score: " + currentScore);
            totalScoreTxt.setText("Total Score: " + totalScore);
            currentRound++;
            currentRoundTxt.setText("Round: " + currentRound);
            for (int i = 0; i < buttons.length; i++) {
                buttons[i].setEnabled(false);
                buttonState[i] = HOT_DIE;
                buttons[i].setBackgroundColor(Color.LTGRAY);
            } roll.setEnabled(true);
            score.setEnabled(false);
            stop.setEnabled(false);
        } else {
            for (int i = 0; i < buttons.length; i++) {
                if (v.equals(buttons[i])) {
                    if (buttonState[i] == HOT_DIE) {
                        buttons[i].setBackgroundColor(Color.RED);
                        buttonState[i] = SCORE_DIE;
                    } else {
                        buttons[i].setBackgroundColor(Color.LTGRAY);
                        buttonState[i] = HOT_DIE;
                    }
                }
            }
        }
    }
}
