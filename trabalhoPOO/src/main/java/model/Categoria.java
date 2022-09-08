package model;


public class Categoria {

	private int id_categoria ;
	private String nome_categoria;
	public int getId_categoria() {
		return id_categoria;
	}
	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}
	public String getNome_categoria() {
		return nome_categoria;
	}
	public void setNome_categoria(String txtNomeCategoria) {
		this.nome_categoria = txtNomeCategoria;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id_categoria;
		result = prime * result + ((nome_categoria == null) ? 0 : nome_categoria.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Categoria other = (Categoria) obj;
		if (id_categoria != other.id_categoria)
			return false;
		if (nome_categoria == null) {
			if (other.nome_categoria != null)
				return false;
		} else if (!nome_categoria.equals(other.nome_categoria))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Categoria [id_categoria=" + id_categoria + ", nome_categoria=" + nome_categoria + "]";
	}
	public void setIdCategoria(int valueAt) {
		// TODO Auto-generated method stub
		
	}
	
}
