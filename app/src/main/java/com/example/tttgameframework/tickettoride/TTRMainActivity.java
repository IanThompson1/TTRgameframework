package com.example.tttgameframework.tickettoride;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.tttgameframework.GameFramework.GameMainActivity;
import com.example.tttgameframework.GameFramework.LocalGame;
import com.example.tttgameframework.GameFramework.gameConfiguration.GameConfig;
import com.example.tttgameframework.GameFramework.infoMessage.GameState;
import com.example.tttgameframework.R;

public class TTRMainActivity extends GameMainActivity {

    @Override
    public GameConfig createDefaultConfig() {
        return null;
    }

    @Override
    public LocalGame createLocalGame(GameState gameState) {
        return null;
    }


}