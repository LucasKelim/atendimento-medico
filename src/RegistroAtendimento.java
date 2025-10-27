import java.time.Duration;
import java.time.LocalDateTime;

public class RegistroAtendimento {
    private Paciente paciente;
    private Medico medico;
    private LocalDateTime horarioAtendimento;
    private LocalDateTime horarioChegada;
    private Duration tempoEspera;

    public RegistroAtendimento(Paciente paciente, Medico medico, LocalDateTime horarioAtendimento) {
        this.paciente = paciente;
        this.medico = medico;
        this.horarioAtendimento = horarioAtendimento;
        this.horarioChegada = paciente.getHorarioChegada();
        this.tempoEspera = calcularTempoEspera();
    }

    private Duration calcularTempoEspera() {
        return Duration.between(horarioChegada, horarioAtendimento);
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public Medico getMedico() {
        return medico;
    }

    public LocalDateTime getHorarioAtendimento() {
        return horarioAtendimento;
    }

    public Duration getTempoEspera() {
        return tempoEspera;
    }

    @Override
    public String toString() {
        long minutos = tempoEspera.toMinutes();
        return "Paciente: " + paciente.getNome() +
                " | Médico: " + medico.getNome() +
                " | Espera: " + minutos + " min" +
                " | Início do atendimento: " + horarioAtendimento;
    }
}
