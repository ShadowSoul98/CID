//javac -cp lib\jade.jar -d classes D:\ibian\proyects\hand-on\Nueva-carpeta\SLRAgent.java
//java -cp lib\jade.jar;classes jade.Boot -gui -agents SLR:MultiLinearRegression.MLRAgent
package multiLinearRegression;

import jade.core.Agent;
import jade.core.Regrersiones.Cargardatos.getXData;
import jade.core.behaviours.Behaviour;

public class SLRAgent extends Agent {
    private static final long serialVersionUID = 1L;
    private int i = 0;
    private double sigma_x = 0.0, sigma_y = 0.0, sigma_xy = 0.0, sigma_x2 = 0.0;
    private static double beta_1 = 0.0;
    private static double beta_0 = 0.0;
    private final static double[][] matriz = getXData();


    private int n = 50;
    private double predictX = 0.0;

    protected void setup() {
        System.out.println("Agente " + getLocalName() + " en linea.");

        //Object[] args = getArguments();
        //if (args != null && args.length > 0) {
            addBehaviour(new Train());
            addBehaviour(new PresentData());
            predictX = 116983.8;//Float.parseFloat((String) args[0]);
            System.out.println("Prediccion para el advertising = " + predictX);
       // }

    }

    private class Train extends Behaviour {
        private static final long serialVersionUID = 1L;

        @Override
        public void action() {
            sigma_x += matriz[i][2];
            sigma_x2 += matriz[i][2] * matriz[i][2];
            sigma_xy += matriz[i][2] * matriz[i][4];
            sigma_y += matriz[i][4];
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
                s.append(String.format(" Beta1: %.2f | Beta2 %.2fx", beta_0, beta_1));
                System.out.print("\nEcuacion: RLS" + s.toString());
            }
        }

        @Override
        public boolean done() {
            return i == n;
        }

        @Override
        public int onEnd() {
            double predicted_y = (beta_1 * predictX) + beta_0;
            System.out.print(String.format("\nEn el advertising %.2f predice un valor de %.2f en sales\n", predictX, predicted_y));
            myAgent.doDelete();
            return super.onEnd();
        }
    }
}