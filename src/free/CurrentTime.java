package free;

import java.text.SimpleDateFormat;

import javax.swing.SwingWorker;

public class CurrentTime extends SwingWorker<String, String> {
	AlarmClass ac;

	CurrentTime(AlarmClass ac) {
		this.ac = ac;
	}

	@Override
	protected String doInBackground() throws Exception {

		SimpleDateFormat format = new SimpleDateFormat("yyyy년 M월d일 H시m분 s초");
		while (true) {
			try {
				String current_time = format.format(System.currentTimeMillis());
				ac.setTime(current_time);
				ac.compareTime(current_time);
				
				String[] str1 = current_time.split(" ");
				String[] str2 = str1[3].split("초");
				ac.setTimeLabel(str2[0]);
				
				Thread.sleep(1000);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
	}
}
