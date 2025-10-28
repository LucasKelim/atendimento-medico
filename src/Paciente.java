import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Paciente extends Pessoa {
    private LocalDateTime horarioChegada;
    private Prioridade classificacao;
    private List<Boolean> respostasClassificacao;

    public Paciente(String nome, String cpf, LocalDate dataNascimento, LocalDateTime horarioChegada) {
        super(nome, cpf, dataNascimento);
        this.horarioChegada = horarioChegada;
    }

    public void realizarClassificacao(Prioridade classificacao, List<Boolean> respostas) {
        this.classificacao = classificacao;
        this.respostasClassificacao = respostas;
    }

    public Prioridade getClassificacao() {
        return classificacao;
    }

    public LocalDateTime getHorarioChegada() {
        return horarioChegada;
    }

    public List<Boolean> getRespostasClassificacao() {
        return respostasClassificacao;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Paciente{");
        sb.append(super.toString());
        sb.append("horarioChegada=").append(horarioChegada);
        sb.append(", classificacao=").append(classificacao);
        sb.append(", respostasClassificacao=").append(respostasClassificacao);
        sb.append('}');
        return sb.toString();
    }
}
