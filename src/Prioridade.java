import java.time.Duration;

public enum Prioridade {
    VERMELHO("Vermelho", Duration.ofMinutes(0),
            "Atendimento imediato - risco de morte"),
    AMARELO("Amarelo", Duration.ofMinutes(10),
            "Emergência - atendimento muito urgente"),
    VERDE("Verde", Duration.ofMinutes(30),
            "Pouco urgente - pode aguardar um pouco"),
    AZUL("Azul", Duration.ofMinutes(60),
            "Não urgente - atendimento eletivo");

    private final String nome;
    private final Duration tempoMaximoAtendimento;
    private final String descricao;

    private Prioridade(String nome, Duration tempoMaximoAtendimento, String descricao) {
        this.nome = nome;
        this.tempoMaximoAtendimento = tempoMaximoAtendimento;
        this.descricao = descricao;
    }

    public String getNome() {
        return nome;
    }

    public Duration getTempoMaximoAtendimento() {
        return tempoMaximoAtendimento;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return nome + " (" + descricao + ")";
    }
}
