package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cliente;
import model.Servico;
import persistence.ClienteDao;
import persistence.GenericDao;
import persistence.IClienteDao;
import persistence.IServicoDao;
import persistence.ServicoDao;

@WebServlet("/servico")
public class ServicoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ServicoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		GenericDao gDao = new GenericDao();
		IClienteDao cDao = new ClienteDao(gDao);
		String erro = "";
		List<Cliente> clientes = new ArrayList<Cliente>();

		try {
			clientes = cDao.consultaClientes();
		} catch (ClassNotFoundException | SQLException e) {
			erro = e.getMessage();
		} finally {
			RequestDispatcher rd = request.getRequestDispatcher("servico.jsp");
			request.setAttribute("clientes", clientes);
			request.setAttribute("erro", erro);
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String placa = request.getParameter("placa");
		String servico = request.getParameter("servico");
		String dt_entrada = request.getParameter("dt_entrada");
		String dt_retirada = request.getParameter("dt_retirada");
		String status = request.getParameter("status");
		String cliente = request.getParameter("cliente");
		String botao = request.getParameter("botao");

		Servico s = new Servico();
		Cliente c = new Cliente();

		GenericDao gDao = new GenericDao();
		IServicoDao sDao = new ServicoDao(gDao);
		IClienteDao cDao = new ClienteDao(gDao);

		String erro = "";
		String saida = "";

		List<Servico> servicos = new ArrayList<Servico>();
		List<Cliente> clientes = new ArrayList<Cliente>();

		try {

			if (botao.equals("Listar")) {
				servicos = sDao.consultaServicos();
			}

			if (botao.equals("Inserir")) {
				s = valido(placa, servico, dt_entrada, dt_retirada, cliente, status, botao);
				saida = sDao.insereServiço(s);
				s = new Servico();
			}

			if (botao.equals("Atualizar")) {
				s = valido(placa, servico, dt_entrada, dt_retirada, cliente, status, botao);
				saida = sDao.atualizaServiço(s);
				s = new Servico();
			}

			if (botao.equals("Excluir")) {
				s = valido(placa, servico, dt_entrada, dt_retirada, cliente, status, botao);
				saida = sDao.excluiServiço(s);
				s = new Servico();
			}

			if (botao.equals("Buscar")) {
				s = valido(placa, servico, dt_entrada, dt_retirada, cliente, status, botao);
				s = sDao.consultaServiço(s);
				c = s.getCliente();
			}

		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			try {
				clientes = cDao.consultaClientes();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("servico.jsp");
			request.setAttribute("servico", s);
			request.setAttribute("servicos", servicos);
			request.setAttribute("erro", erro);
			request.setAttribute("saida", saida);
			request.setAttribute("clientes", clientes);
			request.setAttribute("cliente", c);

			rd.forward(request, response);
		}

	}

	private Servico valido(String placa, String servico, String dt_entrada, String dt_retirada, String cliente,
			String status, String botao) throws IOException {

		Servico s = new Servico();
		Cliente c = new Cliente();

		if (botao.equals("Inserir")) {
			if (placa.equals("") || servico.equals("") || dt_entrada.equals("") || dt_retirada.equals("")
					|| status.equals("") || cliente.equals("0")) {
				throw new IOException("Preencha os campos");
			} else {
				c.setCpf(cliente);

				s.setPlaca(placa);
				s.setServico(servico);
				s.setDt_entrada(dt_entrada);
				s.setDt_retirada(dt_retirada);
				s.setStatus(status);
				s.setCliente(c);
			}
		}

		if (botao.equals("Atualizar")) {
			if (placa.equals("") || servico.equals("") || dt_entrada.equals("") || dt_retirada.equals("")
					|| status.equals("") || cliente.equals("0")) {
				throw new IOException("Preencha os campos");
			} else {
				c.setCpf(cliente);

				s.setPlaca(placa);
				s.setServico(servico);
				s.setDt_entrada(dt_entrada);
				s.setDt_retirada(dt_retirada);
				s.setStatus(status);
				s.setCliente(c);
			}
		}

		if (botao.equals("Excluir")) {
			if (placa.equals("")) {
				throw new IOException("Preencha a placa do veículo");
			} else {
				s.setPlaca(placa);
			}
		}

		if (botao.equals("Buscar")) {
			if (placa.equals("")) {
				throw new IOException("Preencha a placa do veículo");
			} else {
				s.setPlaca(placa);
			}
		}

		return s;
	}
}
