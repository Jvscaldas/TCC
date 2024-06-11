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
import persistence.ClienteDao;
import persistence.GenericDao;
import persistence.IClienteDao;

@WebServlet("/cliente")
public class ClienteServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ClienteServlet() {
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
			RequestDispatcher rd = request.getRequestDispatcher("cliente.jsp");
			request.setAttribute("clientes", clientes);
			request.setAttribute("erro", erro);
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String botao = request.getParameter("botao");

		Cliente c = new Cliente();

		GenericDao gDao = new GenericDao();
		IClienteDao cDao = new ClienteDao(gDao);

		String erro = "";
		String saida = "";

		List<Cliente> clientes = new ArrayList<Cliente>();

		try {

			if (botao.equals("Listar")) {
				clientes = cDao.consultaClientes();
			}

		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();
		} finally {
			try {
				clientes = cDao.consultaClientes();
			} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
			}
			RequestDispatcher rd = request.getRequestDispatcher("cliente.jsp");
			request.setAttribute("erro", erro);
			request.setAttribute("saida", saida);
			request.setAttribute("clientes", clientes);
			request.setAttribute("cliente", c);

			rd.forward(request, response);
		}
	}
}
