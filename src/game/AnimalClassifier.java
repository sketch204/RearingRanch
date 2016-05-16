package game;

public class AnimalClassifier extends GameStage {

    /**
     * Initiates the playing process by filling the array with random
     * generated instance of animal classes and draws them in their proper positions.
     *
     * @param difficulty
     */
    public AnimalClassifier(int difficulty) {
        super(difficulty);
    }

    @Override
    protected void generateAnimals() {

    }

    @Override
    protected boolean inputLegal() {
        return false;
    }

    @Override
    protected void createButtons() {

    }
}