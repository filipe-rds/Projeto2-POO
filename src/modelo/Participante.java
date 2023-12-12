package modelo;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Participante {
	// Atributtes
	private String cpf ;
	private String nascimento;
	private ArrayList<Ingresso> Ingressos ;
	
	// Constructors
	public Participante (String cpf,String nascimento) {
		
		this.cpf = cpf;
		this.nascimento = nascimento;
		this.Ingressos = new ArrayList<>();
	}
	
	// Getters and Setters
	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNascimento() {
		return nascimento;
	}

	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}

	public ArrayList<Ingresso> getIngressos() {
		return Ingressos;
	}

	public void setIngressos(ArrayList<Ingresso> ingressos) {
		Ingressos = ingressos;
	}

	// Methods
	public int calcularIdade() {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		LocalDate dataNascimento = LocalDate.parse(nascimento, formatter);
		
		LocalDate dataAtual = LocalDate.now();
		
		return Period.between(dataNascimento, dataAtual).getYears();
		
	}
	
	@Override
	public String toString() {
    StringBuilder ingressosStr = new StringBuilder();

    if (Ingressos != null && !Ingressos.isEmpty()) {
        ingressosStr.append("Ingressos [");
        for (Ingresso ingresso : Ingressos) {
            ingressosStr.append(String.format("código=%s, telefone=%s; ", ingresso.getCodigo(), ingresso.getTelefone()));
        }
        ingressosStr.setLength(ingressosStr.length() - 2);  // Remover a última vírgula e espaço
        ingressosStr.append("]");
    } else {
        ingressosStr.append("null");
    }

    return String.format("Participante [cpf=%s, nascimento=%s, %s]\n", cpf, nascimento, ingressosStr.toString());
}



}