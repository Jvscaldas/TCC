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

@WebServlet("/agendamento")
public class AgendamentoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public AgendamentoServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String cpf = request.getParameter("cpf");
		String nome = request.getParameter("nome");
		String email = request.getParameter("email");
		String telefone = request.getParameter("telefone");
		String botao = request.getParameter("botao");

		Cliente c = new Cliente();
		GenericDao gDao = new GenericDao();
		IClienteDao cDao = new ClienteDao(gDao);

		String erro = "";
		String saida = "";

		List<Cliente> clientes = new ArrayList<Cliente>();

		try {
			if (botao.equals("Inserir")) {
				c = valido(cpf, nome, email, telefone, botao);
				saida = cDao.insereCliente(c);
				c = new Cliente();
			}
			if (botao.equals("Atualizar")) {
				c = valido(cpf, nome, email, telefone, botao);
				saida = cDao.atualizaCliente(c);
				c = new Cliente();
			}

			if (botao.equals("Excluir")) {
				c = valido(cpf, nome, email, telefone, botao);
				saida = cDao.excluiCliente(c);
				c = new Cliente();
			}

			if (botao.equals("Buscar")) {
				c = valido(cpf, nome, email, telefone, botao);
				c = cDao.consultaCliente(c);
			}

		} catch (SQLException | ClassNotFoundException | IOException e) {
			erro = e.getMessage();
		} finally {
			RequestDispatcher rd = request.getRequestDispatcher("agendamento.jsp");
			request.setAttribute("cliente", c);
			request.setAttribute("clientes", clientes);
			request.setAttribute("erro", erro);
			request.setAttribute("saida", saida);

			rd.forward(request, response);
		}
	}

	private Cliente valido(String cpf, String nome, String email, String telefone, String botao) throws IOException {

		Cliente c = new Cliente();

		if (botao.equals("Inserir")) {
			if (cpf.equals("") || nome.equals("") || email.equals("") || telefone.equals("")) {
				throw new IOException("Preencher cpf, nome, email e telefone");
			} else {
				c.setCpf(cpf);
				c.setNome(nome);
				c.setEmail(email);
				c.setTelefone(telefone);
			}
		}

		if (botao.equals("Atualizar")) {
			if (cpf.equals("") || nome.equals("") || email.equals("") || telefone.equals("")) {
				throw new IOException("Preencher cpf, nome, email e telefone");
			} else {
				c.setCpf(cpf);
				c.setNome(nome);
				c.setEmail(email);
				c.setTelefone(telefone);
			}
		}

		if (botao.equals("Excluir")) {
			if (cpf.equals("")) {
				throw new IOException("Preencher cpf");
			} else {
				c.setCpf(cpf);
			}
		}

		if (botao.equals("Buscar")) {
			if (cpf.equals("")) {
				throw new IOException("Preencher cpf");
			} else {
				c.setCpf(cpf);
			}

		}
		return c;
	}
}