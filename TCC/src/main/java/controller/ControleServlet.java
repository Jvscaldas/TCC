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

@WebServlet("/controle")
public class ControleServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ControleServlet() {
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
			RequestDispatcher rd = request.getRequestDispatcher("controle.jsp");
			request.setAttribute("clientes", clientes);
			request.setAttribute("erro", erro);
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

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

			if (botao.equals("Controle")) {
				servicos = sDao.consultaServicos();
			}

		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			try {
				clientes = cDao.consultaClientes();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("controle.jsp");
			request.setAttribute("servico", s);
			request.setAttribute("servicos", servicos);
			request.setAttribute("erro", erro);
			request.setAttribute("saida", saida);
			request.setAttribute("clientes", clientes);
			request.setAttribute("cliente", c);

			rd.forward(request, response);
		}
	}
}
