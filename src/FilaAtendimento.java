import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class FilaAtendimento {
    private Queue<Paciente> pacientesFila;
    private List<RegistroAtendimento> historicoAtendidos;

    public void inserirPaciente(Paciente paciente) {
        pacientesFila.add(paciente);
    }

    public List<Paciente> mostrarFile() {
        return pacientesFila.stream().toList();
    }
}
