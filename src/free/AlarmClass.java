package free;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class AlarmClass extends JFrame {
	private Container c;
	private JLabel time = new JLabel();
	private JComboBox<String>[] calendar = new JComboBox[4];
	private ArrayList<String> alarm = new ArrayList<String>();
	private DefaultListModel<String> model = new DefaultListModel<>();
	private JList list;
	private JButton[] btn = new JButton[4];
	private String audioPath = "music/1.mp3";
	private JLabel[] imgLabel = new JLabel[2];
	private JLabel backLabel = new JLabel();
	private ImageIcon[] timeImg = new ImageIcon[9];

	public AlarmClass() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
		setLayout(null);

		setLabel();
		setComboBox();
		setList();
		setTimeImg();
		setBtn();

		backLabel.setBounds(0, 0, 580, 490);
		backLabel.setIcon(new ImageIcon("img/background.png"));
		add(backLabel);

		setSize(580, 490);
		setVisible(true);
		CurrentTime ct = new CurrentTime(this);
		ct.execute();
	}

	JLabel getBackLabel() {
		return backLabel;
	}

	void setLabel() {
		time.setBounds(30, 100, 300, 40);
		time.setHorizontalAlignment(JLabel.CENTER);
		time.setFont(new Font("�޸�����ü", Font.BOLD, 20));
		add(time);

		String[] str1 = { "현재 날짜", "에약 날짜", "예약 목록" };
		JLabel[] disLabel = new JLabel[3];
		for (int i = 0; i < disLabel.length; i++) {
			disLabel[i] = new JLabel(str1[i]);
			if (i < 2)
				disLabel[i].setBounds(30, 80 + i * 80, 70, 20);
			else
				disLabel[i].setBounds(360, 60, 70, 20);
			disLabel[i].setHorizontalAlignment(JLabel.CENTER);
			disLabel[i].setFont(new Font("�޸�����ü", Font.BOLD, 14));
			add(disLabel[i]);
		}

		String[] str2 = { "월", "일", "시", "분" };
		JLabel[] disDays = new JLabel[4];
		for (int i = 0; i < disDays.length; i++) {
			disDays[i] = new JLabel(str2[i]);
			disDays[i].setBounds(95 + i * 70, 190, 25, 25);
			disDays[i].setHorizontalAlignment(JLabel.CENTER);
			disDays[i].setFont(new Font("�޸�����ü", Font.BOLD, 15));
			add(disDays[i]);
		}

		for (int i = 0; i < imgLabel.length; i++) {
			imgLabel[i] = new JLabel();
			add(imgLabel[i]);
		}
		imgLabel[0].setBounds(50, 270, 180, 180);
		imgLabel[1].setBounds(350, 290, 170, 180);
		imgLabel[1].setIcon(new ImageIcon("img/time.png"));

		String[] str = { "저장", "예약 삭제", "음악 변경" };
		JLabel[] dLabel = new JLabel[3];
		for (int i = 0; i < dLabel.length; i++) {
			dLabel[i] = new JLabel(str[i]);
			dLabel[i].setHorizontalAlignment(JLabel.CENTER);
			dLabel[i].setFont(new Font("�޸�����ü", Font.BOLD, 11));
			add(dLabel[i]);
		}
		dLabel[0].setBounds(270, 285, 60, 20);
		dLabel[1].setBounds(480, 285, 60, 20);
		dLabel[2].setBounds(390, 285, 60, 20);

//		btn[0].setBounds(270, 220, 60, 60);
//		btn[1].setBounds(480, 220, 60, 60);
//		btn[2].setBounds(500, 10, 60, 60);
//		btn[3].setBounds(390, 220, 60, 60);
	}

	void setComboBox() {
		String[] month = new String[12];
		for (int i = 0; i < month.length; i++)
			month[i] = String.valueOf(i + 1);

		String[] days = new String[31];
		for (int i = 0; i < days.length; i++)
			days[i] = String.valueOf(i + 1);

		String[] hour = new String[24];
		for (int i = 0; i < hour.length; i++)
			hour[i] = String.valueOf(i);

		String[] min = new String[60];
		for (int i = 0; i < min.length; i++)
			min[i] = String.valueOf(i);

		for (int i = 0; i < calendar.length; i++) {
			switch (i) {
			case 0:
				calendar[i] = new JComboBox<String>(month);
				break;
			case 1:
				calendar[i] = new JComboBox<String>(days);
				break;
			case 2:
				calendar[i] = new JComboBox<String>(hour);
				break;
			case 3:
				calendar[i] = new JComboBox<String>(min);
				break;
			}
			calendar[i].setBounds(50 + i * 70, 190, 45, 25);
			add(calendar[i]);
		}
	}

	void setBtn() {
		for (int i = 0; i < btn.length; i++) {
			btn[i] = new JButton();
			btn[i].addActionListener(new BtnEvent(this));
			btn[i].setOpaque(false);
			btn[i].setBorderPainted(false);
			btn[i].setContentAreaFilled(false);
			btn[i].setFocusPainted(false);
			add(btn[i]);
		}
		btn[0].setBounds(270, 220, 60, 60);
		btn[1].setBounds(480, 220, 60, 60);
		btn[2].setBounds(500, 10, 60, 60);
		btn[3].setBounds(390, 220, 60, 60);

		btn[0].setIcon(new ImageIcon("img/save_normal.png"));
		btn[0].setRolloverIcon(new ImageIcon("img/save_over.png"));

		btn[1].setIcon(new ImageIcon("img/delete_normal.png"));
		btn[1].setRolloverIcon(new ImageIcon("img/delete_over.png"));

		btn[2].setIcon(new ImageIcon("img/exit_normal.png"));
		btn[2].setRolloverIcon(new ImageIcon("img/exit_over.png"));

		btn[3].setIcon(new ImageIcon("img/change_normal.png"));
		btn[3].setRolloverIcon(new ImageIcon("img/change_over.png"));

	}

	void setTime(String currentTime) {
		time.setText(currentTime);
	}

	void compareTime(String currentTime) {
		for (int i = 0; i < alarm.size(); i++)
			if (alarm.get(i).equals(currentTime)) {

				MusicPlay mp = new MusicPlay(audioPath);
				mp.execute();

				InfoThread ift = new InfoThread(currentTime, this);
				ift.execute();

				imgLabel[1].setIcon(new ImageIcon("img/time.gif"));
				backLabel.setIcon(new ImageIcon("img/background_1.png"));

				model.removeElement(alarm.get(i));
				alarm.remove(i);
			}
	}

	void setList() {
		list = new JList(model);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBorder(new TitledBorder(new LineBorder(Color.LIGHT_GRAY)));
		list.setBackground(new Color(250, 175, 120));
		list.setOpaque(true);
		JScrollPane p = new JScrollPane(list);
		p.setBounds(350, 80, 190, 130);
		add(p);
	}

	JButton[] getBtn() {
		return btn;
	}

	JComboBox<String>[] getComboBox() {
		return calendar;
	}

	DefaultListModel<String> getModel() {
		return model;
	}

	ArrayList<String> getAlarm() {
		return alarm;
	}

	JList getList() {
		return list;
	}

	JLabel[] getImgLabel() {
		return imgLabel;
	}

	void setAudioPath(String audioPath) {
		this.audioPath = audioPath;
	}

	void setTimeImg() {
		String[] str = { "img/clock_wait.png", "img/clock_0.png", "img/clock_1.png", "img/clock_2.png",
				"img/clock_3.png", "img/clock_4.png", "img/clock_5.png", "img/clock_6.png", "img/clock_7.png" };
		Image[] img = new Image[9];
		ImageIcon tmp;
		for (int i = 0; i < str.length; i++) {
			tmp = new ImageIcon(str[i]);
			img[i] = tmp.getImage();
			img[i] = img[i].getScaledInstance(180, 180, Image.SCALE_SMOOTH);
			timeImg[i] = new ImageIcon(img[i]);
		}
		imgLabel[0].setIcon(timeImg[0]);
	}

	void setTimeLabel(String num) {
		switch (Integer.valueOf(num)) {
		case 0:
			imgLabel[0].setIcon(timeImg[1]);
			break;

		case 7:
			imgLabel[0].setIcon(timeImg[2]);
			break;

		case 15:
			imgLabel[0].setIcon(timeImg[3]);
			break;

		case 22:
			imgLabel[0].setIcon(timeImg[4]);
			break;

		case 30:
			imgLabel[0].setIcon(timeImg[5]);
			break;

		case 37:
			imgLabel[0].setIcon(timeImg[6]);
			break;

		case 45:
			imgLabel[0].setIcon(timeImg[7]);
			break;

		case 52:
			imgLabel[0].setIcon(timeImg[8]);
			break;
		}
	}

	public static void main(String[] args) {
		new AlarmClass();
	}

}
