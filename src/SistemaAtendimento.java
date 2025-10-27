package ProjetoAtendimentoMedico;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

public class SistemaAtendimento {
    private FilaAtendimento fila = new FilaAtendimento();
    private List<Medico> medicos = new ArrayList<>();
    private Atendente atendente;

    private Scanner scanner = new Scanner(System.in);

    public SistemaAtendimento() {
        inicializarSistema();
    }

    private void inicializarSistema() {
        atendente = new Atendente("Carlos Silva", "12345678900", LocalDate.of(1990, 5, 10), "AT001");

        medicos.add(new Medico("Dr. João", "22233344455", LocalDate.of(1985, 3, 15), "CRM1234"));
        medicos.add(new Medico("Dra. Ana", "33344455566", LocalDate.of(1992, 8, 22), "CRM5678"));

        for (int i = 1; i <= 20; i++) {
            Paciente paciente = new Paciente(
                    "Paciente " + i,
                    "000111222" + i,
                    LocalDate.of(2000, 1, i % 28 + 1),
                    LocalDateTime.now().minusMinutes(i * 5)
            );

            List<Boolean> respostas = Arrays.asList(
                    Math.random() > 0.7,
                    Math.random() > 0.7,
                    Math.random() > 0.7,
                    Math.random() > 0.7
            );

            fila.realizarClassificacao(paciente, respostas);
            atendente.inserirPacienteNaFila(paciente, fila);
        }

        System.out.println("Sistema inicializado com 20 pacientes.");
    }

    public void exibirMenu() {
        int opcao = -1;
        while (opcao != 0) {
            System.out.println("\n========= MENU PRINCIPAL =========");
            System.out.println("1 - Inserir novo paciente");
            System.out.println("2 - Realizar classificação de paciente");
            System.out.println("3 - Mostrar fila de espera");
            System.out.println("4 - Simular atendimento");
            System.out.println("5 - Mostrar histórico de atendimentos");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                opcao = -1;
            }

            switch (opcao) {
                case 1 -> inserirNovoPaciente();
                case 2 -> classificarPaciente();
                case 3 -> mostrarFila();
                case 4 -> simularAtendimento();
                case 5 -> mostrarHistorico();
                case 0 -> System.out.println("Encerrando o sistema...");
                default -> System.out.println("Opção inválida!");
            }
        }
    }

    private void inserirNovoPaciente() {
        System.out.print("Nome: ");
        String nome = scanner.nextLine();
        System.out.print("CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Ano de nascimento: ");
        int ano = Integer.parseInt(scanner.nextLine());
        System.out.print("Mês de nascimento: ");
        int mes = Integer.parseInt(scanner.nextLine());
        System.out.print("Dia de nascimento: ");
        int dia = Integer.parseInt(scanner.nextLine());

        Paciente paciente = new Paciente(nome, cpf, LocalDate.of(ano, mes, dia), LocalDateTime.now());
        atendente.inserirPacienteNaFila(paciente, fila);
        System.out.println("Paciente inserido com sucesso!");
    }

    private void classificarPaciente() {
        List<Paciente> filaPacientes = fila.getPacientesEmEspera();

        if (filaPacientes.isEmpty()) {
            System.out.println("Nenhum paciente aguardando classificação.");
            return;
        }

        System.out.println("Selecione o paciente para classificar:");
        for (int i = 0; i < filaPacientes.size(); i++) {
            System.out.println((i + 1) + " - " + filaPacientes.get(i).getNome());
        }

        int indice = Integer.parseInt(scanner.nextLine()) - 1;
        Paciente paciente = filaPacientes.get(indice);

        List<Boolean> respostas = new ArrayList<>();
        for (String pergunta : fila.getPerguntasClassificacao()) {
            System.out.print(pergunta + " (s/n): ");
            String resposta = scanner.nextLine().trim().toLowerCase();
            respostas.add(resposta.equals("s"));
        }

        fila.realizarClassificacao(paciente, respostas);
    }

    private void mostrarFila() {
        List<Paciente> pacientes = fila.mostrarFila();
        if (pacientes.isEmpty()) {
            System.out.println("Fila vazia.");
        } else {
            System.out.println("\n--- Pacientes na Fila ---");
            for (Paciente p : pacientes) {
                System.out.println(p);
            }
        }
    }

    private void simularAtendimento() {
        System.out.println("Selecione o médico para atender:");
        for (int i = 0; i < medicos.size(); i++) {
            System.out.println((i + 1) + " - " + medicos.get(i).getNome());
        }

        int indice = Integer.parseInt(scanner.nextLine()) - 1;
        Medico medico = medicos.get(indice);

        RegistroAtendimento registro = fila.simularAtendimento(medico);
        if (registro != null) {
            System.out.println("Atendimento realizado: " + registro);
        }
    }

    private void mostrarHistorico() {
        List<RegistroAtendimento> historico = fila.getHistoricoAtendidos();
        if (historico.isEmpty()) {
            System.out.println("Nenhum paciente atendido ainda.");
        } else {
            System.out.println("\n--- Histórico de Atendimentos ---");
            for (RegistroAtendimento r : historico) {
                System.out.println(r);
            }
        }
    }
}
