package bomberman;

import bomberman.Entities.Bomber;
import bomberman.Entities.BomberControl;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.Level;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.parser.text.TextLevelParser;
import com.almasb.fxgl.settings.GameSettings;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;

import java.util.Random;

public class MainControl extends GameApplication {

    public static Entity getBomber() {
        return bomber;
    }
    private static Bomber bomberControl  ;
    private static Entity bomber;
   // private static Bomber bomberControl;
    private BombermanUI bombermanUI;
    private static boolean requestNewGame = false ;
    private static boolean requestEndGame = false ;


    /*
     *  CREATE GAME WINDOW
     */
    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setTitle(GameProperties.TITLE);
        gameSettings.setVersion(GameProperties.VERSION);
        gameSettings.setWidth(GameProperties.WIDTH);
        gameSettings.setHeight(GameProperties.HEIGHT);
    }


    /*
     *  MUSIC
     */
    @Override
    protected void preInit()
    {
        Random random = new Random();
        int i = random.nextInt(5);
        getAudioPlayer().loopBGM("mainScene" + i + ".wav");

    }

    /*
     *    End game and start new game
     */

    @Override
    protected void onPostUpdate ( double tpf){
        if  ( requestEndGame) getDisplay().showMessageBox("You win !", this :: exit);

        if (requestNewGame ){
            requestNewGame = false ;
            startNewGame();
        }
    }

    private static void getDeath(){
        requestNewGame = true ;
    }

    private static void gameVictory(){
        requestEndGame = true ;
    }



    /*
     *  LOAD BACKGROUND
     */


    @Override
    protected void initGame() {


        // load map from text
        GameFactory factory= new GameFactory();
        getGameWorld().addEntityFactory(factory);
        TextLevelParser levelParser = new TextLevelParser(factory);
        Level level = levelParser.parse("levels/Level1.txt");
        getGameWorld().setLevel(level);
        loadBackGround();

        //  load Player
        bomber = getGameWorld().spawn("Player", GameProperties.SIZE_TILES, GameProperties.SIZE_TILES);
        bomberControl = bomber.getComponent(Bomber.class);
        getGameScene().getViewport().setBounds(-1500,0, 1500, getHeight());
       // getGameScene().getViewport().bindToEntity(bomber, getWidth()/3, getHeight()/2);
    }

    // ?????
    private void loadBackGround(){
        for(int i = 0; i < 31; i++){
            for(int j = 0; j < 13; j ++){
                int finalI = i;
                int finalJ = j;
                getGameWorld()
                        .getEntitiesAt(new Point2D(i * GameProperties.SIZE_TILES, j * GameProperties.SIZE_TILES))
                        .stream()
                        .forEach(e->{
                            if(e.isType(GameObject.ENEMY)){
                                getGameWorld().spawn("Grass", finalI * GameProperties.SIZE_TILES, finalJ * GameProperties.SIZE_TILES);
                            }
                        });
            }
        }
    }

    /*
     *   CONTROL BOMBER PLAYER
     */
    @Override
    protected void initInput() {
        getInput().addAction(new UserAction("Move Up") {
            @Override
            protected void onAction() {
                bomberControl.moveUp();
            }
        }, KeyCode.UP);

        getInput().addAction(new UserAction("Move Left") {
            @Override
            protected void onAction() {
                bomberControl.moveLeft();
            }
        }, KeyCode.LEFT);

        getInput().addAction(new UserAction("Move Down") {
            @Override
            protected void onAction() {
                bomberControl.moveDown();
            }
        }, KeyCode.DOWN);

        getInput().addAction(new UserAction("Move Right") {
            @Override
            protected void onAction() {
                bomberControl.moveRight();
            }
        }, KeyCode.RIGHT);

        getInput().addAction(new UserAction("Place Bomb") {
            @Override
            protected void onAction() {
                bomberControl.placeBomb();
            }
        }, KeyCode.SPACE);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
