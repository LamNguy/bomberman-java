package bomberman;

import com.almasb.fxgl.app.listener.StateListener;
import com.almasb.fxgl.ui.UIController;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;

public class BombermanUI implements UIController, StateListener {

    @FXML
    AnchorPane root;

    @FXML
    TextArea score;

    @Override
    public void init() {

    }

    @Override
    public void onUpdate(double v) {

    }
}
