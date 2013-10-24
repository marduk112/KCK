package Gra;

import java.awt.BorderLayout;

public class Szkielet extends JFrame {

	private JPanel contentPane;
	private JButton label;
	String sciezkaDoPliku, path_sciezki; //zmienna kontrolujaca grafike

	
	public Szkielet() {
		
		
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\menu.jpg"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300); //gdzie sie pojawi
		contentPane = new JPanel();
		contentPane.setAlignmentY(Component.TOP_ALIGNMENT);
		contentPane.setAlignmentX(Component.LEFT_ALIGNMENT);
		contentPane.setBorder(null);
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setSize(960,692);
		path_sciezki = "/Gra/Plansze/images/";
		
		label = new JButton("");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent m) {
				Point e;
				e = m.getPoint();
				String xx = String.valueOf(e.x);
				String yy = String.valueOf(e.y);
				String lacz = "("+xx+","+yy+")";
				int x = Integer.parseInt(xx);
				int y = Integer.parseInt(yy);
				
				if(x>300 && x<600 && y>300 && y<600){
					
					System.out.print(xx);
				}
				if(x>630 && x<850 && y>300 && y<365){
					sciezkaDoPliku = path_sciezki+"stage1.jpg";
					getImg();
				}
				if(x>650 && x<830 && y>380 && y<430){
					sciezkaDoPliku = path_sciezki+"menu.jpg";
					getImg();
				//	label.removeMouseListener(this);
				}
				if(x>580 && x<930 && y>440 && y<500){
					sciezkaDoPliku = path_sciezki+"stage1.jpg";
					getImg();
				}
				if(x>650 && x<830 && y>515 && y<560){
					sciezkaDoPliku = path_sciezki+"stage1.jpg";
					getImg();
				}
				
			}
		});
		
		addKeyListener(new KeyAdapter() { //wcisniecie klawisza
			@Override
			public void keyPressed(KeyEvent k) {
				char c = k.getKeyChar();
				System.out.print(c);
				
			}
		});
		
		label.setPreferredSize(new Dimension(960, 692));
		label.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		label.setBorder(null);
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		label.setHorizontalTextPosition(SwingConstants.CENTER);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(new Color(204, 255, 204));
		label.setSize(new Dimension(960, 692));
		label.setSize(960,692);
		contentPane.add(label, BorderLayout.CENTER);
		sciezkaDoPliku = path_sciezki+"menu.jpg";
		getImg();
		
	}

	private void getImg(){ //ladowanie grafiki
		
		label.setIcon(new ImageIcon(Szkielet.class.getResource(sciezkaDoPliku)));
	}
}
