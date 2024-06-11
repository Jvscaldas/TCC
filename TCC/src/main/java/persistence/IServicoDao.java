package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Servico;

public interface IServicoDao {

	public String insereServi�o(Servico s) throws SQLException, ClassNotFoundException;

	public String atualizaServi�o(Servico s) throws SQLException, ClassNotFoundException;

	public String excluiServi�o(Servico s) throws SQLException, ClassNotFoundException;

	public Servico consultaServi�o(Servico s) throws SQLException, ClassNotFoundException;

	public List<Servico> consultaServicos() throws SQLException, ClassNotFoundException;

	public List<Servico> consultaMes(String data) throws SQLException, ClassNotFoundException;
}
