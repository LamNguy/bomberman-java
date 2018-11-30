package bomberman.items;

import com.almasb.fxgl.app.FXGL;
import com.almasb.fxgl.entity.component.Component;

public class Item extends Component {
    public Item(){
        FXGL.getApp()
                .getGameWorld()
                .spawn("Powerup Bomb");

    }
}