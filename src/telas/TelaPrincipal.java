package telas;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class TelaPrincipal {

	private JFrame frame;
	private JMenuBar menuBar;
	private JMenu teventos;
	private JMenu tparticipantes;
	private JMenu tingressos;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TelaPrincipal window = new TelaPrincipal();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TelaPrincipal() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 515, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //.HIDE_ON_CLOSE
		frame.setTitle("4ever");
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		

		
		teventos = new JMenu("Eventos");
		teventos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaEventos telaEvento = new TelaEventos();
				//telaEvento.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				
				

			}
		});
		teventos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		menuBar.add(teventos);
		tparticipantes = new JMenu("Participantes");
		tparticipantes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaParticipantes telaParticipante = new TelaParticipantes();
			}
		});
		menuBar.add(tparticipantes);
		tingressos = new JMenu("Ingressos");
		tingressos.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				TelaIngressos telaIngresso = new TelaIngressos();
			}
		});
		menuBar.add(tingressos);
		frame.getContentPane().setLayout(null);
		
		label = new JLabel("New label");
		label.setBounds(0, 0, 501, 241);
		frame.getContentPane().add(label);
		ImageIcon icon =
				new ImageIcon(TelaPrincipal.class.getResource("/imagens/projeto-poo.jpg"));
		icon.setImage(icon.getImage().getScaledInstance(
				label.getWidth(),
				label.getHeight(),
				Image.SCALE_DEFAULT
				));
				label.setIcon(icon);
	}
}
