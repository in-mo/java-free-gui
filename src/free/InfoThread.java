package free;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingWorker;

public class InfoThread extends SwingWorker<String, String> {
	String currentTime;
	AlarmClass ac;

	public InfoThread(String currentTime, AlarmClass ac) {
		this.currentTime = currentTime;
		this.ac = ac;
	}

	@Override
	protected String doInBackground() throws Exception {
		InfoMain test = new InfoMain(currentTime, ac);
		return null;
	}
}

class InfoMain extends JFrame {
	String currentTime;
	AlarmClass ac;

	public InfoMain(String currentTime, AlarmClass ac) {
		this.currentTime = currentTime;
		this.ac = ac;

		JDialog jd = new JDialog(this);
		jd.setModal(true);
		jd.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		jd.setLocationByPlatform(false);
		jd.pack();

		jd.setTitle("알람");
		jd.setLayout(new FlowLayout());
		JLabel l = new JLabel(currentTime + " 입니다!");
		jd.add(l);

		JButton btn = new JButton("확인");
		btn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ac.getImgLabel()[1].setIcon(new ImageIcon("img/time.png"));
				ac.getBackLabel().setIcon(new ImageIcon("img/background.png"));
				dispose();
			}
		});
		jd.add(btn);

		jd.setSize(new Dimension(250, 110));
		jd.setLocationRelativeTo(ac);
		jd.setResizable(false);
		jd.setVisible(true);
	}
}
