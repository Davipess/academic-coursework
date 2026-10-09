/**
 * Classe do Sistema que gere todos os utilizadores e a lista global de eventos.
 * Responsável pela coordenação entre utilizadores.
 *
 * @author David Figueiredo 74167
 * @author Diana Barra 74154
 */
public class Calendar {

    private static final int MAX_USERS = 100;
    private static final int MAX_EVENTS = 6000;

    private User[] users;
    private int numUsers;

    private Event[] events;
    private int numEvents;

    /**
     * Construtor da classe Calendar.
     * Inicializa os vetores de utilizadores e eventos com os tamanhos máximos definidos.
     * Os contadores de utilizadores e eventos são inicializados a zero.
     */
    public Calendar() {
        this.users = new User[MAX_USERS];
        this.numUsers = 0;
        this.events = new Event[MAX_EVENTS];
        this.numEvents = 0;
    }

    /**
     * Adiciona um novo utilizador ao sistema, se houver espaço disponível.
     * O utilizador é adicionado ao final do vetor de utilizadores.
     *
     * @param userName O nome do utilizador a criar.
     * pre: userName != null && !hasUsers(userName) && numUsers < MAX_USERS
     */
    public void addUser(String userName) {
        if (numUsers < MAX_USERS) {
            users[numUsers] = new User(userName);
            numUsers++;
        }
    }

    /**
     * Verifica se existe um utilizador com o nome dado no sistema.
     * Percorre o vetor de utilizadores à procura de uma correspondência de nome.
     *
     * @param name O nome do utilizador a procurar.
     * @return true se o utilizador existir, false caso contrário.
     * pre: name != null
     */
    public boolean hasUsers(String name) {
        boolean found = false;
        int i = 0;
        while (i < numUsers && !found) {
            if (users[i].getName().equals(name)) {
                found = true;
            }
            i++;
        }
        return found;
    }

    /**
     * Verifica se todos os utilizadores numa lista de nomes existem no sistema.
     * Útil para validar se todos os participantes de um evento estão registados.
     *
     * @param names Um array com os nomes dos utilizadores a verificar.
     * @return true se todos os utilizadores na lista existirem, false se pelo menos um não existir.
     * pre: names != null
     */
    public boolean allUsersExist(String[] names) {
        boolean allExist = true;
        int i = 0;
        while (i < names.length && allExist) {
            if (!hasUsers(names[i])) {
                allExist = false;
            }
            i++;
        }
        return allExist;
    }

    /**
     * Verifica se existe um evento global com o nome dado.
     * Utiliza um iterador para percorrer a lista global de eventos.
     *
     * @param eventName O nome do evento a procurar.
     * @return true se o evento existir, false caso contrário.
     * pre: eventName != null
     */
    public boolean isEqualEvent(String eventName) {
        boolean found = false;
        EventIterator it = new EventIterator(events, numEvents);
        while (it.hasNext() && !found) {
            Event e = it.next();
            if (e.getName().equals(eventName)) {
                found = true;
            }
        }
        return found;
    }

    /**
     * Cria um novo evento e adiciona-o ao sistema global e aos calendários pessoais de todos os participantes.
     * O evento é adicionado apenas se houver espaço na lista global de eventos.
     *
     * @param name O nome do evento.
     * @param day O dia da semana em que o evento ocorre (1 a 5).
     * @param start A hora de início do evento (8 a 19).
     * @param end A hora de fim do evento (9 a 20).
     * @param participants Um array com os nomes dos participantes (o primeiro é o proponente).
     * pre: name != null && participants != null && numEvents < MAX_EVENTS
     */
    public void addEvent(String name, int day, int start, int end, String[] participants) {
        if (numEvents < MAX_EVENTS) {
            Event newEvent = new Event(name, day, start, end, participants);

            events[numEvents] = newEvent;
            numEvents++;

            for (int i = 0; i < participants.length; i++) {
                User u = findUser(participants[i]);
                if (u != null) {
                    u.addEventCheck(newEvent);
                }
            }
        }
    }

    /**
     * Remove um evento do sistema global e dos calendários pessoais de todos os participantes.
     * Procura o evento pelo nome, remove-o de cada participante e depois da lista global.
     *
     * @param eventName O nome do evento a remover.
     * pre: eventName != null && isEqualEvent(eventName)
     */
    public void removeEvent(String eventName) {
        int idx = -1;
        int k = 0;
        while (k < numEvents && idx == -1) {
            if (events[k].getName().equals(eventName)) {
                idx = k;
            }
            k++;
        }

        if (idx != -1) {
            Event e = events[idx];
            removeEventFromParticipants(e);
            removeEventFromGlobalList(idx);
        }
    }

    /**
     * Verifica se o proponente (primeiro utilizador da lista) está disponível no horário indicado.
     * Esta verificação delega na função genérica isUserAvailable.
     *
     * @param proposerName O nome do proponente.
     * @param day O dia do evento.
     * @param start A hora de início.
     * @param end A hora de fim.
     * @return true se o proponente estiver livre, false caso contrário.
     * pre: proposerName != null
     */
    public boolean isProposerAvailable(String proposerName, int day, int start, int end) {
        return isUserAvailable(proposerName, day, start, end);
    }

    /**
     * Verifica se todos os convidados (excluindo o proponente) estão livres no horário indicado.
     * Os convidados são considerados a partir do índice 1 do array de participantes.
     *
     * @param participants Um array com os nomes de todos os participantes.
     * @param day O dia do evento.
     * @param start A hora de início.
     * @param end A hora de fim.
     * @return true se todos os convidados estiverem livres, false caso contrário.
     * pre: participants != null && participants.length > 0
     */
    public boolean areAllGuestsAvailable(String[] participants, int day, int start, int end) {
        boolean allAreFree = true;
        int i = 1;
        while (i < participants.length && allAreFree) {
            if (!isUserAvailable(participants[i], day, start, end)) {
                allAreFree = false;
            }
            i++;
        }
        return allAreFree;
    }

    /**
     * Verifica se um utilizador específico está livre num determinado intervalo de tempo.
     * Encontra o utilizador e pergunta-lhe se está ocupado (isBusy).
     *
     * @param userName O nome do utilizador.
     * @param day O dia do evento.
     * @param start A hora de início.
     * @param end A hora de fim.
     * @return true se o utilizador estiver livre (não ocupado), false se estiver ocupado ou não existir.
     * pre: userName != null
     */
    public boolean isUserAvailable(String userName, int day, int start, int end) {
        boolean isFree = false;
        User u = findUser(userName);
        if (u != null) {
            isFree = !u.isBusy(day, start, end);
        }
        return isFree;
    }

    /**
     * Verifica se um utilizador tem um determinado evento no seu calendário pessoal.
     *
     * @param userName O nome do utilizador.
     * @param eventName O nome do evento.
     * @return true se o utilizador tiver o evento, false caso contrário.
     * pre: userName != null && eventName != null
     */
    public boolean isUserOnEvent(String userName, String eventName) {
        boolean hasEvent = false;
        User u = findUser(userName);
        if (u != null) {
            hasEvent = u.hasEvent(eventName);
        }
        return hasEvent;
    }

    /**
     * Verifica se um utilizador tem algum evento registado no seu calendário.
     * Utiliza o iterador do utilizador para verificar se existe um próximo elemento.
     *
     * @param userName O nome do utilizador.
     * @return true se o utilizador tiver pelo menos um evento, false caso contrário.
     * pre: userName != null
     */
    public boolean isUserOccupied(String userName) {
        boolean hasEvents = false;
        User u = findUser(userName);
        if (u != null) {
            hasEvents = u.iterator().hasNext();
        }
        return hasEvents;
    }

    /**
     * Verifica se um utilizador é o proponente de um determinado evento.
     * Percorre a lista global de eventos para encontrar o evento e verificar o seu proponente.
     *
     * @param userName O nome do utilizador.
     * @param eventName O nome do evento.
     * @return true se o utilizador for o proponente do evento, false caso contrário.
     * pre: userName != null && eventName != null
     */
    public boolean isProposer(String userName, String eventName) {
        boolean isTheProposer = false;
        EventIterator it = new EventIterator(events, numEvents);

        while (it.hasNext() && !isTheProposer) {
            Event e = it.next();
            if (e.getName().equals(eventName)) {
                if (e.getProposer().equals(userName)) {
                    isTheProposer = true;
                }
            }
        }
        return isTheProposer;
    }

    /**
     * Verifica se existem eventos registados no sistema global.
     *
     * @return true se existirem eventos (numEvents > 0), false caso contrário.
     */
    public boolean AreThereEvents() {
        return numEvents > 0;
    }

    /**
     * Obtém um iterador para os eventos de um utilizador, ordenados cronologicamente.
     * Cria uma cópia dos eventos do utilizador, ordena-os e devolve um iterador para essa cópia.
     *
     * @param userName O nome do utilizador.
     * @return Um EventIterator com os eventos ordenados, ou null se o utilizador não existir.
     * pre: userName != null
     */
    public EventIterator getUserEventsIterator(String userName) {
        User u = findUser(userName);
        if (u != null) {
            Event[] userEvents = u.getEvents();
            int count = u.getEventCount();

            arraySorter(userEvents, count);

            return new EventIterator(userEvents, count);
        }
        return null;
    }

    /**
     * Obtém um iterador para os eventos com maior número de participantes.
     * Filtra os eventos com o máximo de participantes e ordena-os por Dia, Início, Fim e Nome.
     *
     * @return Um EventIterator com os eventos "top" ordenados.
     */
    public EventIterator getTopEventsIterator() {
        int maxPart = getMaxParticipants();
        int countTop = countEventsWithParticipants(maxPart);
        Event[] topEvents = filterEventsByParticipants(maxPart, countTop);

        arraySorter(topEvents, countTop);

        return new EventIterator(topEvents, countTop);
    }

    /**
     * Ordena um array de eventos.
     * A ordem é determinada pelo método compareTo da classe Event.
     *
     * @param events O array de eventos a ordenar.
     * @param size O número de elementos válidos no array.
     * pre: arr != null && size >= 0
     */
    private void arraySorter(Event[] events, int size) {
        for (int i = 0; i < size - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < size; j++) {
                if (events[j].compareTo(events[minIdx]) < 0) {
                    minIdx = j;
                }
            }
            Event temp = events[i];
            events[i] = events[minIdx];
            events[minIdx] = temp;
        }
    }

    /**
     * Encontra um utilizador no vetor de utilizadores pelo nome.
     *
     * @param name O nome do utilizador a procurar.
     * @return O objeto User correspondente, ou null se não for encontrado.
     * pre: name != null
     */
    private User findUser(String name) {
        User found = null;
        int i = 0;
        while (i < numUsers && found == null) {
            if (users[i].getName().equals(name)) {
                found = users[i];
            }
            i++;
        }
        return found;
    }

    /**
     * Remove um evento dos calendários pessoais de todos os seus participantes.
     *
     * @param e O evento a remover.
     * pre: e != null
     */
    private void removeEventFromParticipants(Event e) {
        String[] parts = e.getParticipants();
        for (int i = 0; i < parts.length; i++) {
            User u = findUser(parts[i]);
            if (u != null) {
                u.removeEvent(e.getName());
            }
        }
    }

    /**
     * Remove um evento da lista global de eventos dado o seu índice.
     * Move os elementos subsequentes para a esquerda para preencher o espaço.
     *
     * @param idx O índice do evento a remover no vetor global.
     * pre: idx >= 0 && idx < numEvents
     */
    private void removeEventFromGlobalList(int idx) {
        for (int i = idx; i < numEvents - 1; i++) {
            events[i] = events[i + 1];
        }
        events[numEvents - 1] = null;
        numEvents--;
    }

    /**
     * Determina o número máximo de participantes entre todos os eventos registados.
     *
     * @return O número máximo de participantes encontrado.
     */
    private int getMaxParticipants() {
        int maxPart = -1;
        for (int i = 0; i < numEvents; i++) {
            if (events[i].getNumParticipants() > maxPart) {
                maxPart = events[i].getNumParticipants();
            }
        }
        return maxPart;
    }

    /**
     * Conta quantos eventos têm um número específico de participantes.
     *
     * @param count O número de participantes a procurar.
     * @return O total de eventos com esse número de participantes.
     */
    private int countEventsWithParticipants(int count) {
        int total = 0;
        for (int i = 0; i < numEvents; i++) {
            if (events[i].getNumParticipants() == count) {
                total++;
            }
        }
        return total;
    }

    /**
     * Cria um novo array contendo apenas os eventos com um número específico de participantes.
     *
     * @param count O número de participantes para filtrar.
     * @param size O tamanho do array de resultado (previamente calculado).
     * @return Um array de eventos contendo apenas os eventos filtrados.
     */
    private Event[] filterEventsByParticipants(int count, int size) {
        Event[] result = new Event[size];
        int j = 0;
        for (int i = 0; i < numEvents; i++) {
            if (events[i].getNumParticipants() == count) {
                result[j] = events[i];
                j++;
            }
        }
        return result;
    }
}