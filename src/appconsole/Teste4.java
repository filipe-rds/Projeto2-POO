package appconsole;

import regras_negocio.Fachada;

public class Teste4 {
    
    public static void main(String[] args) {
        

        try{
            System.out.println(Fachada.listarParticipantes());
        }

        catch(Exception e){
            System.out.println("erro ---->"+ e.getMessage());
        }
    }
}
