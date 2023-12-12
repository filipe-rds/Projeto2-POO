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
import modelo.Ingresso;
import modelo.Participante;
import modelo.Evento;
import regras_negocio.Fachada;

public class TelaEventos {
	
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
		private JLabel label_5;
		private JTextField textField_3;


	
		public TelaEventos() {
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
			frame.setTitle("Evento");
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
						label.setText("Selecionado: "+ table.getValueAt( table.getSelectedRow(), 0));
						
						Integer id = ((Integer) table.getValueAt(table.getSelectedRow(), 0));
						ArrayList<Evento> eventos;
						ArrayList <Ingresso> ingressos;
						Evento apontador = null;
						//int idInteiro = Integer.parseInt(IdString);
						
						try {
							eventos = Fachada.listarEventos();
							
							for(Evento ev:eventos) {
								if(ev.getId()==id) {
									apontador = ev;
									break;
								}
							}
							
						ingressos = apontador.getIngressos();
						
						String aparecer = "";
						
						for (int i =0  ;i<ingressos.size();i++) {
							
							String concatenar = ingressos.get(i).getCodigo() +","+ ingressos.get(i).getTelefone()+ "\n";
							
							aparecer = aparecer + concatenar ;
							
						}
						
						if (aparecer.isEmpty()) {
							textArea_1.append("Sem ingressos");
							return;
						}
						
					
						 textArea_1.append(aparecer);
						
							
						}
						
						catch(Exception t) {
							
							label.setText(t.getMessage());
							
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
							Integer id = (Integer) table.getValueAt( table.getSelectedRow(), 0);
							
							Object[] options = { "Confirmar", "Cancelar" };
							int escolha = JOptionPane.showOptionDialog(null, "Confirma a exclusão do evento com id: "+id, "Alerta",
									JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[1]);
							if(escolha == 0) {
								Fachada.apagarEvento(id);
								label.setForeground(Color.BLUE);
								label.setText("Exclusão realizada");
								listagem();
								return;
							}
						}
						else
							label.setText("Selecione uma linha");
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

			label_1 = new JLabel("Data");
			label_1.setBounds(26, 205, 71, 14);
			label_1.setHorizontalAlignment(SwingConstants.LEFT);
			label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			frame.getContentPane().add(label_1);

			label_2 = new JLabel("Descrição");
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
			button.setBounds(258, 205, 105, 23);
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try {
						if(textField.getText().isEmpty() ||  textField_1.getText().isEmpty()
							|| textField_2.getText().isEmpty() ||  textField_3.getText().isEmpty()) {
							label.setForeground(Color.RED);
							label.setText("Campo vazio");
							return;
						}
						
						String data = textField.getText();
						
						if(!data.matches("\\d{2}/\\d{2}/\\d{4}")){
							label.setForeground(Color.RED);
							label.setText("A data deve seguir o padrão dd/MM/aaaa");
							return;
							
						}
						
						String descricao = textField_1.getText();
						
						
						String capacidade = textField_2.getText();
						
						if (!capacidade.matches("\\d+")) {
							label.setForeground(Color.RED);
							label.setText("A capacidade só suporta números inteiros");
							return;
						}
						int newCapacidade = Integer.parseInt(capacidade);
						
						String preco = textField_3.getText();
						
						preco = preco.replace(",", ".");
						
						
	
						if(!preco.matches("[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?")) {
							
							label.setForeground(Color.RED);
							label.setText("O preço só suporta números");
							return;
							
						}
						
						Double newPreco = Double.parseDouble(preco);
						
						
						
				
						
						
					
						Fachada.criarEvento(data ,descricao,newCapacidade,newPreco);
						label.setForeground(Color.BLUE);
						label.setText("Evento criado");
						listagem();
						
						
						
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
			
			label_3 = new JLabel("Capacidade");
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
			
			label_5 = new JLabel("Preço");
			label_5.setHorizontalAlignment(SwingConstants.LEFT);
			label_5.setFont(new Font("Tahoma", Font.PLAIN, 12));
			label_5.setBounds(26, 275, 71, 14);
			frame.getContentPane().add(label_5);
			
			textField_3 = new JTextField();
			textField_3.setColumns(10);
			textField_3.setBounds(143, 274, 105, 20);
			frame.getContentPane().add(textField_3);
			
		
			
			
			
			
			
		
			
			
			
			
			



		}
		
		

		public void listagem () {
			try{
				label.setText("");

				List<Evento> lista = Fachada.listarEventos();
				
				DefaultTableModel model = new DefaultTableModel();
				
				model.addColumn("Id");
				model.addColumn("Data");
				model.addColumn("Capacidade");
				model.addColumn("Preço");
				model.addColumn("Ingressos");
				model.addColumn("Total");
				model.addColumn("Lotado");
				
				for(Evento e : lista) {
						
						String escolha;
						if (e.lotado()) 
							escolha = "Sim";
						else
							escolha = "Não";
						
						model.addRow(new Object[]{
								 
									e.getId(),e.getData(),e.getCapacidade(),e.getPreco(),
									e.quantidadeIngressos(),e.totalArrecadado(),escolha});}
								
						
					
				
					
			
				table.setModel(model);
				label_4.setText("Resultados: "+lista.size()+ " linhas  - selecione uma linha");
				
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 		//desabilita
				table.getColumnModel().getColumn(0).setMaxWidth(30);
				table.getColumnModel().getColumn(1).setMaxWidth(250);
				table.getColumnModel().getColumn(2).setMaxWidth(50);
				table.getColumnModel().getColumn(3).setMaxWidth(60);
				table.getColumnModel().getColumn(4).setMaxWidth(70);
				table.getColumnModel().getColumn(5).setMaxWidth(50);
				table.getColumnModel().getColumn(6).setMaxWidth(60);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); //habilita
			
		}catch(Exception erro)
			{
				
				DefaultTableModel model = new DefaultTableModel();
				model.addColumn("Id");
				model.addColumn("Data");
				model.addColumn("Capacidade");
				model.addColumn("Preço");
				model.addColumn("Ingressos");
				model.addColumn("Total");
				model.addColumn("Lotado");
				table.setModel(model);
				
				
				table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 		//desabilita
				table.getColumnModel().getColumn(0).setMaxWidth(200);
				table.getColumnModel().getColumn(1).setMaxWidth(100);
				table.getColumnModel().getColumn(2).setMaxWidth(35);
				table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS); //habilita
			
				
			}
		
		}
}
