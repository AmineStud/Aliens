package controller;

public interface AudioPlayerInterface {

    public static final String BACKGROUND_MUSIC_FILE = "BackgroundMusic.mp3" ;
    public static final String SHOOTING_MUSIC_FILE = "shoot.mp3";
    public static final String DESTRUCTION_MUSIC_FILE = "dest.mp3";

    String getShootingSoundFilePath();
    
    String getDestructionSoundFilePath();
    
    String getBackgroundMusicFilePath();
    void playBackgroundMusic();
    void stopBackgroundMusic();
    boolean isPlayingBackgroundMusic();

    void playSound(String soundname, int looptimes);
    
    void playShootingSound();
    boolean getShootingSoundPlayed();


    void playDestructionSound();
    boolean getDestructionSoundPlayed();
    
}
