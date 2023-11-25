package mmodelo;
import java.time.LocalDate;
import java.util.ArrayList;

public class Evento {
	private int id;
	private LocalDate data;
	private String descricao;
	private int capacidade ;
	private double preco;
	private ArrayList<Ingresso> Ingressos;
	
	
	public Evento(LocalDate data,String descricao,int capacidade,double preco) {
		// this.id = método
		this.data = data;
		this.descricao = descricao;
		this.capacidade = capacidade;
		this.preco = preco;
		Ingressos = new ArrayList<Ingresso>();
	}
	
	
	
	@Override
	public String toString() {
		return "Evento [id=" + id + ", data=" + data + ", descricao=" + descricao + ", capacidade=" + capacidade
				+ ", preco=" + preco + ", Ingressos=" + Ingressos + "]";
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public LocalDate getData() {
		return data;
	}



	public void setData(LocalDate data) {
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
	

}
