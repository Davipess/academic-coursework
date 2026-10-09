/**
 * Representa um evento no sistema.
 * Guarda a informação sobre o dia, horário, nome e participantes.
 *
 * @author David Figueiredo 74167
 * @author Diana Barra 74154
 */
public class Event {

    private String name;
    private int day;
    private int start;
    private int end;
    private String[] participants;

    /**
     * Construtor da classe Event
     * Cria um novo evento com os dados fornecidos
     * @param name O nome do evento
     * @param day O dia da semana (1 a 5)
     * @param start A hora de início
     * @param end A hora de fim
     * @param participants Um array com os nomes dos participantes
     * pre: name != null && participants != null && participants.length > 0
     */
    public Event(String name, int day, int start, int end, String[] participants) {
        this.name = name;
        this.day = day;
        this.start = start;
        this.end = end;
        this.participants = participants;
    }

    /**
     * Obtém o nome do evento.
     * @return O nome do evento.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtém o dia da semana do evento.
     * @return O dia do evento (inteiro).
     */
    public int getDay() {
        return day;
    }

    /**
     * Obtém a hora de início do evento.
     * @return A hora de início.
     */
    public int getStart() {
        return start;
    }

    /**
     * Obtém a hora de fim do evento.
     * @return A hora de fim.
     */
    public int getEnd() {
        return end;
    }

    /**
     * Obtém o número de participantes do evento.
     * @return O número de participantes.
     */
    public int getNumParticipants() {
        return participants.length;
    }

    /**
     * Obtém o array com os nomes dos participantes.
     * @return Um array de Strings com os nomes.
     */
    public String[] getParticipants() {
        return participants;
    }

    /**
     * Obtém o nome do proponente do evento (o primeiro participante da lista).
     * @return O nome do proponente.
     */
    public String getProposer() {
        return participants[0];
    }

    /**
     * Compara este evento com outro para determinar a ordem relativa.
     * A ordem é definida por: Dia (asc), Hora Início (asc), Hora Fim (asc), Nome (asc).
     * @param other O evento com o qual vamos comparar.
     * @return Um valor negativo se este evento for menor, positivo se maior, ou 0 se iguais.
     * pre: other != null
     */
    public int compareTo(Event other) {
        if (this.day != other.day) {
            return this.day - other.day;
        }
        if (this.start != other.start) {
            return this.start - other.start;
        }
        if (this.end != other.end) {
            return this.end - other.end;
        }
        return this.name.compareTo(other.name);
    }
}