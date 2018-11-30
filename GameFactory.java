package bomberman;

import bomberman.Entities.Bomber;
import bomberman.items.Bomb;
import bomberman.items.ItemType;
import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.*;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import javafx.util.Duration;

import static com.almasb.fxgl.app.DSLKt.texture;

public class GameFactory implements TextEntityFactory {


    // LOAD PLAYER
    @Spawns("Player")
    public Entity newPlayer(SpawnData data) {
        return Entities.builder()
                .from(data)
                .type(GameObject.PLAYER)
                .bbox(new HitBox(BoundingShape.box(GameProperties.SIZE_TILES, GameProperties.SIZE_TILES)))
                .with(new CollidableComponent(true))
                .with(Bomber.getBomber())
                .build();

    }

    // LOAD INIT WALL COMPONENT
    @SpawnSymbol('#')
    public Entity wall(SpawnData data) {
        return Entities.builder()
                .type(GameObject.WALL)
                .from(data)
                .renderLayer(RenderLayer.BACKGROUND)
                .viewFromNodeWithBBox(FXGL.getAssetLoader().loadTexture("wall.png", GameProperties.SIZE_TILES , GameProperties.SIZE_TILES ))
                .build();
    }

    // LOAD INIT BRICK COMPONENT
    @SpawnSymbol('*')
    public Entity brick(SpawnData data) {
        return Entities.builder()
                .type(GameObject.BRICK)
                .from(data)
                .renderLayer(RenderLayer.BACKGROUND)
                .viewFromNodeWithBBox(FXGL.getAssetLoader().loadTexture("brick.png", GameProperties.SIZE_TILES , GameProperties.SIZE_TILES ))
                .build();
    }

    // LOAD INIT GRASS COMPONENT
    @SpawnSymbol(' ')
    public Entity grass(SpawnData data) {
        return Entities.builder()
                .type(GameObject.GRASS)
                .from(data)
                .renderLayer(RenderLayer.BACKGROUND)
                .viewFromNodeWithBBox(FXGL.getAssetLoader().loadTexture("grass.png", GameProperties.SIZE_TILES , GameProperties.SIZE_TILES ))
                .build();
    }

    @SpawnSymbol('p')
    public Entity grassPlayer(SpawnData data) {
        return Entities.builder()
                .type(GameObject.GRASS)
                .from(data)
                .renderLayer(RenderLayer.BACKGROUND)
                .viewFromNodeWithBBox(FXGL.getAssetLoader().loadTexture("grass.jpg", GameProperties.SIZE_TILES , GameProperties.SIZE_TILES ))
                .build();
    }

    // LOAD PORTAL COMPONENT
    @SpawnSymbol('x')
    public Entity portal(SpawnData data) {
        return Entities.builder()
                .type(GameObject.PORTAL)
                .from(data)
                .viewFromNodeWithBBox(FXGL.getAssetLoader().loadTexture("portal.png", GameProperties.SIZE_TILES , GameProperties.SIZE_TILES ))
                .with(new CollidableComponent(true))
                .build();
    }

    // LOAD ITEM POWER UP
    @SpawnSymbol('f')
    public Entity newPowerUpBomb(SpawnData data) {
        return Entities.builder()
                .type(ItemType.POWERUPBOMB)
                .from(data)
                .viewFromNodeWithBBox(FXGL.getAssetLoader().loadTexture("powerup_bombs.png", GameProperties.SIZE_TILES , GameProperties.SIZE_TILES ))
                .with(new CollidableComponent(true))
                .build();
    }

    // LOAD BOMB ITEM
    @Spawns("Bomb")
    public Entity newBomb(SpawnData data) {
        return Entities.builder()
                .type(GameObject.BOMB)
                .from(data)
                .viewFromNodeWithBBox(FXGL.getAssetLoader().loadTexture("bomb.png", GameProperties.SIZE_TILES , GameProperties.SIZE_TILES ))
                .with(new Bomb())
                .build();
    }

    /*
    @SpawnSymbol('1')
    public Entity grass1(SpawnData data) {
        return Entities.builder()
                .type(BombermanType.ENEMY)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(Game.SIZE_TILES, Game.SIZE_TILES)))
                .with(new CollidableComponent(true))
                .with(new EnemyBFS())
                .viewFromNodeWithBBox(FXGL.getAssetLoader().loadTexture("water1.png", Game.SIZE_TILES , Game.SIZE_TILES ))
                .build();
    } */

    /*
    @Spawns("Grass")
    public Entity newGrass(SpawnData data) {
        return Entities.builder()
                .type(BombermanType.GRASS)
                .from(data)
                .viewFromNodeWithBBox(FXGL.getAssetLoader().loadTexture("grass.jpg", Game.SIZE_TILES , Game.SIZE_TILES ))
                .renderLayer(RenderLayer.BOTTOM)
                .build();
    } */


    /*
    @SpawnSymbol('2')
    public Entity newEnemy(SpawnData data){
        return Entities.builder()
                .from(data)
                .type(BombermanType.ENEMY)
                .bbox(new HitBox(BoundingShape.box(Game.SIZE_TILES, Game.SIZE_TILES)))
                .viewFromNodeWithBBox(FXGL.getAssetLoader().loadTexture("baloon1.png", Game.SIZE_TILES , Game.SIZE_TILES ))
                .with(new CollidableComponent(true))
                .with(new EnemyRandom())
                .build();
    } */

    @Spawns("centerExplode")
    public Entity newCenterExploe(SpawnData data){
        return Entities.builder()
                .type(GameObject.EXPLODE)
                .from (data)
                .bbox(new HitBox(BoundingShape.box(GameProperties.SIZE_TILES, GameProperties.SIZE_TILES)))
                .viewFromAnimatedTexture(texture("centerExplode1.png", GameProperties.SIZE_TILES, GameProperties.SIZE_TILES).toAnimatedTexture(1, Duration.seconds(0.5)), false, true)
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("topExplodeX")
    public Entity newTopExploeX(SpawnData data){
        return Entities.builder()
                .type(GameObject.EXPLODE)
                .from (data)
                .bbox(new HitBox(BoundingShape.box(GameProperties.SIZE_TILES, GameProperties.SIZE_TILES)))
                .viewFromAnimatedTexture(texture("topExplode1.png", GameProperties.SIZE_TILES , GameProperties.SIZE_TILES - 2).toAnimatedTexture(1, Duration.seconds(0.5)), false, true)
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("topExplodeY")
    public Entity newTopExploeY(SpawnData data){
        return Entities.builder()
                .type(GameObject.EXPLODE)
                .from (data)
                .bbox(new HitBox(BoundingShape.box(GameProperties.SIZE_TILES - 2, GameProperties.SIZE_TILES - 2)))
                .viewFromAnimatedTexture(texture("topExplode2.png", GameProperties.SIZE_TILES, GameProperties.SIZE_TILES).toAnimatedTexture(1, Duration.seconds(0.5)), false, true)
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("midExplodeX")
    public Entity newMidExploeX(SpawnData data){
        return Entities.builder()
                .type(GameObject.EXPLODE)
                .from (data)
                .bbox(new HitBox(BoundingShape.box(GameProperties.SIZE_TILES, GameProperties.SIZE_TILES)))
                .viewFromAnimatedTexture(texture("midExplode1.png", GameProperties.SIZE_TILES, GameProperties.SIZE_TILES).toAnimatedTexture(1, Duration.seconds(0.5)), false, true)
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("midExplodeY")
    public Entity newMidExploeY(SpawnData data){
        return Entities.builder()
                .type(GameObject.EXPLODE)
                .from (data)
                .bbox(new HitBox(BoundingShape.box(GameProperties.SIZE_TILES, GameProperties.SIZE_TILES)))
                .viewFromAnimatedTexture(texture("midExplode2.png", GameProperties.SIZE_TILES, GameProperties.SIZE_TILES).toAnimatedTexture(1, Duration.seconds(0.5)), false, true)
                .with(new CollidableComponent(true))
                .build();
    }

    @Override
    public char emptyChar() {
        return ' ';
    }

    @Override
    public int blockWidth() {
        return GameProperties.SIZE_TILES;
    }

    @Override
    public int blockHeight() {
        return GameProperties.SIZE_TILES;
    }
}
