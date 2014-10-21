package cn.libgdx.flappybird;

import com.badlogic.gdx.Game;
import cn.libgdx.Screens.SplashScreen;
import cn.libgdx.FlappyHelpers.AssetLoader;

public class FlappyBird extends Game {

	@Override
	public void create() {
		AssetLoader.load();
		setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
		AssetLoader.dispose();
	}

}