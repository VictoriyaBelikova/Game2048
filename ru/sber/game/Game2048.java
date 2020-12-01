package ru.sber.game;

import java.util.*;

import static java.util.Arrays.asList;

public class Game2048 implements Game{

    public static final int GAME_SIZE = 4;

    private GameHelper helper = new GameHelper();

    private final Board<Key, Integer> board = new SquareBoard<>(GAME_SIZE);

    public Game2048() {
    }

    @Override
    public void init() {
        clearBoard();
        addItem();
        addItem();
    }

    private void clearBoard() {
        for (int i = 0; i < GAME_SIZE; i++) {
            for (int j = 0; j < GAME_SIZE; j++) {
                board.addItem(new Key(i, j), null);
            }
        }
    }

    @Override
    public boolean canMove() {

        if (board.availableSpace().isEmpty()) {
            return true;
        } else {
            for (Key key : board.getBoard().keySet()) {
                if (haveEqualNeighbour(key)) {
                    return true;
                }
            }
            return false;
        }
    }

    private boolean haveEqualNeighbour(Key key) {
        List<Integer> neighboursValues = new ArrayList<>();

        neighboursValues = getNeighboursValues(key);

        if (neighboursValues == null) {
            return false;
        } else {
            return neighboursValues.contains(board.getValue(key));
        }
    }

    private List<Integer> getNeighboursValues(Key key) {
        List<Key> neighbours = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        neighbours = getNeighbours(key.getI(), key.getJ());
        if (neighbours.contains(null)) {
            return null;
        } else {
            for (Key keyNeighbour : neighbours) {
                values.add(board.getValue(keyNeighbour));
            }
            return values;
        }
    }

    private List<Key> getNeighbours(int i, int j) {
        List<Key> neighbours = new ArrayList<>();

        neighbours.add(getNeighbour(i - 1, j)); // top
        neighbours.add(getNeighbour(i, j - 1)); // left
        neighbours.add(getNeighbour(i - 1, j + 1)); // right
        neighbours.add(getNeighbour(i + 1, j)); // bottom

        return neighbours;
    }

    private Key getNeighbour(int i, int j) {
        Integer y = (i < 0) ? null : i;
        y = i > GAME_SIZE ? null : i;
        Integer x = (j < 0) ? null : j;
        x = j > GAME_SIZE ? null : j;
        return ((x != null) && (y != null)) ? new Key(x, y) : null;
    }

    @Override
    public void move(Direction direction) throws GameOverException {

        boolean wasMoved = false;

        switch (direction.toString()) {
            case "TOP" :
                wasMoved = moveAndMergeUp();
                break;
            case "DOWN" :
                wasMoved = moveAndMergeDown();
                break;
            case "LEFT" :
                wasMoved = moveAndMergeLeft();
                break;
            case "RIGHT" :
                wasMoved = moveAndMergeRight();
                break;
        }
        if (board.availableSpace().isEmpty()) {
            throw new GameOverException();
        }
        if (wasMoved) {
            addItem();
        }
        if (!canMove()) {
            throw new GameOverException();
        }
    }

    private boolean moveAndMergeUp() {
        boolean res = false;

        for(int j = 0; j < GAME_SIZE; j++) {
            List<Key> col = board.getColumn(j);
            res |= helpMeth(col);
        }
        return res;
    }

    private boolean moveAndMergeDown() {
        boolean res = false;

        for(int j = 0; j < GAME_SIZE; j++) {
            List<Key> col = board.getColumn(j);
            Collections.reverse(col);
            res |= helpMeth(col);
        }
        return res;
    }

    private boolean moveAndMergeLeft() {
        boolean res = false;

        for(int i = 0; i < GAME_SIZE; i++) {
            List<Key> col = board.getRow(i);
            res |= helpMeth(col);
        }
        return res;
    }

    private boolean moveAndMergeRight() {
        boolean res = false;

        for(int i = 0; i < GAME_SIZE; i++) {
            List<Key> col = board.getRow(i);
            Collections.reverse(col);
            res |= helpMeth(col);
        }
        return res;
    }

    private boolean helpMeth(List<Key> oldKeys) {
        List<Integer> oldValues = board.getValues(oldKeys);
        List<Integer> updValues = helper.moveAndMergeEqual(oldValues);

        if (!oldValues.equals(updValues)) {
            Iterator<Integer> iterator = updValues.iterator();
            for (Key key : oldKeys) {
                board.addItem(key, iterator.next());
            }
            return true;
        }
        return false;
    }

    @Override
    public void addItem() {
        Random rand = new Random();

        List<Key> keysEmpty = board.availableSpace();

        int randomIndex = rand.nextInt(keysEmpty.size());
        Key key = keysEmpty.get(randomIndex);

        Integer val = 2;
        int randomValue = (int)Math.random() * 9;
        if (randomValue == 4) {
            val = 4;
        }

        board.addItem(key, val);
    }

    @Override
    public Board getGameBoard() {
        return this.board;
    }

    @Override
    public boolean hasWin() {
        return board.getBoard().containsValue(2048);
    }
}
