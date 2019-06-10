package sqa_project;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import javax.swing.JScrollPane;
import javax.swing.JProgressBar;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import javax.swing.SwingConstants;

public class Gui {

	private JFrame frame;
	private JComboBox comboBox;
	private JTable table;
	private JLabel label_school;
	private JLabel label_student;
	private JLabel label_progress;
	private JProgressBar progressBar;
	private DefaultTableModel model;
	private String schoolPath = null;
	private String studentPath = null;
	Choose c = new Choose();
	Readfile r = new Readfile();
	ArrayList<School> schoolList = new ArrayList<School>();
	Object[] rowData = new Object[20];

	// Launch the application.
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Gui window = new Gui();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Create the application.
	public Gui() {
		initialize();
	}

	// Initialize the contents of the frame.
	private void initialize() {
		frame = new JFrame();
		frame.setResizable(false);
		frame.setBounds(100, 100, 460, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e1) {
			e1.printStackTrace();
		}

		JButton button_school = new JButton("選擇檔案");
		button_school.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		button_school.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfile = new JFileChooser(
						new File("D:/Software/java-2019-03/eclipse/workspace/git/sqa_project/sqa_project/res"));
				if (jfile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					schoolPath = jfile.getSelectedFile().getPath();
					label_school.setText(jfile.getSelectedFile().getName());
				}
			}
		});
		button_school.setBounds(25, 59, 125, 30);
		frame.getContentPane().add(button_school);

		JButton button_student = new JButton("選擇檔案");
		button_student.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfile = new JFileChooser(
						new File("D:/Software/java-2019-03/eclipse/workspace/git/sqa_project/sqa_project/res"));
				if (jfile.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
					studentPath = jfile.getSelectedFile().getPath();
					label_student.setText(jfile.getSelectedFile().getName());
				}
			}
		});
		button_student.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		button_student.setBounds(25, 139, 125, 30);
		frame.getContentPane().add(button_student);

		JButton button_start = new JButton("產生榜單");
		button_start.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					progressBar.setVisible(true);
					progressBar.setIndeterminate(true);
					schoolList = c.choose(r.loadSchoolList(schoolPath), r.loadStudentList(studentPath));
					progressBar.setIndeterminate(false);
					progressBar.setValue(progressBar.getMaximum());
					label_progress.setText("執行完成");
					label_progress.setForeground(Color.white);
				} catch (Exception e1) {
					label_progress.setForeground(Color.red);
					label_progress.setText(e1.getMessage());
					e1.printStackTrace();
				}
				comboBox.removeAllItems();
				comboBox.addItem("請選擇學校...");
				for (School s : schoolList) {
					comboBox.addItem(s.getId() + " " + s.getName());
				}
				comboBox.addItemListener(new ItemListener() {
					public void itemStateChanged(ItemEvent e) {
						int index = comboBox.getSelectedIndex();
						if (index != 0 && e.getStateChange() == 2) {
							model.setRowCount(0);
							int temp = -1;
							int count = 0;
							int index_student = 0;
							String text;
							for (Student s : schoolList.get(index - 1).getList()) {
								if (temp != s.getGrade()) { // 計算順位 ( 同分排在同一順位 )
									temp = s.getGrade();
									count++;
									index_student++;
								}
								if (count <= schoolList.get(index - 1).getQuota()) { // 小於正取名額為正取
									text = "正取";
								} else if (count == schoolList.get(index - 1).getQuota() + 1) { // 大於正取名額 1 則為備取 1
									index_student = 1; // 重置順位
									text = "備取";
								} else {
									text = "備取";
								}
								rowData[0] = text + Integer.toString(index_student);
								rowData[1] = s.getId();
								rowData[2] = s.getName();
								model.addRow(rowData);
								int value = progressBar.getValue();
							}
						} else if (index == 0 && e.getStateChange() == 2) {
							model.setRowCount(0);
						}

					}
				});
			}
		});
		button_start.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		button_start.setBounds(25, 188, 125, 30);
		frame.getContentPane().add(button_start);

		String[] defaultItem = { "請選擇學校..." };
		comboBox = new JComboBox(defaultItem);
		comboBox.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		comboBox.setBounds(172, 22, 250, 25);
		frame.getContentPane().add(comboBox);

		table = new JTable(new DefaultTableModel(new Object[][] {}, new String[] { "順位", "准考證號碼", "姓名" }));
		table.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		table.setShowVerticalLines(false);
		model = (DefaultTableModel) table.getModel();
		table.setBounds(172, 57, 250, 161);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(172, 57, 250, 161);
		frame.getContentPane().add(scrollPane);

		label_progress = new JLabel("尚未產生榜單");
		label_progress.setHorizontalAlignment(SwingConstants.CENTER);
		label_progress.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		label_progress.setBounds(25, 228, 397, 30);
		frame.getContentPane().add(label_progress);

		progressBar = new JProgressBar();
		progressBar.setBounds(25, 228, 397, 30);
		frame.getContentPane().add(progressBar);

		label_school = new JLabel("請選擇學校檔案");
		label_school.setForeground(new Color(50, 205, 50));
		label_school.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		label_school.setBounds(25, 20, 125, 30);
		frame.getContentPane().add(label_school);

		label_student = new JLabel("請選擇學生檔案");
		label_student.setForeground(new Color(50, 205, 50));
		label_student.setFont(new Font("微軟正黑體", Font.BOLD, 14));
		label_student.setBounds(25, 99, 125, 30);
		frame.getContentPane().add(label_student);

		JButton button = new JButton("輸出檔案");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					r.output(schoolList, "res/output.txt");
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				JOptionPane.showMessageDialog(frame, "檔案輸出成功!");
			}
		});
		button.setFont(new Font("微軟正黑體", Font.PLAIN, 14));
		button.setBounds(25, 266, 397, 30);
		frame.getContentPane().add(button);
	}
}
