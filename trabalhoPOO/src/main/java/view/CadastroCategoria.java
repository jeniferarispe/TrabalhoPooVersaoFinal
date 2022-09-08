
package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.SQLException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JTextField;

import control.dao.ClasseDAO;
import model.Categoria;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CadastroCategoria {

	private JFrame frame;
	private JTextField txtNomeCategoria;
	private JButton txtEnviar;
	private JTextField txtIdCategoria;
	private JTable tbDados;
	private JTextField txtBuscar;

	;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroCategoria window = new CadastroCategoria();

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
	public CadastroCategoria() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 748, 407);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Nome da Categoria:");
		lblNewLabel.setBounds(271, 41, 170, 14);
		frame.getContentPane().add(lblNewLabel);

		txtNomeCategoria = new JTextField();
		txtNomeCategoria.setBounds(269, 66, 86, 20);
		frame.getContentPane().add(txtNomeCategoria);
		txtNomeCategoria.setColumns(10);

		JButton btnEnviar = new JButton("Cadastrar");
		btnEnviar.setBounds(118, 115, 122, 23);
		frame.getContentPane().add(btnEnviar);

		JButton btnListar = new JButton("Listar");
		btnListar.setBounds(374, 115, 110, 23);
		frame.getContentPane().add(btnListar);

		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.setBounds(435, 149, 110, 23);
		frame.getContentPane().add(btnBuscar);

		JButton btnLimpar = new JButton("Limpar");
		btnLimpar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNomeCategoria.setText(null);
				txtBuscar.setText(null);
				txtIdCategoria.setText(null);

			}
		});
		btnLimpar.setBounds(250, 114, 117, 25);
		frame.getContentPane().add(btnLimpar);

		JLabel lblNewLabel_1 = new JLabel("Id:");
		lblNewLabel_1.setBounds(144, 41, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);

		txtIdCategoria = new JTextField();
		txtIdCategoria.setEditable(false);
		txtIdCategoria.setBounds(145, 64, 86, 20);
		frame.getContentPane().add(txtIdCategoria);
		txtIdCategoria.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(144, 183, 401, 174);
		frame.getContentPane().add(scrollPane);

		tbDados = new JTable();
		tbDados.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				DefaultTableModel modelo = (DefaultTableModel) tbDados.getModel();
				String txtIdCategoria1 = modelo.getValueAt(tbDados.getSelectedRow(), 0).toString();
				String txtNome = modelo.getValueAt(tbDados.getSelectedRow(), 1).toString();

				txtNomeCategoria.setText(txtNome);
				txtIdCategoria.setText(txtIdCategoria1);
			}
		});
		tbDados.setModel(
				new DefaultTableModel(new Object[][] { { null, null }, }, new String[] { "Id", "Nome Categoria" }));
		scrollPane.setViewportView(tbDados);

		txtBuscar = new JTextField();
		txtBuscar.setColumns(10);
		txtBuscar.setBounds(210, 150, 215, 20);
		frame.getContentPane().add(txtBuscar);

		JButton btnAtualizar = new JButton("Atualizar");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (tbDados.getSelectedRow() != -1) {
				Categoria categoria = new Categoria();
				ClasseDAO classeDao = new ClasseDAO();

				categoria.setNome_categoria(txtNomeCategoria.getText());
				categoria.setId_categoria(Integer.parseInt(txtIdCategoria.getText()));
				// categoria.setId_categoria((Integer)
				// tbDados.getValueAt(tbDados.getSelectedRow(), 0));

				classeDao.atualizarCategoria(categoria);
				JOptionPane.showMessageDialog(null, "Categoria Atualizada com Sucesso!");
				}else {
					JOptionPane.showMessageDialog(null, "Selecione uma Categoria Para Atualizar");
				}

			}
		});
		btnAtualizar.setBounds(494, 115, 110, 23);
		frame.getContentPane().add(btnAtualizar);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.setBounds(614, 115, 110, 23);
		frame.getContentPane().add(btnExcluir);

		btnEnviar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Categoria categoria = new Categoria();
				ClasseDAO classeDao = new ClasseDAO();
				categoria.setNome_categoria(txtNomeCategoria.getText());
				classeDao.salvar(categoria);
				JOptionPane.showMessageDialog(null, "Categoria Adicionada!");
				txtNomeCategoria.setText(null);
				txtBuscar.setText(null);

			}

		});
		btnListar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {

					ClasseDAO classeDao = new ClasseDAO();

					List<Categoria> list;
					list = classeDao.listar();
					DefaultTableModel modelo = (DefaultTableModel) tbDados.getModel();

					modelo.setNumRows(0);

					for (Categoria categoria : list) {

						Object[] dados = { categoria.getId_categoria(), categoria.getNome_categoria() };
						modelo.addRow(dados);
					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

		});

		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				try {
					ClasseDAO classeDao = new ClasseDAO();
					classeDao.buscarCategoria(txtBuscar.getText());
					List<Categoria> list;
					list = classeDao.buscarCategoria(txtBuscar.getText());
					DefaultTableModel modelo = (DefaultTableModel) tbDados.getModel();

					modelo.setNumRows(0);

					for (Categoria c : list) {

						Object[] dados = { c.getId_categoria(), c.getNome_categoria() };
						modelo.addRow(dados);

					}

				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

		});
		btnExcluir.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {

				try {
					ClasseDAO classeDao = new ClasseDAO();

					Categoria categoria = new Categoria();
					if (tbDados.getSelectedRow() != -1) {

					// categoria.setNome_categoria(txtNomeCategoria.getText());

					categoria.setId_categoria(Integer.parseInt(txtIdCategoria.getText()));
					classeDao.deletarCategoria(categoria);
					} else {
						JOptionPane.showMessageDialog(null, "Selecione uma Categoria para Excluir.");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// try {
				// if (tbDados.getSelectedRow() != -1) {

				// Categoria categoria = new Categoria();
				// ClasseDAO classeDao = new ClasseDAO();

				// categoria.setId_categoria((Integer)
				// tbDados.getValueAt(tbDados.getSelectedRow(), 1));
				// categoria.setNome_categoria((String)
				// tbDados.getValueAt(tbDados.getSelectedRow(), 2));
				// classeDao.deletarCategoria(categoria);
				// txtNomeCategoria.setText("");
				// txtIdCategoria.setText("");
				// }
				// else {
				// JOptionPane.showMessageDialog(null, "Selecione um produto para excluir.");
				// }
				// } catch (SQLException e1) {
				// TODO Auto-generated catch block
				// e1.printStackTrace();
				// }

			}

		});

		JButton btnProduto = new JButton("Produto");
		btnProduto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				CadastroProduto CadastroProduto = new CadastroProduto();

				CadastroProduto.main(null);
			}
		});
		btnProduto.setBounds(10, 115, 96, 23);
		frame.getContentPane().add(btnProduto);

	}
}
