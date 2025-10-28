import java.time.LocalDate;

public class Atendente extends Pessoa {
    private String matricula;

    public Atendente(String nome, String cpf, LocalDate dataNascimento, String matricula) {
        super(nome, cpf, dataNascimento);
        this.matricula = matricula;
    }

    public String getMatricula() {
        return matricula;
    }

    public void inserirPacienteNaFila(Paciente paciente, FilaAtendimento fila) {
        fila.inserirPaciente(paciente);
        System.out.println("Paciente " + paciente.getNome() + " inserido na fila por " + getNome());
    }

    @Override
    public String toString() {
        return "Atendente: " + getNome() + " (Matrícula: " + matricula + ")";
    }
}
