import java.util.Scanner;

/**
 * @Autor David Figueiredo
 */

// Constantes
final String INVALID_COMMAND_MESSAGE = "Invalid command";
final char BOOST_TILE = '+';
final char DRAG_TILE = '-';
final char OIL_TILE = '!';
final char START_TILE = 'S';
final char PLAYER_CHAR = 'P';
final String CMD_ACCEL = "accel";
final String CMD_SHOW = "show";
final String CMD_STATUS = "status";
final String CMD_QUIT = "quit";
final int DEFAULT_SPEED = 1;
final int MIN_SPEED = 0;
final int YELLOW_FLAG_MAX_SPEED = 1;
final int AI_LOOKAHEAD = 3;
final int FIRST_OPPONENT_INDEX = 1;
final int PLAYER_INDEX = 0;
final int POLE_POSITION_OFFSET = 1;
final int NUM_HUMAN_PLAYERS = 1;
final int INITIAL_LAPS = 0;
final int INVALID_INDEX = -1;

// Constantes para verificar se passaram a meta
final int FLAG_NOT_PASSED = 0;
final int FLAG_HAS_PASSED = 1;

// Variáveis globais
int[] carPositions;
int[] carSpeeds;
int[] carLaps;
int[] carPassedStartFlag;
char[] carIDs;
char[] players = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i'};
char[] trackLayout;
int trackLength;
int lapsToWin;
int maxSpeed;
int numOpponents;
int startPosition;
int numCars;
boolean raceEnded;
char winner;

/**
 * Corrige uma posição para esta estar dentro do intervalo do array, garantindo wrap-around circular.
 *
 * @param pos posição por corrigir (pode ser negativa ou >= trackLength)
 * @return posição corrigida para estar dentro do intervalo do array
 */
int calculateCircularPosition(int pos) {
    while (pos >= trackLength) {
        pos = pos - trackLength;
    }
    while (pos < 0) {
        pos = pos + trackLength;
    }
    return pos;
}

/**
 * Procura a posição da linha de partida/meta na pista.
 * Atualiza a variável global startPosition com o índice do tile 'S'.
 */
void findStartPosition() {
    int i = 0;
    boolean found = false;
    while (i < trackLength && !found) {
        if (trackLayout[i] == START_TILE) {
            startPosition = i;
            found = true;
        }
        i++;
    }
}

/**
 * Converte a string da pista para array de caracteres e atualiza o comprimento.
 *
 * @param track string representando a pista com tiles ('.', 'S', '+', '-', '!')
 */
void setTrackLayout(String track) {
    trackLayout = track.toCharArray();
    trackLength = track.length();
}

/**
 * Calcula a aceleração que um adversário IA deve aplicar.
 * A IA analisa as 3 próximas posições à frente:
 * Se encontrar boost (+) e velocidade < max: acelera (+1)
 * Senão, se encontrar oil (!) e velocidade > 0: desacelera (-1)
 * Caso contrário: mantém velocidade (0)
 *
 * @param carIndex índice do carro adversário
 * @return aceleração a aplicar (-1, 0 ou +1)
 */
int getAIAcceleration(int carIndex) {
    int currentSpeed = carSpeeds[carIndex];
    int currentPos = carPositions[carIndex];
    boolean boostFound = false;
    boolean oilFound = false;
    int acceleration = MIN_SPEED;

    for (int i = DEFAULT_SPEED; i <= AI_LOOKAHEAD; i++) {
        int posToCheck = calculateCircularPosition(currentPos + i);
        char tile = trackLayout[posToCheck];
        if (tile == BOOST_TILE) {
            boostFound = true;
        }
        if (tile == OIL_TILE) {
            oilFound = true;
        }
    }

    if (boostFound && currentSpeed < maxSpeed) {
        acceleration = DEFAULT_SPEED;
    } else if (oilFound && currentSpeed > MIN_SPEED) {
        acceleration = -DEFAULT_SPEED;
    }

    return acceleration;
}

/**
 * Verifica se uma posição específica está ocupada por outro carro.
 *
 * @param position posição a verificar
 * @param currentCarIndex índice do carro que está a verificar (para excluir a si próprio)
 * @return true se a posição está ocupada por outro carro, false caso contrário
 */
boolean isPositionOccupied(int position, int currentCarIndex) {
    boolean occupied = false;
    int i = 0;
    while (i < numCars && !occupied) {
        if (i != currentCarIndex && carPositions[i] == position) {
            occupied = true;
        }
        i++;
    }
    return occupied;
}

/**
 * Verifica se o carro cruzou a linha de partida durante o movimento.
 * Percorre todas as posições no trajeto desde fromPos até fromPos+distance.
 *
 * @param fromPos  posição inicial do movimento
 * @param distance distância percorrida
 * @return true se cruzou a posição de partida, false caso contrário
 */
boolean crossedStartLine(int fromPos, int distance) {
    boolean crossed = false;
    if (distance > 0) {
        int step = 1;
        while (step <= distance && !crossed) {
            int pos = calculateCircularPosition(fromPos + step);
            if (pos == startPosition) {
                crossed = true;
            }
            step++;
        }
    }
    return crossed;
}

/**
 * Procura a última posição livre no trajeto antes do destino pretendido.
 * Percorre o trajeto até encontrar uma posição livre.
 *
 * @param oldPosition  posição inicial do carro
 * @param moveDistance distância pretendida
 * @param carIndex índice do carro a mover
 * @return posição livre mais próxima do destino, ou oldPosition se nenhuma livre
 */
int findFreePosition(int oldPosition, int moveDistance, int carIndex) {
    int finalPosition = oldPosition;
    int step = moveDistance - 1;
    boolean found = false;

    while (step >= 0 && !found) {
        int testPos = calculateCircularPosition(oldPosition + step);
        if (!isPositionOccupied(testPos, carIndex)) {
            finalPosition = testPos;
            found = true;
        }
        step--;
    }

    return finalPosition;
}

/**
 * Aplica o efeito do tile onde o carro parou.
 * Boost (+): aumenta velocidade em 1 (máximo: maxSpeed)
 * Drag (-): diminui velocidade em 1 (mínimo: 0)
 * Oil (!): velocidade passa a 0
 *
 * @param carIndex índice do carro que parou
 */
void applyTileEffect(int carIndex) {
    char currentTile = trackLayout[carPositions[carIndex]];
    if (currentTile == BOOST_TILE) {
        carSpeeds[carIndex] = Math.min(carSpeeds[carIndex] + DEFAULT_SPEED, maxSpeed);
    } else if (currentTile == DRAG_TILE) {
        carSpeeds[carIndex] = Math.max(carSpeeds[carIndex] - DEFAULT_SPEED, MIN_SPEED);
    } else if (currentTile == OIL_TILE) {
        carSpeeds[carIndex] = MIN_SPEED;
    }
}

/**
 * Verifica se o carro completou uma volta e atualiza o contador.
 *
 * @param carIndex     índice do carro
 * @param oldPosition  posição antes do movimento
 * @param moveDistance distância percorrida no movimento
 */
void checkLaps(int carIndex, int oldPosition, int moveDistance) {
    if (crossedStartLine(oldPosition, moveDistance)) {
        if (carPassedStartFlag[carIndex] == FLAG_HAS_PASSED) {
            carLaps[carIndex]++;
            if (carLaps[carIndex] >= lapsToWin) {
                raceEnded = true;
                winner = carIDs[carIndex];
            }
        } else {
            carPassedStartFlag[carIndex] = FLAG_HAS_PASSED;
        }
    }
}

/**
 * Move um carro individual na pista.
 * Aplica o limite de bandeira amarela se ativa, verifica colisões,
 * calcula distância real percorrida, verifica voltas e aplica efeitos de tiles.
 *
 * @param carIndex     índice do carro a mover
 * @param moveDistance distância pretendida (velocidade do carro)
 * @param yellowFlag   indica se a bandeira amarela está ativa (limita movimento a 1)
 * @return true se houve colisão, false caso contrário
 */
boolean moveCar(int carIndex, int moveDistance, boolean yellowFlag) {
    int oldPosition = carPositions[carIndex];

    if (yellowFlag && moveDistance > YELLOW_FLAG_MAX_SPEED) {
        moveDistance = YELLOW_FLAG_MAX_SPEED;
    }

    int targetPos = calculateCircularPosition(oldPosition + moveDistance);
    boolean hadCollision = false;
    int actualDistance = moveDistance;

    if (isPositionOccupied(targetPos, carIndex)) {
        hadCollision = true;
        targetPos = findFreePosition(oldPosition, moveDistance, carIndex);

        actualDistance = targetPos - oldPosition;
        if (actualDistance < 0) {
            actualDistance = actualDistance + trackLength;
        }
    }

    carPositions[carIndex] = targetPos;
    checkLaps(carIndex, oldPosition, actualDistance);
    applyTileEffect(carIndex);

    return hadCollision;
}

/**
 * Aplica uma aceleração ao carro e limita a velocidade aos valores válidos.
 *
 * @param carIndex índice do carro
 * @param accel aceleração a aplicar (-1, 0 ou +1)
 */
void applyAcceleration(int carIndex, int accel) {
    carSpeeds[carIndex] = Math.max(MIN_SPEED,
            Math.min(carSpeeds[carIndex] + accel, maxSpeed));
}

/**
 * Aplica as acelerações a todos os carros.
 * O jogador P usa a aceleração fornecida, os adversários usam a IA.
 *
 * @param playerAccel aceleração escolhida pelo jogador (-1, 0 ou +1)
 */
void applyAllAccelerations(int playerAccel) {
    applyAcceleration(PLAYER_INDEX, playerAccel);
    for (int i = FIRST_OPPONENT_INDEX; i < numCars; i++) {
        int aiAccel = getAIAcceleration(i);
        applyAcceleration(i, aiAccel);
    }
}

/**
 * Move todos os carros pela ordem (P, a, b, c, ...).
 * Ativa bandeira amarela após primeira colisão, limitando carros seguintes.
 * Para imediatamente se a corrida terminar.
 *
 * @param speedsBeforeMove array com as velocidades de cada carro antes de se moverem
 */
void moveAllCars(int[] speedsBeforeMove) {
    boolean yellowFlag = false;
    int i = PLAYER_INDEX;
    while (i < numCars && !raceEnded) {
        boolean hadCollision = moveCar(i, speedsBeforeMove[i], yellowFlag);
        if (hadCollision) {
            yellowFlag = true;
        }
        i++;
    }
}

/**
 * Processa o comando 'accel' e executa uma ronda completa.
 * Lê a aceleração do jogador, aplica acelerações, guarda velocidades
 * e move todos os carros.
 *
 * @param race scanner para ler input
 */
void processAccel(Scanner race) {
    int playerAccel = race.nextInt();

    if (raceEnded) {
        System.out.println("Race ended: " + winner + " won the race!");
    } else {
        applyAllAccelerations(playerAccel);

        int[] speedsBeforeMove = new int[numCars];
        for (int i = PLAYER_INDEX; i < numCars; i++) {
            speedsBeforeMove[i] = carSpeeds[i];
        }

        moveAllCars(speedsBeforeMove);

        if (raceEnded) {
            System.out.println("Player " + winner + " won the race!");
        } else {
            System.out.println("Player P: cell " + carPositions[PLAYER_INDEX] +
                    ", laps " + carLaps[PLAYER_INDEX] + "!");
        }
    }
}

/**
 * Processa o comando 'show' e apresenta o estado atual da pista.
 * Sobrepõe os IDs dos carros nas suas posições.
 * Indica se a corrida está 'ongoing' ou 'ended'.
 */
void processShow() {
    char[] displayTrack = trackLayout.clone();

    for (int i = numCars - 1; i >= 0; i--) {
        displayTrack[carPositions[i]] = carIDs[i];
    }

    System.out.print(new String(displayTrack));
    if (raceEnded) {
        System.out.println(" (ended)");
    } else {
        System.out.println(" (ongoing)");
    }
}

/**
 * Procura o índice de um carro pelo seu identificador(carID).
 *
 * @param carID identificador do carro ('P', 'a', 'b', ...)
 * @return índice do carro no array, ou INVALID_INDEX se não existir
 */
int getCarIndex(char carID) {
    int index = INVALID_INDEX;
    int i = 0;
    boolean found = false;

    while (i < numCars && !found) {
        if (carIDs[i] == carID) {
            index = i;
            found = true;
        }
        i++;
    }

    return index;
}

/**
 * Processa o comando 'status' e mostra informação sobre um jogador.
 * Se o jogador ganhou, mostra mensagem de vitória.
 * Se o jogador não existir, mostra mensagem de erro.
 * Caso contrário, mostra posição e voltas.
 *
 * @param race scanner para ler input
 */
void processStatus(Scanner race) {
    char playerId = race.next().charAt(0);
    int carIndex = getCarIndex(playerId);

    if (carIndex == INVALID_INDEX) {
        System.out.println("Player " + playerId + " does not exist!");
    } else if (raceEnded && winner == playerId) {
        System.out.println("Race ended: " + winner + " won the race!");
    } else {
        System.out.println("Player " + playerId + ": cell " + carPositions[carIndex] +
                ", laps " + carLaps[carIndex] + "!");
    }
}

/**
 * Processa comandos inválidos (que não sejam accel, show, status ou quit).
 * Imprime mensagem de erro.
 *
 * @param race scanner para ler input
 */
void processInvalidCommand(Scanner race) {
    race.nextLine();
    System.out.println(INVALID_COMMAND_MESSAGE);
}

/**
 * Processa o comando 'quit' e termina o programa.
 * Indica se a corrida terminou ou ainda está a decorrer.
 */
void processQuit() {
    if (raceEnded) {
        System.out.println("Race ended: " + winner + " won the race, with:" + carLaps[getCarIndex(winner)] + "laps!");
    } else {
        System.out.println("The race is not over yet!");
    }
}

/**
 * Loop principal que lê e processa comandos até receber 'quit'.
 *
 * @param race scanner para ler input
 */
void executeCommands(Scanner race) {
    boolean running = true;
    while (running) {
        String command = race.next();
        switch (command) {
            case CMD_ACCEL -> processAccel(race);
            case CMD_SHOW -> processShow();
            case CMD_STATUS -> processStatus(race);
            case CMD_QUIT -> {
                processQuit();
                running = false;
            }
            default -> processInvalidCommand(race);
        }
    }
}

/**
 * Inicializa os arrays para armazenar dados dos carros.
 */
void initializeCarArrays() {
    carPositions = new int[numCars];
    carSpeeds = new int[numCars];
    carLaps = new int[numCars];
    carPassedStartFlag = new int[numCars];
    carIDs = new char[numCars];
}

/**
 * Inicializa o jogador humano (P) na pole position.
 * Define posição inicial, velocidade, voltas e flag de passagem.
 */
void initializePlayer() {
    carIDs[PLAYER_INDEX] = PLAYER_CHAR;
    carPositions[PLAYER_INDEX] = calculateCircularPosition(startPosition - POLE_POSITION_OFFSET);
    carSpeeds[PLAYER_INDEX] = DEFAULT_SPEED;
    carLaps[PLAYER_INDEX] = INITIAL_LAPS;
    carPassedStartFlag[PLAYER_INDEX] = FLAG_NOT_PASSED;
}

/**
 * Inicializa os adversários IA atrás do jogador P.
 */
void initializeOpponents() {
    for (int i = FIRST_OPPONENT_INDEX; i < numCars; i++) {
        carIDs[i] = players[i - NUM_HUMAN_PLAYERS];
        carPositions[i] = calculateCircularPosition(startPosition - i - POLE_POSITION_OFFSET);
        carSpeeds[i] = DEFAULT_SPEED;
        carLaps[i] = INITIAL_LAPS;
        carPassedStartFlag[i] = FLAG_NOT_PASSED;
    }
}

/**
 * Lê os parâmetros iniciais e inicializa o estado da corrida.
 * Lê pista, número de voltas, velocidade máxima e número de adversários.
 * Inicializa posições iniciais de todos os carros.
 *
 * @param race scanner para ler input
 */
void initState(Scanner race) {
    String track = race.nextLine();
    lapsToWin = race.nextInt();
    maxSpeed = race.nextInt();
    numOpponents = race.nextInt();

    setTrackLayout(track);
    findStartPosition();
    raceEnded = false;
    winner = ' ';
    numCars = NUM_HUMAN_PLAYERS + numOpponents;

    initializeCarArrays();
    initializePlayer();
    initializeOpponents();
}

/**
 * Método principal
 * Inicializa o scanner, estado da corrida e loop de comandos.
 */
void main() {
    Scanner race = new Scanner(System.in);
    initState(race);
    executeCommands(race);
    race.close();
}