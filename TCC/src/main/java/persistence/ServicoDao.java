package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Cliente;
import model.Servico;

public class ServicoDao implements IServicoDao {

	private GenericDao gDao;

	public ServicoDao(GenericDao gDao) {
		this.gDao = gDao;
	}

	@Override
	public String insereServiço(Servico s) throws SQLException, ClassNotFoundException {

		Connection con = gDao.getConnection();

		String sql = "INSERT INTO servico VALUES (?,?,?,?,?,?)";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, s.getPlaca());
		ps.setString(2, s.getServico());
		ps.setString(3, s.getDt_entrada());
		ps.setString(4, s.getDt_retirada());
		ps.setString(5, s.getCliente().getCpf());
		ps.setString(6, s.getStatus());

		ps.execute();
		ps.close();
		con.close();

		return "Serviço agendado com sucesso";
	}

	@Override
	public String atualizaServiço(Servico s) throws SQLException, ClassNotFoundException {

		Connection con = gDao.getConnection();

		String sql = "UPDATE servico SET servico = ?, dt_entrada = ?, dt_retirada = ?, cliente = ?, status = ? WHERE placa = ?";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, s.getServico());
		ps.setString(2, s.getDt_entrada());
		ps.setString(3, s.getDt_retirada());
		ps.setString(4, s.getCliente().getCpf());
		ps.setString(5, s.getStatus());
		ps.setString(6, s.getPlaca());

		ps.execute();
		ps.close();
		con.close();

		return "Serviço atualizado com sucesso";
	}

	@Override
	public String excluiServiço(Servico s) throws SQLException, ClassNotFoundException {

		Connection con = gDao.getConnection();

		String sql = "DELETE servico WHERE placa = ?";

		PreparedStatement ps = con.prepareStatement(sql);

		ps.setString(1, s.getPlaca());

		ps.execute();
		ps.close();
		con.close();

		return "Serviço excluído com sucesso";
	}

	@Override
	public Servico consultaServiço(Servico s) throws SQLException, ClassNotFoundException {

		Connection con = gDao.getConnection();

		StringBuffer sql = new StringBuffer();

		sql.append("SELECT c.cpf, c.nome, c.email, c.telefone, s.placa, s.servico, convert(varchar(10),dt_entrada,103) AS dt_entrada, convert(varchar(10),dt_retirada,103) AS dt_retirada, s.status ");
		sql.append("FROM servico s, cliente c ");
		sql.append("WHERE c.cpf = s.cliente ");
		sql.append("AND s.placa = ?");

		PreparedStatement ps = con.prepareStatement(sql.toString());

		ps.setString(1, s.getPlaca());

		ResultSet rs = ps.executeQuery();

		if (rs.next()) {

			Cliente c = new Cliente();

			c.setCpf(rs.getString("cpf"));
			c.setNome(rs.getString("nome"));
			c.setEmail(rs.getString("email"));
			c.setTelefone(rs.getString("telefone"));

			s.setPlaca(rs.getString("placa"));
			s.setServico(rs.getString("servico"));
			s.setDt_entrada(rs.getString("dt_entrada"));
			s.setDt_retirada(rs.getString("dt_retirada"));
			s.setStatus(rs.getString("status"));
			s.setCliente(c);
		}

		rs.close();
		ps.close();
		con.close();

		return s;
	}

	@Override
	public List<Servico> consultaServicos() throws SQLException, ClassNotFoundException {

		List<Servico> servicos = new ArrayList<Servico>();

		Connection con = gDao.getConnection();

		StringBuffer sql = new StringBuffer();

		sql.append("SELECT c.cpf, c.nome, c.email, c.telefone, s.placa, s.servico, convert(varchar(10),dt_entrada,103) AS dt_entrada, convert(varchar(10),dt_retirada,103) AS dt_retirada, s.status ");
		sql.append("FROM servico s, cliente c ");
		sql.append("WHERE c.cpf = s.cliente ");

		PreparedStatement ps = con.prepareStatement(sql.toString());

		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			Cliente c = new Cliente();
			c.setCpf(rs.getString("cpf"));
			c.setNome(rs.getString("nome"));
			c.setEmail(rs.getString("email"));
			c.setTelefone(rs.getString("telefone"));

			Servico s = new Servico();
			s.setPlaca(rs.getString("placa"));
			s.setServico(rs.getString("servico"));
			s.setDt_entrada(rs.getString("dt_entrada"));
			s.setDt_retirada(rs.getString("dt_retirada"));
			s.setStatus(rs.getString("status"));
			s.setCliente(c);

			servicos.add(s);
		}

		rs.close();
		ps.close();
		con.close();

		return servicos;
	}

	@Override
	public List<Servico> consultaMes(String data) throws SQLException, ClassNotFoundException {

		Connection c = gDao.getConnection();
		List<Servico> lista = new ArrayList<Servico>();
		StringBuffer sql = new StringBuffer();

		sql.append("SELECT convert(varchar(10),dt_entrada,103) AS dt_entrada, servico ");
		sql.append("FROM servico ");
		sql.append("WHERE dt_entrada = ? ");
		sql.append("ORDER BY dt_entrada");

		PreparedStatement ps = c.prepareStatement(sql.toString());
		ps.setString(1, data);
		ResultSet rs = ps.executeQuery();

		while (rs.next()) {

			Servico s = new Servico();

			s.setServico(rs.getString("servico"));
			s.setDt_entrada(rs.getString("dt_entrada"));

			lista.add(s);

		}

		rs.close();
		ps.close();
		c.close();

		return lista;
	}
}
