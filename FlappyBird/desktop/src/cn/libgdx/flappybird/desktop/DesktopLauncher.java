package cn.libgdx.flappybird.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import cn.libgdx.flappybird.FlappyBird;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "ÏñËØÄñ";
		cfg.useGL30 = false;
		cfg.width = 1080 / 3;
		cfg.height = 1920 / 3;
		new LwjglApplication(new FlappyBird(), cfg);
	}
}
