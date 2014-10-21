package cn.libgdx.FlappyHelpers;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import cn.libgdx.GameObjects.Bird;
import cn.libgdx.GameWorld.GameWorld;
import cn.libgdx.ui.SimpleButton;

public class InputHandler implements InputProcessor {
	private Bird myBird;
	private GameWorld myWorld;

	private List<SimpleButton> menuButtons;

	private SimpleButton playButton;

	private float scaleFactorX;
	private float scaleFactorY;

	public InputHandler(GameWorld myWorld, float scaleFactorX,
			float scaleFactorY) {
		this.myWorld = myWorld;
		myBird = myWorld.getBird();

		int midPointY = myWorld.getMidPointY();

		this.scaleFactorX = scaleFactorX;
		this.scaleFactorY = scaleFactorY;

		menuButtons = new ArrayList<SimpleButton>();
		playButton = new SimpleButton(
				136 / 2 - (AssetLoader.playButtonUp.getRegionWidth() / 2),
				midPointY + 50, 29, 16, AssetLoader.playButtonUp,
				AssetLoader.playButtonDown);
		menuButtons.add(playButton);
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		screenX = scaleX(screenX);
		screenY = scaleY(screenY);

		if (myWorld.isMenu()) {
			playButton.isTouchDown(screenX, screenY);
		} else if (myWorld.isReady()) {
			myWorld.start();
			myBird.onClick();
		} else if (myWorld.isRunning()) {
			myBird.onClick();
		}

		if (myWorld.isGameOver() || myWorld.isHighScore()) {
			myWorld.restart();
		}

		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		screenX = scaleX(screenX);
		screenY = scaleY(screenY);

		if (myWorld.isMenu()) {
			if (playButton.isTouchUp(screenX, screenY)) {
				myWorld.ready();
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean keyDown(int keycode) {

		// Can now use Space Bar to play the game
		if (keycode == Keys.SPACE) {

			if (myWorld.isMenu()) {
				myWorld.ready();
			} else if (myWorld.isReady()) {
				myWorld.start();
			}

			myBird.onClick();

			if (myWorld.isGameOver() || myWorld.isHighScore()) {
				myWorld.restart();
			}

		}

		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	private int scaleX(int screenX) {
		return (int) (screenX / scaleFactorX);
	}

	private int scaleY(int screenY) {
		return (int) (screenY / scaleFactorY);
	}

	public List<SimpleButton> getMenuButtons() {
		return menuButtons;
	}
}
