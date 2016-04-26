import javafx.application.Application;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Main.
 */
public class Main extends Application {
    /**
     * The name of the file where settings are.
     */
    private String fileName = "settings.txt";
    /**
     * Game difficulty (1-7), shows how many blocks are used.
     */
    private int gameDifficulty = 0;
    /**
     * Determines how fast blocks fall (1-10).
     */
    private int gameSpeed = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {

        int[] settingsInts = setSettings();
        gameDifficulty = settingsInts[0];
        gameSpeed = settingsInts[1];
        GameControl gameControl = new GameControl(gameDifficulty, gameSpeed);

        Button settingsButton = gameControl.getSettingsButton();

        Settings settings = new Settings(gameDifficulty, gameSpeed);

        settingsButton.setOnMouseClicked(event -> {
            settings.showSettings();
        });

        Button okButton = settings.getOkButton();
        okButton.setOnMouseClicked(event -> {
            editTextFile(fileName, settings);
            gameDifficulty = setSettings()[0];
            gameSpeed = setSettings()[1];
            gameControl.setSettings(setSettings()[0], setSettings()[1]);
        });

        primaryStage.setTitle("Tetris");
        primaryStage.setScene(gameControl.getScene());
        primaryStage.setResizable(false);
        primaryStage.sizeToScene();
        primaryStage.show();
    }

    /**
     * Program starts here.
     * @param args idk
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The position in the settings string line.
     */
    private static final int GAME_DIFFICULTY_POSITION = 17;
    /**
     * The place in the settings string line.
     */
    private static final int GAME_SPEED_POSITION = 12;
    /**
     * Reads settings.
     * @return settings.
     */
    private int[] setSettings() {
        int[] settings = new int[2];
        try {
            for (String line : readSmallFile(fileName)) {
                try {
                    if (line.contains("Game difficulty: ")) {
                        settings[0] = Integer.parseInt(line.substring(GAME_DIFFICULTY_POSITION));
                    }
                    if (line.contains("Game speed: ")) {
                        settings[1] = Integer.parseInt(line.substring(GAME_SPEED_POSITION));
                    }
                } catch (NumberFormatException e) {
                    System.out.println("error");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return settings;
    }

    /**
     * Reads a textfile.
     * @param filename the file that has to be read
     * @return returns all lines from the file
     * @throws IOException the error that may happen.
     */
    private List<String> readSmallFile(String filename) throws IOException {
        Path path = Paths.get(filename);
        List<String> lines = Files.readAllLines(path);
        return lines;
    }

    /**
     * Writes the necessary stuff in the file.
     * @param fileName FILE NAME
     * @param settings THE NECESSARY SETTINGS
     */
    private void editTextFile(String fileName, Settings settings) {
        try {
            Path path = Paths.get(fileName);
            BufferedWriter writer = Files.newBufferedWriter(path);
            writer.write("Game difficulty: " + settings.getGameDifficulty() + "\n");
            writer.write("Game speed: " + settings.getGameSpeed());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
