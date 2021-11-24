package com.example.tttgameframework.tickettoride;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;

import com.example.tttgameframework.GameFramework.GameMainActivity;
import com.example.tttgameframework.GameFramework.LocalGame;
import com.example.tttgameframework.GameFramework.gameConfiguration.GameConfig;
import com.example.tttgameframework.GameFramework.gameConfiguration.GamePlayerType;
import com.example.tttgameframework.GameFramework.infoMessage.GameState;
import com.example.tttgameframework.GameFramework.players.GamePlayer;
import com.example.tttgameframework.R;
import com.example.tttgameframework.tickettoride.infoMessage.TTRState;
import com.example.tttgameframework.tickettoride.players.TTRComputerPlayer0;
import com.example.tttgameframework.tickettoride.players.TTRComputerPlayer1;
import com.example.tttgameframework.tickettoride.players.TTRHumanPlayer;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class TTRMainActivity extends GameMainActivity {

    //Tag for logging (Copied from TTT main, not sure if we need)
    private static final String TAG = "TTTMainActivity";
    public static final int PORT_NUMBER = 5213;

    //music / soundFX
    private MediaPlayer thomas;
    private MediaPlayer mariahCarey;
    private MediaPlayer roll;
    private MediaPlayer themeSong;
    private MediaPlayer error;
    private MediaPlayer success;

    @Override
    public GameConfig createDefaultConfig() {
        //music
        thomas = MediaPlayer.create(this,R.raw.thomas);
        mariahCarey = MediaPlayer.create(this,R.raw.mariahcarey);
        roll = MediaPlayer.create(this,R.raw.roll);
        themeSong = MediaPlayer.create(this,R.raw.beatles);
        error = MediaPlayer.create(this,R.raw.error);
        success = MediaPlayer.create(this,R.raw.success);



        //Define allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        playerTypes.add(new GamePlayerType("Local Human Player") {
        public GamePlayer createPlayer(String name){return new TTRHumanPlayer(name,thomas,mariahCarey, roll, themeSong, error, success); }
        });

        playerTypes.add(new GamePlayerType("Computer Player (dumb)") {
        public GamePlayer createPlayer(String name){return new TTRComputerPlayer0(name); }
        });

        playerTypes.add(new GamePlayerType("Computer Player (smart)") {
            public GamePlayer createPlayer(String name){return new TTRComputerPlayer1(name); }
        });

        GameConfig defaultConfig = new GameConfig(playerTypes, 2, 4, "Ticket To Ride", PORT_NUMBER);
        defaultConfig.addPlayer("Human", 0);
        defaultConfig.addPlayer("Computer", 1);
        defaultConfig.setRemoteData("Remote Human player", "", 0); //not used
        return defaultConfig;
    }

    @Override
    public LocalGame createLocalGame(GameState gameState) {
        return new TTRLocalGame();
    }

}