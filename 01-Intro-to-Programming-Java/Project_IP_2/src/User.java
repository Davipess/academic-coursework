/**
 * Representa um utilizador no sistema de calendário partilhado.
 * Gere os eventos pessoais do utilizador.
 *
 * @author David Figueiredo 74167
 * @author Diana Barra 74154
 */
public class User {

    private static final int MAX_USER_EVENTS = 100;

    private String name;
    private Event[] events;
    private int count;

    /**
     * Construtor da classe User.
     * Cria um novo utilizador com um vetor de eventos vazio.
     *
     * @param name O nome do utilizador.
     * pre: name != null
     */
    public User(String name) {
        this.name = name;
        this.events = new Event[MAX_USER_EVENTS];
        this.count = 0;
    }

    /**
     * Obtém o nome do utilizador.
     *
     * @return O nome do utilizador.
     */
    public String getName() {
        return name;
    }

    /**
     * Obtém o número atual de eventos no calendário do utilizador.
     *
     * @return O número de eventos registados.
     */
    public int getEventCount() {
        return count;
    }

    /**
     * Adiciona um evento ao calendário do utilizador.
     * Verifica se ainda existe capacidade no vetor de eventos pessoal.
     *
     * @param event O evento a adicionar.
     * pre: event != null && count < MAX_USER_EVENTS
     */
    public void addEventCheck(Event event) {
        if (count < MAX_USER_EVENTS) {
            events[count++] = event;
        }
    }

    /**
     * Remove um evento do calendário do utilizador dado o nome do evento.
     * Encontra o índice do evento e remove-o, reorganizando o vetor.
     *
     * @param eventName O nome do evento a remover.
     * pre: eventName != null && hasEvent(eventName)
     */
    public void removeEvent(String eventName) {
        int index = -1;
        int i = 0;

        while (i < count && index == -1) {
            if (events[i].getName().equals(eventName)) {
                index = i;
            }
            i++;
        }

        if (index != -1) {
            for (int j = index; j < count - 1; j++) {
                events[j] = events[j + 1];
            }
            events[count - 1] = null;
            count--;
        }
    }

    /**
     * Verifica se o utilizador tem um evento com o nome dado.
     * Percorre o vetor de eventos do utilizador.
     *
     * @param eventName O nome do evento a procurar.
     * @return true se o evento existir, false caso contrário.
     * pre: eventName != null
     */
    public boolean hasEvent(String eventName) {
        boolean found = false;
        int i = 0;
        while (i < count && !found) {
            if (events[i].getName().equals(eventName)) {
                found = true;
            }
            i++;
        }
        return found;
    }

    /**
     * Verifica se o utilizador está ocupado num determinado intervalo de tempo.
     * Verifica se existe sobreposição entre o horário proposto e os eventos existentes.
     *
     * @param day O dia da semana.
     * @param start A hora de início.
     * @param end A hora de fim.
     * @return true se houver sobreposição (ocupado), false se estiver livre.
     */
    public boolean isBusy(int day, int start, int end) {
        boolean busy = false;
        int i = 0;
        while (i < count && !busy) {
            Event e = events[i];
            if (e.getDay() == day) {
                if (start < e.getEnd() && end > e.getStart()) {
                    busy = true;
                }
            }
            i++;
        }
        return busy;
    }

    /**
     * Cria e retorna um iterador para percorrer os eventos do utilizador.
     * Permite acesso sequencial aos eventos sem expor o vetor interno.
     *
     * @return Um EventIterator inicializado.
     */
    public EventIterator iterator() {
        return new EventIterator(events, count);
    }

    /**
     * Cria e retorna uma cópia do vetor de eventos do utilizador.
     * Útil para operações de ordenação que não devem alterar a ordem original.
     *
     * @return Um array contendo cópias das referências dos eventos.
     */
    public Event[] getEvents() {
        Event[] copy = new Event[count];
        for (int i = 0; i < count; i++) {
            copy[i] = events[i];
        }
        return copy;
    }
}