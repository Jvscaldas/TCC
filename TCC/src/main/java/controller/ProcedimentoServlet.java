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

import model.Servico;
import persistence.GenericDao;
import persistence.ServicoDao;

@WebServlet("/procedimento")
public class ProcedimentoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ProcedimentoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String data = request.getParameter("data");
		String botao = request.getParameter("botao");

		GenericDao gDao = new GenericDao();
		ServicoDao sDao = new ServicoDao(gDao);
		List<Servico> lista = new ArrayList<Servico>();
		String saida = "";
		String erro = "";

		try {
			if (botao.equals("Pesquisar")) {
				lista = sDao.consultaMes(data);
			}
		} catch (SQLException | ClassNotFoundException e) {
			erro = e.getMessage();

		} finally {
			RequestDispatcher rd = request.getRequestDispatcher("procedimento.jsp");
			request.setAttribute("lista", lista);
			request.setAttribute("dt_entrada", data);
			request.setAttribute("erro", erro);
			request.setAttribute("saida", saida);
			rd.forward(request, response);
		}
	}

}