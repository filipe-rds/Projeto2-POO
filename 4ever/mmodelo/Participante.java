package mmodelo;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class Participante {
	
	
	@Override
	public String toString() {
		return "Participante [cpf=" + cpf + ", nascimento=" + nascimento + ", Ingressos=" + Ingressos + "]";
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public LocalDate getNascimento() {
		return nascimento;
	}

	public void setNascimento(LocalDate nascimento) {
		this.nascimento = nascimento;
	}

	public ArrayList<Ingresso> getIngressos() {
		return Ingressos;
	}

	public void setIngressos(ArrayList<Ingresso> ingressos) {
		Ingressos = ingressos;
	}

	private String cpf ;
	private LocalDate nascimento;
	private ArrayList<Ingresso> Ingressos ;
	
	
	public Participante (String cpf,LocalDate nascimento) {
		
		this.cpf = cpf;
		this.nascimento = nascimento;
		this.Ingressos = new ArrayList<>();
	}
	
	public int calcularIdade() {
		
		LocalDate dataAtual = LocalDate.now();
		return Period.between(nascimento, dataAtual).getYears();
		
	}

}
