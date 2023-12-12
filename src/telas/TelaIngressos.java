/**********************************
 * IFPB - Curso Superior de Tec. em Sist. para Internet
 * Programa��o orientada a objetos
 * Prof. Fausto Maranh�o Ayres
 **********************************/
package telas;


import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Convidado;
import modelo.Ingresso;
import regras_negocio.Fachada;



public class TelaIngressos {
	private JDialog frame;
	private JTable table;
	private JScrollPane scrollPane;
	private JLabel label;
	private JLabel label_2;
	private JButton button_1;
	private JButton button_2;
	private JButton button;
	public boolean controle = false;

	/**
	 * Create the application.
	 */
	public TelaIngressos() {
		initialize();
		listagem();
		frame.setVisible(true);
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JDialog();
		frame.setModal(true);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				//listagem();
			}
		});
		frame.setTitle("Ingressos");
		frame.setBounds(100, 100, 854, 362);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 11, 758, 207);
		frame.getContentPane().add(scrollPane);

		table = new JTable() {
			public boolean isCellEditable(int rowIndex, int vColIndex) {
				return false;
			}
		};
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (table.getSelectedRow() >= 0) 
					label.setForeground(Color.BLUE);
					
					label.setText("Selecionado: "+ (String)table.getValueAt( table.getSelectedRow(), 0));
			}
		});
		table.setGridColor(Color.BLACK);
		table.setRequestFocusEnabled(false);
		table.setFocusable(false);
		table.setBackground(Color.WHITE);
		table.setFillsViewportHeight(true);
		table.setRowSelectionAllowed(true);
		table.setFont(new Font("Tahoma", Font.PLAIN, 14));
		scrollPane.setViewportView(table);
		table.setBorder(new LineBorder(new Color(0, 0, 0)));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowGrid(true);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		label = new JLabel("");
		label.setForeground(Color.RED);
		label.setBounds(21, 296, 587, 14);
		frame.getContentPane().add(label);

		label_2 = new JLabel("resultados");
		label_2.setBounds(21, 216, 394, 14);
		frame.getContentPane().add(label_2);

		button_1 = new JButton("Criar Ingresso");
		button_1.setToolTipText("");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controle = false;
				label.setText("");
				try {
					
					String codigoStr = JOptionPane.showInputDialog(frame, "Digite o id do evento");
				
					if (codigoStr == null) {
						label.setText("");
						return;
					}
					if (codigoStr.equals("")) {
						label.setForeground(Color.RED);
						label.setText("O código não pode ser vazio");
						return;
					}
					if (!codigoStr.matches("\\d+")) {
						label.setForeground(Color.RED);
						label.setText("Só é permitido inteiros");
						return;
					}
					Integer codigoInt = Integer.parseInt(codigoStr);
//-------------------------------------------------------------------------------------------------------------------
					String cpf = JOptionPane.showInputDialog(frame, "Digite o cpf do participante/convidado");
					if (cpf == null) {
						label.setText("");
						return; //
					}
					if (cpf.equals("")) {
						label.setForeground(Color.RED);
						label.setText("O CPF não pode ser vazio");
						return;
					}
					if (!cpf.matches("\\d+")) {
						label.setForeground(Color.RED);
						label.setText("Só é permitido inteiros");
						return;
					}
//-------------------------------------------------------------------------------------------------------------------
					String telefone = JOptionPane.showInputDialog(frame, "Digite um telefone");
					if (telefone == null) {
						label.setText("");
						return; //
					}
					if (telefone.equals("")) {
						label.setForeground(Color.RED);
						label.setText("O telefone não pode ser vazio");
						return;
					}
					if (!telefone.matches("\\d+")) {
						label.setForeground(Color.RED);
						label.setText("Só é permitido inteiros");
						return;
					}
//-------------------------------------------------------------------------------------------------------------------					
					Fachada.criarIngresso(codigoInt,cpf,telefone);
					String apresentar = codigoInt + "-" + cpf;
					label.setForeground(Color.BLUE);
					label.setText("Ingresso criado: "+apresentar);
					listagem();
				}
				catch(Exception ex) {
				
					label.setText(ex.getMessage());
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_1.setBounds(318, 247, 160, 23);
		frame.getContentPane().add(button_1);

		button_2 = new JButton("Listar");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				listagem();				
				//listagem();
			}
		});
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button_2.setBounds(101, 247, 160, 23);
		frame.getContentPane().add(button_2);
		
		button = new JButton("Apagar Ingresso");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				controle = false;
				try {
					if (table.getSelectedRow() >= 0) {
						String codigo = (String) table.getValueAt( table.getSelectedRow(), 0);
						Object[] options = { "Confirmar", "Cancelar" };
						int escolha = JOptionPane.showOptionDialog(null, "Confirmar apagamento do ingresso: "+codigo, "Alerta",
								JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
					
						if(escolha == 0) {
							
							
					
							
							label.setForeground(Color.BLUE);
							label.setText("Apagamento realizado");
							label.setForeground(Color.RED);
							

							Fachada.apagarIngresso(codigo);
							listagem();
							
							
						
							
							return;
						}
						}
						else
							label.setForeground(Color.RED);
							label.setText("selecione uma linha");
							listagem();
					}
					catch(Exception erro) {
						
						
						controle = true;
						listagem();
						
						label.setForeground(Color.RED);
						label.setText(erro.getMessage());
					}
					}
				});
		
		button.setToolTipText("");
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		button.setBounds(539, 247, 160, 23);
		frame.getContentPane().add(button);
		

		
	}
	
	

	public void listagem() {
		try{
			label.setText("");
			
			   List<Ingresso> ingressos = Fachada.listarIngressos();
			

				//objeto model contem todas as linhas e colunas da tabela
				DefaultTableModel model = new DefaultTableModel();
	
				//criar as colunas (0,1,2) da tabela
				model.addColumn("Código");
				model.addColumn("Telefone");
				model.addColumn("Preço do Ingresso");
				model.addColumn("Preço do evento");
				model.addColumn("Data do evento");
				model.addColumn("Idade do participante");
				
	
				//model.setRowCount(0);
				//table.setModel(model);
				
				//criar as linhas da tabela String.join(i.getTelefone()
				int cont = 0 ;
				
				for(Ingresso i : ingressos) {
					double valor = 0.0;
					
					if(i.getParticipante() instanceof Convidado) {
						valor = i.calcularPreco();
						
					}
					else {
						valor = i.calcularPreco();
						
					}
	

					model.addRow(new Object[]{
							i.getCodigo(),i.getTelefone(),
							i.calcularPreco(),i.getEvento().getPreco(),i.getEvento().getData(),i.getParticipante().calcularIdade()});
					cont = cont + 1;

			}
		
			table.setModel(model);
			label_2.setText("resultados: "+cont+ " linhas - selecione uma linha");

			//redimensionar a coluna 2
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 		//desabilita
			table.getColumnModel().getColumn(0).setMaxWidth(50);	
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); //habilita
		
		}
		catch(Exception erro){

			DefaultTableModel model = new DefaultTableModel();
			
			model.addColumn("Código");
			model.addColumn("Telefone");
			model.addColumn("Preço do Ingresso");
			model.addColumn("Preço do evento");
			model.addColumn("Data do evento");
			model.addColumn("Idade do participante");
			
			table.setModel(model);
			
			table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 		//desabilita
			table.getColumnModel().getColumn(0).setMaxWidth(50);	
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); //habilita
			
			
			label.setText(erro.getMessage());
		}
	} 
}
