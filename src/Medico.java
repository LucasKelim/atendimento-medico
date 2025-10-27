package ProjetoAtendimentoMedico;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Medico extends Pessoa {
    private String crm;

    public Medico(String nome, String cpf, LocalDate dataNascimento, String crm) {
        super(nome, cpf, dataNascimento);
        this.crm = crm;
    }

    public String getCrm() {
        return crm;
    }

    public RegistroAtendimento atenderPaciente(Paciente paciente) {
        RegistroAtendimento registro = new RegistroAtendimento(paciente, this, LocalDateTime.now());
        System.out.println("Médico " + getNome() + " atendeu " + paciente.getNome());
        return registro;
    }

    @Override
    public String toString() {
        return "Médico: " + getNome() + " (CRM: " + crm + ")";
    }
}
