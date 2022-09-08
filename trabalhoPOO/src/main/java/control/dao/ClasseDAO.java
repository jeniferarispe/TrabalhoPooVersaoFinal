package control.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import control.connection.SingleConnection;
import model.Categoria;
import model.Produto;

public class ClasseDAO {

	public Connection connection;
	private PreparedStatement statement;

	public ClasseDAO() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(Categoria categoria) {
		try {
			String sql = "INSERT INTO categoria(nome_categoria) VALUES (?)";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, categoria.getNome_categoria());

			statement.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public List<Categoria> listar() throws SQLException {
		List<Categoria> list = new ArrayList<Categoria>();

		String sql = "SELECT * FROM categoria";

		PreparedStatement statement = connection.prepareStatement(sql);
		try {

			ResultSet resultado = statement.executeQuery();
			while (resultado.next()) {
				Categoria categoria = new Categoria();
				categoria.setId_categoria(resultado.getInt("id_categoria"));
				categoria.setNome_categoria(resultado.getString("nome_categoria"));
				list.add(categoria);

			}
		} catch (SQLException e) {

			list = null;
		}
		return list;

	}

	public List<Categoria> buscarCategoria(String nome_categoria) throws SQLException {

		String sql = "SELECT * FROM categoria WHERE nome_categoria ILIKE ?";

		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultado = null;

		List<Categoria> categorias = new ArrayList<Categoria>();

		try {
			statement.setString(1, "%" + nome_categoria + "%");

			resultado = statement.executeQuery();

			while (resultado.next()) {

				Categoria categoria = new Categoria();
				categoria.setId_categoria(resultado.getInt("id_categoria"));
				categoria.setNome_categoria(resultado.getString("nome_categoria"));
				categorias.add(categoria);
			}

		} catch (SQLException ex) {

		}

		return categorias;

	}

	public void atualizarCategoria(Categoria categoria) {

		try {
			String sql = ("UPDATE categoria SET id_categoria=?,nome_categoria = ? WHERE id_categoria= "
					+ categoria.getId_categoria());

			// String sql = ("UPDATE categoria SET nome_categoria = ?");
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, categoria.getId_categoria());
			statement.setString(2, categoria.getNome_categoria());

			statement.execute();
			connection.commit();
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public void deletarCategoria(Categoria categoria) throws SQLException {

		// Categoria categoria = new Categoria();
		try {
			String sql = "DELETE FROM categoria WHERE id_categoria =? ";
			// String sql = "DELETE FROM categoria WHERE id_categoria =? " +
			// categoria.getId_categoria();
			// String sql = "DELETE FROM categoria WHERE id_categoria = ? ";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, categoria.getId_categoria());
			// statement.setString(2, categoria.getNome_categoria());
			// statement.setString(2, categoria.getNome_categoria());
			statement.execute();
			connection.commit();
			// connection.close();
			JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public void salvarProduto(Produto produto) {
		try {
			String sql = "INSERT INTO produto(nome_produto,valor_produto,descricao_produto) VALUES (?,?,?)";
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, produto.getNome_produto());
			statement.setFloat(2, produto.getValor_produto());
			statement.setString(3, produto.getDescricao_produto());

			statement.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	public List<Produto> listarProduto() throws SQLException {
		List<Produto> list = new ArrayList<Produto>();

		String sql = "SELECT * FROM produto";

		PreparedStatement statement = connection.prepareStatement(sql);
		try {

			ResultSet resultado = statement.executeQuery();
			while (resultado.next()) {
				Produto produto = new Produto();
				produto.setId_produto(resultado.getInt("id_produto"));
				produto.setNome_produto(resultado.getString("nome_produto"));
				// produto.setValor_produto(Float.parseFloat(("valor_produto")));
				produto.setValor_produto(resultado.getFloat("valor_produto"));
				produto.setDescricao_produto(resultado.getString("descricao_produto"));
				list.add(produto);

			}
		} catch (SQLException e) {

			list = null;
		}
		return list;

	}

	public List<Produto> buscarProduto(String nome_produto) throws SQLException {

		String sql = "SELECT * FROM produto WHERE nome_produto ILIKE ?";

		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultado = null;

		List<Produto> produtos = new ArrayList<Produto>();

		try {
			statement.setString(1, "%" + nome_produto + "%");

			resultado = statement.executeQuery();

			while (resultado.next()) {

				Produto produto = new Produto();
				produto.setId_produto(resultado.getInt("id_produto"));
				produto.setNome_produto(resultado.getString("nome_produto"));
				produto.setValor_produto(resultado.getFloat("valor_produto"));

				produtos.add(produto);
			}

		} catch (SQLException ex) {

		}

		return produtos;

	}

	public void atualizarProduto(Produto produto) {

		try {
			String sql = ("UPDATE produto SET id_produto=?,nome_produto = ?,valor_produto=?,descricao_produto=? WHERE id_produto= "
					+ produto.getId_produto());

			// String sql = ("UPDATE categoria SET nome_categoria = ?");
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, produto.getId_produto());
			statement.setString(2, produto.getNome_produto());
			statement.setFloat(3, produto.getValor_produto());
			statement.setString(4, produto.getDescricao_produto());

			statement.execute();
			connection.commit();
			JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");

		} catch (SQLException e) {
			try {
				JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + e);
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	public void deletarProduto(Produto produto) throws SQLException {

	
		try {
			String sql = "DELETE FROM produto WHERE id_produto =? ";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, produto.getId_produto());
			statement.execute();
			connection.commit();
			// connection.close();
			JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao excluir: " + e);
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}
	
	
}
