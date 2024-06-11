package model;

public class Servico {

	private String placa;
	private String servico;
	private String dt_entrada;
	private String dt_retirada;
	private Cliente cliente;
	private String status;

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public String getServico() {
		return servico;
	}

	public void setServico(String servico) {
		this.servico = servico;
	}

	public String getDt_entrada() {
		return dt_entrada;
	}

	public void setDt_entrada(String dt_entrada) {
		this.dt_entrada = dt_entrada;
	}

	public String getDt_retirada() {
		return dt_retirada;
	}

	public void setDt_retirada(String dt_retirada) {
		this.dt_retirada = dt_retirada;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Servico [placa=" + placa + ", servico=" + servico + ", dt_entrada=" + dt_entrada + ", dt_retirada="
				+ dt_retirada + ", cliente=" + cliente + ", status=" + status + "]";
	}

}
