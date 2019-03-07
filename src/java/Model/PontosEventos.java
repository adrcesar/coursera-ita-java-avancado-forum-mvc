package Model;

public enum PontosEventos {    
    TOPICO(10), COMENTARIO(5);
     
    private final int pontos;
    
    PontosEventos(int valorOpcao){
        pontos = valorOpcao;
    }
    public int getPontos(){
        return pontos;
    }
}