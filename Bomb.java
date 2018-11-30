package bomberman.items;

import bomberman.MainControl;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;


public class Bomb extends Component {
    private double timeToExplode;
    private Entity bomber;

    public Bomb(){
        this.timeToExplode = 2;
        bomber = MainControl.getBomber();
    }
}
