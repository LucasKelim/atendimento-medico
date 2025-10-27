package ProjetoAtendimentoMedico;

import java.time.LocalDateTime;
import java.util.*;

public class FilaAtendimento {
    private Queue<Paciente> pacientesFila = new LinkedList<>();
    private List<RegistroAtendimento> historicoAtendidos = new ArrayList<>();
    private List<String> perguntasClassificacao = Arrays.asList(
            "O paciente está inconsciente?",
            "O paciente apresenta dificuldade para respirar?",
            "O paciente está com hemorragia?",
            "O paciente apresenta dor intensa?"
    );

    public void inserirPaciente(Paciente paciente) {
        pacientesFila.add(paciente);
        System.out.println("Paciente inserido na fila: " + paciente.getNome());
    }

    public Prioridade realizarClassificacao(Paciente paciente, List<Boolean> respostas) {
        int positivo = 0;
        for (boolean resposta : respostas) {
            if (resposta) positivo++;
        }

        Prioridade prioridade;
        if (positivo == 4) prioridade = Prioridade.VERMELHO;
        else if (positivo >= 2) prioridade = Prioridade.AMARELO;
        else if (positivo == 1) prioridade = Prioridade.VERDE;
        else prioridade = Prioridade.AZUL;

        paciente.realizarClassificacao(prioridade, respostas);
        System.out.println("Classificação de " + paciente.getNome() + ": " + prioridade.getNome());
        return prioridade;
    }

    public List<Paciente> mostrarFila() {
        return new ArrayList<>(pacientesFila);
    }

    public RegistroAtendimento simularAtendimento(Medico medico) {
        if (pacientesFila.isEmpty()) {
            System.out.println("Fila vazia. Nenhum paciente para atender.");
            return null;
        }

        Paciente paciente = pacientesFila.poll();
        RegistroAtendimento registro = medico.atenderPaciente(paciente);
        historicoAtendidos.add(registro);
        return registro;
    }

    public List<Paciente> getPacientesEmEspera() {
        return new ArrayList<>(pacientesFila);
    }

    public List<RegistroAtendimento> getHistoricoAtendidos() {
        return historicoAtendidos;
    }

    public List<String> getPerguntasClassificacao() {
        return perguntasClassificacao;
    }
}
