package persistence;

import java.sql.SQLException;
import java.util.List;

import model.Servico;

public interface IServicoDao {

	public String insereServiço(Servico s) throws SQLException, ClassNotFoundException;

	public String atualizaServiço(Servico s) throws SQLException, ClassNotFoundException;

	public String excluiServiço(Servico s) throws SQLException, ClassNotFoundException;

	public Servico consultaServiço(Servico s) throws SQLException, ClassNotFoundException;

	public List<Servico> consultaServicos() throws SQLException, ClassNotFoundException;

	public List<Servico> consultaMes(String data) throws SQLException, ClassNotFoundException;
}
