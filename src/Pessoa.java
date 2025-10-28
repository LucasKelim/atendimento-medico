import java.time.LocalDate;

abstract public class Pessoa {
    private String nome;
    private String cpf;
    private LocalDate dataNascimento;

    public Pessoa() {}

    public Pessoa(String nome, String cpf, LocalDate dataNascimento) {
        setNome(nome);
        setCpf(cpf);
        this.dataNascimento = dataNascimento;
    }

    public Pessoa(String nome, String cpf, LocalDate dataNascimento, String crm) {
        setNome(nome);
        setCpf(cpf);
        this.dataNascimento = dataNascimento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().length() < 3) {
            throw new IllegalArgumentException("O nome deve ter pelo menos 3 caracteres.");
        }
        this.nome = nome.trim();
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        if (cpf == null || cpf.trim().length() < 11) {
            throw new IllegalArgumentException("O CPF deve ter pelo menos 11 dígitos.");
        }
        this.cpf = cpf.trim();
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Pessoa [nome=");
        builder.append(nome);
        builder.append(", cpf=");
        builder.append(cpf);
        builder.append(", dataNascimento=");
        builder.append(dataNascimento);
        builder.append("]");
        return builder.toString();
    }
}