
package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;

import control.dao.ClasseDAO;
import model.Categoria;
import model.Produto;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class CadastroProduto {

	private JFrame frame;
	private JTextField txtNomeProduto;

	private JButton txtEnviar;
	private JTextField txtIdProduto;
	private JTable tbDados;
	private JTextField txtBuscarProduto;
	private JTextField txtValorProduto;
	private JTextField txtDescricao;

	;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// public static void main(CadastroProduto cadastroProduto) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroProduto window = new CadastroProduto();
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
	public CadastroProduto() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 785, 432);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Nome do Produto:");
		lblNewLabel.setBounds(271, 41, 96, 14);
		frame.getContentPane().add(lblNewLabel);

		txtNomeProduto = new JTextField();
		txtNomeProduto.setBounds(269, 66, 86, 20);
		frame.getContentPane().add(txtNomeProduto);
		txtNomeProduto.setColumns(10);

		JButton btnEnviar = new JButton("Cadastrar");
		btnEnviar.setBounds(144, 116, 122, 23);
		frame.getContentPane().add(btnEnviar);

		JButton btnListar = new JButton("Listar");
		btnListar.setBounds(405, 116, 110, 23);
		frame.getContentPane().add(btnListar);

		JButton btnBuscarProduto = new JButton("Buscar");
		btnBuscarProduto.setBounds(436, 163, 110, 23);
		frame.getContentPane().add(btnBuscarProduto);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNomeProduto.setText(null);
				txtValorProduto.setText(null);
				txtBuscarProduto.setText(null);
				txtIdProduto.setText(null);
				txtDescricao.setText(null);

			}
		});

		btnLimpar.setBounds(276, 115, 117, 25);
		frame.getContentPane().add(btnLimpar);

		JLabel lblNewLabel_1 = new JLabel("Id:");
		lblNewLabel_1.setBounds(144, 41, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);

		txtIdProduto = new JTextField();
		txtIdProduto.setEditable(false);
		txtIdProduto.setBounds(145, 64, 86, 20);
		frame.getContentPane().add(txtIdProduto);
		txtIdProduto.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(203, 197, 401, 140);
		frame.getContentPane().add(scrollPane);

		tbDados = new JTable();
		tbDados.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel modelo = (DefaultTableModel) tbDados.getModel();
				String txtIdProduto1 = modelo.getValueAt(tbDados.getSelectedRow(), 0).toString();
				String txtNome = modelo.getValueAt(tbDados.getSelectedRow(), 1).toString();
				String txtValor = modelo.getValueAt(tbDados.getSelectedRow(), 2).toString();
				String txtDescricao1 = modelo.getValueAt(tbDados.getSelectedRow(), 3).toString();

				txtNomeProduto.setText(txtNome);
				txtIdProduto.setText(txtIdProduto1);
				txtValorProduto.setText(txtValor);
				txtDescricao.setText(txtDescricao1);

			}
		});
		tbDados.setModel(new DefaultTableModel(new Object[][] { { null, null, null, null }, },
				new String[] { "Id Produto", "Nome Produto", "Valor Produto", "Descri\u00E7\u00E3o do Produto" }));
		scrollPane.setViewportView(tbDados);

		txtBuscarProduto = new JTextField();
		txtBuscarProduto.setColumns(10);
		txtBuscarProduto.setBounds(211, 164, 215, 20);
		frame.getContentPane().add(txtBuscarProduto);

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tbDados.getSelectedRow() != -1) {

				Produto produto = new Produto();
				ClasseDAO classeDao = new ClasseDAO();
				produto.setId_produto(Integer.parseInt(txtIdProduto.getText()));
				produto.setNome_produto(txtNomeProduto.getText());
				produto.setValor_produto(Float.parseFloat(txtValorProduto.getText()));
				produto.setDescricao_produto(txtDescricao.getText());
				classeDao.atualizarProduto(produto);
				txtNomeProduto.setText(null);
				txtValorProduto.setText(null);
				txtBuscarProduto.setText(null);
				txtIdProduto.setText(null);
				txtDescricao.setText(null);
				}else {
					JOptionPane.showMessageDialog(null, "Selecione um produto Para Atualizar");
				}
			}
		});
		btnAtualizar.setBounds(527, 116, 110, 23);
		frame.getContentPane().add(btnAtualizar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					if (tbDados.getSelectedRow() != -1) {

						ClasseDAO classeDao = new ClasseDAO();
						Produto produto = new Produto();

						produto.setId_produto(Integer.parseInt(txtIdProduto.getText()));
						classeDao.deletarProduto(produto);
						txtNomeProduto.setText(null);
						txtValorProduto.setText(null);
						txtBuscarProduto.setText(null);
						txtIdProduto.setText(null);
						txtDescricao.setText(null);
					} else {
						JOptionPane.showMessageDialog(null, "Selecione um produto para Excluir.");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		});
		btnExcluir.setBounds(649, 116, 110, 23);
		frame.getContentPane().add(btnExcluir);

		JLabel lblValorDoProduto = new JLabel("Valor do Produto:");
		lblValorDoProduto.setBounds(388, 41, 96, 14);
		frame.getContentPane().add(lblValorDoProduto);

		txtValorProduto = new JTextField();
		txtValorProduto.setColumns(10);
		txtValorProduto.setBounds(386, 66, 86, 20);
		frame.getContentPane().add(txtValorProduto);

		JLabel lbDescricaoProduto = new JLabel("Descrição do Produto:");
		lbDescricaoProduto.setBounds(508, 41, 129, 14);
		frame.getContentPane().add(lbDescricaoProduto);

		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Produto produto = new Produto();
				ClasseDAO classeDao = new ClasseDAO();
				//if(txtNomeProduto.getText()!=null && txtValorProduto.getText()!=null && txtDescricao.getText()!=null)  {
				produto.setNome_produto(txtNomeProduto.getText());
				produto.setValor_produto(Float.parseFloat((txtValorProduto.getText())));
				produto.setDescricao_produto(txtDescricao.getText());
				classeDao.salvarProduto(produto);

				JOptionPane.showMessageDialog(null, "Produto Adicionado!");
			
				//}else {
					
					
				//	JOptionPane.showMessageDialog(null, "Preencha todos os campos!");
					
				//}
			}

		});
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					ClasseDAO classeDao = new ClasseDAO();

					List<Produto> list;
					list = classeDao.listarProduto();
					DefaultTableModel modelo = (DefaultTableModel) tbDados.getModel();

					modelo.setNumRows(0);

					for (Produto produto : list) {

						Object[] dados = { produto.getId_produto(), produto.getNome_produto(),
								produto.getValor_produto(), produto.getDescricao_produto() };
						modelo.addRow(dados);
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});

		btnBuscarProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					ClasseDAO classeDao = new ClasseDAO();
					classeDao.buscarCategoria(txtBuscarProduto.getText());
					List<Produto> list;
					list = classeDao.buscarProduto(txtBuscarProduto.getText());
					DefaultTableModel modelo = (DefaultTableModel) tbDados.getModel();

					modelo.setNumRows(0);

					for (Produto p : list) {

						Object[] dados = { p.getId_produto(), p.getNome_produto(), p.getValor_produto() };
						modelo.addRow(dados);

					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});
		JButton btnCategoria = new JButton("Categoria");
		btnCategoria.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroCategoria CadastroCategoria = new CadastroCategoria();
				CadastroCategoria.main(null);
			}
		});
		btnCategoria.setBounds(10, 115, 122, 23);
		frame.getContentPane().add(btnCategoria);

		txtDescricao = new JTextField();
		txtDescricao.setColumns(10);
		txtDescricao.setBounds(518, 66, 215, 39);
		frame.getContentPane().add(txtDescricao);
	}
}
