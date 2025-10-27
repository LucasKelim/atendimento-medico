package hospital;

import java.time.LocalDateTime;

public class Paciente extends Pessoa{
	private LocalDateTime horarioChegada;
	private Prioridade classificacao;
	private List<Boolean> respostasClassificacao;
	
	public LocalDateTime getHorarioChegada() {
		return horarioChegada;
	}
	public void setHorarioChegada(LocalDateTime horarioChegada) {
		this.horarioChegada = horarioChegada;
	}
	public Prioridade getClassificacao() {
		return classificacao;
	}
	public void setClassificacao(Prioridade classificacao) {
		this.classificacao = classificacao;
	}
	public List<Boolean> getRespostasClassificacao() {
		return respostasClassificacao;
	}
	public void setRespostasClassificacao(List<Boolean> respostasClassificacao) {
		this.respostasClassificacao = respostasClassificacao;
	}
	
	
}
