package modelo;
import java.util.ArrayList;

public class Evento {
	// Atributtes
	private int id;
	private String data;
	private String descricao;
	private int capacidade;
	private double preco;
	private ArrayList<Ingresso> Ingressos;
	
	// Constructors
	public Evento(int id, String data, String descricao, int capacidade, double preco) {
		this.id = id;
		this.data = data;
		this.descricao = descricao;
		this.capacidade = capacidade;
		this.preco = preco;
		Ingressos = new ArrayList<Ingresso>();
	}
	
	// Getters and Setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public void setCapacidade(int capacidade) {
		this.capacidade = capacidade;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public ArrayList<Ingresso> getIngressos() {
		return Ingressos;
	}

	public void setIngressos(ArrayList<Ingresso> ingressos) {
		Ingressos = ingressos;
	}
	
	// Methods
	public boolean lotado() {
		int qt_ingressos = Ingressos.size();
		
		if (capacidade == qt_ingressos) {
			return true;
		}
		else
			return false;
	}
	
	public int quantidadeIngressos() {
		
		return Ingressos.size();
		
	}
	
	public double totaArrecadado() {
		return preco * Ingressos.size();
	}
	
	@Override
	public String toString() {
		return "Evento [id=" + id + ", data=" + data + ", descricao=" + descricao + ", capacidade=" + capacidade
				+ ", preco=" + preco + ", Ingressos=" + Ingressos + "]";
	}

}