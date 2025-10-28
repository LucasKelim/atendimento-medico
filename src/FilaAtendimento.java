import java.util.*;

public class FilaAtendimento {
    private Queue<Paciente> pacientesFila = new PriorityQueue<>(new Comparator<Paciente>() {
        @Override
        public int compare(Paciente p1, Paciente p2) {
            if (p1.getClassificacao() == null && p2.getClassificacao() == null) return 0;
            if (p1.getClassificacao() == null) return 1;
            if (p2.getClassificacao() == null) return -1;

            int prioridadeCompare = Integer.compare(p1.getClassificacao().ordinal(), p2.getClassificacao().ordinal());
            if (prioridadeCompare != 0) return prioridadeCompare;

            return p1.getHorarioChegada().compareTo(p2.getHorarioChegada());
        }
    });
    private List<RegistroAtendimento> historicoAtendidos = new ArrayList<>();
    private List<String> perguntasClassificacao = Arrays.asList(
            "O paciente está inconsciente?",
            "O paciente apresenta dificuldade para respirar?",
            "O paciente está com hemorragia?",
            "O paciente apresenta dor intensa?"
    );

    public void inserirPaciente(Paciente paciente) {
        if (paciente.getClassificacao() == null) {
            paciente.realizarClassificacao(Prioridade.AZUL, Arrays.asList(false, false, false, false));
            System.out.println("Paciente sem classificação, definido como Azul automaticamente.");
        }

        pacientesFila.add(paciente);
        System.out.println("Paciente inserido na fila: " + paciente.getNome() + " - " + paciente.getClassificacao().getNome());
    }

    public Prioridade realizarClassificacao(Paciente paciente, List<Boolean> respostas) {
        int positivo = 0;
        for (boolean resposta : respostas) {
            if (resposta) positivo++;
        }

        Prioridade prioridade = switch (positivo) {
            case 1 -> Prioridade.VERDE;
            case 2, 3 -> Prioridade.AMARELO;
            case 4 -> Prioridade.VERMELHO;
            default -> Prioridade.AZUL;
        };

        paciente.realizarClassificacao(prioridade, respostas);
        System.out.println("Classificação de " + paciente.getNome() + ": " + prioridade.getNome());

        pacientesFila.remove(paciente);
        pacientesFila.add(paciente);

        return prioridade;
    }

    public List<Paciente> mostrarFila() {
        List<Paciente> lista = new ArrayList<>(pacientesFila);
        lista.sort(new Comparator<Paciente>() {
            @Override
            public int compare(Paciente p1, Paciente p2) {
                if (p1.getClassificacao() == null && p2.getClassificacao() == null) return 0;
                if (p1.getClassificacao() == null) return 1;
                if (p2.getClassificacao() == null) return -1;

                int prioridadeCompare = Integer.compare(p1.getClassificacao().ordinal(), p2.getClassificacao().ordinal());
                if (prioridadeCompare != 0) return prioridadeCompare;
                return p1.getHorarioChegada().compareTo(p2.getHorarioChegada());
            }
        });
        return lista;
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
