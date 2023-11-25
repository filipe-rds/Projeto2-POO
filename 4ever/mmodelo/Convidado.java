package mmodelo;
import java.time.LocalDate;

public class Convidado extends Participante{
	
	private String empresa;
	public Convidado(String cpf,LocalDate nascimento,String empresa) {
		
		super(cpf,nascimento);
		this.empresa = empresa;
		
	}
	public String getEmpresa() {
		return empresa;
	}
	public void setEmpresa(String empresa) {
		this.empresa = empresa;
	}
	
	@Override
	public String toString() {
        return "Convidado [ cpf=" + getCpf() +", nascimento=" + getNascimento() +", empresa=" + empresa + ", Ingressos=" + getIngressos() +']';
  }
	
	
	

}
