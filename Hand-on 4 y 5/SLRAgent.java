
package simpleLinearRegression;

import jade.core.Agent;
import jade.core.behaviours.Behaviour;

public class SLRAgent extends Agent {
    private static final long serialVersionUID = 1L;
    private int i = 0;
    private double sigma_x = 0.0, sigma_y = 0.0, sigma_xy = 0.0, sigma_x2 = 0.0;
    private static double beta_1 = 0.0;
    private static double beta_0 = 0.0;
    private final static double[] x = {23, 26, 30, 34, 43, 48, 52, 57, 58};
    private final static double[] y = {651, 762, 856, 1063, 1190, 1298, 1421, 1440, 1518};
    private int n = x.length;
    private double predictX = 0.0;

    protected void setup() {
        System.out.println("Agent " + getLocalName() + " started.");

        //Object[] args = getArguments();
        //if (args != null && args.length > 0) {
            addBehaviour(new Train());
            addBehaviour(new PresentData());
            predictX = 23;//Float.parseFloat((String) args[0]);
            System.out.println("x to predict = " + predictX);
       // }

    }

    private class Train extends Behaviour {
        private static final long serialVersionUID = 1L;

        @Override
        public void action() {
            sigma_x += x[i];
            sigma_x2 += x[i] * x[i];
            sigma_xy += x[i] * y[i];
            sigma_y += y[i];
            i++;
        }

        @Override
        public boolean done() {
            return i == n;
        }

        @Override
        public int onEnd() {
            beta_1 = (n * sigma_xy - sigma_x * sigma_y) / (n * sigma_x2 - sigma_x * sigma_x);
            beta_0 = (sigma_y - beta_1 * sigma_x) / n;
            return super.onEnd();
        }
    }

    private class PresentData extends Behaviour {
        private static final long serialVersionUID = 1L;

        @Override
        public void action() {
            if (i < n) {
                System.out.println("Training yet, I cannot predict!");
            } else {
                StringBuilder s = new StringBuilder();
                s.append(String.format("%.2f + %.2fx", beta_0, beta_1));
                System.out.print("\nSimple linear regression equation: " + s.toString());
            }
        }

        @Override
        public boolean done() {
            return i == n;
        }

        @Override
        public int onEnd() {
            double predicted_y = (beta_1 * predictX) + beta_0;
            System.out.print(String.format("\nx: %.2f | predicted y: %.2f\n", predictX, predicted_y));
            myAgent.doDelete();
            return super.onEnd();
        }
    }
}