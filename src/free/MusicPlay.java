package free;

import java.io.FileInputStream;

import javax.swing.SwingWorker;

import javazoom.jl.player.Player;

public class MusicPlay extends SwingWorker<String, String> {
	String filePath;

	MusicPlay(String filePath) {
		this.filePath = filePath;
	}

	void playMusic(String filePath) {
		try {
			FileInputStream fis = new FileInputStream(filePath);
			Player playMp3 = new Player(fis);
			playMp3.play();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	@Override
	protected String doInBackground() throws Exception {
		playMusic(filePath);
		return null;
	}
}
