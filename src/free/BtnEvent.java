package free;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class BtnEvent implements ActionListener {

	AlarmClass ac;
	String saveTime = "";

	public BtnEvent(AlarmClass ac) {
		this.ac = ac;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		for (int i = 0; i < ac.getBtn().length; i++)
			if (e.getSource() == ac.getBtn()[i]) {
				switch (i) {
				case 0: // 예약 저장 버튼
					int cnt = 0;
					String[] tmp = new String[4];
					int result = JOptionPane.showConfirmDialog(null, "저장하시겠습니까?", "예약 저장", JOptionPane.OK_CANCEL_OPTION);
					if (result == 0) {
						for (int j = 0; j < ac.getComboBox().length; j++) {
							tmp[j] = (String) ac.getComboBox()[j].getSelectedItem();
						}
						saveTime = "2021년 " + tmp[0] + "월" + tmp[1] + "일 " + tmp[2] + "분" + tmp[3] + "초";
						if (ac.getAlarm().size() == 0) {
							ac.getModel().addElement(saveTime);
							ac.getAlarm().add(saveTime);
						} else {
							for (int j = 0; j < ac.getAlarm().size(); j++) {
								if (ac.getAlarm().get(j).equals(saveTime)) {
									System.out.println(j);
									JOptionPane.showMessageDialog(null, "에러가 발생했어요!", "에러",
											JOptionPane.WARNING_MESSAGE);

								} else
									cnt++;
							}
							if (ac.getAlarm().size() == cnt) {
								ac.getModel().addElement(saveTime);
								ac.getAlarm().add(saveTime);
							}
						}
					}
					break;

				case 1: // 예약 삭제 버튼
					try {
						int rDialog = JOptionPane.showConfirmDialog(null, "삭제 하시겠습니까?", "예약 삭제",
								JOptionPane.OK_CANCEL_OPTION);
						if (rDialog == 0) {
							for (int j = 0; j < ac.getAlarm().size(); j++)
								if (ac.getList().getSelectedValue().equals(ac.getAlarm().get(j))) {
									ac.getModel().removeElement(ac.getAlarm().get(j));
									ac.getAlarm().remove(j);
								}
						}
					} catch (NullPointerException e1) {
						JOptionPane.showMessageDialog(null, "에러가 발생했어요!", "오류", JOptionPane.WARNING_MESSAGE);
					}
					break;

				case 2: // 종료 버튼
					int rDialog = JOptionPane.showConfirmDialog(null, "종료하시겠습니까?", "시스템 종료",
							JOptionPane.OK_CANCEL_OPTION);
					if (rDialog == 0)
						System.exit(0);
					break;

				case 3: // 음악 변경 버튼
					JFileChooser chooser = new JFileChooser();
					chooser.setFileFilter(new FileNameExtensionFilter("mp3", "mp3"));
					int ret = chooser.showOpenDialog(null);
					if (ret != JFileChooser.APPROVE_OPTION) {
						JOptionPane.showMessageDialog(null, "취소하였습니다.", "파일 선택", JOptionPane.WARNING_MESSAGE);
						return;
					}

					String filePath = chooser.getSelectedFile().getPath();
					ac.setAudioPath(filePath);
					System.out.println(filePath); 
					break;
				}
			}
	}
}
