package telas;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import modelo.Convidado;
import modelo.Evento;
import modelo.Ingresso;
import modelo.Participante;
import regras_negocio.Fachada;

public class TelaParticipantes {
	
		private JFrame frame;
		private JTable table;
		private JScrollPane scrollPane;
		private JButton button_1;
		private JLabel label;
		private JLabel label_1;
		private JLabel label_2;
		private JLabel label_4;
		private JTextField textField_1;
		private JButton button;
	
		private JTextField textField;
		private JButton button_4;
		private JLabel label_3;
		private JTextField textField_2;
		private JTextArea textArea_1;


	
		public TelaParticipantes() {
			initialize();
			frame.setVisible(true);
			listagem();
		}

		/**
		 * Initialize the contents of the frame.
		 */
		private void initialize() {
			frame = new JFrame();
			frame.addWindowListener(new WindowAdapter() {
				@Override
				public void windowOpened(WindowEvent e) {
					listagem();
				}
			});
			frame.setTitle("Participante");
			frame.setBounds(100, 100, 628, 362);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.getContentPane().setLayout(null);
			frame.setResizable(false);
			frame.setLocationRelativeTo(null);

			scrollPane = new JScrollPane();
			scrollPane.setBounds(26, 11, 369, 172);
			frame.getContentPane().add(scrollPane);

			table = new JTable() {
				public boolean isCellEditable(int rowIndex, int vColIndex) {
					return false;
				}
			};
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					textArea_1.setText("");

					if (table.getSelectedRow() >= 0) 
						label.setText("Selecionado: "+ (String) table.getValueAt( table.getSelectedRow(), 0));
						
						String cpfString = ((String) table.getValueAt(table.getSelectedRow(), 0));
						ArrayList<Participante> par;
						ArrayList <Ingresso> in;
						Participante global = null;
						
						try {
							par = Fachada.listarParticipantes();
							
							for(Participante p:par) {
								if(p.getCpf().equals(cpfString)) {
									global = p;
									break;
								}
							}
						in = global.getIngressos();
						
						String aparecer = "";
						
						for (int i =0  ;i<in.size();i++) {
							
							String concatenar = in.get(i).getCodigo() +","+ in.get(i).getTelefone()+ "\n";
							
							aparecer = aparecer + concatenar ;
							
						}
						
						if (aparecer.isEmpty()) {
							textArea_1.append("Sem ingressos");
							return;
						}
						
						 
						 
						 textArea_1.append(aparecer);
						
							
						}
						
						catch(Exception t) {
							
						}
						
						
						
				}
			});
			table.setGridColor(Color.BLACK);
			table.setRequestFocusEnabled(false);
			table.setFocusable(false);
			table.setBackground(Color.WHITE);
			table.setFillsViewportHeight(true);
			table.setRowSelectionAllowed(true);
			table.setFont(new Font("Tahoma", Font.PLAIN, 12));
			scrollPane.setViewportView(table);
			table.setBorder(new LineBorder(new Color(0, 0, 0)));
			table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			table.setShowGrid(true);
			table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

			button_1 = new JButton("Apagar Selecionado");
			button_1.setBounds(405, 57, 160, 23);
			button_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if (table.getSelectedRow() >= 0){
							String nome = (String) table.getValueAt( table.getSelectedRow(), 0);
							//confirma��o
							Object[] options = { "Confirmar", "Cancelar" };
							int escolha = JOptionPane.showOptionDialog(null, "Confirma exclusão "+nome, "Alerta",
									JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
							if(escolha == 0) {
								Fachada.apagarParticipante(nome);
								label.setForeground(Color.BLUE);
								label.setText("Exclusão realizadaaa");
								listagem();
								return;
							}
						}
						else
							label.setText("selecione uma linha");
							return;
					}
					catch(Exception erro) {
						label.setForeground(Color.RED);
						label.setText(erro.getMessage());
					}
				}
			});
			frame.getContentPane().add(button_1);

			label = new JLabel("");
			label.setBounds(26, 299, 441, 14);
			label.setForeground(Color.RED);
			frame.getContentPane().add(label);

			label_1 = new JLabel("CPF:");
			label_1.setBounds(26, 205, 71, 14);
			label_1.setHorizontalAlignment(SwingConstants.LEFT);
			label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			frame.getContentPane().add(label_1);

			label_2 = new JLabel("Nascimento:");
			label_2.setBounds(26, 229, 71, 14);
			label_2.setHorizontalAlignment(SwingConstants.LEFT);
			label_2.setFont(new Font("Tahoma", Font.PLAIN, 12));
			frame.getContentPane().add(label_2);

			label_4 = new JLabel("selecione uma linha");
			label_4.setBounds(26, 181, 315, 14);
			frame.getContentPane().add(label_4);

			textField_1 = new JTextField();
			textField_1.setBounds(143, 228, 105, 20);
			textField_1.setColumns(10);
			frame.getContentPane().add(textField_1);

			button = new JButton("Criar");
			button.setBounds(258, 252, 105, 23);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if(textField.getText().isEmpty() ||  textField_1.getText().isEmpty()) {
							label.setText("Campo vazio");
							return;
						}
						
						String cpf = textField.getText();
						if (!cpf.matches("\\d+")) {
							label.setForeground(Color.RED);
							label.setText("O CPF só suporta números inteiros");
							return;
						}
						
						String nascimento = textField_1.getText();
						
						if(!nascimento.matches("\\d{2}/\\d{2}/\\d{4}")){
							label.setForeground(Color.RED);
							label.setText("A data deve seguir o padrão dd/MM/aaaa");
							return;
							
						}
						
						String empresa = textField_2.getText();
						
						if (empresa.equals("")) {
							Fachada.criarParticipante(cpf ,nascimento);
							label.setForeground(Color.BLUE);
							label.setText("Participante criado");
							listagem();
							return;
						}
						
						Fachada.criarConvidado(cpf,nascimento,empresa);
						label.setForeground(Color.BLUE);
						listagem();
						label.setText("Convidado criado");
						
					}
					catch(Exception ex) {
						label.setText(ex.getMessage());
						listagem();
					}
				}
			});
			button.setFont(new Font("Tahoma", Font.PLAIN, 12));
			frame.getContentPane().add(button);

			textField = new JTextField();
			textField.setBounds(143, 204, 105, 20);
			textField.setColumns(10);
			frame.getContentPane().add(textField);
			
			button_4 = new JButton("Listar");
			button_4.setBounds(405, 24, 160, 23);
			button_4.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					textArea_1.setText("");
					listagem();
				}
			});
			button_4.setFont(new Font("Tahoma", Font.PLAIN, 12));
			frame.getContentPane().add(button_4);
			
			label_3 = new JLabel("Empresa:");
			label_3.setBounds(26, 252, 71, 14);
			label_3.setHorizontalAlignment(SwingConstants.LEFT);
			label_3.setFont(new Font("Tahoma", Font.PLAIN, 12));
			frame.getContentPane().add(label_3);
			
			textField_2 = new JTextField();
			textField_2.setBounds(143, 251, 105, 20);
			textField_2.setColumns(10);
			frame.getContentPane().add(textField_2);
			
			
			textArea_1 = new JTextArea();
			textArea_1.setBounds(405, 110, 160, 172);
			frame.getContentPane().add(textArea_1);
			
		
			
			
			
			
			
		
			
			
			
			
			



		}
		
		

		public void listagem () {
			try{
				label.setText("");
				
				List<Participante> lista = Fachada.listarParticipantes();
				

				DefaultTableModel model = new DefaultTableModel();
				
				model.addColumn("CPF");
				model.addColumn("Nascimento");
				model.addColumn("Idade");
				model.addColumn("Empresa");
				
				for(Participante p : lista) {
				
					if (p instanceof Convidado) {
						Convidado c = (Convidado) p;
						model.addRow(new Object[]{
								c.getCpf(),c.getNascimento(),c.calcularIdade(),c.getEmpresa()});	
						
					}
						else {
						model.addRow(new Object[]{
							p.getCpf(),p.getNascimento(),p.calcularIdade(),"--"});	}

						}
					
					
			
				table.setModel(model);
				label_4.setText("Resultados: "+lista.size()+ " linhas  - selecione uma linha");
				
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 		//desabilita
				table.getColumnModel().getColumn(0).setMaxWidth(200);
				table.getColumnModel().getColumn(1).setMaxWidth(100);
				table.getColumnModel().getColumn(2).setMaxWidth(35);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); //habilita
			
		}catch(Exception erro)
			{
				
				DefaultTableModel model = new DefaultTableModel();
				model.addColumn("CPF");
				model.addColumn("Nascimento");
				model.addColumn("Idade");
				model.addColumn("Empresa");
				table.setModel(model);
				
				
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 		//desabilita
				table.getColumnModel().getColumn(0).setMaxWidth(200);
				table.getColumnModel().getColumn(1).setMaxWidth(100);
				table.getColumnModel().getColumn(2).setMaxWidth(35);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); //habilita
			
				
			}
		
		}
}
