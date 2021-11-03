package com.example.tttgameframework.tickettoride;

import androidx.appcompat.app.AppCompatActivity;

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

public class TTRMainActivity extends GameMainActivity {

    //Tag for logging (Copied from TTT main, not sure if we need)
    private static final String TAG = "TTTMainActivity";
    public static final int PORT_NUMBER = 5213;

    @Override
    public GameConfig createDefaultConfig() {

        //Define allowed player types
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        playerTypes.add(new GamePlayerType("Local Human Player") {
        public GamePlayer createPlayer(String name){return new TTRHumanPlayer(name); }
        });

        playerTypes.add(new GamePlayerType("Computer Player (dumb)") {
        public GamePlayer createPlayer(String name){return new TTRComputerPlayer0(name); }
        });

        playerTypes.add(new GamePlayerType("Computer Player (smart)") {
            public GamePlayer createPlayer(String name){return new TTRComputerPlayer1(name); }
        });

        GameConfig defaultConfig = new GameConfig(playerTypes, 2, 2, "Ticket To Ride", PORT_NUMBER);
        defaultConfig.addPlayer("Human", 0);
        defaultConfig.addPlayer("Computer", 1);
        defaultConfig.setRemoteData("Remote Human player", "", 0); //not used

        return defaultConfig;
    }

    @Override
    public LocalGame createLocalGame(GameState gameState) { return new TTRLocalGame(); }


}