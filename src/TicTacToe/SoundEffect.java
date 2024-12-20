/**
 * ES234317-Algorithm and Data Structures
 * Semester Ganjil, 2024/2025
 * Group Capstone Project
 * Group #11
 * 1 - 5026231069 - Muhammad Zaky Al Khair
 * 2 - 5026231068 - Nailah Adlina
 * 3 - 5026231173 - Naura Salsabila
 */
ackage TicTacToe;

import java.io.IOException;
import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public enum SoundEffect {
    GAME_START("TicTacToe/bgmusic.wav", true), // Background music (looping)
    GAME_WON("TicTacToe/win.wav", false),      // Winning sound
    GAME_LOST("TicTacToe/lose.wav", false),    // Losing sound
    GAME_DRAW("TicTacToe/seri.wav", false);    // Draw sound

    public static enum Volume {
        MUTE, LOW, MEDIUM, HIGH
    }

    public static Volume volume = Volume.LOW;

    public Clip clip;
    private boolean shouldLoop; // Flag to determine if this sound should loop

}
