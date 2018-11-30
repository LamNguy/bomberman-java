package bomberman.Entities;

import bomberman.GameObject;
import bomberman.GameProperties;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.PositionComponent;
import com.almasb.fxgl.entity.components.TypeComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.geometry.Point2D;
import javafx.util.Duration;

/*
 *  Class Singleton bomber
 */
public class Bomber  extends Component {
    private PositionComponent position;
    private static double speed;
    private double SPEED;
    private AnimatedTexture texture;
    private AnimationChannel bomberRight, bomberUp, bomberDown, bomberRightIdle, bomberUpIdle, bomberDownIdle;
    private static Bomber  bomber ;
    private BomberMove typeMove ;
    private  int bombsPlaced = 0;
    private  int maxBombs = 1;
    private  int bombSize =  1 ;
    private  int enemyKilled = 0;
    private boolean powerUpBomb = false;


    public int getEnemyKilled() {
        return enemyKilled;
    }

    public void setEnemyKilled(int enemyKilled) {
        this.enemyKilled = enemyKilled;
    }

    public boolean isPowerUpBomb() {
        return powerUpBomb;
    }

    public int getBombsPlaced() {
        return bombsPlaced;
    }

    public void setBombsPlaced(int bombsPlaced) {
        this.bombsPlaced = bombsPlaced;
    }

    public int getMaxBombs() {
        return maxBombs;
    }
    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public BomberMove getType() {
        return typeMove;
    }

    public void setType(BomberMove type) {
        this.typeMove = type;
    }

    public double getBomberX(){
        return position.getX();
    }

    public void increaseMaxBomb(){
        maxBombs ++ ;
    }

    public void increaseBombSize(){
        bombSize++ ;
    }

    public static Bomber getBomber(){
        if ( bomber == null) bomber = new Bomber();
        return bomber ;
    }

    private Bomber(){
        bomberRight = new AnimationChannel("bomberRight.png", 8, 40, 40, Duration.seconds(2), 0, 7);
        bomberUp = new AnimationChannel("bomberUp.png", 8, 40,  40, Duration.seconds(2), 0, 7);
        bomberDown = new AnimationChannel("bomberDown.png", 8, 40, 40, Duration.seconds(2), 0, 7);
        bomberRightIdle = new AnimationChannel("bomberRight.png", 8, 40, 40, Duration.seconds(0.1), 0, 0);
        bomberUpIdle = new AnimationChannel("bomberUp.png", 8, 40, 40, Duration.seconds(0.1), 0, 0);
        bomberDownIdle = new AnimationChannel("bomberDown.png", 8, 40, 40, Duration.seconds(0.1), 0, 0);

        texture = new AnimatedTexture(bomberRight);
        SPEED = 150 ;
    }


    public void increaseSpeed() { this.speed = this.speed*1.5 ;}



    // show player on map 0
    @Override
    public void onAdded() {
        entity.setView(texture);
    }

    @Override
    public void onUpdate(double tpf) {
        //  chia map thanh he  truc toa do Oxy
        int x = position.getGridX(GameProperties.SIZE_TILES);
        int y = position.getGridY(GameProperties.SIZE_TILES);
        if(speed != 0){

              if((getType() == BomberMove.DOWN || getType() == BomberMove.UP)
                    && (getEntity().getX() - x*GameProperties.SIZE_TILES >= -10)
                    && (getEntity().getX() + getEntity().getWidth() - (x+1)*GameProperties.SIZE_TILES <= 10)){

                if(getType() == BomberMove.UP && texture.getAnimationChannel() != bomberUp)
                    texture.loopAnimationChannel(bomberUp);

                if(getType() == BomberMove.DOWN && texture.getAnimationChannel() != bomberDown)
                    texture.loopAnimationChannel(bomberDown);

                if((-getEntity().getY() + y*GameProperties.SIZE_TILES > 1) && getType() == BomberMove.UP){
                    if(canMove(new Point2D(x*GameProperties.SIZE_TILES, (y-1)*GameProperties.SIZE_TILES))) {
                        entity.translateY(tpf * speed);
                    }
                }
                else if((-getEntity().getY() + y*GameProperties.SIZE_TILES < 1) && getType() == BomberMove.DOWN){
                    if(canMove(new Point2D(x*GameProperties.SIZE_TILES, (y+1)*GameProperties    .SIZE_TILES))){
                        entity.translateY(tpf*speed);
                    }

                }
                else if(canMove(new Point2D(x*GameProperties.SIZE_TILES, (y-1)*GameProperties.SIZE_TILES)) || canMove(new Point2D(x*40, (y+1)*40))){
                    entity.translateY(tpf*speed);
                }
            }

            if((getType() == BomberMove.LEFT || getType() == BomberMove.RIGHT)
                    && (getEntity().getY() + getEntity().getHeight() - (y+1) * GameProperties.SIZE_TILES <= 5)){
                if(texture.getAnimationChannel() != bomberRight)
                    texture.loopAnimationChannel(bomberRight);

                if((-getEntity().getX() + x*GameProperties.SIZE_TILES > 1)
                        && getType() == BomberMove.LEFT){

                    if(canMove(new Point2D((x-1)*GameProperties.SIZE_TILES, y*GameProperties.SIZE_TILES))) {
                        entity.translateX(tpf * speed);
                    }
                }
                else if((-getEntity().getX() + x*GameProperties.SIZE_TILES < 1)
                        && getType() == BomberMove.RIGHT){

                    if(canMove(new Point2D((x+1)*GameProperties.SIZE_TILES, y*GameProperties.SIZE_TILES))){
                        entity.translateX(tpf*speed);
                    }

                }
                else if((canMove(new Point2D((x-1)*GameProperties.SIZE_TILES, y*GameProperties.SIZE_TILES))
                        || canMove(new Point2D((x+1)*GameProperties.SIZE_TILES, y*GameProperties.SIZE_TILES)))){
                    entity.translateX(tpf*speed);
                }
            }

        }
        speed = speed * 0.7;

        if(Math.abs(speed * tpf) < 1){
            if(getType() == BomberMove.RIGHT || getType() == BomberMove.LEFT){
                texture.loopAnimationChannel(bomberRightIdle);
            }
            else if (getType() == BomberMove.UP){
                texture.loopAnimationChannel(bomberUpIdle);
            }
            else if(getType() == BomberMove.DOWN){
                texture.loopAnimationChannel(bomberDownIdle);
            }
        }

    }

    private boolean canMove(Point2D direction) {


        return FXGL.getApp()
                .getGameScene()
                .getViewport()
                .getVisibleArea()
                .contains(direction)

                &&

                FXGL.getApp()
                        .getGameWorld()
                        .getEntitiesAt(direction)
                        .stream()
                        .filter(e -> e.hasComponent(TypeComponent.class))
                        .map(e -> e.getComponent(TypeComponent.class))
                        .noneMatch(type -> type.isType(GameObject.BRICK)
                                || type.isType(GameObject.WALL));
    }

    public void moveRight(){
        setType(BomberMove.RIGHT);
        setSpeed(speed);
        getEntity().setScaleX(1);
    }

    public void moveLeft(){
        setType(BomberMove.LEFT);
        setSpeed(speed);
        getEntity().setScaleX(-1);
    }

    public void moveUp(){
        setType(BomberMove.UP);
        setSpeed(speed);
        getEntity().setScaleY(1);
    }

    public void moveDown(){
        setType(BomberMove.DOWN);
        setSpeed(speed);
        getEntity().setScaleY(1);
    }



    public void placeBomb() {
        if (bombsPlaced == maxBombs) {
            return;
        }
        bombsPlaced++;

        int x = position.getGridX(GameProperties.SIZE_TILES);
        int y = position.getGridY(GameProperties.SIZE_TILES);
    }

}
