/**
 * Iterador para percorrer uma coleção de eventos.
 * Permite percorrer um vetor de eventos sem expor a estrutura interna.
 *
 * @author David Figueiredo 74167
 * @author Diana Barra 74154
 */
public class EventIterator {

    private Event[] events;
    private int size;
    private int nextIndex;

    /**
     * Construtor do iterador.
     * Inicializa o iterador para o início do vetor fornecido.
     *
     * @param events O vetor de eventos a percorrer.
     * @param size O número de elementos válidos no vetor.
     * pre: events != null && size >= 0
     */
    public EventIterator(Event[] events, int size) {
        this.events = events;
        this.size = size;
        this.nextIndex = 0;
    }

    /**
     * Verifica se ainda existem eventos para visitar na coleção.
     *
     * @return true se existir um próximo evento, false caso contrário.
     */
    public boolean hasNext() {
        return nextIndex < size;
    }

    /**
     * Devolve o próximo evento na coleção e avança o iterador.
     *
     * @return O próximo objeto Event.
     * pre: hasNext() == true
     */
    public Event next() {
        return events[nextIndex++];
    }
}