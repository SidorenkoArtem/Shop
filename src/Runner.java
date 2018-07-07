import model.GeneratorCashier;
import model.GeneratorPurchaser;

public class Runner {

    public static void main(String[] args) {

        GeneratorPurchaser generatorPurchaser = new GeneratorPurchaser(10);
        GeneratorCashier generatorCashier = new GeneratorCashier(2);
        generatorCashier.runCashiers();
        generatorPurchaser.runPurchaser();
    }
}
