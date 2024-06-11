package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;

public class ClienteDao implements IClienteDao {

	private GenericDao gDao;

	public ClienteDao(GenericDao gDao) {
		this.gDao = gDao;
	}

	@Override
	public String insereCliente(Cliente c) throws SQLException, ClassNotFoundException {

		Connection con = gDao.getConnection();

		String sql = "INSERT INTO cliente VALUES (?,?,?,?)";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, c.getCpf());
		ps.setString(2, c.getNome());
		ps.setString(3, c.getEmail());
		ps.setString(4, c.getTelefone());

		ps.execute();

		ps.close();
		con.close();

		return "Cliente inserido com sucesso!";
	}

	@Override
	public String atualizaCliente(Cliente c) throws SQLException, ClassNotFoundException {

		Connection con = gDao.getConnection();

		String sql = "UPDATE cliente SET nome = ?, email = ?, telefone = ? WHERE cpf = ?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, c.getNome());
		ps.setString(2, c.getEmail());
		ps.setString(3, c.getTelefone());
		ps.setString(4, c.getCpf());

		ps.execute();

		ps.close();
		con.close();

		return "Cliente atualizado com sucesso!";
	}

	@Override
	public String excluiCliente(Cliente c) throws SQLException, ClassNotFoundException {

		Connection con = gDao.getConnection();

		String sql = "DELETE FROM cliente WHERE cpf = ?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, c.getCpf());

		ps.execute();

		ps.close();
		con.close();

		return "Cliente excluido com sucesso";
	}

	@Override
	public Cliente consultaCliente(Cliente c) throws SQLException, ClassNotFoundException {

		Connection con = gDao.getConnection();

		String sql = "SELECT cpf, nome, email, telefone FROM cliente WHERE cpf = ?";

		PreparedStatement ps = con.prepareStatement(sql);
		ps.setString(1, c.getCpf());

		ResultSet rs = ps.executeQuery();
		
		if (rs.next()) {
			c.setCpf(rs.getString("cpf"));
			c.setNome(rs.getString("nome"));
			c.setEmail(rs.getString("email"));
			c.setTelefone(rs.getString("telefone"));
		}

		rs.close();
		ps.close();
		con.close();

		return c;
	}

	@Override
	public List<Cliente> consultaClientes() throws SQLException, ClassNotFoundException {

		List<Cliente> clientes = new ArrayList<Cliente>();

		Connection con = gDao.getConnection();

		String sql = "SELECT cpf, nome, email, telefone FROM cliente";

		PreparedStatement ps = con.prepareStatement(sql);

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {
			Cliente c = new Cliente();
			c.setCpf(rs.getString("cpf"));
			c.setNome(rs.getString("nome"));
			c.setEmail(rs.getString("email"));
			c.setTelefone(rs.getString("telefone"));

			clientes.add(c);
		}

		rs.close();
		ps.close();
		con.close();

		return clientes;
	}

}
