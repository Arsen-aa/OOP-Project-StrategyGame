package am.aua.StrategyGame.core;

/**
 * Custom exception used to indicate that a player's action is not allowed,
 * such as trying to buy a troop without enough gold.
 */
public class InvalidActionException extends Exception {

    /**
     * Creates a new InvalidActionException with the given error message.
     *
     * @param message the reason why the action is invalid
     */
    public InvalidActionException(String message) {
        super(message);
    }
}
